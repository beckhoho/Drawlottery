package com.hudongwx.drawlottery.mobile.service.notification;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/27 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/27 17:35　<br/>
 * <p>
 * 中奖通知service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface INotificationPrizeService {

    /**
     * 查询用户中奖消息（APP首页提示）
     *
     * @param accountId
     * @return
     */
    List<Map<String, Object>> selectUserPrizeNotify(Long accountId);

}
