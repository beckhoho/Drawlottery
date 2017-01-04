package com.hudongwx.drawlottery.dao;

import com.hudongwx.drawlottery.common.base.BaseMapper;
import com.hudongwx.drawlottery.pojo.User;

public interface UserMapper extends BaseMapper<User> {

    public User selectUserByPhoneNumber(final String phoneNum);
}