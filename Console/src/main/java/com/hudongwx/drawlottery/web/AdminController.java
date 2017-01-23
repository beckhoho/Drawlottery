package com.hudongwx.drawlottery.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Drawlottery.
 * Date: 2017/1/23 0023
 * Time: 0:05
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Controller
@RequestMapping
public class AdminController {
    @RequestMapping("")
    public String admin() {
        return "index.html";
    }
}
