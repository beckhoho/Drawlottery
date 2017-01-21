package com.hudongwx.drawlottery.mobile.service.alipay;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.OrderFormData;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

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
    public JSONObject createTemporaryOrder(OrderFormData data);

    /**
     * 获取缓存订单
     * @return
     */
    public OrderFormData getCacheTemporaryOrder(OrderFormData data);

    /**
     * 删除缓存订单
     */
    public void removeTemporaryOrder(OrderFormData data);

    /**
     * 检查支付宝订单返回来的订单,是否有效
     * @param params
     * @return
     */
    public boolean checkAliPayOrderValidator(Map params);


    /**
     * 放到缓存中
     * @param data
     * @return
     */
    public OrderFormData putCacheOrderFormData(OrderFormData data);

}
