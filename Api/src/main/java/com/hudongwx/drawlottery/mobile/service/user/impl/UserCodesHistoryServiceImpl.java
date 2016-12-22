package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;
import com.hudongwx.drawlottery.mobile.service.user.IUserCodesHistoryService;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:27　<br/>
 * <p>
 *      用户历史幸运码service实现类
 * <p>
 * @email 346905702@qq.com
 */
public class UserCodesHistoryServiceImpl implements IUserCodesHistoryService {
    @Override
    public boolean addToHistory(UserCodesHistory userhis) {
        return false;
    }

    @Override
    public UserCodesHistory select(User user, Commoditys commod, LuckCodes luckCodes) {
        return null;
    }
}
