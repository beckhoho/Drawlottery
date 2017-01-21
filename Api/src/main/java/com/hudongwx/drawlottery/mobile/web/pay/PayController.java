package com.hudongwx.drawlottery.mobile.web.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.hudongwx.drawlottery.mobile.entitys.OrderFormData;
import com.hudongwx.drawlottery.mobile.service.alipay.IAliPayService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import com.hudongwx.drawlottery.mobile.web.pay.alipay.config.AlipayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 * @author Kiter
 * @version 1.0, 2017/1/15 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/15 21:21　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Api(value = "PayController", description = "支付管理")
@RestController
public class PayController extends BaseController {

    private static final Logger LOG = Logger.getLogger(PayController.class);

    @Autowired
    IAliPayService aliPayService;


    @ApiOperation(value = "支付宝支付,创建订单接口")
    @RequestMapping(value = "/api/v1/user/order/alipay", method = RequestMethod.POST)
    public JSONObject createOrderByAlipay(@Valid @RequestBody OrderFormData param, BindingResult br){
        if(br.hasErrors()){
            LOG.debug(br.getAllErrors());
            return fail(-1,"参数错误");
        }
        try {
            param.getOrder().setUserAccountId(getUserId());//设置当前的用户id
            JSONObject result = aliPayService.createTemporaryOrder(param);
            return success("",result.toString());
        }catch (Exception e){
            return fail(-1,"订单创建失败");
        }

        /*//创建临时订单
        OrderFormData order = new OrderFormData();
        order.setOrder(new Orders());
        order.getOrder().setId(OrderUtils.getOrderId());
        List<CommodityAmount> amounts = new ArrayList<CommodityAmount>();
        order.setCaList(amounts);
        aliPayService.createTemporaryOrder(order);

        OrderFormData temporaryOrder = aliPayService.getTemporaryOrder(order.getOrder().getId());
        aliPayService.removeTemporaryOrder(order.getOrder().getId());
        temporaryOrder = aliPayService.getTemporaryOrder(order.getOrder().getId());*/

     /*   String orderNum = UtilDate.getOrderNum();
        // 1.构建阿里支付订单参数map
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("partner", "\"" + AlipayConfig.partner + "\"");
        paramMap.put("seller_id", "\"" + AlipayConfig.partner + "\"");
        paramMap.put("out_trade_no", "\"" + orderNum + "\"");
        paramMap.put("subject", "\"" + "闪币充值" + "\"");
        paramMap.put("body", "\"" + "互动无限测试数据" + "\"");
        paramMap.put("total_fee", "\"" + 0.01 + "\"");
        //https://openapi.alipaydev.com/gateway.do
        //mobile.securitypay.pay
        paramMap.put("service", "\"" +"mobile.securitypay.pay"+ "\"");
        paramMap.put("payment_type", "\"" + "1" + "\"");
        paramMap.put("_input_charset", "\"" + "utf-8" + "\"");
        paramMap.put("it_b_pay", "\"" + "5m" + "\"");
        //paramMap.put("return_url", "\"" + "m.alipay.com" + "\"");
        paramMap.put("notify_url", "\"" + AlipayConfig.NOTIFY_URL + "\"");
        // 2.参数map转string
        String paramStr = AlipayCore.createLinkString(paramMap);

        // 3.签名
        String signStr = RSA.sign(paramStr, AlipayConfig.private_key, "utf-8");
        // 4.签名URLEncoder编码
        try {
            signStr = URLEncoder.encode(signStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 5.汇总参数string
        String retrnStr = paramStr + "&sign=\"" + signStr + "\"&sign_type=\"RSA\"";
        JSONObject json = success("操作成功", getTest());
        System.out.println(JSON.toJSONString(json,true));*/
    }

