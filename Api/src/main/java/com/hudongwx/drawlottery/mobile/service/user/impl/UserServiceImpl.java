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
    UserMapper mapper;
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

    @Override
    public boolean register(String phone, String password, String verifyCode, String code) {
        if (null != mapper.selectByPhoneNumber(phone))
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
        return mapper.insert(user) > 0;
    }

    @Override
    public User queryUserByPhoneNum(String phone) {
        return mapper.selectByPhoneNumber(phone);
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
            Map<String, Object> map = new HashMap<>();
            map.put("genre", com.getGenre());//添加商品类型
            map.put("commodityName", com.getCommodityName());//添加商品名
            map.put("luckCode", com.getLuckCode());//添加当期中奖码
            map.put("roundTime", com.getRoundTime());//添加期数
            map.put("endTime", com.getEndTime());//揭晓时间
            map.put("luckCode", com.getLuckCode());//添加历史商品ID
            map.put("buyNumber", com.getBuyNumber());//购买人次
            map.put("buyNumber", com.getBuyNumber());//购买人次
            map.put("imgUrl", Settings.SERVER_URL_PATH + com.getCoverImgUrl());//中奖商品图片地址
            map.put("shareState", 0);//是否晒单（0、未晒单；1、已晒单）
            map.put("state", 0);//中奖确认流程（0、中奖--->1、确认手机号--->2、已充值）
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 查看购买记录
     *
     * @param accountId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectHistoryPay(Long accountId, Integer item) {

        List<Map<String, Object>> mapList = selectToHistory(accountId);
        if (item == 1) {
            return selectToNew(accountId);
        } else if (item == 2) {
            return selectToHistory(accountId);
        } else {
            mapList.addAll(selectToHistory(accountId));
            mapList.addAll(selectToNew(accountId));
            return mapList;
        }
    }

    //添加历史购买商品
    public List<Map<String, Object>> selectToHistory(Long accountId) {
        //添加已开奖的商品
        List<Map<String, Object>> list = new ArrayList<>();
        UserCodesHistory userHistory = new UserCodesHistory();
        userHistory.setUserAccountId(accountId);
        List<UserCodesHistory> s1 = userCodeHistMapper.select(userHistory);
        for (UserCodesHistory u : s1) {
            Map<String, Object> map = new HashMap<>();
            CommodityHistory history = comHistoryMapper.selectBycommId(u.getCommodityId());
            User user1 = mapper.selectByPrimaryKey(history.getLuckUserAccountId());
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
            Commoditys com = comMapper.selectByPrimaryKey(commId);
            List<String> integers = luckUserList(accountId, com.getId());
            map.put("id", com.getId());//添加商品ID
            map.put("buyCurrentNumber", com.getBuyCurrentNumber());//添加当前购买人次
            map.put("buyTotalNumber", com.getBuyTotalNumber());//添加总购买人次
            map.put("commState", com.getStateId());//商品状态
            map.put("roundTime", com.getRoundTime());//添加期数
            map.put("coverImgUrl", Settings.SERVER_URL_PATH + com.getCoverImgUrl());//添加封面图URL
            map.put("commName", com.getName());//添加商品名
            map.put("userAccountId", accountId);//添加用户ID
            map.put("userCodesList", integers);//添加用户参与购买的幸运码集合
            map.put("userBuyNumber", integers.size());//添加用户本商品购买人次；
            map.put("isWinner", 0);
            map.put("userNickname", null);//中奖者昵称
            map.put("endTime", null);//添加揭晓时间
            list.add(map);
        }
        return list;
    }

    //查询用户参与商品购买人次和幸运码
    public List<String> luckUserList(Long accountId, Long commodityId) {
        List<String> list = new ArrayList<>();
        UserLuckCodes luckCodes = new UserLuckCodes();
        luckCodes.setCommodityId(commodityId);
        luckCodes.setUserAccountId(accountId);
        List<UserLuckCodes> codes = luckCodesMapper.select(luckCodes);
        for (UserLuckCodes code : codes) {
            LuckCodes key = codesMapper.selectByPrimaryKey(code.getLockCodeId());
            list.add(key.getLockCode());
        }
        return list;
    }
}
