package com.hudongwx.drawlottery.mobile.utils;

import com.alibaba.fastjson.JSON;
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
        buffer.append(System.nanoTime());
        buffer.append(current.nextInt(1000,9999));
        return Long.valueOf(buffer.toString());
    }


    /**
     * 创建阿里订单信息
     * @param orderInfo 订单详细信息自定义格式
     * @param orderId 订单id号码
     * @param subject 订单支付提示信息
     * @param body  订单附加信息
     * @param price  订单价格
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
        builder.append(0.01);//订单价格
        builder.append("\"");

        builder.append("&order_infos=\"");
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


    public  String getTest(){
        String partner = "2088102169231984";
        String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJtgOuu5XWJdVd6Vqgpsf168xUu53ZqoJenn8bbaI7Q22wB5tTFxH81YvbrxqZOMZctB689rGaERSCP+9xNoalHCwjgP/+rrvjpYzUVdO3rRhZfAnkmmui1j2ibcGEEWemQEGv5EBxZvI6uUs1NbHcJQJumvS0ZvJApChJ44OX/fAgMBAAECgYBBlDsqNQGaO8S7frXXUnXr+YbYcGl9Fk1yTUhzId0B+kkzCSuV46ZFmJfz6H3nXdG1GWy7DfwyREYLk1ibY23DziluqHc44yXb3pG9r5AOog7KFVvy3rDL83C0XFmIIQsSt4PGUf7/VZ2auvvt/ENCzc5+NGJmfnARSWO7wSj6sQJBAMtm0z3nMZJNe11z9LIQ8O8fotuzslULKGIRB+wfkbeF1v6Y4/D6ntHbcuV9FvSLszI8gQIDDXnD0HbGqHQ5XOkCQQDDjhpnJeGYdZ0rcOTt7vaEdQD4caVVl6K+1F4OK9K/Qo2us67ST1vQ8+yte/2O3Tewk0+UJkITehYmpx4ZeNmHAkBpNZ+wlmSaw387QJ5ieMbXOWr474Mf4CycRSju5wltf0pM2PKWlFwQOs28jK6SAazIIGmui7utry6mMW2y6HT5AkEAm/S2ZdC2K8qQv9ZXHNJY06YkUf8AZlR6PEpNgGu+tT20lMFECQG1Ld16wZiCzO7rvOyeqH4icDoLdGQPAy13/QJBALmg5D8Lzn1OZWi6YBjol9u6hiJ3JJfLIn1E3tfqtCz/H8k+3v0IkMaxMs0v1h+ZxoconNT6Zl0ziKnsz1ZSbfo=";
        String orderNum = UtilDate.getOrderNum();
        // 1.构建阿里支付订单参数map
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("app_id","\"" + "2016073100129773" + "\"");
        paramMap.put("partner", "\"" + partner + "\"");
        paramMap.put("seller_id", "\"" + partner + "\"");
        paramMap.put("out_trade_no", "\"" + orderNum + "\"");
        paramMap.put("subject", "\"" + "闪币充值---" + "\"");
        paramMap.put("body", "\"" + "互动无限测试数据" + "\"");
        paramMap.put("total_fee", "\"" + 0.05 + "\"");
        paramMap.put("service", "\"" +"mobile.securitypay.pay"+ "\"");
        paramMap.put("payment_type", "\"" + "1" + "\"");
        paramMap.put("_input_charset", "\"" + "utf-8" + "\"");
        paramMap.put("it_b_pay", "\"" + "5m" + "\"");
        //paramMap.put("return_url", "\"" + "m.alipay.com" + "\"");
        paramMap.put("notify_url", "\"" + AlipayConfig.NOTIFY_URL + "\"");

        // 2.参数map转string
        String paramStr = AlipayCore.createLinkString(paramMap);
        System.out.println(paramStr);
        // 3.签名
        String signStr = RSA.sign(paramStr,private_key, "utf-8");
        // 4.签名URLEncoder编码
        try {
            signStr = URLEncoder.encode(signStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 5.汇总参数string
        String retrnStr = paramStr + "&sign=\"" + signStr + "\"&sign_type=\"RSA\"";
        return retrnStr;
    }




    public static void main(String[] args) {
       /* OrderFormData order = new OrderFormData();
        order.setOrder(new Orders());
        order.getOrder().setId(OrderUtils.getOrderId());
        List<CommodityAmount> amounts = new ArrayList<CommodityAmount>();
        order.setCaList(amounts);
        String s = JSON.toJSONString(order, true);
        System.out.println(s);*/
        //createAlipayInfo("drawlottery_info","1000000000","购买标题","商品信息信息描述");
    }


}
