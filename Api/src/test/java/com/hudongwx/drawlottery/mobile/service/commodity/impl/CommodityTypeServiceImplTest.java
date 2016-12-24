package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/23 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/23 15:36　<br/>
 * <p>
 *             商品类型测试类；
 * <p>
 * @email 346905702@qq.com
 */
public class CommodityTypeServiceImplTest extends TestBaseMapper {

    @Autowired
    ICommodityTypeService commodType;
    @Test
    public void testAddType() throws Exception {
        CommodityType ct = new CommodityType();
        ct.setName("手机");
        ct.setState(1);
        boolean b = commodType.addType(ct);
        Assert.assertTrue(b,"保存成功！！！！");

        //测试成功  可用
    }

    @Test
    public void testHideType() throws Exception {
        boolean b = commodType.hideType(0);
        Assert.assertTrue(b);


        //测试完成  可用~
    }

    @Test
    public void testUpdateType() throws Exception {
        CommodityType ct = new CommodityType();
        ct.setId(1);
        ct.setName("电脑");
        ct.setState(1);
        commodType.updateType(ct);

        //测试完成    可用~
    }

    @Test
    public void testSelectType() throws Exception {
        CommodityType typeName = commodType.selectType("电脑");
        Assert.assertNotNull(typeName,"查询成功!!!!");

        //测试完成  可用~
    }

}