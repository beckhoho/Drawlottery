package com.hudongwx.drawlottery.mobile.utils;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.NotificationPrizeMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/29 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/29 <br/>
 * <p>
 * service工具类
 * <p>
 * @email 294786949@qq.com
 */
public class ServiceUtils {
    /**
     * 将集合处理成分页数据
     *
     * @param list 直接从数据库的获取到的list集合
     * @param page 查询的当前page
     * @param <T>  泛型
     * @return
     */
    public static <T extends Object> List<T> getPageList(List<T> list, Integer page) {
        List<T> newList = new ArrayList<>();
        if (null == page || page <= 0)
            page = 1;
        int startPosition = Settings.PAGE_LOAD_SIZE_10 * (page - 1);
        int endPosition = Settings.PAGE_LOAD_SIZE_10 + startPosition;
        int maxPosition = endPosition >= list.size() ? list.size() : endPosition;
        for (int i = startPosition; i < maxPosition; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

    public static Integer getResidualLotterySeconds(Commoditys comm) {
        if (comm.getStateId() == Settings.COMMODITY_STATE_ON_LOTTERY || (null != comm.getSellOutTime() && comm.getSellOutTime() != 0)) {
            long nowTime = new Date().getTime();
            long sellOutTime = null == comm.getSellOutTime() ? 0 : comm.getSellOutTime();
            long endTime = sellOutTime + Settings.LOTTERY_ANNOUNCE_TIME_INTERVAL;
            if (nowTime < endTime)
                return (int) ((endTime - nowTime) / 1000);
        }
        return 0;
    }

    public static boolean insertNotificationPrizeInfo(NotificationPrizeMapper npMapper, Commoditys comms, LotteryInfo lotteryInfo, User user) {
        if (!npMapper.selectIdByCommId(comms.getId()).isEmpty() )
            return false;
        Long acc = lotteryInfo.getUserAccountId();
        NotificationPrize nPrize = new NotificationPrize();
        nPrize.setAccountId(acc);
        nPrize.setCommodityId(comms.getId());
        nPrize.setLuckAccountId(acc);
        nPrize.setLuckCodesId(lotteryInfo.getLotteryId());
        nPrize.setNoticeTitle("商品中奖消息");
        nPrize.setNoticeContent("恭喜【" + user.getNickname() + "】获得【" + comms.getName()+"】!");
        Date endDate = lotteryInfo.getEndDate();
        nPrize.setOnPrizeDate(endDate==null?System.currentTimeMillis():endDate.getTime());
        nPrize.setState(0);
        nPrize.setSendDate(System.currentTimeMillis());
        return npMapper.insertNotificationPrize(nPrize) > 0;
    }


}
