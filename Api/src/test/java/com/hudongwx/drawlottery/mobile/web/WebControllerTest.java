package com.hudongwx.drawlottery.mobile.web;

import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Drawlottery.
 * Date: 2017/1/24 0024
 * Time: 2:59
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class WebControllerTest extends TestBaseWeb {
    @Resource
    private WebController webController;
    @Override
    public Object getController() {
        return webController;
    }

    @Test
    public void testCalc(){
        try {
            get("/web/commodityCalc/29","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
