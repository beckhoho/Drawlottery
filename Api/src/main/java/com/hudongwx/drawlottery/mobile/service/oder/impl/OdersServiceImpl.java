package com.hudongwx.drawlottery.mobile.service.oder.impl;

import com.hudongwx.drawlottery.mobile.entitys.Oders;
import com.hudongwx.drawlottery.mobile.mappers.OdersMapper;
import com.hudongwx.drawlottery.mobile.service.oder.IOdersService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    OdersMapper odersMapper;

    @Override
    public boolean addOder(Oders oders) {

        int insert = odersMapper.insert(oders);

        if(insert>0){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public Oders selectOder(Long userAccount, Date submitDate) {
        Oders o = new Oders();
        o.setSubmitDate(submitDate);
        o.setUserAccountId(userAccount);
        List<Oders> select = odersMapper.selectAll();
        if(select!=null&&select.size()!=0){
            for (Oders od: select){
                if(od.getUserAccountId()==userAccount&&od.getSubmitDate()==submitDate){
                    return od;
                }
            }
        }
        return null;
    }

    @Override
    public boolean deleteOder(Long id) {
        return false;
    }

    @Override
    public boolean update(Oders oders, Long id) {
        return false;
    }
}
