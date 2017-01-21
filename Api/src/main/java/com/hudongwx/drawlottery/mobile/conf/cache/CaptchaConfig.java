package com.hudongwx.drawlottery.mobile.conf.cache;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/21 0021 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/21 0021　<br/>
 * <p>
 * 验证码缓存配置
 * <p>
 * @email 294786949@qq.com
 */
@Configuration
public class CaptchaConfig {

    /**
     * 图片验证码配置
     */
    @Bean
    public DefaultManageableImageCaptchaService getImageCaptchaService(){
        return new DefaultManageableImageCaptchaService(180,50000,35000);
    }

}
