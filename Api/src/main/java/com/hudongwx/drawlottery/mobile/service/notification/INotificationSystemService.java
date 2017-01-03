package com.hudongwx.drawlottery.mobile.service.notification;

import com.hudongwx.drawlottery.mobile.entitys.NotificationSystem;

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
 * 创建　kiter　2017/1/3 15:08　<br/>
 * <p>
 *      系统通知service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface INotificationSystemService {

    //添加系统通知
    boolean addSystemNotice(NotificationSystem system);

    //删除系统通知
    boolean deleteNotice(NotificationSystem system);

    //查看系统通知
    List<Map<String,Object>> selectAllSystemNotice(Long accountId);
}
