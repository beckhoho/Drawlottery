package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.User;

/**
 * 用户
 */
public interface IUserService {

    /**
     * 用户登录
     * @param name
     * @param paswd
     * @return
     */
    User login(String name,String paswd);


}

