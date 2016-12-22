package com.hudongwx.drawlottery.mobile.service.luckcodes;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.entitys.User;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:33　<br/>
 * <p>
 *  幸运码service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface ILuckCodesService {
    //添加幸运码
    boolean addLuckCode(LuckCodes codes);

    //查询幸运码
    LuckCodes select(User user, Commoditys commd);

    //删除幸运码
    boolean delete(LuckCodes codes);
}
