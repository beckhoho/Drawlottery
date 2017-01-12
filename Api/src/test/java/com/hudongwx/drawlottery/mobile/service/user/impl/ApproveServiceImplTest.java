package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Approve;
import com.hudongwx.drawlottery.mobile.service.user.IApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

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
 * 创建　kiter　2016/12/26 16:24　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class ApproveServiceImplTest extends TestBaseMapper {

    @Autowired
    IApproveService approve;
    @Test
    public void testAddApproveMassage() throws Exception {
        Approve a = new Approve();
        a.setUserAccountId(1l);
        a.setApproveDate(new Date().getTime());
        a.setPhoneNumber(15283590214l);
        a.setRealName("小张");
        a.setSite("成都市");
        a.setRealNumber("123123123123123123");
        boolean b = approve.addApproveMassage(a);
        Assert.assertTrue(b);
    }

    @Test
    public void testSelect() throws Exception {
        List<Approve> select = approve.select(1l);
        System.out.println(select.get(0).getRealName());
        Assert.assertNotNull(select);

    }

    @Test
    public void testUpdate() throws Exception {

        Approve a = new Approve();
        a.setId(1l);
        a.setApproveDate(new Date().getTime());
        a.setRealName("小王");
        boolean update = approve.update(a);
        Assert.assertTrue(update);

    }

}