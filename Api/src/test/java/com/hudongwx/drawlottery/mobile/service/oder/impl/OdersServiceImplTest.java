package com.hudongwx.drawlottery.mobile.service.oder.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Oders;
import com.hudongwx.drawlottery.mobile.service.oder.IOdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 19:54　<br/>
 * <p>
 *            订单测试类
 * <p>
 * @email 346905702@qq.com
 */
public class OdersServiceImplTest extends TestBaseMapper {

    @Autowired
    IOdersService odersSer;
    @Test
    public void testAddOder() throws Exception {

        Date d = new Date();
        byte s = 1;
        Oders o = new Oders();
        o.setCommodityId(1l);
        o.setPayModeId(s);
        o.setPayState(s);
        o.setUserAccountId(1l);
        o.setSubmitDate(d);
        boolean b = odersSer.addOder(o);
        Assert.isTrue(b,"成功添加订单！！！！");
    }
    @Test
    public void testSelectOder() throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeStart=sdf.parse("2016-12-22 20:06:02").getTime();
        Date date = new Date(timeStart);
        Oders oders = odersSer.selectOder(1l, date);

    }

}