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
    public List<Oders> selectByUserAccount(Long userAccount) {
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
    public boolean update(Oders oders) {
        int i = odersMapper.updateByPrimaryKeySelective(oders);
        if(i>0){
          return true;
        }
        return false;
    }
}
