package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/16 0016　<br/>
 * <p>
 * 用户service接口实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper mapper;


    private User createUser(Long accountid, String password) {
        User user = new User();
        user.setAccountId(accountid);
        user.setPassword(password);
        PasswordUtils.encryptPassword(user);//加密用户密码
        return user;
    }


    @Override
    public boolean register(String phone, String password) {
        User user = new User();
        user.setAccountId(Long.valueOf(phone));
        user.setPassword(password);
        PasswordUtils.encryptPassword(user);//加密用户密码
        int i = mapper.insert(user);
        return i>0;
    }

    public User login(String accountid, String password) {
        User user = createUser(Long.valueOf(accountid), password);
//        System.out.println("password-------------->" + user.getPassword());
        List<User> userList = mapper.select(user);
        if (!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public boolean isExist(Long accountId) {
        User user = new User();
        user.setAccountId(accountId);
        return !mapper.select(user).isEmpty();
    }

    @Override
    public User getUserByAccount(Long accountId, String password) {
        User user = createUser(accountId, password);
        if (mapper.select(user).isEmpty())
            return null;
        return mapper.select(user).get(0);
    }

    @Override
    public int updateUserInfo(User user) {
        return 0;
    }


}
