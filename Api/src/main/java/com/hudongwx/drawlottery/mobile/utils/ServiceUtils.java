package com.hudongwx.drawlottery.mobile.utils;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodesMapper;

import java.text.SimpleDateFormat;
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
        int startPosition = Settings.PAGE_LOAD_SIZE * (page - 1);
        int endPosition = Settings.PAGE_LOAD_SIZE + startPosition;
        int maxPosition = endPosition >= list.size() ? list.size() : endPosition;
        for (int i = startPosition; i < maxPosition; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

    public static Integer getResidualLotteryMinute(Commoditys comm) {
        if (comm.getStateId() != Settings.COMMODITY_STATE_ON_LOTTERY)
            return 0;
        long nowTime = new Date().getTime();
        long sellOutTime = null == comm.getSellOutTime() ? 0 : comm.getSellOutTime();
        long endTime = sellOutTime + Settings.LOTTERY_ANNOUNCE_TIME_INTERVAL;
        if (nowTime < endTime) {
            return (int) ((endTime - nowTime) / 1000);
        }
        return 0;
    }

    public static void createLuckCode(LuckCodesMapper mapper, CommoditysMapper commMapper, Long commId, boolean rebuild) {
        //期数+总购买人次 例：201707101532000001
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        int total = commMapper.selectByPrimaryKey(commId).getBuyTotalNumber();
        LuckCodes lc = new LuckCodes();
        lc.setCommodityId(commId);
        int size = mapper.select(lc).size();
        if ((size >= total && rebuild) || size < total) {
            mapper.delete(lc);
        } else if (size >= total && !rebuild) {
            return;
        }
        for (int i = 1; i <= total; i++) {
            LuckCodes luckCodes = new LuckCodes();
            luckCodes.setCommodityId(commId);
            int tl = String.valueOf(total).length();
            int il = String.valueOf(i).length();
            StringBuffer s = new StringBuffer();
            for (int j = 0; j < tl - il; j++) {
                s.append("0");
            }
            s.append(i);
            //luckCodes.setLockCode(sdf.format(date) + s);
            luckCodes.setState(0);
            mapper.insert(luckCodes);
        }
    }
}
