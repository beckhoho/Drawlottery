package com.hudongwx.drawlottery.mobile.service.luckcodes;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;

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
 * 创建　kiter　2016/12/22 11:08　<br/>
 * <p>
 *      用户历史幸运码service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface IUserCodesHistoryService {

    //将幸运码添加到历史
    boolean addToHistory(UserCodesHistory userhis);

    //通过用户名查看历史幸运码
    List<UserCodesHistory> selectByUserAccount(Long accounId);

    //通过商品查看历史幸运码
    List<UserCodesHistory> selectByCommodId(Long commodId);

    //查看所有历史幸运码
    List<UserCodesHistory> selectAll();

}
