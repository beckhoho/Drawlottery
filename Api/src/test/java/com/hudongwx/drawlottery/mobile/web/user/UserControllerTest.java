package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.User;
import org.testng.annotations.Test;
/**
 * Created by wu on 2016/12/23.
 */

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/23 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/23 0016　<br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class UserControllerTest extends TestBaseWeb {

    @Test
    public void testRegister() throws Exception {
        User user = new User();
        user.setAccountId(1000l);
        user.setPassword("123");
        user.setNickname("hahaha");
        post("/user/register", JSON.toJSONString(user));

    }

    @Override
    public Object getController() {
        return new UserController();
    }
}