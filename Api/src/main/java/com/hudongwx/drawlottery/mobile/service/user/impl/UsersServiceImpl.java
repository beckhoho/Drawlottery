package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Users;
import com.hudongwx.drawlottery.mobile.mappers.UsersMapper;
import com.hudongwx.drawlottery.mobile.service.user.IUsersService;
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
public class UsersServiceImpl implements IUsersService {

    @Autowired
    UsersMapper mapper;

    public Users login(String username, String password) {
        Users user = new Users();
        user.setName(username);
        user.setPassword(password);
//        PasswordUtils.encryptPassword(user);//加密用户密码
        return user;
    }

    @Override
    public boolean register(Users users) {
        if (mapper.insert(users) == 1)
            return true;
        return false;
    }

    @Override
    public boolean isExist(Long accountId) {
        Users users = mapper.selectByAccountId(accountId);
        if (users != null)
            return true;
        return false;
    }

    @Override
    public Users getUsersByAccount(Long accountId, String password) {
        return null;
    }

}
