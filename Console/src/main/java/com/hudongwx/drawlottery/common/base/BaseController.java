package com.hudongwx.drawlottery.common.base;

import com.hudongwx.drawlottery.common.constants.CommonConstants;
import com.hudongwx.drawlottery.common.dto.response.AjaxResult;
import com.hudongwx.drawlottery.pojo.User;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * controller 通用基类.
 * Date: 2017/1/4 0004
 * Time: 14:49
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class BaseController {

    @Resource
    protected CommonConstants commonConstants;

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @Resource
    protected MessageSource messageSource;

    /**
     * 将 request 和 response 注入方便使用.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    @ModelAttribute
    public void setReqAndRes(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        this.request = request;
        this.response = response;
    }

    protected User getCurrentUser() {
        return (User) request.getSession().getAttribute("user");
    }

    /* ============================     ajax    =================================================  */

    /**
     * 成功,返回状态
     *
     * @return ajax result
     */
    protected AjaxResult success() {
        return new AjaxResult(true);
    }

    /**
     * 成功,返回状态
     *
     * @param message the message
     * @return ajax result
     */
    protected AjaxResult success(final String message) {
        return new AjaxResult(true, message);
    }

    /**
     * Success ajax result.
     *
     * @param message the message
     * @param data    the data
     * @return the ajax result
     */
    protected AjaxResult success(final String message, final Object data) {
        return new AjaxResult(true, message, data);
    }

    /**
     * 返回json数据
     *
     * @param success 状态true/false
     * @param data    实体
     * @return ajax result
     */
    protected AjaxResult json(final Boolean success, final Object data) {
        return new AjaxResult(success, data);
    }

    /**
     * 失败,返回状态及原因
     *
     * @param message 消息
     * @return ajax result
     */
    protected AjaxResult fail(final String message) {
        return new AjaxResult(false, message);
    }

    /**
     * Fail ajax result.
     *
     * @param message the message
     * @param data    the data
     * @return the ajax result
     */
    protected AjaxResult fail(final String message, final Object data) {
        return new AjaxResult(false, message, data);
    }

    /**
     * 转换为ajax需要的 JSON
     *
     * @param success 状态
     * @param message 消息
     * @param entity  实体
     * @return json json
     */
    protected Map<String, Object> setJson(final boolean success, final String message, final Object entity) {
        Map<String, Object> json = new HashMap<>();
        json.put("success", success);
        json.put("message", message);
        json.put("entity", entity);
        return json;
    }

    /**
     * ajax成功/失败
     *
     * @param success 状态
     * @return json json
     */
    protected Map<String, Object> setJson(final boolean success) {
        Map<String, Object> json = new HashMap<>();
        json.put("success", success);
        return json;
    }

    /**
     * ajax成功/失败 + 消息
     *
     * @param success 状态
     * @param message 消息
     * @return json json
     */
    protected Map<String, Object> setJson(final boolean success, final String message) {
        Map<String, Object> json = new HashMap<>();
        json.put("message", message);
        json.put("success", success);
        return json;
    }

    /**
     * 返回的是工程项目的相对路径
     *
     * @return context path
     */
    protected String getContextPath() {
        return request.getContextPath();
    }

    /**
     * 重定向至地址 url
     *
     * @param url 请求地址
     * @return string string
     */
    protected String redirectTo(final String url) {
        StringBuffer rto = new StringBuffer("redirect:");
        rto.append(url);
        return rto.toString();
    }

    /**
     * 生成随机数
     *
     * @param count 生成个数
     * @return String random num
     */
    protected String getRandomNum(final int count) {
        Random ra = new Random();
        String random = "";
        for (int i = 0; i < count; i++) {
            random += ra.nextInt(9);
        }
        return random;
    }

}
