package com.hudongwx.drawlottery.mobile.web.auth;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户认证
 */
@RestController
public class AuthorController extends BaseController {

    @Autowired
    IUserService usersService;

    /**
     * 用户登录,post
     *
     * @return
     */
    @RequestMapping(value = "/api/v1/pub/auth/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody(required = true) UsernamePasswordToken token) {
        JSONObject object = new JSONObject();
        //获取用户信息
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {//是否已经验证过
            try {
                token.setRememberMe(true);
                subject.login(token);
                object.put("code", 200);
                object.put("msg", "登录成功");
            } catch (UnknownAccountException e) {//账号不存在
                object.put("code", -1);
                object.put("msg", "账号错误");
            } catch (LockedAccountException e) {//账号锁定了
                object.put("code", -1);
                object.put("msg", "账号被禁用");
            } catch (AuthenticationException e) {
                object.put("code", -1);
                object.put("msg", "用户名或密码错误");
            }
        }
        return object;
    }

    /**
     * 用户登出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/api/v1/pub/auth/logout")
    public void logout() {
        logout();
    }


    /**
     * 用户注册
     *
     * @param phone    注册账号
     * @param password 注册密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/auth/register", method = RequestMethod.POST)
    public JSONObject register(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        boolean register = usersService.register(phone, password);
        return response(register, "注册成功!", "注册失败!");
    }


}
