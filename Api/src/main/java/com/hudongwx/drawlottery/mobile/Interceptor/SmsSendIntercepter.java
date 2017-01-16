package com.hudongwx.drawlottery.mobile.Interceptor;

import com.hudongwx.drawlottery.mobile.service.captcha.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 * @author origin
 * @version 1.0, 2017/1/14 0014 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/14 0014　<br/>
 * <p>
 * 手机短信验证码发送
 * <p>
 * @email 294786949@qq.com
 */
@Component
public class SmsSendIntercepter implements HandlerInterceptor {

    @Autowired
    ICaptchaService captchaService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("==");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
