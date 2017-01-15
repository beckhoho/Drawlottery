package com.hudongwx.drawlottery.mobile.service.alipay.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.hudongwx.drawlottery.mobile.conf.alipay.AlipayConfig;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.mappers.OrdersMapper;
import com.hudongwx.drawlottery.mobile.service.alipay.IAliPayService;
import com.hudongwx.drawlottery.mobile.utils.AlipayCore;
import com.hudongwx.drawlottery.mobile.utils.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/15 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/15 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class AliPayServiceImpl implements IAliPayService {

    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public String createSign(String body, String subject, String out_trade_no, String total_amount) throws Exception {
        //公共参数
        String orderId=createOrderInfo(10000L,1,Integer.valueOf(total_amount),1L);

        Map<String, String> map = new HashMap<String, String>();
        map.put("app_id", AlipayConfig.app_id);
        map.put("method", "alipay.trade.app.pay");
        map.put("format", "json");
        map.put("charset", "utf-8");
        map.put("sign_type", "RSA");
        map.put("timestamp", UtilDate.getDateFormatter());
        map.put("version", "1.0");
        map.put("notify_url", AlipayConfig.service);

        Map<String, String> m = new HashMap<String, String>();

        m.put("body", body);//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
        m.put("subject", subject);// 商品的标题/交易标题/订单标题/订单关键字等。
        m.put("out_trade_no", orderId);//商户网站唯一订单号
        m.put("timeout_express", "30m");//该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        m.put("total_amount", total_amount);//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        m.put("seller_id", AlipayConfig.partner);//收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
        m.put("product_code", "QUICK_MSECURITY_PAY");//销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY

        JSONObject bizcontentJson = JSON.parseObject(JSON.toJSONString(m));

        map.put("biz_content", bizcontentJson.toString());
        //对未签名原始字符串进行签名
        String rsaSign = AlipaySignature.rsaSign(map, AlipayConfig.private_key, "utf-8");

        Map<String, String> map4 = new HashMap<String, String>();

        map4.put("app_id", AlipayConfig.app_id);
        map4.put("method", "alipay.trade.app.pay");
        map4.put("format", "json");
        map4.put("charset", "utf-8");
        map4.put("sign_type", "RSA");
        map4.put("timestamp", URLEncoder.encode(UtilDate.getDateFormatter(), "UTF-8"));
        map4.put("version", "1.0");
        map4.put("notify_url", URLEncoder.encode(AlipayConfig.service, "UTF-8"));
        //最后对请求字符串的所有一级value（biz_content作为一个value）进行encode，编码格式按请求串中的charset为准，没传charset按UTF-8处理
        map4.put("biz_content", URLEncoder.encode(bizcontentJson.toString(), "UTF-8"));

        Map par = AlipayCore.paraFilter(map4); //除去数组中的空值和签名参数
        String json4 = AlipayCore.createLinkString(par);   //拼接后的字符串

        json4 = json4 + "&sign=" + URLEncoder.encode(rsaSign, "UTF-8");

        System.out.println(json4.toString());

//        AliPayMsg apm = new AliPayMsg();
//        apm.setCode("1");
//        apm.setMsg("支付成功");
//        apm.setData(json4.toString());
//
//        JSONObject json = JSONObject.fromObject(apm);


//        System.out.println(json.toString());

        return json4.toString();
    }

    /**
     * 创建订单,并且返回订单号
     *
     * @param accountId
     * @param payMode
     * @param price
     * @return
     */
    private String createOrderInfo(Long accountId, Integer payMode, Integer price, Long rpId) {
        Date date = new Date();
        Orders order = new Orders();
        order.setUserAccountId(accountId);
        order.setPayModeId(payMode);
        order.setPrice(price);
        order.setSubmitDate(date.getTime());
        order.setRedPacketId(rpId);
        ordersMapper.insert(order);
        return ordersMapper.selectOneByUserDate(accountId, date.getTime()).getId() + "";
    }
}
