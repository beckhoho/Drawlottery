package com.hudongwx.drawlottery.mobile.web.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.hudongwx.drawlottery.mobile.conf.alipay.AlipayConfig;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.utils.payutils.AlipayUtil;
import com.hudongwx.drawlottery.mobile.utils.payutils.PayUtil;
import com.hudongwx.drawlottery.mobile.utils.payutils.UtilDate;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
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
@RestController
@Api(value = "PayController", description = "第三方支付请求")
public class PayController extends BaseController {


    private static final Logger LOG = Logger.getLogger(PayController.class);

    /**
     * 支付下订单
     *
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/order/alipay/sub", method = RequestMethod.POST)
    public JSONObject orderPay(@ApiParam("订单信息") @RequestBody Orders orders) throws UnsupportedEncodingException {
        LOG.info("[/order/pay]");
//        if (!"001".equals(mercid)) {
//            WebUtil.response(response, WebUtil.packJsonp(callback, JSON
//                    .toJSONString(new JsonResult(-1, "商品不存在", new ResponseData()), SerializerFeatureUtil.FEATURES)));
//        }

        Map<String, String> param = new HashMap<>();
        // 公共请求参数
        param.put("app_id", AlipayConfig.app_id);// 商户订单号
        param.put("method", "alipay.trade.app.pay");// 交易金额
        param.put("format", "json");
        param.put("charset", "utf-8");
        param.put("timestamp", URLEncoder.encode(UtilDate.getDateFormatter(), "UTF-8"));
        param.put("version", "1.0");
        param.put("notify_url", "https://www.andy.org/alipay/order/pay/notify.shtml"); // 支付宝服务器主动通知商户服务
        param.put("sign_type", "RSA");

        Map<String, Object> pcont = new HashMap<>();
        // 支付业务请求参数
        pcont.put("out_trade_no", ""); // 商户订单号
        pcont.put("total_amount", String.valueOf(100));// 交易金额
        pcont.put("subject", "测试支付"); // 订单标题
        pcont.put("body", "Andy");// 对交易或商品的描述
        pcont.put("product_code", "QUICK_MSECURITY_PAY");// 销售产品码

        param.put("biz_content", JSON.toJSONString(pcont)); // 业务请求参数  不需要对json字符串转义
        Map<String, String> payMap = new HashMap<>();
        try {
            param.put("sign", PayUtil.getSign(param, AlipayUtil.APP_PRIVATE_KEY)); // 业务请求参数
            payMap.put("orderStr", PayUtil.getSignEncodeUrl(param, true));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        WebUtil.response(response, WebUtil.packJsonp(callback, JSON.toJSONString(
//                new JsonResult(1, "订单获取成功", new ResponseData(null, payMap)), SerializerFeatureUtil.FEATURES)));
        return success();
    }

    /**
     * @param request
     * @param response
     * @param tradeno  支付宝订单交易编号
     * @param orderno  商家交易编号
     * @param callback
     */
    @RequestMapping(value = "/pay/query", method = RequestMethod.POST)
    public void orderPayQuery(HttpServletRequest request, HttpServletResponse response, String tradeno, String orderno,
                              String callback) throws AlipayApiException {
        LOG.info("[/order/pay/query]");
//        if (StringUtil.isEmpty(tradeno) && StringUtil.isEmpty(orderno)) {
//            WebUtil.response(response, WebUtil.packJsonp(callback, JSON
//                    .toJSONString(new JsonResult(-1, "订单号不能为空", new ResponseData()), SerializerFeatureUtil.FEATURES)));
//        }

        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest(); // 统一收单线下交易查询
        // 只需要传入业务参数
        Map<String, Object> param = new HashMap<>();
        param.put("out_trade_no", orderno); // 商户订单号
        param.put("trade_no", tradeno);// 交易金额
        alipayRequest.setBizContent(JSON.toJSONString(param)); // 不需要对json字符串转义

        Map<String, String> restmap = new HashMap<String, String>();// 返回提交支付宝订单交易查询信息
        boolean flag = false; // 查询状态
        try {
            AlipayTradeQueryResponse alipayResponse = AlipayUtil.getAlipayClient().execute(alipayRequest);
            if (alipayResponse.isSuccess()) {
                // 调用成功，则处理业务逻辑
                if ("10000".equals(alipayResponse.getCode())) {
                    // 订单创建成功
                    flag = true;
                    restmap.put("order_no", alipayResponse.getOutTradeNo());
                    restmap.put("trade_no", alipayResponse.getTradeNo());
                    restmap.put("buyer_logon_id", alipayResponse.getBuyerLogonId());
                    restmap.put("trade_status", alipayResponse.getTradeStatus());
                    LOG.info("订单查询结果：" + alipayResponse.getTradeStatus());
                } else {
                    LOG.info("订单查询失败：" + alipayResponse.getMsg() + ":" + alipayResponse.getSubMsg());
                }
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

//        if (flag) {
//            // 订单查询成功
//            WebUtil.response(response,
//                    WebUtil.packJsonp(callback,
//                            JSON.toJSONString(new JsonResult(1, "订单查询成功", new ResponseData(null, restmap)),
//                                    SerializerFeatureUtil.FEATURES)));
//        } else { // 订单查询失败
//            WebUtil.response(response, WebUtil.packJsonp(callback, JSON
//                    .toJSONString(new JsonResult(-1, "订单查询失败", new ResponseData()), SerializerFeatureUtil.FEATURES)));
//        }
    }

    /**
     * 订单支付微信服务器异步通知
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/pay/notify", method = RequestMethod.POST)
    public void orderPayNotify(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("[/order/pay/notify]");
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

                boolean signVerified = AlipaySignature.rsaCheckV1(param, AlipayUtil.ALIPAY_PUBLIC_KEY,
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
    @RequestMapping(value = "/pay/refund", method = RequestMethod.POST)
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
        param.put("out_request_no", PayUtil.getRefundNo()); //退款单号
        alipayRequest.setBizContent(JSON.toJSONString(param)); // 不需要对json字符串转义

        Map<String, Object> restmap = new HashMap<>();// 返回支付宝退款信息
        boolean flag = false; // 查询状态
        try {
            AlipayTradeRefundResponse alipayResponse = AlipayUtil.getAlipayClient().execute(alipayRequest);
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
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

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
    @RequestMapping(value = "/pay/refund/query", method = RequestMethod.POST)
    public void orderPayRefundQuery(HttpServletRequest request, HttpServletResponse response, String orderno,
                                    String tradeno, String callback) {
        LOG.info("[/pay/refund/query]");
//        if (StringUtil.isEmpty(orderno) && StringUtil.isEmpty(tradeno)) {
//            WebUtil.response(response,
//                    WebUtil.packJsonp(callback,
//                            JSON.toJSONString(new JsonResult(-1, "商家订单号或支付宝订单号不能为空", new ResponseData()),
//                                    SerializerFeatureUtil.FEATURES)));
//        }

        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest(); // 统一收单交易退款查询
        // 只需要传入业务参数
        Map<String, Object> param = new HashMap<>();
        param.put("out_trade_no", orderno); // 商户订单号
        param.put("trade_no", tradeno);// 交易金额
        param.put("out_request_no", orderno);// 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
        alipayRequest.setBizContent(JSON.toJSONString(param)); // 不需要对json字符串转义

        Map<String, Object> restmap = new HashMap<>();// 返回支付宝退款信息
        boolean flag = false; // 查询状态
        try {
            AlipayTradeFastpayRefundQueryResponse alipayResponse = AlipayUtil.getAlipayClient().execute(alipayRequest);
            if (alipayResponse.isSuccess()) {
                // 调用成功，则处理业务逻辑
                if ("10000".equals(alipayResponse.getCode())) {
                    // 订单创建成功
                    flag = true;
                    restmap.put("out_trade_no", alipayResponse.getOutTradeNo());
                    restmap.put("trade_no", alipayResponse.getTradeNo());
                    restmap.put("out_request_no", alipayResponse.getOutRequestNo());// 退款订单号
                    restmap.put("refund_reason", alipayResponse.getRefundReason()); // 退款原因
                    restmap.put("total_amount", alipayResponse.getTotalAmount());// 订单交易金额
                    restmap.put("refund_amount", alipayResponse.getTotalAmount());// 订单退款金额
                    LOG.info("订单退款结果：退款成功");
                } else {
                    LOG.info("订单失败：" + alipayResponse.getMsg() + ":" + alipayResponse.getSubMsg());
                }
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

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