    /**
     *
     * 支付成功,支付宝异步通知界面
     */
    @ApiOperation(value = "支付成功,支付宝异步通知接口")
    @RequestMapping(value = "/api/v1/pub/user/order/alipay/callback",method = RequestMethod.POST)
    public void alipayCallback(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map params = request.getParameterMap();//获取传递过来的参数
        String notify_id = (String) params.get("notify_id"); //异步通知id
        //是否是支付宝异步通知
        if(notify_id!="" && notify_id!=null){
            //交易状态
            String trade_status = (String) params.get("trade_status"); //TRADE_FINISHED 不支持退款
            if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
                //// TODO: 2017/1/20 0020 支付订单完成了,需要去生成订单信息

                response.getWriter().print("success");
            } else if (trade_status.equals("WAIT_BUYER_PAY")){ //交易创建的时候触发
                boolean b = aliPayService.checkAliPayOrderValidator(params);
                response.getWriter().print(b?"success":"fail");
            }
        }else{
            LOG.debug("订单支付失败,不是异步消息通知,notify_id等于空");
            response.getWriter().print("fail");
        }
    }


    /**
     * 支付宝支付结果异步通知业务处理
     * 接收支付宝返回的请求参数
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @ApiOperation(value = "接收支付宝返回的请求参数")
    @RequestMapping(value = "/api/v1/pub/orders/alipay/callbacks.do", produces = "text/html;charset=UTF-8", method = {RequestMethod.POST})
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
            LOG.info(new Date() + "[/api/v1/user/history/alipay/sub] :支付成功!商户的订单编号：" + out_trade_no);
            return "SUCCESS";
        } else {

            /**
             *支付失败后的业务处理
             */
            LOG.info(new Date() + "[/api/v1/user/history/alipay/sub] :支付成功!商户的订单编号：" + out_trade_no);
            return "SUCCESS";
        }
    }


    /**
     * @param request
     * @param response
     * @param tradeno  支付宝订单交易编号
     * @param orderno  商家交易编号
     * @param callback
     */
    @RequestMapping(value = "/api/v1/user/order/alipay/query", method = RequestMethod.POST)
    public void orderPayQuery(HttpServletRequest request, HttpServletResponse response, String tradeno, String orderno,
                              String callback) throws AlipayApiException {
//        LOG.info("[/history/pay/query]");
//        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest(); // 统一收单线下交易查询
//        // 只需要传入业务参数
//        Map<String, Object> param = new HashMap<>();
//        param.put("out_trade_no", orderno); // 商户订单号
//        param.put("trade_no", tradeno);// 交易金额
//        alipayRequest.setBizContent(JSON.toJSONString(param)); // 不需要对json字符串转义
//
//        Map<String, String> restmap = new HashMap<String, String>();// 返回提交支付宝订单交易查询信息
//        boolean flag = false; // 查询状态
//        try {
//            AlipayTradeQueryResponse alipayResponse = AliPayUtil.getAlipayClient().execute(alipayRequest);
//            if (alipayResponse.isSuccess()) {
//                // 调用成功，则处理业务逻辑
//                if ("10000".equals(alipayResponse.getCode())) {
//                    // 订单创建成功
//                    flag = true;
//                    restmap.put("order_no", alipayResponse.getOutTradeNo());
//                    restmap.put("trade_no", alipayResponse.getTradeNo());
//                    restmap.put("buyer_logon_id", alipayResponse.getBuyerLogonId());
//                    restmap.put("trade_status", alipayResponse.getTradeStatus());
//                    LOG.info("订单查询结果：" + alipayResponse.getTradeStatus());
//                } else {
//                    LOG.info("订单查询失败：" + alipayResponse.getMsg() + ":" + alipayResponse.getSubMsg());
//                }
//            }
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 订单支付微信服务器异步通知
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/api/v1/user/order/wxpay/notify", method = RequestMethod.POST)
    public void orderPayNotify(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("[/history/pay/notify]");
        // 获取到返回的所有参数 先判断是否交易成功trade_status 再做签名校验
        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        // 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
        if ("TRADE_SUCCESS".equals(request.getParameter("trade_status"))) {
            Enumeration<?> pNames = request.getParameterNames();
            Map<String, String> param = new HashMap<String, String>();
            try {
                while (pNames.hasMoreElements()) {
                    String pName = (String) pNames.nextElement();
                    param.put(pName, request.getParameter(pName));
                }

                boolean signVerified = AlipaySignature.rsaCheckV1(param, AlipayConfig.alipay_public_key,
                        AlipayConstants.CHARSET_UTF8); // 校验签名是否正确
                if (signVerified) {
                    // TODO 验签成功后
                    // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                    LOG.info("订单支付成功：" + JSON.toJSONString(param));
                } else {
                    // TODO 验签失败则记录异常日志，并在response中返回failure.
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 订单退款
     *
     * @param request
     * @param response
     * @param tradeno  支付宝交易订单号
     * @param orderno  商家交易订单号
     * @param callback
     */
    @RequestMapping(value = "/api/v1/user/order/alipay/refund", method = RequestMethod.POST)
    public void orderPayRefund(HttpServletRequest request, HttpServletResponse response, String tradeno, String orderno,
                               String callback) {
        LOG.info("[/pay/refund]");
//        if (StringUtil.isEmpty(tradeno) && StringUtil.isEmpty(orderno)) {
//            WebUtil.response(response, WebUtil.packJsonp(callback, JSON
//                    .toJSONString(new JsonResult(-1, "订单号不能为空", new ResponseData()), SerializerFeatureUtil.FEATURES)));
//        }

        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest(); // 统一收单交易退款接口
        // 只需要传入业务参数
        Map<String, Object> param = new HashMap<>();
        param.put("out_trade_no", orderno); // 商户订单号
        param.put("trade_no", tradeno);// 交易金额
        param.put("refund_amount", 0.01);// 退款金额
        param.put("refund_reason", "测试支付退款");// 退款金额
//        param.put("out_request_no", PayUtil.getRefundNo()); //退款单号
        alipayRequest.setBizContent(JSON.toJSONString(param)); // 不需要对json字符串转义

        Map<String, Object> restmap = new HashMap<>();// 返回支付宝退款信息
        boolean flag = false; // 查询状态
//        try {
        AlipayTradeRefundResponse alipayResponse = null;/*AliPayUtil.getAlipayClient().execute(alipayRequest)*/
        ;
        if (alipayResponse.isSuccess()) {
            // 调用成功，则处理业务逻辑
            if ("10000".equals(alipayResponse.getCode())) {
                // 订单创建成功
                flag = true;
                restmap.put("out_trade_no", alipayResponse.getOutTradeNo());
                restmap.put("trade_no", alipayResponse.getTradeNo());
                restmap.put("buyer_logon_id", alipayResponse.getBuyerLogonId());// 用户的登录id
                restmap.put("gmt_refund_pay", alipayResponse.getGmtRefundPay()); // 退看支付时间
                restmap.put("buyer_user_id", alipayResponse.getBuyerUserId());// 买家在支付宝的用户id
                LOG.info("订单退款结果：退款成功");
            } else {
                LOG.info("订单查询失败：" + alipayResponse.getMsg() + ":" + alipayResponse.getSubMsg());
            }
        }
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        if (flag) {
//            // 订单查询成功
//            WebUtil.response(response,
//                    WebUtil.packJsonp(callback,
//                            JSON.toJSONString(new JsonResult(1, "订单退款成功", new ResponseData(null, restmap)),
//                                    SerializerFeatureUtil.FEATURES)));
//        } else { // 订单查询失败
//            WebUtil.response(response, WebUtil.packJsonp(callback, JSON
//                    .toJSONString(new JsonResult(-1, "订单退款失败", new ResponseData()), SerializerFeatureUtil.FEATURES)));
//        }
    }

    /**
     * @param request
     * @param response
     * @param orderno  商家订单号
     * @param tradeno  支付宝订单号
     * @param callback
     */
    @RequestMapping(value = "/api/v1/user/order/alipay/refund/query", method = RequestMethod.POST)
    public void orderPayRefundQuery(HttpServletRequest request, HttpServletResponse response, String orderno,
                                    String tradeno, String callback) {
//        if (StringUtil.isEmpty(orderno) && StringUtil.isEmpty(tradeno)) {
//            WebUtil.response(response,
//                    WebUtil.packJsonp(callback,
//                            JSON.toJSONString(new JsonResult(-1, "商家订单号或支付宝订单号不能为空", new ResponseData()),
//                                    SerializerFeatureUtil.FEATURES)));
//        }

//        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest(); // 统一收单交易退款查询
//        // 只需要传入业务参数
//        Map<String, Object> param = new HashMap<>();
//        param.put("out_trade_no", orderno); // 商户订单号
//        param.put("trade_no", tradeno);// 交易金额
//        param.put("out_request_no", orderno);// 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
//        alipayRequest.setBizContent(JSON.toJSONString(param)); // 不需要对json字符串转义
//
//        Map<String, Object> restmap = new HashMap<>();// 返回支付宝退款信息
//        boolean flag = false; // 查询状态
//        try {
//            AlipayTradeFastpayRefundQueryResponse alipayResponse = null;/*AliPayUtil.getAlipayClient().execute(alipayRequest)*/;
//            if (alipayResponse.isSuccess()) {
//                // 调用成功，则处理业务逻辑
//                if ("10000".equals(alipayResponse.getCode())) {
//                    // 订单创建成功
//                    flag = true;
//                    restmap.put("out_trade_no", alipayResponse.getOutTradeNo());
//                    restmap.put("trade_no", alipayResponse.getTradeNo());
//                    restmap.put("out_request_no", alipayResponse.getOutRequestNo());// 退款订单号
//                    restmap.put("refund_reason", alipayResponse.getRefundReason()); // 退款原因
//                    restmap.put("total_amount", alipayResponse.getTotalAmount());// 订单交易金额
//                    restmap.put("refund_amount", alipayResponse.getTotalAmount());// 订单退款金额
//                    LOG.info("订单退款结果：退款成功");
//                } else {
//                    LOG.info("订单失败：" + alipayResponse.getMsg() + ":" + alipayResponse.getSubMsg());
//                }
//            }
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }

//        if (flag) {
//            // 订单查询成功
//            WebUtil.response(response,
//                    WebUtil.packJsonp(callback,
//                            JSON.toJSONString(new JsonResult(1, "订单退款成功", new ResponseData(null, restmap)),
//                                    SerializerFeatureUtil.FEATURES)));
//        } else { // 订单查询失败
//            WebUtil.response(response, WebUtil.packJsonp(callback, JSON
//                    .toJSONString(new JsonResult(-1, "订单退款失败", new ResponseData()), SerializerFeatureUtil.FEATURES)));
//        }
    }

}