package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

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
        List<Map<String, Object>> mapList = service.selectHistoryLottery(1l);
        for (Map<String,Object> map: mapList) {
            System.out.println(map.get("commodityName"));
        }

    }

    @Test
    public void testSelectHistoryPay() throws Exception {
        List<Map<String, Object>> mapList = service.selectHistoryPay(2l, 1);
        for (Map<String,Object> map: mapList) {
            System.out.println(map.get("name"));
        }
    }

    @Test
    public void testSelectToHistory() throws Exception {

    }

    @Test
    public void testSelectToNew() throws Exception {

    }

}