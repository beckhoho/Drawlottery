package com.hudongwx.drawlottery.mobile.service.order.impl;

import com.hudongwx.drawlottery.mobile.entitys.OrdersCommoditys;
import com.hudongwx.drawlottery.mobile.mappers.OrdersCommoditysMapper;
import com.hudongwx.drawlottery.mobile.service.order.IOrdersCommoditysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/6 13:59　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class OrdersCommoditysServiceImpl implements IOrdersCommoditysService {

    @Autowired
    OrdersCommoditysMapper mapper;

    @Override
    public boolean addOrdersCommodity(OrdersCommoditys commoditys) {
        return mapper.insert(commoditys)>0;
    }

    @Override
    public boolean deleteOrdersCommodity(OrdersCommoditys commoditys) {
        return mapper.delete(commoditys)>0;
    }

    @Override
    public boolean updateOrdersCommodity(OrdersCommoditys commoditys) {
        Example e = new Example(OrdersCommoditys.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("id",commoditys.getId());
        return mapper.updateByExampleSelective(commoditys,e)>0;
    }

    @Override
    public List<OrdersCommoditys> selectCommoditys(Long ordersId) {
        OrdersCommoditys com =  new OrdersCommoditys();
        com.setOrdersId(ordersId);
        return mapper.select(com);
    }

}
