package com.hudongwx.drawlottery.mobile.web.index;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hudongwx.drawlottery.mobile.ApiApplication;
import com.hudongwx.drawlottery.mobile.ApiWebApplicationTests;
import com.hudongwx.drawlottery.mobile.conf.shiro.ShiroConfig;
import com.hudongwx.drawlottery.mobile.mappers.HelloMapper;
import com.hudongwx.drawlottery.mobile.web.auth.AuthController;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

//用于web测试
/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class})
@WebAppConfiguration
@Transactional*/
public class AuthControllerTest extends ApiWebApplicationTests {

    @Test
    public void testLogin() throws Exception {
        executepPost("/auth/login");
    }

    @Override
    public String getContent() {
        JSONObject object = new JSONObject();
        object.put("username","王五");
        object.put("password","123456");
        return object.toJSONString();
    }

    @Override
    public Object getController() {
        return new AuthController();
    }

}