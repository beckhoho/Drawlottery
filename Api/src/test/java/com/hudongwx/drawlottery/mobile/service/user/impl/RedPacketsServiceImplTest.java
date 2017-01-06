package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.RedPackets;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/26 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/26 18:42　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class RedPacketsServiceImplTest extends TestBaseMapper {

    @Autowired
    IRedPacketsService ird;
    @Test
    public void testAddRP() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {
        RedPackets r = new RedPackets();
        r.setId(2l);
    }

    @Test
    public void testSelect() throws Exception {

    }

}