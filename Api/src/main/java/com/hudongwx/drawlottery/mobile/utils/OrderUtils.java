package com.hudongwx.drawlottery.mobile.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.OrderFormData;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.web.pay.alipay.config.AlipayConfig;
import com.hudongwx.drawlottery.mobile.web.pay.alipay.sign.RSA;
import com.hudongwx.drawlottery.mobile.web.pay.alipay.util.AlipayCore;
import com.hudongwx.drawlottery.mobile.web.pay.alipay.util.UtilDate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/18 0018 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/18 0018　<br/>
 * <p>
 *     订单工具类
 * <p>
 * @email 294786949@qq.com
 */
public final class OrderUtils {

    /**
     * 生成订单id:纳秒+随机数4位=18位数
     * @return
     */
    public final static Long getOrderId(){
        StringBuffer buffer = new StringBuffer(18);
        ThreadLocalRandom current = ThreadLocalRandom.current();
        buffer.append(System.currentTimeMillis());
        buffer.append(current.nextInt(10000,99999));
        return Long.valueOf(buffer.toString());
    }

    /**
     * 判断订单收款方是否为自己
     * @param sellerId
     * @return
     */
    public final static boolean isMySellerid(String sellerId){
        //partner的id和sellerId是同一个值
        return AlipayConfig.partner.equals(sellerId);
    }

    /**
     *
     * 阿里返回的数据中获取订单对象
     * @param map
     * @return
     */
    public static OrderFormData  getOrderFormDataFromAlipay(Map map){
        String order_attach = (String) map.get("order_attach");
        return JSON.parseObject(order_attach,OrderFormData.class);
    }

    public static String getUUId(){
        return UUID.randomUUID().toString();
    }

    /**
     * 创建阿里订单信息
     * @param orderInfo  订单详细信息,附加数据
     * @param orderId    订单id号码,可能重复
     * @param subject    订单支付提示信息
     * @param body       订单附加信息
     * @param price      订单价格
     * @return
     * @throws Exception
     */
    public final static String createAlipayInfo(String orderInfo,String orderId,String subject,String body,String price) throws Exception {
        StringBuilder builder = new StringBuilder("service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"10m\"");
        builder.append("&partner=\"");
        builder.append(AlipayConfig.partner);//商户id
        builder.append("\"");

        builder.append("&seller_id=\"");
        builder.append(AlipayConfig.partner);//卖家id
        builder.append("\"");

        builder.append("&notify_url=\"");
        builder.append(AlipayConfig.NOTIFY_URL);//回调地址
        builder.append("\"");

        builder.append("&out_trade_no=\"");
        builder.append(orderId);//订单id
        builder.append("\"");

        builder.append("&subject=\"");
        builder.append(subject);//支付标题
        builder.append("\"");

        builder.append("&body=\"");
        builder.append(body);//商品附加信息描述
        builder.append("\"");

        builder.append("&total_fee=\"");
        builder.append(price);//订单价格
        builder.append("\"");

        builder.append("&order_attach=\"");
        builder.append(orderInfo);//拼接商户个人订单信息
        builder.append("\"");

        //生成待签名字符串:builder.toString()
        //3.字符串签名
        String signStr = RSA.sign(builder.toString(),AlipayConfig.private_key, "utf-8");
        //4.签名URLEncoder编码
        signStr = URLEncoder.encode(signStr,"UTF-8");
        builder.append("&sign=\"");
        builder.append(signStr);
        builder.append("\"&sign_type=\"RSA\"");

        // 1.构建阿里支付订单参数map
       /* Map<String, String> paramMap = new LinkedHashMap<String,String>();
        paramMap.put("service", "\"" +"mobile.securitypay.pay"+ "\"");
        paramMap.put("payment_type", "\"" + "1" + "\"");
        paramMap.put("_input_charset", "\"" + "utf-8" + "\"");
        paramMap.put("it_b_pay", "\"" + "10m" + "\"");
        paramMap.put("partner", "\"" + AlipayConfig.partner + "\"");
        paramMap.put("seller_id", "\"" + AlipayConfig.partner + "\"");
        paramMap.put("notify_url", "\"" + AlipayConfig.NOTIFY_URL + "\"");
        paramMap.put("out_trade_no", "\"" + orderId + "\"");
        paramMap.put("subject", "\"" + subject + "\"");
        paramMap.put("body", "\"" + body + "\""); //订单附加信息
        paramMap.put("total_fee", "\"" + 0.01 + "\"");*/
        // 2.参数map转string
        //String paramStr = AlipayCore.createLinkString(paramMap);
        // 3.字符串签名
        //String signStr = RSA.sign(builder.toString(),AlipayConfig.private_key, "utf-8");
        //4.签名URLEncoder编码
           /*try {
                    signStr = URLEncoder.encode(signStr,"UTF-8");
             } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
            }
           */
        // 5.汇总参数string
        //String retrnStr = paramStr + "&sign=\"" + signStr + "\"&sign_type=\"RSA\"";
        return builder.toString();
    }

}
