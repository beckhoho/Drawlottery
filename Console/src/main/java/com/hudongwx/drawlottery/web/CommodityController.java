package com.hudongwx.drawlottery.web;

import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Drawlottery.
 * Date: 2017/1/4 0004
 * Time: 14:45
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Controller
@RestController
@RequestMapping("/")
public class CommodityController {

    @Resource
    private IUserService userService;
    @RequestMapping("/")
    public User test(){
        return userService.queryUserByPhoneNum("000");
    }
}
