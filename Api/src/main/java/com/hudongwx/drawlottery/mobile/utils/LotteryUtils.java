package com.hudongwx.drawlottery.mobile.utils;

import com.alibaba.fastjson.JSONArray;
import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.UserLuckCodesMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 11 on 2017/1/14.
 */
public class LotteryUtils {
    //    时间格式化样式
    private static String pattern = "yyyy年MM月dd日 HH:mm:ss:SS";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

    public static LotteryInfo raffle(UserLuckCodesMapper luckCodesMapper, UserMapper userMapper, Commodity commodity) {
        //查询最后购买的五十条商品信息
        List<UserLuckCodes> userLuckCodes = luckCodesMapper.selectByBuyDateDesc();
        //开奖信息
        LotteryInfo lotteryInfo = new LotteryInfo();
        //最后五十商品毫秒和
        long sumDate = 0;
        JSONArray array = new JSONArray();
        for (UserLuckCodes userLuckCode : userLuckCodes) {
            Long buyDate = userLuckCode.getBuyDate();
            sumDate += buyDate;
            User user = userMapper.selectById(userLuckCode.getUserAccountId());
            String date = formatDate(buyDate);
            array.add(date+" "+buyDate+" : "+user.getNickname());
        }
        lotteryInfo.setCommodityId(commodity.getId());//商品id
        lotteryInfo.setBuyNum((long)commodity.getBuyCurrentNumber());//总购买数
        lotteryInfo.setLotteryInfo(array.toString());//具体每条信息json
        lotteryInfo.setSumDate(sumDate);//五十和
        /*
            计算中奖id
         */
        long lotteryId = sumDate%commodity.getBuyCurrentNumber()+10000001;
        lotteryInfo.setLotteryId(lotteryId);
        return lotteryInfo;
    }

    /**
     * 时间格式化
     *
     * @param buyDate
     * @return
     */
    private static String formatDate(Long buyDate) {
        Date date = new Date(buyDate);
        return dateFormat.format(date);
    }
}
