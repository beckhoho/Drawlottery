package com.hudongwx.drawlottery.mobile.utils;

import com.alibaba.fastjson.JSONArray;
import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.UserLuckCodesMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 11 on 2017/1/14.
 */
public class LotteryUtils {
    //    时间格式化样式
    private static String pattern = "yyyy年MM月dd日 HH:mm:ss:SSS";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

    public static LotteryInfo raffle(UserLuckCodesMapper luckCodesMapper, UserMapper userMapper, Commodity commodity) {
        Calendar calendar = Calendar.getInstance();
        //查询最后购买的五十条商品信息
        List<UserLuckCodes> userLuckCodes = luckCodesMapper.selectByBuyDateDesc();
        //开奖信息
        LotteryInfo lotteryInfo = new LotteryInfo();
        //最后五十商品毫秒和
        long sumDate = 0;
        //时分秒毫秒拼接值
        String subTime;
        JSONArray array = new JSONArray();
        for (UserLuckCodes userLuckCode : userLuckCodes) {
            Long buyDate = userLuckCode.getBuyDate();
            calendar.setTimeInMillis(buyDate);
            User user = userMapper.selectById(userLuckCode.getUserAccountId());
            String date = formatDate(buyDate);
            //计算毫秒值
            subTime = substringTime(date);
            sumDate+=Integer.valueOf(subTime);
            array.add(date + " " + subTime + " : " + user.getNickname());
        }
        lotteryInfo.setCommodityId(commodity.getId());//商品id
        lotteryInfo.setBuyNum(commodity.getBuyCurrentNumber());//总购买数
        lotteryInfo.setLotteryInfo(array.toString());//具体每条信息json
        lotteryInfo.setSumDate(sumDate);//五十和
        /*
            计算中奖id
         */
        long lotteryId = (sumDate % commodity.getBuyCurrentNumber()) + 100000001;
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

    /**
     * 拼接时分秒数
     *
     * @return
     */
    private static String substringTime(String date) {
        String temp = date.split(" ")[1].trim();
        String res = "";
        if(temp!=null&&!temp.equals("")){
            for (int i = 0; i < temp.length(); i++) {
                if(temp.charAt(i)>=48&&temp.charAt(i)<=57){
                    res+=temp.charAt(i);
                }
            }
        }
        return res;
    }
}
