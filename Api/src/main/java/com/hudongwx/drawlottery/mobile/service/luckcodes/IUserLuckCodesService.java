package com.hudongwx.drawlottery.mobile.service.luckcodes;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;

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
 * 创建　kiter　2016/12/22 10:29　<br/>
 * <p>
 *     用户幸运码service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface IUserLuckCodesService {

    //添加用户幸运码数据
    boolean addNewLockCodes(UserLuckCodes userLCodes);

    //查看用户幸运码
    List<UserLuckCodes> select(Long accountId);

    //删除用户幸运码
    boolean delete(Long id);

}
