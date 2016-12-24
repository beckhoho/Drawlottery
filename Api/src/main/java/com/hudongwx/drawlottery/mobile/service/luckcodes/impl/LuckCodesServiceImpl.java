package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodesMapper;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
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
 * 创建　kiter　2016/12/22 11:40　<br/>
 * <p>
 *      幸运码service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class LuckCodesServiceImpl implements ILuckCodesService{

    @Autowired
    LuckCodesMapper luckCodes;
    @Override
    public boolean addLuckCode(LuckCodes codes) {
        int insert = luckCodes.insert(codes);
        if(insert>0){
            return true;
        }
        return false;
    }

    @Override
    public List<LuckCodes> select(Long commodId) {
        return this.luckCodes.selectCommodCodes(commodId);
    }

    @Override
    public boolean delete(Long id) {
        int i = luckCodes.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }
        return false;
    }

}
