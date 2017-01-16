package com.hudongwx.drawlottery.mobile.service.alipay;

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
public interface IAliPayService {

    /**
     * 支付宝APP支付–申请支付请求参数
     *
     * @param body         商品描述
     * @param subject      订单标题
     * @param out_trade_no 商品唯一订单号
     * @param total_amount 订单总金额
     * @return
     * @throws Exception
     */
    String createSign(String body, String subject, String out_trade_no, String total_amount) throws Exception;
}
