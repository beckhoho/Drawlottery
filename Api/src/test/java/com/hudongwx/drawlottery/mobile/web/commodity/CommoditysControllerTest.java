package com.hudongwx.drawlottery.mobile.web.commodity;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
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
public class CommoditysControllerTest extends TestBaseWeb {
    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testSearch() throws Exception {
        JSONObject jo=new JSONObject();
        jo.put("category",null);
        jo.put("name",null);
        post("http://localhost:8080/commodity/search",jo.toJSONString());
    }

    @Override
    public Object getController() {
        return new CommoditysController();
    }
}