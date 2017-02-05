package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.utils.PasswordUtils;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/16 0016　<br/>
 * <p>
 * 用户service接口实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class UserServiceImpl implements IUserService {

    private Long overTime = 1000L * 60 * 60 * 24 * 7;//过期时间
    private Integer[] worths = {1, 1, 2, 5, 10, 15, 20, 45};//价值
    private Integer[] prices = {10, 10, 20, 50, 100, 150, 200, 450};//满减

    @Autowired
    UserMapper userMapper;
    @Autowired
    CommodityHistoryMapper comHistoryMapper;
    @Autowired
    UserCodesHistoryMapper userCodeHistMapper;
    @Autowired
    CommoditysMapper comMapper;
    @Autowired
    LuckCodesMapper codesMapper;
    @Autowired
    CommodityExchangeMapper exchangeMapper;
    @Autowired
    ExchangeWayMapper wayMapper;
    @Autowired
    CommodityTemplateMapper tempMapper;
    @Autowired
    ShareMapper shareMapper;
    @Autowired
    LuckCodeTemplateMapper luckTemplateMapper;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    OrdersCommoditysMapper ordersCommoditysMapper;
    @Autowired
    RedPacketsMapper redPacketsMapper;
    @Autowired
    CommodityMapper cMapper;
    @Autowired
    LotteryInfoMapper lotteryInfoMapper;

    @Override
    public boolean register(String phone, String password) {
        if (null != userMapper.selectByPhoneNumber(phone))
            return false;
        User user = new User();
        user.setPhoneNumber(phone);
        user.setPassword(password);
        user.setRealName("未认证");
        int length = phone.length();
        if (length == 11) {
            user.setNickname(phone.substring(0, 3) + "*****" + phone.substring(length - 3, length));
        } else {
            user.setNickname("用户" + userMapper.countUsers());
        }
        user.setUserIntegral(0);
        user.setHeaderUrl(Settings.USER_PORTRAIT_URL_DEFAULT);
        user.setGoldNumber(0);
        user.setRegistDate(new Date().getTime());
        user.setCurrentState(Settings.USER_STATE_NORMAL);
        PasswordUtils.encryptPassword(user);//加密用户密码
        boolean rs = userMapper.insert(user) > 0;
        giveRegisterRedPackets(user.getAccountId());
        return rs;
    }

    @Override
    public User queryUserByPhoneNum(String phone) {
        return userMapper.selectByPhoneNumber(phone);
    }

    @Override
    public Map<String, Object> getUserInfo(Long accountId) {
        User user = userMapper.selectById(accountId);
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getAccountId());
        map.put("headUrl", user.getHeaderUrl());
        map.put("accountId", user.getAccountId());
        map.put("nickname", user.getNickname());
        map.put("integral", user.getUserIntegral());
        map.put("gold", user.getGoldNumber());
        return map;
    }

    /**
     * 查询中奖历史
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectHistoryLottery(Long accountId, Long lastCommId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<LotteryInfo> infos = lotteryInfoMapper.selectByUserAccountId(accountId, lastCommId, Settings.PAGE_LOAD_SIZE_10);
        for (LotteryInfo lotteryInfo : infos) {
            //查询商品模板
            Commodity key = cMapper.selectByKey(lotteryInfo.getCommodityId());
            CommodityTemplate template = tempMapper.selectById(key.getTempId());
            //查询是否已晒单
            Share share = shareMapper.selectByCommId(lotteryInfo.getCommodityId());
            Map<String, Object> map = new HashMap<>();
            if (share != null) {
                map.put("shareState", 1);//是否晒单（0、未晒单；1、已晒单）
            } else {
                map.put("shareState", 0);
            }
            map.put("id", lotteryInfo.getCommodityId());//添加商品id
            map.put("commodityName", template.getName());//添加商品名
            map.put("roundTime", key.getRoundTime());//添加期数
            map.put("endTime", key.getEndTime());//揭晓时间
            map.put("buyNumber", key.getBuyNumber());//购买人次
            map.put("luckCode", lotteryInfo.getLotteryId());//添加幸运码
            map.put("imgUrl", template.getCoverImgUrl());//中奖商品图片地址
            map.put("exchangeId", selectExchange(lotteryInfo.getCommodityId()));//添加兑换方式
            map.put("withdrawalsMoney", template.getWithdrawalsMoney());//折换现金金额
            map.put("exchangeMoney", template.getExchangeMoney());//折换闪币
            map.put("state", key.getExchangeState());//添加兑换状态
            map.put("exchangeWay", key.getExchangeWay());//添加已选择兑奖方式
            mapList.add(map);
        }
        return mapList;
    }

    public Map<String, Object> selectExchange(Long commodityId) {
        if (null == commodityId)
            return null;
        List<CommodityExchange> exchanges = exchangeMapper.selectExcInfoByCommodityId(commodityId);
        Map<String, Object> map = new HashMap<>();
        for (CommodityExchange ex : exchanges)
            map.put(ex.getExchangeWayId() + "", ex.getEwName());
        return map;
    }


    /**
     * 查看购买记录
     *
     * @param accountId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectHistoryPay(Long accountId, Integer item) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (item == 1) {
            mapList = selectToNew(accountId);
        } else if (item == 2) {
            mapList = selectToHistory(accountId);
        } else {
            mapList.addAll(selectToHistory(accountId));
            mapList.addAll(selectToNew(accountId));
        }
        return mapList;
    }

    /**
     * 添加推广Id
     *
     * @param accountId 当前用户Id
     * @param promId    推广Id
     * @return
     */
    @Override
    public boolean addPromoterId(Long accountId, Long promId) {
        User user = new User();
        user.setAccountId(accountId);
//        user.setPromoterId(promId);//
        // TODO: 2017/1/12 查询当前用户之前有没有填写推广id,无则保存当前推广id
        return false;
    }

    //添加历史购买商品
    public List<Map<String, Object>> selectToHistory(Long accountId) {
        //添加已开奖的商品
//        List<Map<String, Object>> list = new ArrayList<>();
//        UserCodesHistory userHistory = new UserCodesHistory();
//        userHistory.setUserAccountId(accountId);
//        List<UserCodesHistory> s1 = userCodeHistMapper.selectByUserAccountId(accountId);
//        for (UserCodesHistory u : s1) {
//            Map<String, Object> map = new HashMap<>();
//            CommodityHistory history = comHistoryMapper.selectIdByCommId(u.getCommodityId());
//            User user1 = userMapper.selectById(history.getLuckUserAccountId());
//            List<String> integers = luckUserList(accountId, history.getCommodityId());
//            map.put("id", history.getCommodityId());//商品ID
//            map.put("buyTotalNumber", history.getBuyTotalNumber());//添加当期总需人次
//            map.put("buyCurrentNumber", history.getBuyTotalNumber());//添加当前购买人次
//            map.put("commState", 1);//商品状态
//            map.put("roundTime", history.getRoundTime());//添加期数
//            map.put("coverImgUrl", Settings.SERVER_URL_PATH + history.getCoverImgUrl());//添加商品封面图URL
//            map.put("commName", history.getCommodityName());//添加商品名
//            map.put("userAccountId", accountId);//添加用户ID
//            map.put("userCodesList", integers);//添加用户参与购买的幸运码集合
//            map.put("userBuyNumber", history.getBuyNumber());//添加本用户购买次数
//            if (accountId.longValue() == history.getLuckUserAccountId().longValue()) {
//                map.put("isWinner", 1);//是否中奖者是本用户（是）
//            } else {
//                map.put("isWinner", 0);//是否中奖者是本用户（不是）
//            }
//            map.put("userNickname", user1.getNickname());//中奖者昵称
//            map.put("endTime", history.getEndTime());//添加揭晓时间
//            list.add(map);
//        }
//        return list;
        return null;
    }

    //添加正在进行的商品
    public List<Map<String, Object>> selectToNew(Long accountId) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Long> commIdList = codesMapper.selectDistinctGroupByCommId(accountId);
        for (Long commId : commIdList) {
            Map<String, Object> map = new HashMap<>();
            Commoditys com = comMapper.selectByKey(commId);

            List<String> integers = luckUserList(accountId, com.getId());
            //CommodityHistory history = comHistoryMapper.selectIdByCommId(commId);
            // User user = userMapper.selectById(history.getLuckUserAccountId());
            map.put("id", com.getId());//添加商品ID
            map.put("buyCurrentNumber", com.getBuyTotalNumber() - com.getBuyCurrentNumber());//添加当前购买人次
            map.put("buyTotalNumber", com.getBuyTotalNumber());//添加总购买人次
            map.put("commState", com.getStateId());//商品状态
            map.put("roundTime", com.getRoundTime());//添加期数
            map.put("coverImgUrl", com.getCoverImgUrl());//添加封面图URL
            map.put("commName", com.getName());//添加商品名
            map.put("userAccountId", accountId);//添加用户ID
            map.put("userCodesList", integers);//添加用户参与购买的幸运码集合
            map.put("userBuyNumber", integers.size());//添加用户本商品购买人次；
            map.put("isWinner", 0);
            //map.put("userNickname", user.getNickname());//中奖者昵称
            //map.put("endTime", com.getEndTime());//添加揭晓时间
            list.add(map);
        }
        return list;
    }

    //查询用户参与商品购买人次和幸运码
    public List<String> luckUserList(Long accountId, Long commodityId) {
        List<String> list = new ArrayList<>();
        List<LuckCodes> codes = codesMapper.selectByAccAndCommId(accountId, commodityId);
        for (LuckCodes code : codes) {
            LuckCodes key = codesMapper.selectById(code.getId());
            LuckCodeTemplate template = luckTemplateMapper.selectById(key.getLuckCodeTemplateId());
            list.add(template.getLuckCode());
        }
        return list;
    }

    /**
     * 第三方账号绑定注册
     *
     * @param openId
     * @param password
     * @param header_url
     * @param nickname
     * @param platform
     * @return
     */
    @Override
    public User registerByOpenId(String openId, String password, String header_url, String nickname, String platform) {
        User user = new User();
        user.setPassword(password);
        user.setHeaderUrl(header_url);
        if (platform.equals("wx")) {
            user.setWeixinOpenId(openId);
        } else {
            user.setQqOpenId(openId);
        }
        user.setNickname(nickname);
        userMapper.insert(user);
        return queryByOpenId(openId, platform);
    }

    /**
     * 校验openId是否合法
     *
     * @return
     */
    @Override
    public boolean checkOpenId(String token, String openId) {
        //// TODO: 2017/1/13 0013 qq登录校验

       /* try {
            //通过token获取openId
            return new OpenID(token).getUserOpenID().equals(openId);
        } catch (QQConnectException e) {
            e.printStackTrace();
        }*/
        return false;
    }

    @Override
    public Map<String, Object> queryPersonalInfo(Long accountId) {
        User user = userMapper.selectById(accountId);
        Map<String, Object> map = new HashMap<>();
        map.put("imgUrl", user.getHeaderUrl());
        map.put("nickname", user.getNickname());
        map.put("phone", user.getPhoneNumber());
        map.put("qq", user.getQqNumber());
        map.put("id", user.getAccountId());
        map.put("codePicture", null);
        map.put("promId", user.getPromoterId());
        map.put("promProf", 0);
        return map;
    }

    @Override
    public List<String> selectGroupLuckCode(Long accountId, String lastCode) {
        return userCodeHistMapper.selectLimitCodeNum(accountId, lastCode, Settings.PAGE_LOAD_SIZE_10);
    }


    /**
     * 分平台查询用户信息
     *
     * @param openId
     * @param platform
     * @return
     */
    @Override
    public User queryByOpenId(String openId, String platform) {
        if (platform.equals("wx")) {
            return userMapper.selectByWxOpenId(openId);
        }
        return userMapper.selectByQQOpenId(openId);
    }

    @Autowired
    OkHttpClient httpClient;

    /**
     * qq验证 ,失败返回false
     *
     * @param token
     * @return
     */
    private boolean validatorQQOpenId(ThirdPartyLoginToken token) {
        String url = "https://graph.qq.com/oauth2.0/me?access_token=%s";
        url = String.format(url, token.getAccessToken());
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String body = response.body().string();
                boolean errcode = body.contains("errcode");
                return !errcode;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证微信
     *
     * @param token
     * @return
     */
    private boolean validatorWeiXinOpenId(ThirdPartyLoginToken token) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        url = String.format(url, token.getAccessToken(), token.getOpenid());
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String body = response.body().string();
                JSONObject object = JSON.parseObject(body);
                boolean errcode = object.containsKey("errcode");
                return !errcode;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 第三方注册登录
     *
     * @param token
     * @return
     */
    @Override
    public User registerAndLoginThirdParty(ThirdPartyLoginToken token) {
        User user = null;
        if (token.isQQPlatform()) { //qq平台授权
            user = userMapper.selectByQQOpenId(token.getOpenid());
            if (user == null) {
                if (validatorQQOpenId(token)) {//注册
                    user = new User();
                    user.setQqOpenId(token.getOpenid());
                    user.setHeaderUrl(token.getHeadImg());
                    user.setNickname(token.getNickName());
                    insertUser(user, token);
                    return userMapper.selectByQQOpenId(token.getOpenid());//重新查询携带自增id
                }
            }
        } else {//微信授权
            user = userMapper.selectByWxOpenId(token.getOpenid());
            if (user == null) {
                if (validatorWeiXinOpenId(token)) {
                    user = new User();
                    user.setWeixinOpenId(token.getOpenid());
                    user.setHeaderUrl(token.getHeadImg());
                    user.setNickname(token.getNickName());

                    insertUser(user, token);
                    return userMapper.selectByWxOpenId(token.getOpenid());//重新查询携带自增id
                }
            }
        }
        if (user == null)
            throw new AuthenticationException("第三方登录错误");
        return user;
    }

    public int insertUser(User user, ThirdPartyLoginToken token) {
        user.setPassword(token.getOpenid());
        PasswordUtils.encryptPassword(user);
        return userMapper.insert(user);
    }

    /**
     * 添加推广人id
     *
     * @param promId
     * @param accountId
     * @return
     */
    @Override
    public int addPromoter(Long promId, Long accountId) {
        User user = userMapper.selectById(accountId);
        if (user.getPromoterId() == null) {
            User promUser = userMapper.selectById(promId);
            if (promUser != null) {
                if (user.getRegistDate() > promUser.getRegistDate()) {
                    return userMapper.updateUserPromteId(accountId, promId, new Date().getTime());
                }
            } else {
                return -1;
            }
        }
        return -2;
    }


    /**
     * 添加QQ号
     *
     * @param accountId
     * @param qq
     * @return
     */
    @Override
    public boolean addQQNumber(Long accountId, String qq) {
        return userMapper.updateUserQQ(accountId, qq) > 0;
    }

    /**
     * 夺宝记录
     *
     * @param accountId
     * @param item      （显示形式：1、进行中；2、已揭晓；其他数字、显示全部）
     * @return
     */
    @Override
    public List<Map<String, Object>> selectPurchaseRecords(Integer item, Long accountId, Long lastCommId) {
        //查询已支付的订单
        List<Long> orderIdList = ordersMapper.selectUserOrderIdByPayState(accountId, Settings.ORDERS_ALREADY_PAID);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Long orderId : orderIdList) {
            List<Long> commIdList = ordersCommoditysMapper.selectCommIdByOrderId(orderId);
            for (Long commId : commIdList) {
                boolean has = false;
                for (Map<String, Object> map : mapList) {
                    if (map.get("id") == commId)
                        has = true;
                }
                if (has)
                    continue;
                Commoditys comm = comMapper.selectByKey(commId);
                if (null == comm)
                    continue;
                if (item == 1 && comm.getStateId() == Settings.COMMODITY_STATE_HAS_LOTTERY) {
                    continue;
                } else if (item == 2 && (comm.getStateId() == Settings.COMMODITY_STATE_ON_SALE || comm.getStateId() == Settings.COMMODITY_STATE_ON_LOTTERY)) {
                    continue;
                } else {
                    Map<String, Object> map = new HashMap<>();
                    Integer amount1 = codesMapper.countUserCommAmount(accountId, commId);
                    Integer amount2 = userCodeHistMapper.countHistoryUserCommAmount(accountId, commId);
                    map.put("userBuyNumber", amount1 + amount2);//添加用户本商品购买人次；
                    map.put("id", comm.getId());//添加商品ID
                    map.put("buyCurrentNumber", comm.getBuyTotalNumber() - comm.getBuyCurrentNumber());//添加当前购买人次
                    map.put("buyTotalNumber", comm.getBuyTotalNumber());//添加总购买人次
                    map.put("commState", comm.getStateId());//商品状态
                    map.put("roundTime", comm.getRoundTime());//添加期数
                    map.put("coverImgUrl", comm.getCoverImgUrl());//添加封面图URL
                    map.put("commName", comm.getName());//添加商品名
                    map.put("userAccountId", accountId);//添加用户ID
                    LotteryInfo lotteryInfo = lotteryInfoMapper.selectByComId(comm.getId());
                    if (null != lotteryInfo) {
                        map.put("endTime", lotteryInfo.getEndDate().getTime());//添加揭晓时间
                        User user = userMapper.selectById(lotteryInfo.getUserAccountId());
                        map.put("userNickname", user == null ? null : user.getNickname());//中奖者昵称
                    }
                    map.put("isWinner", lotteryInfo == null ? 0 : 1);
                    mapList.add(map);
                }
            }

        }
        return mapList;
    }

    /**
     * 查询用户夺宝记录中的Code码
     *
     * @param accountId 用户id
     * @param commId    商品id
     * @param lastCode  回传的前端显示的最后一个code码
     * @return
     */
    public List<String> selectUserLuckCode(Long accountId, Long commId, String lastCode) {
        List<String> onList = codesMapper.selectUserCommLuckCode(accountId, commId, lastCode, Settings.PAGE_LOAD_SIZE_16);
        if (!onList.isEmpty())
            return onList;
        return userCodeHistMapper.selectUserCommLuckCode(accountId, commId, lastCode, Settings.PAGE_LOAD_SIZE_16);
    }

    @Override
    public boolean updateUserNickname(Long accountId, String nickname) {
        User user = new User();
        user.setAccountId(accountId);
        user.setNickname(nickname);
        return userMapper.updateByPrimaryKey(user) > 0;
    }

    /**
     * 新注册用户送红包
     *
     * @param accountId
     */
    public void giveRegisterRedPackets(Long accountId) {
        Long registerDate = userMapper.selectUserRegisterDate(accountId);
        for (int i = 0; i < worths.length; i++) {
            RedPackets rp = new RedPackets();
            rp.setUserAccountId(accountId);
            rp.setUseState(0);
            rp.setName("新用户红包");
            rp.setValidDate(registerDate);
            rp.setUsePrice(prices[i]);
            rp.setWorth(worths[i]);
            rp.setOverdueDate(registerDate + overTime);
            redPacketsMapper.insert(rp);
        }
    }
}
