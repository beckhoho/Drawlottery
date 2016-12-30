package com.hudongwx.drawlottery.mobile.web.index;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.web.auth.AuthorController;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

//用于web测试
/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class})
@WebAppConfiguration
@Transactional*/
public class AuthControllerTest extends TestBaseWeb {

    @Test
    public void testLogin() throws Exception {
        JSONObject object = new JSONObject();
        object.put("username","王五");
        object.put("password","123456");
        post("/auth/queryUserByPhoneNum",object.toString());
    }

    @Override
    public Object getController() {
        return new AuthorController();
    }

}