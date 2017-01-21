package com.hudongwx.drawlottery.mobile.service.alipay.impl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.OrdersCommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.OrdersMapper;
import com.hudongwx.drawlottery.mobile.service.alipay.IAliPayService;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import com.hudongwx.drawlottery.mobile.utils.OrderUtils;
import com.hudongwx.drawlottery.mobile.web.pay.PayController;
import com.hudongwx.drawlottery.mobile.web.pay.alipay.config.AlipayConfig;
import com.hudongwx.drawlottery.mobile.web.pay.alipay.util.AlipayNotify;
import com.hudongwx.drawlottery.mobile.web.pay.alipay.util.UtilDate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
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
@CacheConfig(cacheNames = {"linshi_order_cache"})
public class AliPayServiceImpl implements IAliPayService {

    private static final Logger LOG = Logger.getLogger(AliPayServiceImpl.class);

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    OrdersCommoditysMapper ordersCommoditysMapper;

    @Autowired
    CommoditysMapper commoditysMapper;

    @Autowired
    IRedPacketsService mRedPacketsService;

    /**
     * 创建临时订单,放到缓存框架中
     * @param data
     * @return
     */
    public JSONObject createTemporaryOrder(OrderFormData data){
        int totalPrice = 0; //订单总价格
        //获取购买的商品,计算总价格
        List<CommodityAmount> caList = data.getCaList();
        for (CommodityAmount info:caList){
            totalPrice+=info.getAmount();
        }

        //红包校验
/*        Long redPacketId = data.getOrder().getRedPacketId();
        if(redPacketId  != null){
            RedPackets packets = mRedPacketsService.selectOne(account, redPacketId);
            //校验订单过期和最小订单的使用
            if(packets!= null && !packets.isExpired() && packets.isCanUse(totalPrice)){
                totalPrice = totalPrice-packets.getWorth();//除去红包的价格
            }
        }*/

        //返回数据
        JSONObject info = new JSONObject();
        try {
            //创建订单id
            Long orderId = OrderUtils.getOrderId();
            //设置订单id
            data.getOrder().setId(orderId);
            data.getOrder().setPrice(totalPrice);
            //订单附加json信息
            String orderAttach = JSON.toJSONString(data);
            String order = OrderUtils.createAlipayInfo(orderAttach,String.valueOf(orderId),"惊喜商城-"+orderId,"惊喜商城",String.valueOf(totalPrice));
            info.put("id",orderId);
            info.put("order",order);

            //记录到缓存中
            putCacheOrderFormData(data);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("订单创建失败",e);
            throw new RuntimeException("订单创建失败");
        }
        return info;
    }

    @CachePut(key = "#data.order.id+''+#data.order.userAccountId")
    public OrderFormData putCacheOrderFormData(OrderFormData data){
        return data;
    }

    /**
     * 获取缓存订单
     * @return
     */
    @Cacheable(key = "#data.order.id+''+#data.order.userAccountId")
    public OrderFormData getCacheTemporaryOrder(OrderFormData data){
        return data;
    }

    /**
     * 删除缓存订单
     */
    @CacheEvict(key = "#data.order.id+''+#data.order.userAccountId")
    public void removeTemporaryOrder(OrderFormData data){
    }

    @Override
    public boolean checkAliPayOrderValidator(Map params) {
        //异步通知id
        String notify_id = (String) params.get("notify_id");
        //1.验证签名是否正确
        String sign= (String) params.get("sign");//获取签名sign
        if(true){//AlipayNotify.getSignVeryfy(params,sign)
            //2.验证订单是否是系统的订单
            OrderFormData formData = OrderUtils.getOrderFormDataFromAlipay(params); //来自支付宝的订单信息
            OrderFormData cacheTemporaryOrder = getCacheTemporaryOrder(formData);//来自缓存的订单信息
            if(cacheTemporaryOrder == null){
                LOG.debug("缓存中没有找到对应的订单信息");
                return false;
            }
            //3.验证金额是否正确
            String trade_no = (String) params.get("total_fee");
            if(!trade_no.equals(String.valueOf(cacheTemporaryOrder.getOrder().getPrice()))){
                LOG.debug("缓存中订单价格不一致");
                return false;
            }
            //4.校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方
            String seller_id = (String) params.get("seller_id");
            if(!OrderUtils.isMySellerid(seller_id)){
                LOG.debug("订单收款方不一致当前是:"+seller_id+"应该是:"+AlipayConfig.partner);
                return false;
            }
            //商户订单号
            String out_trade_no = (String) params.get("out_trade_no");
            LOG.debug("支付成功订单id:"+out_trade_no+"---支付宝交易号:"+trade_no);
            return true;
        }else{
            LOG.debug("签名验证失败");
            return  false;
        }
    }

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
        map.put("app_id", AlipayConfig.partner);
        map.put("method", "alipay.trade.app.pay");
        map.put("format", "json");
        map.put("charset", "utf-8");
        map.put("sign_type", "RSA");
        //map.put("timestamp", UtilDate.getDateFormatter());
        map.put("version", "1.0");
        map.put("notify_url", "====");

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

        //map4.put("app_id", AlipayConfig.APP_ID);
        map4.put("method", "alipay.trade.app.pay");
        map4.put("format", "json");
        map4.put("charset", "utf-8");
        map4.put("sign_type", "RSA");
        //map4.put("timestamp", URLEncoder.encode(UtilDate.getDateFormatter(),"UTF-8"));
        map4.put("version", "1.0");
        map4.put("notify_url",  URLEncoder.encode("==","UTF-8"));
        //最后对请求字符串的所有一级value（biz_content作为一个value）进行encode，编码格式按请求串中的charset为准，没传charset按UTF-8处理
        map4.put("biz_content", URLEncoder.encode(bizcontentJson.toString(), "UTF-8"));

        //Map par = AlipayCore.paraFilter(map4); //除去数组中的空值和签名参数
       // String json4 = AlipayCore.createLinkString(par);   //拼接后的字符串
        String json4 = "";
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
        List<String> nameList = commoditysMapper.selectCommNameListByCommId(list);
        for (String name : nameList) {
            sb.append(name);
        }
        return sb.toString();
    }
}
