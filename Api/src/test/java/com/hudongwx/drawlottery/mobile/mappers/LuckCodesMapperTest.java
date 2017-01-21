package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/21 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/21 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class LuckCodesMapperTest extends TestBaseMapper {
    @Resource
    LuckCodesMapper mapper;

    @Test
    public void testSelectUserCommLuckCode() throws Exception {
        List<String> list = mapper.selectUserCommLuckCode(100000L, 2L, "0", 10);
        for (String s : list) {
            System.out.println(s);
        }
    }

}