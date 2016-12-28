package com.hudongwx.drawlottery.mobile.service.images.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Images;
import com.hudongwx.drawlottery.mobile.service.images.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
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
 * 创建　kiter　2016/12/27 14:56　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class ImagesServiceImplTest extends TestBaseMapper {
    @Autowired
    ImagesService image;
    @Test
    public void testAddImage() throws Exception {
        Images i = new Images();
        i.setImgGenre(1);
        i.setImgUrl("http://img.25pp.com/uploadfile/app/icon/20160606/1465217533244712.jpg");
        i.setDepict("高中奖率");
        //i.setSkipUrl("http://www.yiqiandai.com/fileStore/2/2015/10/19/2804.jpg");
        i.setState(1);
        boolean b = image.addImage(i);
        Assert.assertTrue(b);
    }

    @Test
    public void testDeleteImage() throws Exception {

        boolean b = image.deleteImage(1l);
        Assert.assertTrue(b);
    }

    @Test
    public void testSelectIcon() throws Exception {
//        List<Images> images = image.selectIcon();
//        Assert.assertNotNull(images);
//        System.out.println(images.get(0).getImgUrl());
    }

    @Test
    public void testSelectAdvert() throws Exception {
//        List<Images> images = image.selectAdvert();
//        Assert.assertNotNull(images);
//        System.out.println(images.get(0).getImgUrl());
    }

    @Test
    public void testUpdate() throws Exception {

        Images i = new Images();
        i.setId(1l);
        i.setImgGenre(2);
        boolean b = image.update(i);
        Assert.assertTrue(b);
    }

}