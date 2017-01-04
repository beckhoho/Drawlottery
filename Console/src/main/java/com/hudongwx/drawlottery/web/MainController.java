package com.hudongwx.drawlottery.web;

import com.hudongwx.drawlottery.common.base.BaseController;
import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.user.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Drawlottery.
 * Date: 2017/1/5 0005
 * Time: 2:20
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Controller
public class MainController extends BaseController {
    @Resource
    private IUserService userService;

    @RequestMapping("/")
    public ModelAndView main() {
        final ModelAndView modelAndView = new ModelAndView("index");
        final User user = userService.queryUserByPhoneNum("13990949387");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
