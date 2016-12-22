package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Users;
import com.hudongwx.drawlottery.mobile.service.user.IUsersService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    IUsersService usersService;

    @RequestMapping(value = "/user/register")
    public JSONObject register(@RequestBody Users users) {
        System.out.println("用户账号：------------>" + users.getAccountId());
        JSONObject jo = new JSONObject();
        if (usersService.isExist(users.getAccountId())) {
            jo.put("msg", "该账号已存在！");
            jo.put("code", -1);
        } else {
            if (usersService.register(users)) {
                jo.put("msg", "用户账号创建成功！");
                jo.put("code", 200);
            } else {
                jo.put("msg", "用户账号创建不合规范！");
                jo.put("code", -1);
            }
        }
        return jo;
    }

        return jsonObject;
    }
}
