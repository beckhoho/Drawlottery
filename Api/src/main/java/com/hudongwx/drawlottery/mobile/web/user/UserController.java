package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
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
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/user/register")
    public JSONObject register(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();

        return jsonObject;
    }
}
