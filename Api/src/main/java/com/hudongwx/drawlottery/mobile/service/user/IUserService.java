package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.User;

import java.util.List;
import java.util.Map;

/**
 * 用户
 */
public interface IUserService {

    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @return
     */
    boolean register(String phone, String password);

    /**
     * 用户手机号登录
     *
     * @param phone
     * @param password
     * @return Users
     */
    User login(String phone, String password);

    /**
     * 判断用户手机号是否已注册
     *
     * @return boolean
     */
    boolean isRegistered(String phone);

    /**
     * 获取账号信息
     *
     * @param phone
     * @param password
     * @return
     */
    User getUserByPhone(String phone, String password);

    int updateUserInfo(User user);


    //中奖历史
    List<Map<String,Object>> selectHistoryLottery(Long accountId);

    //购买历史
    List<Map<String,Object>> selectHistoryPay(Long accountId,Integer item);
}

