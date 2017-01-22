package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.utils.Settings;
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
    public void testTestPage() throws Exception {
        List<Commoditys> list = commoditysMapper.testPage(Settings.COMMODITY_STATE_ON_SALE, Settings.COMMODITY_ORDER_POPULARITY, 0L, Settings.PAGE_LOAD_SIZE_10);
        System.out.println(list.size());

    }

    @Test
    public void testSelectByKey() throws Exception {

    }

    @Test
    public void testSelectPaging() throws Exception {

    }

    @Test
    public void testSelectTypeCount() throws Exception {

    }

    @Test
    public void testSelectType() throws Exception {

    }

    @Test
    public void testSelectByTemp1() throws Exception {

    }

    @Test
    public void testSelectByTemp2() throws Exception {

    }

    @Test
    public void testSelectByTemp3() throws Exception {

    }

    @Test
    public void testSelectByTemp4() throws Exception {

    }

    @Test
    public void testSelectOnLottery() throws Exception {

    }

    @Test
    public void testSelectTopTenOnLottery() throws Exception {

    }

    @Test
    public void testSelectByType() throws Exception {

    }

    @Test
    public void testSelectByName() throws Exception {

    }

    @Test
    public void testSelectByTypeAndName() throws Exception {

    }

    @Test
    public void testSelectByGuess() throws Exception {

    }

    @Test
    public void testSelectHeight() throws Exception {

    }

    @Test
    public void testSelectHasTheLotteryComm() throws Exception {

    }

    @Test
    public void testUpdateCommState() throws Exception {

    }

    @Test
    public void testUpdateById() throws Exception {

    }

    @Test
    public void testSelectMaxRoundTime() throws Exception {

    }

    @Test
    public void testSelectCommNameByCommId() throws Exception {
        List<Long>longList=new ArrayList<>();
        longList.add(43L);
        List<String> list = commoditysMapper.selectCommNameListByCommId(longList);
        for (String s : list) {
            System.out.println("name:"+s);
        }
    }

}