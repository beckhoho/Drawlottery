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
    public void testQueryUsableRedPacket() throws Exception {
        JSONObject jo=new JSONObject();
        jo.put("acc",1000l);
        post("http://localhost:8080/user/redpacket/usable",jo.toJSONString());
    }

    @Test
    public void testQueryUnusableRedPacket() throws Exception {

    }

    @Test
    public void testUpdateRedPacket() throws Exception {

    }

    @Override
    public Object getController() {
        return new RedPacketsController();
    }
}