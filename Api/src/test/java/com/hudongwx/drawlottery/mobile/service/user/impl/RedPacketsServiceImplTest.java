package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.RedPackets;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

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
        BigDecimal big = new BigDecimal(1);
        RedPackets rp = new RedPackets();
        rp.setUserAccountId(1l);
        rp.setName("新手红包");
        rp.setValidDate(new Date());
        rp.setOverdueDate(new Date());
        rp.setUsePrice(big);
        rp.setWorth(big);
        ird.addRP(rp);
    }

    @Test
    public void testDelete() throws Exception {
        RedPackets r = new RedPackets();
        r.setId(2l);
        boolean d = ird.delete(r);
        Assert.assertTrue(d);
    }

    @Test
    public void testSelect() throws Exception {

        List<RedPackets> s = ird.select(3l);
        for (RedPackets r : s){
            System.out.println(r.getName());
        }
    }

}