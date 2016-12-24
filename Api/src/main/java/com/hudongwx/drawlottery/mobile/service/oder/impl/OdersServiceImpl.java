package com.hudongwx.drawlottery.mobile.service.oder.impl;

import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.mappers.OrdersMapper;
import com.hudongwx.drawlottery.mobile.service.oder.IOdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 19:50　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class OdersServiceImpl implements IOdersService {

    @Autowired
    OrdersMapper odersMapper;

    @Override
    public boolean addOder(Orders oders) {

        int insert = odersMapper.insert(oders);

        if(insert>0){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public List<Orders> selectByUserAccount(Long userAccount) {
        return odersMapper.selectByUserAccount(userAccount);
    }

    @Override
    public boolean deleteOder(Long id) {
        int i = odersMapper.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Orders oders) {
        int i = odersMapper.updateByPrimaryKeySelective(oders);
        if(i>0){
          return true;
        }
        return false;
    }
}
