package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/23 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/23 10:20　<br/>
 * <p>
 *             商品测试类
 * <p>
 * @email 346905702@qq.com
 */
public class CommodityServiceImplTest extends TestBaseMapper {

    @Autowired
    ICommodityService commodService;

    @Test
    public void testAddCommodity() throws Exception {
        Date date = new Date();

        Commoditys com = new Commoditys();
        com.setName("IPhone7");
        com.setDesc(null);
        com.setCommodityTypeId(5);
        com.setGenre(5);
        com.setBuyCurrentNumber(null);
        com.setBuyTotalNumber(4544);
        com.setStartTime(null);
        com.setLuckCodeId(5);
        com.setState(5);
        com.setRoundTime("123321");
        com.setCoverImgId(5);
        com.setAutoRound(5);
        commodService.addCommodity(com);
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testSelect() throws Exception {

        Commoditys select = commodService.selectByid(1l);
        System.out.println("商品名："+select.getName()+";商品详情："+select.getDesc());

        //自己写的sql语句不会出错，但是用原生的  就会出现sql语句出错！
    }

    @Test
    public void testSelectTypeAll() throws Exception {

        List<Commoditys> list = commodService.selectTypeAll("手机");
        if(list!=null&&list.size()!=0){
            System.out.println("查询成功！！！数量为"+list.size());
        }
        else System.out.println("查询失败！！！");
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testSelectCount() throws Exception {
        int i = commodService.selectCount(1);
        System.out.println("查询成功数量为:"+i);

    }

    @Test
    public void testSelectPaging() throws Exception {
        List<Commoditys> commodityses = commodService.selectPaging(1, 0, 1);
        if(commodityses!=null&&commodityses.size()!=0){
            System.out.println("查询成功！！数据数量为："+commodityses.size());
        }
        else{
            System.out.println("查询失败！！");
        }
    }


}