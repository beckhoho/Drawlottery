package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.User;

/**
 * 用户
 */
public interface IUserService {

    /**
     * 用户注册
     * @param accountid
     * @param password
     * @return
     */
    boolean register(Long accountid,String password);

    /**
     * 用户登录
     *
     * @param accountId
     * @param password
     * @return Users
     */
    User login(String accountId, String password);

    /**
     * 判断用户账号是否存在
     *
     * @return boolean
     */
    boolean isExist(Long accountId);

    /**
     *获取账号信息
     * @param accountId
     * @return Users
     */
    User getUserByAccount(Long accountId, String password);

    int updateUserInfo(User user);
}

