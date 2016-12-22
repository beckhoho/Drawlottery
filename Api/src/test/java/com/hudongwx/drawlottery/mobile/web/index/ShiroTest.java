package com.hudongwx.drawlottery.mobile.web.index;

import com.hudongwx.drawlottery.mobile.ApiApplication;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * DATE:十二月
 * Author: origin
 * DESC:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApiApplication.class})
public class ShiroTest extends TestBaseWeb {

    @Override
    public Object getController() {

        return null;
    }
}
