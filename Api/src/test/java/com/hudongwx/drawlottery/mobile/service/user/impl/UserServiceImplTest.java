package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.ThirdPartyLoginToken;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/31 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/31 9:32　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class UserServiceImplTest extends TestBaseMapper {

    @Autowired
    IUserService service;


    @Test
    public void testRegister() throws Exception {

    }

    @Test
    public void testQueryUserByPhoneNum() throws Exception {

    }

    @Test
    public void testGetUserInfo() throws Exception {

    }

    @Test
    public void testSelectHistoryLottery() throws Exception {
        List<Map<String, Object>> mapList = service.selectHistoryLottery(10000l);
        for (Map<String,Object> map: mapList) {
            System.out.println(map.get("commodityName"));
        }

    }

    @Test
    public void testSelectHistoryPay() throws Exception {
        List<Map<String, Object>> mapList = service.selectHistoryPay(10000l, 1);
        for (Map<String,Object> map: mapList) {
            System.out.println(map.get("userBuyNumber"));
        }
    }

    @Test
    public void testSelectToHistory() throws Exception {

    }

    @Test
    public void testSelectToNew() throws Exception {

    }


    public static void main(String[] args) {
        GenericApplicationContext parent = new GenericApplicationContext();
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setParent(parent);
        context.refresh();
        context.start();
        context.close();
    }

    @Test
    public void testRegisterAndLoginThirdParty() throws Exception {
        /*ThirdPartyLoginToken token  = new ThirdPartyLoginToken("0A87870128FC92F3A9C69CDDF5F63ED8","6E4D87DCEAD3DEA351B0A364976F6371",1);
        User user = service.registerAndLoginThirdParty(token);
        Assert.assertNotNull(user,"用户不为空");
        */
    }

}