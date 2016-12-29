package com.hudongwx.drawlottery.mobile.web.auth;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
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
public class AuthorControllerTest extends TestBaseWeb {
    @Test
    public void testLogin() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "18381690821");
        jsonObject.put("password", "123456");
        post("/api/v1/auth/register", jsonObject.toJSONString());
    }

    @Override
    public Object getController() {
        return new AuthorController();
    }
}