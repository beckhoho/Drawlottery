package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.hudongwx.drawlottery.mobile.conf.alipay.AlipayConfig;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserLuckCodesService;
import com.hudongwx.drawlottery.mobile.service.order.IOrdersService;
import com.hudongwx.drawlottery.mobile.utils.AlipayCore;
import com.hudongwx.drawlottery.mobile.utils.UtilDate;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/24 <br/>
 * @desc 用户订单管理<p>
 * <p>
 * 创建　wu　2016/12/24 <br/>
 * <p>
 * *********
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "OrdersController", description = "用户订单管理")
public class OrdersController extends BaseController {

    @Autowired
    IOrdersService ordersService;
    @Autowired
    IUserLuckCodesService ulcService;

    /**
     * 用户添加订单信息
     *
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/orders/sub", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addOrders(@RequestBody Orders orders, List<CommodityAmount> commodityAmounts) {
//        System.out.println("jsonObject----------->" + jsonObject.toString());
        return response(ordersService.pay(10000L, orders, commodityAmounts));
    }

    /**
     * 用户查看指定订单信息
     *
     * @param orderid 订单id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/orders/info", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryOrder(@RequestParam("orderid") Long orderid) {
        Orders order = new Orders();// TODO: 2016/12/24 查看单条订单？？？
        return success(order);
    }

    /**
     * 用户查看指定订单信息
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/orders/suc", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryOrderSuccess(@RequestBody JSONObject jsonObject) {
        System.out.println("suc------>" + jsonObject.toString());
        Map<String, Object> mapInfo = ordersService.selectPaySuccess(10000L, jsonObject);
        System.out.println("mapList---------------->" + JSON.toJSONString(mapInfo));
        return success(mapInfo);
    }

    /**
     * 用户查看所有个人订单信息
     *
     * @param accountid 用户id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/orders/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryAllUserOrders(@RequestParam("acc") Long accountid) {
        List<Orders> olist = ordersService.selectByUserAccount(accountid);
        return success(olist);
    }

    /**
     * 用户查看所有个人订单信息
     *
     * @param orderId 用户id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/orders/show/code", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryOrderCodes(@RequestParam("orderId") Long orderId) {
        List<Orders> olist = ordersService.selectByUserAccount(orderId);
        // TODO: 2017/1/9 查询code
        return success(olist);
    }

    /**
     * 支付宝APP支付–申请支付请求参数
     *
     * @param body
     * @param subject
     * @param out_trade_no
     * @param total_amount
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/orders/alipay.do", produces = "text/html;charset=UTF-8", method = {RequestMethod.GET})
    public JSONObject alipay(String body, String subject, String out_trade_no, String total_amount) throws Exception {

        //公共参数
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

        m.put("body", body);
        m.put("subject", subject);
        m.put("out_trade_no", out_trade_no);
        m.put("timeout_express", "30m");
        m.put("total_amount", total_amount);
        m.put("seller_id", AlipayConfig.partner);
        m.put("product_code", "QUICK_MSECURITY_PAY");

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

        return success(json4);

    }

    /**
     * 支付宝支付结果异步通知业务处理
     * 接收支付宝返回的请求参数
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/orders/callbacks.do", produces = "text/html;charset=UTF-8", method = {RequestMethod.POST})
    public String callbacks(HttpServletRequest request) throws Exception {
        //接收支付宝返回的请求参数

        Map requestParams = request.getParameterMap();

        JSONObject json = JSON.parseObject(JSON.toJSONString(requestParams));

        String trade_status = json.get("trade_status").toString().substring(2, json.get("trade_status").toString().length() - 2);
        String out_trade_no = json.get("out_trade_no").toString().substring(2, json.get("out_trade_no").toString().length() - 2);
        String notify_id = json.get("notify_id").toString().substring(2, json.get("notify_id").toString().length() - 2);

        System.out.println("====================================================");
        System.out.println(json.toString());
        System.out.println("支付宝回调地址！");
        System.out.println("商户的订单编号：" + out_trade_no);
        System.out.println("支付的状态：" + trade_status);
        System.out.println("支付的id：" + notify_id);
        if (trade_status.equals("TRADE_SUCCESS")) {

            /**
             *支付成功之后的业务处理
             */

            return "SUCCESS";
        } else {

            /**
             *支付失败后的业务处理
             */

            return "SUCCESS";

        }
    }

}
