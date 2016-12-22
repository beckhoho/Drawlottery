package com.hudongwx.drawlottery.mobile.web.user;

import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.RedPackets;
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
        RedPackets rp = new RedPackets();
        /*rp.setId(10l);
        rp.setUserId(10000l);
        rp.setValidDate(new Date(14800000000l));
        rp.setOverdueDate(new Date(14800000000l));
        rp.setName("新手紅包");
        rp.setUsePrice(new BigDecimal(150));
        rp.setWorth(new BigDecimal(5));
        post("http://localhost:8080/user/rPacket/add", rp.toString());*/

    }

    @Override
    public Object getController() {
        return new RedPacketsController();
    }
}