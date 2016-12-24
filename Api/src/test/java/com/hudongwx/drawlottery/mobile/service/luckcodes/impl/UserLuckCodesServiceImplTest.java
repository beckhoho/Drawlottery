package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserLuckCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/24 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/24 11:21　<br/>
 * <p>
 *             用户幸运码测试类
 * <p>
 * @email 346905702@qq.com
 */
public class UserLuckCodesServiceImplTest extends TestBaseMapper {

    @Autowired
    IUserLuckCodesService userLuckService;
    @Test
    public void testAddNewLockCodes() throws Exception {
        UserLuckCodes userL = new UserLuckCodes();
        userL.setCommodityId(1l);
        userL.setLockCodeId(2l);
        userL.setUserAccountId(1l);
        boolean b = userLuckService.addNewLockCodes(userL);
        Assert.assertTrue(b);

        //测试完成  可用

    }

    @Test
    public void testSelect() throws Exception {
        List<UserLuckCodes> select = userLuckService.selectByUserId(1l);
        Assert.assertNotNull(select);

        //测试完成  可用

    }

    @Test
    public void testDelete() throws Exception {

        boolean delete = userLuckService.delete(1l);
        Assert.assertTrue(delete);

        //测试成功   可用
    }

}