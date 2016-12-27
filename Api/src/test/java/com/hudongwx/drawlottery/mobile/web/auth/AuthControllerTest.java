package com.hudongwx.drawlottery.mobile.web.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.ApiApplication;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/17 0017 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/17 0017　<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
//用于web测试
@SpringBootTest(classes = { ApiApplication.class})
@WebAppConfiguration
@Transactional
public class AuthControllerTest extends TestBaseWeb {

    @Autowired
    net.sf.ehcache.CacheManager manager;

    @Test
    public void testLogin() throws Exception {
        System.out.println(Arrays.toString(manager.getCacheNames()));
        JSONObject object = new JSONObject();
        object.put("username","lisi");
        object.put("password","123456");
        post("/auth/login",object.toJSONString());

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        post("/auth/login",object.toJSONString());

    }

    @Test
    public void testLogout() throws Exception {
        ///auth/logout
        /*JSONObject object = new JSONObject();
        object.put("username","lisi");
        object.put("password","123456");
        post("/auth/logout",object.toJSONString());*/

        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("1","234"));
    }

    @Test
    public void testRegister() throws Exception {
        JSONObject object = new JSONObject();
        object.put("phone","123456");
        object.put("paswd","123456");
        post("/api/v1/auth/register", JSON.toJSONString(object));
    }



    @Bean
    @Override
    public Object getController() {
        return new AuthorController();
    }
}