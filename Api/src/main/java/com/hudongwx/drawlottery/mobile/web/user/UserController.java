package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DATE:2016-12-2016/12/14 0014-23:46
 * Author: origin
 * DESC:
 */
@RestController
@Api(value = "UserController", description = "用户管理")
public class UserController extends BaseController {
    @Autowired
    IUserService usersService;

    @RequestMapping(value = "/user/register")
    public JSONObject register(@RequestBody User user) {
        System.out.println("用户账号：------------>" + user.getAccountId());
        JSONObject jsonObject = new JSONObject();
        if (usersService.isExist(user.getAccountId())) {
            jsonObject.put("msg", "该账号已存在！");
            jsonObject.put("code", -1);
        } else {
            if (usersService.register(user)) {
                jsonObject.put("msg", "用户账号创建成功！");
                jsonObject.put("code", 200);
            } else {
                jsonObject.put("msg", "用户账号创建不合规范！");
                jsonObject.put("code", -1);
            }
        }
        return jsonObject;
    }
}
