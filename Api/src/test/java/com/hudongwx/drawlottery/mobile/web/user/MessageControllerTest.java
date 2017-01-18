package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by 11 on 2017/1/17.
 */
public class MessageControllerTest extends TestBaseWeb {
    @Test
    public void testQueryAllMessageSize() throws Exception {
        JSONObject jsonObject = messageController.queryAllMessageSize();
        System.out.println(jsonObject);
    }

    @Autowired
    MessageController messageController;

    @Test
    public void testQueryMessageByType() throws Exception {
        //JSONObject jsonObject = messageController.queryMessageByType(0, null);
        //System.out.println(jsonObject);
    }

    @Override
    public Object getController() {
        return messageController;
    }
}