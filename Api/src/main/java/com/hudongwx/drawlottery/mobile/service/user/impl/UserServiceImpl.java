package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.utils.PasswordUtils;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import com.hudongwx.drawlottery.mobile.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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


    @Override
    public boolean register(String phone, String password, String code) {
        if (null != mapper.selectByPhoneNumber(phone))
            return false;
        if (!VerifyCodeUtils.isCorrectCode(code))
            return false;
        User user = new User();
        user.setPhoneNumber(phone);
        user.setPassword(password);
        user.setRealName("未认证");
        int length = phone.length();
        user.setNickname(phone.substring(0, 3) + "*****" + phone.substring(length - 3, length));
        user.setUserIntegral(0);
        user.setHeaderUrl(Settings.USER_HEAD_PORTRAIT_URL_DEFAULT);
        user.setGoldNumber(0);
        user.setCurrentState(Settings.USER_STATE_NORMAL);
        PasswordUtils.encryptPassword(user);//加密用户密码
        return mapper.insert(user) > 0;
    }

    @Override
    public User queryUserByPhoneNum(String phone) {
        return mapper.selectByPhoneNumber(phone);
    }

    @Override
    public Map<String, Object> getUserInfo(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("headUrl", user.getHeaderUrl());
        map.put("accountId", user.getAccountId());
        map.put("nickname", user.getNickname());
        map.put("integral", user.getUserIntegral());
        map.put("gold", user.getGoldNumber());
        return map;
    }

}
