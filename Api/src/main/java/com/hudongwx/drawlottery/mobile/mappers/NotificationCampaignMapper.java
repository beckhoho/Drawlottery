package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.NotificationCampaign;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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

    @Select("select  distinct * from t_notification_activity GROUP BY notice_title")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "notice_title", property = "noticeTitle"),
            @Result(column = "notice_url", property = "noticeUrl"),
            @Result(column = "notice_cover_img_url", property = "noticeCoverImgUrl"),
            @Result(column = "send_date", property = "sendDate")
    })
    List<NotificationCampaign> selectTitle();

}
