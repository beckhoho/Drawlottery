package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import org.testng.annotations.Test;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/24 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/24 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class DeliveryAddressControllerTest extends TestBaseWeb {
    @Test
    public void testAddAddress() throws Exception {
        DeliveryAddress da = new DeliveryAddress();
        da.setUserId(1000l);
        da.setAddress("qwert");
        post("/user/address/add", JSON.toJSONString(da));
    }

    @Test
    public void testDeleteAddress() throws Exception {
        String addrid = "addrid=1243";
        post("/user/address/del", addrid);
    }

    @Test
    public void testUpdateAddress() throws Exception {

    }

    @Test
    public void testQueryAddress() throws Exception {

    }

    @Override
    public Object getController() {
        return new DeliveryAddressController();
    }
}