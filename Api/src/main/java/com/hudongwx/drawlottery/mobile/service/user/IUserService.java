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
    boolean register(String phone, String password,String verifyCode,String code);

    /**
     * 手机号查询用户
     *
     * @param phone
     * @return Users
     */
    User queryUserByPhoneNum(String phone);

    Map<String,Object> getUserInfo(User user);

    List<Map<String,Object>> selectHistoryLottery(Long accountId);

    //购买历史
    List<Map<String,Object>> selectHistoryPay(Long accountId,Integer item);
}

