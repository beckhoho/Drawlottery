package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityImg;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/23 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/23 20:29　<br/>
 * <p>
 * 商品图片测试类
 * <p>
 * @email 346905702@qq.com
 */
public class CommodityImgServiceImlplTest extends TestBaseMapper {

    @Autowired
    ICommodityImgService commodImg;

    @Test
    public void testAddImage() throws Exception {
        CommodityImg ci = new CommodityImg();
        ci.setUrl("de.com");
        ci.setAddTime(System.currentTimeMillis());
        ci.setState(1);
        boolean b = commodImg.addImage(ci);
        Assert.assertTrue(b);

        //测试完成   可用
    }

    @Test
    public void testDeleteImage() throws Exception {
        boolean b = commodImg.deleteImage(1l);
        Assert.assertTrue(b);

        //测试完成  可用
    }

    @Test
    public void testSelectImg() throws Exception {
        CommodityImg commodityImg = commodImg.selectImg(1l);
        Assert.assertNotNull(commodityImg, "不是空对象");

        //测试完成  可用

    }

}