package com.hudongwx.drawlottery.mobile.service.captcha;

import java.awt.image.BufferedImage;

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
 * 系统验证码服务
 * <p>
 * @email 294786949@qq.com
 */
public interface ICaptchaService  {

    /**
     * 获取图形验证码
     * @param id
     * @return
     */
    public BufferedImage getBufferedCaptchImage(String id);
    /**
     * 验证图片验证码
     * @param id
     * @param code
     * @return
     */
    public boolean validatorImageCode(String id,String code);


    /**
     * 发送手机验证码
     * @return
     */
    public String sendPhoneCaptchCode(String id);

    /**
     * 验证手机验证码
     * @return
     */
    public boolean validatorPhoneCaptchCode(String id);

}
