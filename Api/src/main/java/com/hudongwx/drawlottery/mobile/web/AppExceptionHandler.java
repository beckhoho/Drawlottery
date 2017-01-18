package com.hudongwx.drawlottery.mobile.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 全局异常处理:无法法针对404、403等多种状态进行处理
 */
@ControllerAdvice
public class AppExceptionHandler extends BaseController {

    /**
     * 全局404处理
     * @return
     */
    @ExceptionHandler(value = ServletException.class)
    @ResponseBody
    public JSONObject error404Handler(ServletException e){
        e.printStackTrace();
        e.printStackTrace();
        JSONObject object = new JSONObject();
        object.put("code",404);
        object.put("msg",e.getMessage());
        return  object;
    }


    /**
     * 登录次数超过限制异常
     * @return
     */
    @ExceptionHandler(value = ExcessiveAttemptsException.class)
    @ResponseBody
    public JSONObject errorLoginLimitHandler(ExcessiveAttemptsException e){
        e.printStackTrace();
        JSONObject object = new JSONObject();
        object.put("code",-2);
        object.put("msg",e.getMessage());
        return object;
    }

//    @ExceptionHandler(value = CaptchaServiceException.class)
    @ResponseBody
    public JSONObject errorHandler(){
        return null;
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject errorHandler(Exception e){
        e.printStackTrace();
        JSONObject object = new JSONObject();
        object.put("code",-1);
        object.put("msg",e.getMessage());
        return  object;
    }


}
