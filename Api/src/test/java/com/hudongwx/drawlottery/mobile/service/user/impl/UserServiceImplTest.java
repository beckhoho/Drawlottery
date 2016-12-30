package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
/**
 * Created by wu on 2016/12/22.
 */

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/22 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/22 0016　<br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class UserServiceImplTest extends TestBaseMapper {

    @Autowired
    IUserService userService;


    @org.testng.annotations.Test
    public void testRegister() throws Exception {
        User login = userService.login("1", "2");
        Assert.assertNotNull(login,"登录成功了！！");
    }

    @org.testng.annotations.Test
    public void testLogin() throws Exception {
    }

    @org.testng.annotations.Test
    public void testIsExist() throws Exception {

    }

    @org.testng.annotations.Test
    public void testGetUserByAccount() throws Exception {

    }

}