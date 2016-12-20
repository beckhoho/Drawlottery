package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.User;

/**
 * 用户
 */
public interface IUserService {

    /**
     * 用户登录
     *
     * @param accountId
     * @param password
     * @return
     */
    User login(String accountId, String password);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    User regster(User user);

    /**
     * @return
     */
    boolean isExist(String accountId);
}

