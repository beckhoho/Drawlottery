package com.hudongwx.drawlottery.mobile.web;

import com.alibaba.fastjson.JSONObject;
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
 *
 * 全局异常处理:无法法针对404、403等多种状态进行处理
 *
 */
@ControllerAdvice
public class AppExceptionHandler {

    /**
     * 全局404处理
     * @return
     */
    @ExceptionHandler(value = ServletException.class)
    @ResponseBody
    public JSONObject error404Handler(ServletException e){
        JSONObject object = new JSONObject();
        object.put("code",404);
        object.put("msg",e.getClass().getName()+"------"+e.getMessage());
        return  object;
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject errorHandler(Exception e){
        JSONObject object = new JSONObject();
        object.put("code",-1);
        object.put("msg",e.getMessage());
        return  object;
    }

}
