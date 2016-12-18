package com.hudongwx.drawlottery.mobile.web.auth;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.ApiApplication;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testng.annotations.Test;

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

    @Test
    public void testLogin() throws Exception {

        JSONObject object = new JSONObject();
        object.put("username","lisi");
        object.put("password","123456");
        post("/auth/login",object.toJSONString());

        Subject subject = SecurityUtils.getSubject();

        subject.getPrincipal();
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

    }

    @Bean
    @Override
    public Object getController() {
        return new AuthorController();
    }
}