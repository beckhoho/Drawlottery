package com.hudongwx.drawlottery.mobile.web;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/17 0017 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/17 0017　<br/>
 * <p>
 * 控制层基类
 * <p>
 * @email 294786949@qq.com
 */
public abstract class BaseController {

    /**
     * 获取当前登录的用户id
     * @return
     */
    public Long getUserId() {
        Long userId = getUser().getAccountId();
        return userId;
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    public User getUser() {
        Object principal = getSubject().getPrincipal();
        User user = (User) principal;
        return user;
    }

    public Session getSession() {
        return getSubject().getSession();
    }

    public String getSessionId() {
        return (String) getSession().getId();
    }

    /**
     * 获取当前用户的的Subject对象
     *
     * @return
     */
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 是否记住密码
     *
     * @return
     */
    public boolean isRemembered() {
        return getSubject().isRemembered();
    }

    /**
     * 用户是否已经验证通过
     *
     * @return
     */
    public boolean isAuthenticated() {
        return getSubject().isAuthenticated();
    }

    /**
     * 用户登录
     *
     * @param token
     * @throws AuthenticationException
     */
    public void login(AuthenticationToken token) throws AuthenticationException {
        getSubject().login(token);
    }

    /**
     * 成功返回
     *
     * @param data 返回的数据
     * @return
     */
    public JSONObject success(Object data) {
        return success("操作成功", data);
    }

    /**
     * 成功,没有返回的数据
     *
     * @return
     */
    public JSONObject success() {
        return success("操作成功", "");
    }

    /**
     * 成功返回
     *
     * @param msg
     * @return
     */
    public JSONObject success(String msg) {
        return success(msg, "");
    }

    /**
     * 成功返回数据
     *
     * @param msg  返回信息
     * @param data 返回数据
     * @return
     */
    public JSONObject success(String msg, Object data) {
        return response(200, msg, data);
    }

    /**
     * 万能数据响应
     *
     * @param isOk
     * @param sucMsg
     * @return
     */
    public JSONObject response(boolean isOk, String sucMsg, String failMsg) {
        return isOk ? success(sucMsg) : fail(failMsg);
    }

    /**
     * 万能数据响应
     *
     * @param isOk
     * @param msg
     * @return
     */
    public JSONObject response(boolean isOk, String msg) {
        return isOk ? success(msg) : fail(msg);
    }

    /**
     * 万能数据响应
     *
     * @param isOk
     * @return
     */
    public JSONObject response(boolean isOk) {
        return isOk ? success() : fail();
    }

    /**
     * 响应数据
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    private JSONObject response(int code, String msg, Object data) {
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("data", data);
        object.put("msg", msg);
        return object;
    }

    /**
     * 操作失败
     *
     * @param code 失败编码
     * @param msg  失败信息
     * @return
     */
    public JSONObject fail(int code, String msg) {
        return response(code, msg, "");
    }

    /**
     * 操作失败
     *
     * @param msg 失败信息
     * @return
     */
    public JSONObject fail(String msg) {
        return response(-1, msg, null);
    }

    /**
     * 操作失败
     *
     * @return
     */
    public JSONObject fail() {
        return response(-1, "操作失败", null);
    }

    /**
     * 登出
     */
    public void logout() {
        getSubject().logout();
    }

}
