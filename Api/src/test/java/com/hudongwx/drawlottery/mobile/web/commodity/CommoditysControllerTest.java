package com.hudongwx.drawlottery.mobile.web.commodity;

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
public class CommoditysControllerTest extends TestBaseWeb {
    @Test
    public void testQueryTest() throws Exception {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("c",100);
        jsonObject.put("name",null);
        jsonObject.put("page",1);
        String s = jsonObject.toJSONString();
        System.out.println(s);
        post("/api/v1/commodity/test",s);
    }

    @Override
    public Object getController() {
        return new CommoditysController();
    }
}