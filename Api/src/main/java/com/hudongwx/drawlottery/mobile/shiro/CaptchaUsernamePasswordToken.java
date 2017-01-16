package com.hudongwx.drawlottery.mobile.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/15 0015 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/15 0015　<br/>
 * <p>
 *  支持验证码的
 * <p>
 * @email 294786949@qq.com
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    /**
     *验证码
     */
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
