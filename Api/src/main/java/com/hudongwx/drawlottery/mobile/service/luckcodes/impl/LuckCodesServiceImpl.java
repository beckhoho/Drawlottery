package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
import org.springframework.stereotype.Service;

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
    @Override
    public boolean addLuckCode(LuckCodes codes) {
        return false;
    }

    @Override
    public LuckCodes select(User user, Commoditys commd) {
        return null;
    }

    @Override
    public boolean delete(LuckCodes codes) {
        return false;
    }
}
