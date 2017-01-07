package com.hudongwx.service.user;

import com.hudongwx.TestBaseMapper;
import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.user.IUserService;
import org.junit.Test;

import javax.annotation.Resource;

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

    @Resource
    private IUserService userService;

    @Test
    public void testQueryUserByPhoneNum(){

        logger.error("start");
        final User user = userService.queryUserByPhoneNum("12321");
        logger.error(user+"");
    }
}