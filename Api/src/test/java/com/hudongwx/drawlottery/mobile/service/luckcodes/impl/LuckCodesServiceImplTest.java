package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Approve;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodeTemplate;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodeTemplateMapper;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodesMapper;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/24 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/24 11:02　<br/>
 * <p>
 *             幸运码   测试类
 * <p>
 * @email 346905702@qq.com
 */
public class LuckCodesServiceImplTest extends TestBaseMapper {

    @Autowired
    LuckCodesMapper mapper;
    @Autowired
    LuckCodeTemplateMapper templateMapper;
    @Test
    public void testCreateLuckCode() throws Exception {
        //生成商品LuckCode;
        LuckCodes luckCodes = new LuckCodes();
        List<LuckCodes> select = mapper.select(luckCodes);
        for (LuckCodes l : select){
            LuckCodeTemplate template = templateMapper.selectById(l.getLuckCodeTemplateId());
            LuckCodes luckCodes1 = new LuckCodes();
            luckCodes1.setId(l.getId());
            //luckCodes1.setLockCode(template.getLuckCode());
            mapper.updateByPrimaryKeySelective(luckCodes1);
        }
    }

    @Autowired
    ILuckCodesService luckService;

    @Test
    public void testSelect() throws Exception {
        List<LuckCodes> select = luckService.select(1l);
        Assert.assertNotNull(select);

        //测试完成   可用
    }

    @Test
    public void testDelete() throws Exception {

        boolean delete = luckService.delete(1l);
        Assert.assertTrue(delete);

        //测试完成  可用
    }

}