package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testSelectByUserAccountId() throws Exception {
        List<Map<String, Object>> mapList = shareService.selectByUserAccountId(10000L, 0L);
        System.out.println(mapList.size());
    }

    @Test
    public void testSelectAll() throws Exception {

    }

    @Test
    public void testSelectByCommId() throws Exception {

    }

    @Autowired
    IShareService shareService;
    @Test
    public void testAddShare() throws Exception {
        shareService.addShare(10000L, 20L, "111", new ArrayList<MultipartFile>());

    }

    @Test
    public void testDeleteShare() throws Exception {
        Share s = new Share();
        s.setId(1l);
        boolean b = shareService.deleteShare(s.getId());
        Assert.assertTrue(b);
    }

    @Test
    public void testSelectShare() throws Exception {
//        List<Share> shares = shareMapper.selectShare(1l);
//        for (Share s : shares){
//            System.out.println(s.getIssueDate()+"; "+s.getParticulars());
//        }
    }

}