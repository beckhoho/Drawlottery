package com.hudongwx.drawlottery.mobile.service.alipay;

import com.hudongwx.drawlottery.mobile.entitys.OrderFormData;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

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

    /**
     * 创建临时订单,放到缓存框架中
     * @param data
     * @return
     */
    public OrderFormData createTemporaryOrder(OrderFormData data);

    /**
     * 获取缓存订单
     * @param orderId 订单id
     * @return
     */
    public OrderFormData getTemporaryOrder(Long orderId);

    /**
     * 删除缓存订单
     * @param orderId 订单id
     *
     */
    public void removeTemporaryOrder(Long orderId);

}
