package com.hudongwx.drawlottery.mobile.utils;

import com.alibaba.fastjson.JSONArray;
import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.LotteryInfoMapper;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodeTemplateMapper;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodesMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;

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
    @Async
    public static LotteryInfo raffle(LuckCodeTemplateMapper templateMapper, LuckCodesMapper luckCodesMapper, LotteryInfoMapper lotteryInfoMapper,UserMapper userMapper, Commoditys commodity) {
        Calendar calendar = Calendar.getInstance();
        //查询最后购买的五十条商品信息
        List<LuckCodes> userLuckCodes = luckCodesMapper.selectByBuyDateDesc();
        //开奖信息
        LotteryInfo lotteryInfo = new LotteryInfo();
        //最后五十商品毫秒和
        long sumDate = 0;
        //时分秒毫秒拼接值
        String subTime;
        JSONArray array = new JSONArray();
        for (LuckCodes userLuckCode : userLuckCodes) {
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
        lotteryInfo.setBuyNum(commodity.getBuyTotalNumber());//总购买数
        //lotteryInfo.setLotteryInfo(array.toString());//具体每条信息json
        lotteryInfo.setSumDate(sumDate);//五十和


        /*
            计算中奖id
         */
        long lotteryId = (sumDate % commodity.getBuyTotalNumber()) + 10000001;
        lotteryInfo.setLotteryId(lotteryId);
        LuckCodeTemplate template = templateMapper.selectByCode(lotteryId + "");
        LuckCodes codes = luckCodesMapper.selectBytemplate(template.getId(), commodity.getId());
        lotteryInfo.setUserAccountId(codes.getUserAccountId());
        lotteryInfoMapper.insert(lotteryInfo);//将开奖信息写入数据库
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
