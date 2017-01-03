package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.SignIn;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/3 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/3 11:16　<br/>
 * <p>
 *          签到service
 * <p>
 * @email 346905702@qq.com
 */
public interface ISignInService {

    //添加签到
    boolean addSignIn(SignIn in);

    //查看签到
    List<Map<String,Object>> selectUserSignIn(Long accountId);
}
