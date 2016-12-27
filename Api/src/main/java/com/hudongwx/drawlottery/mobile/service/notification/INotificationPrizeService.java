package com.hudongwx.drawlottery.mobile.service.notification;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;

import java.util.List;

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
 *          中奖通知service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface INotificationPrizeService {

    //添加中奖通知
    boolean addPrizeNotification(NotificationPrize noPrize);

    //查看所有中奖通知
    List<NotificationPrize> selectAll();

   //通过用户accountID查看中奖通知
   List<NotificationPrize> selectByAccount(Long account, Long commodId);

    //删除用户通知
    boolean delete(Long id);

    //查看最新的中奖通知
    List<NotificationPrize> selectByNew();

}
