package com.hudongwx.drawlottery.mobile.utils.payutils;

import com.hudongwx.drawlottery.mobile.conf.alipay.AlipayConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/15 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/15 21:36　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class AliPayUtil {

    public static final String ALIPAY_APPID = null;/*ConfigUtil.getProperty("alipay.appid"); */// appid

    public static String APP_PRIVATE_KEY = AlipayConfig.private_key; // app支付私钥

    public static String ALIPAY_PUBLIC_KEY = AlipayConfig.alipay_public_key; // 支付宝公钥

    static {
        try {
            Resource resource = new ClassPathResource("alipay_private_key_pkcs8.pem");
//            APP_PRIVATE_KEY = FileUtil.readInputStream2String(resource.getInputStream());
            resource = new ClassPathResource("alipay_public_key.pem");
//            ALIPAY_PUBLIC_KEY = FileUtil.readInputStream2String(resource.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 统一收单交易创建接口
    private static AlipayClient alipayClient = null;

    public static AlipayClient getAlipayClient() {
        if (alipayClient == null) {
            synchronized (AliPayUtil.class) {
                if (null == alipayClient) {
                    alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", ALIPAY_APPID,
                            APP_PRIVATE_KEY, AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
                            ALIPAY_PUBLIC_KEY);
                }
            }
        }
        return alipayClient;
    }
}
