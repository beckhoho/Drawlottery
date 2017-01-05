package com.hudongwx.web.auth;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.TestBaseWeb;
import com.hudongwx.drawlottery.MainApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/29 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/29 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@SpringBootTest(classes = {MainApplication.class})
@WebAppConfiguration
@Transactional
@ActiveProfiles("test")
public class AuthorControllerTest extends TestBaseWeb {

    @Test
    public void testLogin() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "18381690821");
        jsonObject.put("password", "123456");
        post("/api/v1/auth/login", jsonObject.toJSONString());

    }

    @Test
    public void testRegister() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phone", "1234567");
        jsonObject.put("password", "1234567");
        post("/api/v1/auth/register?phone=1234567&password=1234567", jsonObject.toJSONString());
    }

    @Override
    public Object getController() {
        return null;//new AuthorController()
    }
}