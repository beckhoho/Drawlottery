package com.hudongwx.drawlottery.mobile.service.notification.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.service.notification.INotificationPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/27 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/27 17:59　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class NotificationPrizeServiceImplTest extends TestBaseMapper {


    @Autowired
    INotificationPrizeService service;

    @Test
    public void testAddPrizeNotification() throws Exception {
//        NotificationPrize no = new NotificationPrize();
//        no.setCommodityId(3l);
//        no.setAccountId(3l);
//        no.setLuckCodesId(3l);
//        no.setOnPrizeDate(new Date().getTime());
//        boolean b = service.addPrizeNotification(no);
//        Assert.assertTrue(b);
    }

    @Test
    public void testSelectAll() throws Exception {
//        List<NotificationPrize> no = service.selectAll();
//        Assert.assertNotNull(no);

    }

    @Test
    public void testSelectByAccount() throws Exception {
//        List<NotificationPrize> no = service.selectByAccount(1l, 1l);
//        Assert.assertNotNull(no);
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testSelectByNew() throws  Exception{
//        List<String> no = service.selectByNewPrizeNotify();
//        System.out.println(no.size());
    }

}