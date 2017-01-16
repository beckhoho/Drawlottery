package com.hudongwx.drawlottery.mobile.service.alipay;

import com.hudongwx.drawlottery.mobile.entitys.OrderFormData;

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
     * @param formData
     * @return
     * @throws Exception
     */
    String createSign(Long accountId, OrderFormData formData) throws Exception;
}
