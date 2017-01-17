package com.hudongwx.drawlottery.mobile.service.alipay.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.hudongwx.drawlottery.mobile.conf.alipay.AlipayConfig;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.OrderFormData;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.entitys.OrdersCommoditys;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.OrdersCommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.OrdersMapper;
import com.hudongwx.drawlottery.mobile.service.alipay.IAliPayService;
import com.hudongwx.drawlottery.mobile.utils.alipay.AlipayCore;
import com.hudongwx.drawlottery.mobile.utils.alipay.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    OrdersCommoditysMapper ordersCommoditysMapper;
    @Autowired
    CommoditysMapper commoditysMapper;

    @Override
    public String createSign(Long accountId, OrderFormData formData) throws Exception {
        Date date = new Date();
        Orders order = formData.getOrder();
        List<CommodityAmount> caList = formData.getCaList();
        Long orderId = getRebuildOrderId(accountId, date, order);
        String orderTradeNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + orderId;
        /**************************************************************************/

        //公共参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("app_id", AlipayConfig.APP_ID);
        map.put("method", "alipay.trade.app.pay");
        map.put("format", "json");
        map.put("charset", "utf-8");
        map.put("sign_type", "RSA");
        map.put("timestamp", UtilDate.getDateFormatter());
        map.put("version", "1.0");
        map.put("notify_url", AlipayConfig.service);

        Map<String, String> m = new HashMap<String, String>();

        m.put("body", "测试");
        m.put("subject", "");
        m.put("out_trade_no", orderTradeNo);
        m.put("timeout_express", "30m");
        m.put("total_amount", order.getPrice()+"");
        m.put("seller_id", AlipayConfig.partner);
        m.put("product_code", "QUICK_MSECURITY_PAY");

        JSONObject bizcontentJson= JSON.parseObject(JSONObject.toJSONString(m));

        map.put("biz_content", bizcontentJson.toString());
        //对未签名原始字符串进行签名
        String rsaSign = AlipaySignature.rsaSign(map, AlipayConfig.private_key, "utf-8");

        Map<String, String> map4 = new HashMap<String, String>();

        map4.put("app_id", AlipayConfig.APP_ID);
        map4.put("method", "alipay.trade.app.pay");
        map4.put("format", "json");
        map4.put("charset", "utf-8");
        map4.put("sign_type", "RSA");
        map4.put("timestamp", URLEncoder.encode(UtilDate.getDateFormatter(),"UTF-8"));
        map4.put("version", "1.0");
        map4.put("notify_url",  URLEncoder.encode(AlipayConfig.service,"UTF-8"));
        //最后对请求字符串的所有一级value（biz_content作为一个value）进行encode，编码格式按请求串中的charset为准，没传charset按UTF-8处理
        map4.put("biz_content", URLEncoder.encode(bizcontentJson.toString(), "UTF-8"));

        Map par = AlipayCore.paraFilter(map4); //除去数组中的空值和签名参数
        String json4 = AlipayCore.createLinkString(par);   //拼接后的字符串

        json4=json4 + "&sign=" + URLEncoder.encode(rsaSign, "UTF-8");

//        System.out.println(json4.toString());

//        AliPayMsg apm = new AliPayMsg();
//        apm.setCode("1");
//        apm.setMsg("支付成功");
//        apm.setData(json4.toString());

//        JSONObject json =JSON.parseObject(JSONObject.toJSONString(apm));

//        System.out.println(json.toString());

        return json4.toString();
    }

    /**
     * 创建订单,并且返回订单号
     *
     * @return
     */
    private Long getRebuildOrderId(Long accountId, Date date, Orders order) {
        order.setUserAccountId(accountId);
        order.setSubmitDate(date.getTime());
        ordersMapper.insert(order);
        return ordersMapper.selectOneByUserDate(accountId, date.getTime()).getId();
    }

    /**
     * 获取商品名
     *
     * @return
     */
    private String getBufferCommName(Long orderId, List<CommodityAmount> caList) {
        StringBuffer sb = new StringBuffer();
        List<Long> list = new ArrayList<>();
        for (CommodityAmount commodityAmount : caList) {
            OrdersCommoditys oc = new OrdersCommoditys();
            oc.setOrdersId(orderId);
            oc.setCommodityId(commodityAmount.getCommodityId());
            oc.setAmount(commodityAmount.getAmount());
            if (ordersCommoditysMapper.insert(oc) > 0) {
                list.add(commodityAmount.getCommodityId());
            }
        }
        List<String> nameList = commoditysMapper.selectCommNameByCommId(list);
        for (String name : nameList) {
            sb.append(name);
        }
        return sb.toString();
    }
}
