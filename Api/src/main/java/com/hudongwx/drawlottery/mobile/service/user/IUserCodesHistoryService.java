package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:08　<br/>
 * <p>
 *      用户历史幸运码service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface IUserCodesHistoryService {

    //将幸运码添加到历史
    boolean addToHistory(UserCodesHistory userhis);

    //查看历史幸运码
    UserCodesHistory select(User user,Commoditys commod,LuckCodes luckCodes);


}
