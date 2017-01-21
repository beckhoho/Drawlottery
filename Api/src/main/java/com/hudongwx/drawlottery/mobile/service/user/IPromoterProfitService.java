package com.hudongwx.drawlottery.mobile.service.user;

import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/20 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/20 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public interface IPromoterProfitService {

    Map<String, Object> selectPromoterProfitInfo(Long accountId, Long lastTime);
}
