package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/16 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/16 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class CommoditysMapperTest extends TestBaseMapper {

    @Resource
    CommoditysMapper commoditysMapper;
    @Test
    public void testSelectCommNameByCommId() throws Exception {
        List<Long>longList=new ArrayList<>();
        longList.add(43L);
        List<String> list = commoditysMapper.selectCommNameByCommId(longList);
        for (String s : list) {
            System.out.println("name:"+s);
        }
    }

}