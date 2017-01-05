package com.hudongwx.drawlottery.service.user;

import com.hudongwx.drawlottery.pojo.User;

/**
 * 用户相关的 service 接口.
 * Date: 2017/1/4 0004
 * Time: 16:11
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface IUserService {
    /**
     * 通过手机号查询用户.
     *
     * @param phoneNum 用户手机号
     * @return 用户实体
     */
    User queryUserByPhoneNum(final String phoneNum);

    User getUsers();
}
