package com.hudongwx.drawlottery.mobile.service.notification;

import com.hudongwx.drawlottery.mobile.entitys.NotificationCampaign;

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
 * 创建　kiter　2017/1/3 15:13　<br/>
 * <p>
 * 活动通知service
 * <p>
 * @email 346905702@qq.com
 */
public interface INotificationCampaignService {

    //添加活动通知
    boolean addNoticeActivity(NotificationCampaign activity);

    //删除通知
    boolean deleteNotice(NotificationCampaign activity);

    //查看活动通知
    List<Map<String, Object>> selectAllActivity(Long accountId);

    //查询活动
    public List<NotificationCampaign> selectNews();
}
