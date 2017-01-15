package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.CommodityAmount;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.service.alipay.IAliPayService;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserLuckCodesService;
import com.hudongwx.drawlottery.mobile.service.order.IOrdersService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Autowired
    IAliPayService aliPayService;

    /**
     * 用户添加订单信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/orders/sub", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addOrders(@RequestBody JSONObject jsonObject) {
        System.out.println("orders.getPrice()----------->" + jsonObject.toString());
        Orders orders = JSONObject.toJavaObject(jsonObject.getJSONObject("order"),Orders.class);
        System.out.println("getPrice---------------->"+orders.getPrice());
        JSONArray jsonArray = jsonObject.getJSONArray("ca");
        List<CommodityAmount> caList=new ArrayList<>();
        for (Object o : jsonArray) {
            CommodityAmount commodityAmount = JSONObject.toJavaObject(JSONObject.parseObject(o.toString()),CommodityAmount.class);
            System.out.println("getAmount------------->"+commodityAmount.getAmount());
            caList.add(commodityAmount);
        }
        return response(ordersService.pay(10000L, orders, caList));
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
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/orders/alipay.do", method = {RequestMethod.GET})
    public JSONObject alipay(@ApiParam(value = "购买商品总金额") @RequestParam(name = "price")String price) throws Exception {
//        @ApiParam(value = "商品描述") @RequestParam(name = "body")
        String body=null;
//        @ApiParam(value = "订单标题") @RequestParam(name = "subject")
        String subject=null;
//        @ApiParam(value = "商品唯一订单号") @RequestParam(name = "out_trade_no")
        String out_trade_no=null;
//        @ApiParam(value = "订单总金额") @RequestParam(name = "total_amount")
        String total_amount=price;
        return success("操作成功", aliPayService.createSign(body, subject, out_trade_no, total_amount));
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
