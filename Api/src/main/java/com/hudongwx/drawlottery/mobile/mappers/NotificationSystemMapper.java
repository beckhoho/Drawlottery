package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.NotificationSystem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
 * 创建　kiter　2017/1/3 14:22　<br/>
 * <p>
 *      系统通知mapper
 * <p>
 * @email 346905702@qq.com
 */
public interface NotificationSystemMapper extends BaseMapper<NotificationSystem>{

    //查看用户所有系统通知
    List<NotificationSystem> selectAllSystemNotice(@Param("accountId") Long accountId);

    //查询用户未读信息条数
    Integer countUnreadMsg(@Param("UserId") Long UserId);

    //查询往期信息
    List<NotificationSystem> selectLimitTen(@Param("UserId") Long UserId,@Param("msgId") String msgId);

    //更改消息阅读状态
    Integer updateStateById(@Param("Id")Long Id);
}
