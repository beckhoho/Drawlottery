package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.Users;

/**
 * 用户
 */
public interface IUsersService {

    /**
     * 用户注册
     *
     * @param users
     * @return boolean
     */
    boolean register(Users users);

    /**
     * 用户登录
     *
     * @param accountId
     * @param password
     * @return Users
     */
    Users login(String accountId, String password);

    /**
     * 判断用户账号是否存在
     *
     * @return boolean
     */
    boolean isExist(Integer accountId);

    /**
     *获取账号信息
     * @param accountId
     * @return Users
     */
    Users getUserByAccount(Integer accountId, String password);
}

