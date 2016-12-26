package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 <br/>
 * @desc 用户管理
 * <p>
 * <p>
 * 创建　origin　2016/12/16 <br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "UserController", description = "用户管理")
public class UserController extends BaseController {
    @Autowired
    IUserService usersService;

    /**
     * 用户注册
     *
     * @param accountid 注册账号
     * @param pwd       注册密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public JSONObject register(@RequestParam("acc") Long accountid, @RequestParam("pwd") String pwd) {
        boolean isExist = usersService.isExist(accountid);
        if (!isExist) {
            boolean status = usersService.register(accountid, pwd);
            if (status)
                return success("注册成功！");
            return fail("注册失败！");
        } else {
            return fail("账号已存在");
        }
    }

    /**
     * 用户登录
     *
     * @param accountid 账号
     * @param pwd       密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public JSONObject login(@RequestParam("acc") Long accountid, @RequestParam("pwd") String pwd) {
        boolean status = usersService.login(accountid.toString(), pwd) != null;
        if (status) {
            return success("登录成功！");
        } else {
            return fail("登录失败！");
        }
    }
}
