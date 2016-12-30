package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.utils.PasswordUtils;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private User createUser(String phone, String password) {
        User user = new User();
        user.setPhoneNumber(phone);
        user.setPassword(password);
        user.setCurrentState(Settings.USER_STATE_NORMAL);
        PasswordUtils.encryptPassword(user);//加密用户密码
        return user;
    }

    @Override
    public boolean register(String phone, String password) {
        return mapper.insert(createUser(phone, password)) > 0;
    }

    public User login(String phone, String password) {
        User user=new User();
        user.setPhoneNumber(phone);
        user.setPassword(password);
        User user1 = mapper.selectByPhoneNumber(phone);
        if(null!=user1){
            user.setCurrentState(user.getCurrentState());
            user.setSalt(user.getSalt());
        }else{
            return null;
        }
        return user;
    }

    @Override
    public boolean isRegistered(String phone) {
        return false;
    }

    @Override
    public User getUserByPhone(String phone, String password) {
        return null;
    }

    @Override
    public int updateUserInfo(User user) {
        return 0;
    }


}
