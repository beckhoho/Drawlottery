package com.hudongwx.drawlottery.common.config.handlers;

import com.hudongwx.drawlottery.common.dto.response.AjaxResult;
import com.hudongwx.drawlottery.common.exceptions.ControllerException;
import com.hudongwx.drawlottery.common.exceptions.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Drawlottery.
 * Date: 2017/1/10 0010
 * Time: 22:30
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@ControllerAdvice
public class GlobalExpHandler {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public AjaxResult handleServiceException(HttpServletRequest request, HttpServletResponse response, ServiceException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        AjaxResult ajaxResult = new AjaxResult(false, e.getMessage());
        ajaxResult.setData(request.getRequestURL().toString());
        return ajaxResult;
    }

    @ExceptionHandler(ControllerException.class)
    @ResponseBody
    public AjaxResult handleControllerException(HttpServletRequest request, HttpServletResponse response, ServiceException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        AjaxResult ajaxResult = new AjaxResult(false, e.getMessage());
        ajaxResult.setData(request.getRequestURL().toString());
        return ajaxResult;
    }
}
