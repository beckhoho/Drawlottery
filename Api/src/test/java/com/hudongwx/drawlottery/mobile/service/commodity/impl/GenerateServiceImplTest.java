package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.IGenerateService;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Drawlottery.
 * Date: 2017/1/23 0023
 * Time: 17:23
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class GenerateServiceImplTest extends TestBaseMapper {
    @Autowired
    IGenerateService service;

    @Test
    public void testKeepRound() throws Exception{
        service.keepRound(3);
    }
}
