package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.testng.annotations.Test;

import javax.annotation.Resource;
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
    public void testAddPromoter() throws Exception {
        JSONObject jsonObject = userController.addPromoter(10444L);
        System.out.println(jsonObject);
    }

    @Resource
    UserController userController;

    @Test
    public void testQueryUserCommRecord() throws Exception {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("item",1);
        jsonObject.put("page",1);
        post("/api/v1/user/usercomm/show",jsonObject.toJSONString());

    }

    @Test
    public void testRegister() throws Exception {

    }


    @Override
    public Object getController() {
        return userController;
    }
}