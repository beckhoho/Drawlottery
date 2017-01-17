package com.hudongwx.drawlottery.mobile.service.alipay.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayConstants;
import com.hudongwx.drawlottery.mobile.conf.alipay.AlipayConfig;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.OrderFormData;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.entitys.OrdersCommoditys;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.OrdersCommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.OrdersMapper;
import com.hudongwx.drawlottery.mobile.service.alipay.IAliPayService;
import com.hudongwx.drawlottery.mobile.utils.alipay.AlipaySubmit;
import com.hudongwx.drawlottery.mobile.utils.alipay.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Map<String, String> param = new HashMap<>();
        // 公共请求参数
        param.put("app_id", AlipayConfig.APP_ID);// 商户订单号
        param.put("method", "alipay.trade.app.pay");// 交易金额
        param.put("format", AlipayConstants.FORMAT_JSON);
        param.put("charset", AlipayConstants.CHARSET_UTF8);
        param.put("timestamp", UtilDate.getDateFormatter());
        param.put("version", "1.0");
        param.put("notify_url", "https://192.168.6.199:8080/api/v1/user/order/alipay/notify.shtml"); // 支付宝服务器主动通知商户服务
        param.put("sign_type", AlipayConstants.SIGN_TYPE_RSA);

        Map<String, Object> pcont = new HashMap<>();
        // 支付业务请求参数
        pcont.put("out_trade_no", orderTradeNo); // 商户订单号
        pcont.put("total_amount", order.getPrice());// 交易金额
        pcont.put("subject", orderTradeNo); // 订单标题
        pcont.put("body", "测试支付");// 对交易或商品的描述
        pcont.put("product_code", "QUICK_MSECURITY_PAY");// 销售产品码

        param.put("biz_content", JSON.toJSONString(pcont)); // 业务请求参数  不需要对json字符串转义

        Map<String, String> payMap = new HashMap<>();
        try {
            param.put("sign",  AlipaySubmit.buildRequestMysign(param)/*PayUtil.getSign(param, AlipayConfig.private_key)*/); // 业务请求参数
            payMap.put("orderStr", null/*PayUtil.getSignEncodeUrl(param, true)*/);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return payMap.get("orderStr");
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
