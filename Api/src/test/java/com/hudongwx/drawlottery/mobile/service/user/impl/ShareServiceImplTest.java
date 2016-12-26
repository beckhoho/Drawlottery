package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

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
 * 创建　kiter　2016/12/26 18:06　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class ShareServiceImplTest extends TestBaseMapper {

    @Autowired
    IShareService mapper;
    @Test
    public void testAddShare() throws Exception {

        Share s = new Share();
        s.setUserAccountId(2l);
        s.setCommodityId(2l);
        s.setIssueDate(new Date());
        s.setParticulars("url1");
        boolean b = mapper.addShare(s);
        Assert.assertTrue(b);

    }

    @Test
    public void testDeleteShare() throws Exception {
        Share s = new Share();
        s.setId(1l);
        boolean b = mapper.deleteShare(s.getId());
        Assert.assertTrue(b);
    }

    @Test
    public void testSelectShare() throws Exception {
//        List<Share> shares = mapper.selectShare(1l);
//        for (Share s : shares){
//            System.out.println(s.getIssueDate()+"; "+s.getParticulars());
//        }
    }

}