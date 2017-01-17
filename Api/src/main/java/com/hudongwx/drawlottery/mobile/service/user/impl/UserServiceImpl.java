package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.utils.PasswordUtils;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    UserMapper userMapper;
    @Autowired
    CommodityHistoryMapper comHistoryMapper;
    @Autowired
    UserLuckCodesMapper luckCodesMapper;
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

    @Override
    public boolean register(String phone, String password) {
        if (null != userMapper.selectByPhoneNumber(phone))
            return false;
//        if (!verifyCode.toUpperCase().equals(code.toUpperCase()))
//            return false;
        User user = new User();
        user.setPhoneNumber(phone);
        user.setPassword(password);
        user.setRealName("未认证");
        int length = phone.length();
        if (length == 11) {
            user.setNickname(phone.substring(0, 3) + "*****" + phone.substring(length - 3, length));
        } else {
            user.setNickname("未设置");
        }
        user.setUserIntegral(0);
        user.setHeaderUrl(Settings.SERVER_URL_PATH + Settings.USER_PORTRAIT_URL_DEFAULT);
        user.setGoldNumber(0);
        user.setCurrentState(Settings.USER_STATE_NORMAL);
        PasswordUtils.encryptPassword(user);//加密用户密码
        return userMapper.insert(user) > 0;
    }

    @Override
    public User queryUserByPhoneNum(String phone) {
        return userMapper.selectByPhoneNumber(phone);
    }

    @Override
    public Map<String, Object> getUserInfo(User user) {
        Map<String, Object> map = new HashMap<>();
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
    public List<Map<String, Object>> selectHistoryLottery(Long accountId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<CommodityHistory> histories = comHistoryMapper.selectHistoryLottery(accountId);

        for (CommodityHistory com : histories) {
            CommodityTemplate template = tempMapper.selectById(com.getTempId());
            Share s = new Share();
            s.setCommodityId(com.getCommodityId());
            s.setUserAccountId(accountId);
            List<Share> shares = shareMapper.select(s);
            Map<String, Object> map = new HashMap<>();
            if(shares.size()>0){
                map.put("shareState", 1);//是否晒单（0、未晒单；1、已晒单）
            }
            else {
                map.put("shareState",0);
            }
            map.put("id", com.getCommodityId());//添加商品id
            map.put("commodityName", com.getCommodityName());//添加商品名
            map.put("roundTime", com.getRoundTime());//添加期数
            map.put("endTime", com.getEndTime());//揭晓时间
            map.put("buyNumber", com.getBuyNumber());//购买人次
            map.put("luckCode", com.getLuckCode());//添加幸运码
            map.put("imgUrl", Settings.SERVER_URL_PATH + com.getCoverImgUrl());//中奖商品图片地址
            map.put("exchangeId",selectExchange(com.getCommodityId()));//添加兑换方式
            map.put("withdrawalsMoney",template.getWithdrawalsMoney());//折换现金金额
            map.put("exchangeMoney",template.getExchangeMoney());//折换闪币
            map.put("state",com.getExchangeState());//添加兑换状态
            map.put("exchangeWay",com.getExchangeWay());//添加已选择兑奖方式
            mapList.add(map);
        }
        return mapList;
    }

    public Map<String, Object> selectExchange(Long commodityId) {
        Map<String, Object> map = new HashMap<>();
        List<CommodityExchange> exchanges = exchangeMapper.selectByCommodityId(commodityId);
        for (CommodityExchange ex : exchanges) {
            ExchangeWay way = wayMapper.selectById(ex.getExchangeWayId());
            map.put(way.getId() + "", way.getName());
        }
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
        List<Map<String, Object>> list = new ArrayList<>();
        UserCodesHistory userHistory = new UserCodesHistory();
        userHistory.setUserAccountId(accountId);
        List<UserCodesHistory> s1 = userCodeHistMapper.selectByUserAccountId(accountId);
        for (UserCodesHistory u : s1) {
            Map<String, Object> map = new HashMap<>();
            CommodityHistory history = comHistoryMapper.selectBycommId(u.getCommodityId());
            User user1 = userMapper.selectById(history.getLuckUserAccountId());
            List<String> integers = luckUserList(accountId, history.getCommodityId());
            map.put("id", history.getCommodityId());//商品ID
            map.put("buyTotalNumber", history.getBuyTotalNumber());//添加当期总需人次
            map.put("buyCurrentNumber", history.getBuyTotalNumber());//添加当前购买人次
            map.put("commState", 1);//商品状态
            map.put("roundTime", history.getRoundTime());//添加期数
            map.put("coverImgUrl", Settings.SERVER_URL_PATH + history.getCoverImgUrl());//添加商品封面图URL
            map.put("commName", history.getCommodityName());//添加商品名
            map.put("userAccountId", accountId);//添加用户ID
            map.put("userCodesList", integers);//添加用户参与购买的幸运码集合
            map.put("userBuyNumber", history.getBuyNumber());//添加本用户购买次数
            if (accountId.longValue() == history.getLuckUserAccountId().longValue()) {
                map.put("isWinner", 1);//是否中奖者是本用户（是）
            } else {
                map.put("isWinner", 0);//是否中奖者是本用户（不是）
            }
            map.put("userNickname", user1.getNickname());//中奖者昵称
            map.put("endTime", history.getEndTime());//添加揭晓时间
            list.add(map);
        }
        return list;
    }

    //添加正在进行的商品
    public List<Map<String, Object>> selectToNew(Long accountId) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Long> commIdList = luckCodesMapper.selectDistinctGroupByCommId(accountId);

        for (Long commId : commIdList) {
            Map<String, Object> map = new HashMap<>();
            Commoditys com = comMapper.selectByKey(commId);
            List<String> integers = luckUserList(accountId, com.getId());
            CommodityHistory history = comHistoryMapper.selectBycommId(commId);
            User user = userMapper.selectById(history.getLuckUserAccountId());
            map.put("id", com.getId());//添加商品ID
            map.put("buyCurrentNumber", com.getBuyTotalNumber()-com.getBuyCurrentNumber());//添加当前购买人次
            map.put("buyTotalNumber", com.getBuyTotalNumber());//添加总购买人次
            map.put("commState", com.getStateId());//商品状态
            map.put("roundTime", com.getRoundTime());//添加期数
            map.put("coverImgUrl", com.getCoverImgUrl());//添加封面图URL
            map.put("commName", com.getName());//添加商品名
            map.put("userAccountId", accountId);//添加用户ID
            map.put("userCodesList", integers);//添加用户参与购买的幸运码集合
            map.put("userBuyNumber", integers.size());//添加用户本商品购买人次；
            map.put("isWinner", 0);
            map.put("userNickname", user.getNickname());//中奖者昵称
            map.put("endTime", history.getEndTime());//添加揭晓时间
            list.add(map);
        }
        return list;
    }

    //查询用户参与商品购买人次和幸运码
    public List<String> luckUserList(Long accountId, Long commodityId) {
        List<String> list = new ArrayList<>();
        List<UserLuckCodes> codes = luckCodesMapper.selectByAccAndCommId(accountId,commodityId);
        for (UserLuckCodes code : codes) {
            LuckCodes key = codesMapper.selectById(code.getLuckCodeId());
            list.add(key.getLockCode());
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
}
