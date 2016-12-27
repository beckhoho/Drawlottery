package com.hudongwx.drawlottery.mobile.web.index;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.testng.annotations.Test;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/27 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/27 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class AppIndexControllerTest extends TestBaseWeb {
    @Test
    public void testBanner() throws Exception {

    }

    @Test
    public void testQuick() throws Exception {

    }

    @Test
    public void testNotice() throws Exception {

    }

    @Test
    public void testCommodityInfo() throws Exception {
        JSONObject object = new JSONObject();
        object.put("ref",1);
        object.put("type",1);
        object.put("commid",2);
        post("/api/v1/index/commodity",object.toString());
    }

    @Override
    public Object getController() {
        return new AppIndexController();
    }
}