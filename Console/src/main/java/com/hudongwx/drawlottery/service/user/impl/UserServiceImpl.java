package com.hudongwx.drawlottery.service.user.impl;

import com.hudongwx.drawlottery.dao.UserMapper;
import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.user.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    @Resource
    private UserMapper userMapper;

    /**
     * 通过手机号查询用户.
     *
     * @param phoneNum 用户手机号
     * @return 用户实体
     */
    @Override
    public User queryUserByPhoneNum(final String phoneNum) {
        return userMapper.selectUserByPhoneNumber(phoneNum);
    }

    @Override
    public User getUsers() {
        return null;
    }
}
