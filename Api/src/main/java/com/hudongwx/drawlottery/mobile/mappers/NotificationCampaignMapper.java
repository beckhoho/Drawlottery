package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.NotificationCampaign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/3 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/3 14:28　<br/>
 * <p>
 * 活动通知mapper
 * <p>
 * @email 346905702@qq.com
 */
public interface NotificationCampaignMapper extends BaseMapper<NotificationCampaign> {

    //查询所有活动通知
    List<NotificationCampaign> selectTitle();

    //查询用户未读信息条数
    Integer countUnreadMsg(@Param("UserId") Long UserId);

    //查询往期信息
    List<NotificationCampaign> selectLimitTen(@Param("UserId") Long UserId,@Param("msgId") String msgId);

    //更改消息阅读状态
    Integer updateStateById(@Param("Id")Long Id);

    List<NotificationCampaign> selectNews();
}
