package com.hudongwx.drawlottery.mobile.service.user;

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
 * 签到service
 * <p>
 * @email 346905702@qq.com
 */
public interface ISignInService {

    /**
     * 用户签到
     *
     * @param accountId
     * @return
     */
    boolean addUserSignIn(Long accountId);

    /**
     * 查询签到
     *
     * @param accountId
     * @return
     */
    List<Map<String, Object>> selectUserSign(Long accountId);
}
