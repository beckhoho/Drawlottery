package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.testng.annotations.Test;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/16 0016　<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
public class RedPacketsControllerTest extends TestBaseWeb {

    @Test
    public void testAdd() throws Exception {
        JSONObject jo=new JSONObject();
        jo.put("id","1000");
        jo.put("userId","1000");
        jo.put("validDate","14800000000");
        jo.put("overdueDate","14800000000");
        jo.put("name","红包红包");
        jo.put("usePrice",150);
        jo.put("worth",5);
        post("http://localhost:8080/user/rPacket/add",jo.toJSONString());

    }

    @Override
    public Object getController() {
        return new RedPacketsController();
    }
}