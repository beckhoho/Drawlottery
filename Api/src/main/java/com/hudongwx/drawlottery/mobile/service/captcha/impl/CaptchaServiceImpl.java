package com.hudongwx.drawlottery.mobile.service.captcha.impl;

import com.hudongwx.drawlottery.mobile.service.captcha.ICaptchaService;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@Service
@CacheConfig(cacheNames="phone_captcha_cache")
public class CaptchaServiceImpl implements ICaptchaService {

    //手机随机验证码
    final RandomWordGenerator randomWordGenerator = new RandomWordGenerator("0123456789");

    final DefaultManageableImageCaptchaService captchaService = new DefaultManageableImageCaptchaService();

    /*
    获取验证图片信息
     */
    public BufferedImage getBufferedCaptchImage(String id){
        BufferedImage image = captchaService.getImageChallengeForID(id);
        return image;
    }

    /**
     * 发送手机验证码
     * @param id
     * @return
     */
    @CachePut(key = "#id")
    @Override
    public String sendPhoneCaptchCode(String id) {
        return randomWordGenerator.getWord(4);
    }

    @Override
    public boolean validatorPhoneCaptchCode(String id) {

        return false;
    }


    /**
     * 图形验证码校验
     * @param id
     * @param code
     * @return
     */
    public boolean validatorImageCode(String id,String code){
        return captchaService.validateResponseForID(id,code);
    }

}
