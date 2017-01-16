package com.hudongwx.drawlottery.mobile.conf.web;

import com.hudongwx.drawlottery.mobile.Interceptor.SmsSendIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/14 0014 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/14 0014　<br/>
 * <p>
 * 拦截器
 * <p>
 * @email 294786949@qq.com
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //短信发送校验
        /*registry.addInterceptor(smsIntercepter())
                .addPathPatterns("/api*//**");*/
        super.addInterceptors(registry);
    }

    @Bean
    public SmsSendIntercepter smsIntercepter(){
        return new SmsSendIntercepter();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //添加swagger-ui配置
       /*registry.addResourceHandler("swagger-ui.html*//**")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        */
    }


}
