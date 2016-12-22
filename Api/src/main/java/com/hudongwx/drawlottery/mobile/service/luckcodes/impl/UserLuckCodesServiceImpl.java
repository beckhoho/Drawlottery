package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserLuckCodesService;
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
 * 创建　kiter　2016/12/22 11:05　<br/>
 * <p>
 *  用户幸运码service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class UserLuckCodesServiceImpl implements IUserLuckCodesService{

    @Override
    public boolean addNewLockCodes(UserLuckCodes userLCodes) {
        return false;
    }

    @Override
    public UserLuckCodes select(User user, Commoditys commoditys) {
        return null;
    }
}
