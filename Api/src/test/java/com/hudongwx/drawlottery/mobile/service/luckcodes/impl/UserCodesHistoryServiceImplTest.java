package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserCodesHistoryService;
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
 * 创建　kiter　2016/12/24 12:00　<br/>
 * <p>
 *            用户历史幸运码测试类
 * <p>
 * @email 346905702@qq.com
 */
public class UserCodesHistoryServiceImplTest extends TestBaseMapper {

    @Autowired
    IUserCodesHistoryService usercodesHistory;
    @Test
    public void testAddToHistory() throws Exception {
        UserCodesHistory userch = new UserCodesHistory();
        userch.setId(1l);
        userch.setUserAccountId(1l);
        userch.setCommodityId(1l);
        //userch.setUserLuckCodeId(2l);
        boolean b = usercodesHistory.addToHistory(userch);
        Assert.assertTrue(b);

        //测试完成    可用
    }

    @Test
    public void testSelectByUserAccount() throws Exception {
        List<UserCodesHistory> userCodesHistories = usercodesHistory.selectByCommodId(1l);
        Assert.assertNotNull(userCodesHistories);

        //测试完成   可用

    }

    @Test
    public void testSelectByCommodId() throws Exception {

        List<UserCodesHistory> histories = usercodesHistory.selectByCommodId(2l);
        Assert.assertNotNull(histories);

        //测试完成   可用
    }

    @Test
    public void testSelectAll() throws Exception {
        List<UserCodesHistory> histories = usercodesHistory.selectAll();
        Assert.assertNotNull(histories);


        //测试完成  可用
    }

}