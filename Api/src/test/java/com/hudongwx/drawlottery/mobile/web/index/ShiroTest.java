package com.hudongwx.drawlottery.mobile.web.index;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.ApiApplication;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.shiro.AuthorRetryLimitCredentialsMatcher;
import com.hudongwx.drawlottery.mobile.shiro.AuthorUserRealm;
import com.hudongwx.drawlottery.mobile.web.auth.AuthorController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.format.Base64Format;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * DATE:十二月
 * Author: origin
 * DESC:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class})
public class ShiroTest extends TestBaseWeb {

    @Override
    public Object getController() {

        return null;
    }
}
