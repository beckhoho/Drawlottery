package com.hudongwx.drawlottery.mobile.conf.web;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/16 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/16 0016　<br/>
 * <p>
 *     用户做http的网络请求
 * <p>
 * @email 294786949@qq.com
 */
@Configuration
public class OkHttpConfig {

    @Bean
    public OkHttpClient getOkHttpClient(){
        OkHttpClient client = new OkHttpClient();
        return client;
    }


}
