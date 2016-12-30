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


    private User createUser(String phone, String password) {
        User user = new User();
        user.setPhoneNumber(phone);
        user.setPassword(password);
        user.setCurrentState(Settings.USER_STATE_NORMAL);
        PasswordUtils.encryptPassword(user);//加密用户密码
        return user;
    }

    @Override
    public boolean register(String phone, String password) {
        return mapper.insert(createUser(phone, password)) > 0;
    }

    public User login(String phone, String password) {
        User user = new User();
        user.setPhoneNumber(phone);
        user.setPassword(password);
        User user1 = mapper.selectByPhoneNumber(phone);
        if (null != user1) {
            user.setCurrentState(user.getCurrentState());
            user.setSalt(user.getSalt());
        } else {
            return null;
        }
        return user;
    }

    @Override
    public boolean isRegistered(String phone) {
        return false;
    }

    @Override
    public User getUserByPhone(String phone, String password) {
        return null;
    }

    @Override
    public int updateUserInfo(User user) {
        return 0;
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
            map.put("commodityName", com.getCommodityName());//添加商品名
            map.put("luckCode", com.getLuckCode());//添加当期中奖码
            map.put("roundTime", com.getRoundTime());//添加期数
            map.put("endTime", com.getEndTime());//揭晓时间
            map.put("luckCode", com.getId());//添加历史商品ID
            map.put("buyNumber", com.getBuyNumber());//购买人次
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
    public List<Map<String, Object>> selectHistoryPay(Long accountId,Integer item) {

        List<Map<String, Object>> mapList = selectToHistory(accountId);
        if(item==1){
            return selectToNew(accountId);
        }
        else if(item==2){
            return selectToHistory(accountId);
        }
        else{
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
            CommodityHistory history = comHistoryMapper.selectByPrimaryKey(u.getCommodityId());
            User user1 = mapper.selectByPrimaryKey(history.getLuckUserAccountId());
            map.put("commodityId", history.getCommodityId());//商品ID
            map.put("buyNumber", history.getBuyNumber());//添加本用户购买次数
            map.put("endTime", history.getEndTime());//添加揭晓时间
            map.put("name", history.getCommodityName());//添加商品名
            map.put("roundTime", history.getRoundTime());//添加期数
            map.put("state", 1);//添加状态
            map.put("coverImgUrl",history.getCoverImgUrl());//添加商品封面图URL
            map.put("buyTotalNumber",history.getBuyTotalNumber());//添加当期总需人次
            map.put("nickname",user1.getNickname());//中奖者昵称
            map.put("userAccountId",accountId);//添加用户ID
            if(accountId==history.getLuckUserAccountId()) {
                map.put("userState",1);//是否中奖者是本用户（是）
            }
            else{
                map.put("userState",0);//是否中奖者是本用户（不是）
            }
            list.add(map);
        }
        return list;
    }

    //添加正在进行的商品
    public List<Map<String, Object>> selectToNew(Long accountId) {
        List<Map<String, Object>> list = new ArrayList<>();
        UserLuckCodes user = new UserLuckCodes();
        user.setUserAccountId(accountId);
        List<UserLuckCodes> s1 = luckCodesMapper.select(user);
        for(UserLuckCodes u : s1){
            Map<String,Object> map = new HashMap<>();
            Commoditys com = comMapper.selectByPrimaryKey(u.getCommodityId());
            map.put("commodityId",com.getId());//添加商品ID
            map.put("buyCurrentNumber",com.getBuyCurrentNumber());//添加当前购买人次
            map.put("buyTotalNumber",com.getBuyTotalNumber());//添加总购买人次
            map.put("state",com.getState());//添加状态
            map.put("roundTime",com.getRoundTime());//添加期数
            map.put("coverImgUrl",com.getCoverImgUrl());//添加封面图URL
            map.put("name",com.getName());//添加商品名
            map.put("userAccountId",accountId);//添加用户ID
            list.add(map);
        }
        return list;
    }



}
