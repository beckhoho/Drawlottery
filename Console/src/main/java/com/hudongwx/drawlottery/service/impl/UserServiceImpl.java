package com.hudongwx.drawlottery.service.impl;

import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Drawlottery.
 * Date: 2017/1/4 0004
 * Time: 16:12
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    /**
     * 通过手机号查询用户.
     *
     * @param phoneNum 用户手机号
     * @return 用户实体
     */
    @Override
    public User queryUserByPhoneNum(String phoneNum) {
        return null;
    }
}
