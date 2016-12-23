package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import com.hudongwx.drawlottery.mobile.web.util.WebCommonUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 <br/>
 * @desc 用户管理
 * <p>
 * <p>
 * 创建　origin　2016/12/16 <br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "UserController", description = "用户管理")
public class UserController extends BaseController {
    @Autowired
    IUserService usersService;

    /**
     * 用户注册
     *
     * @param user 客户端传来的User信息
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public JSONObject register(@RequestBody User user) {
        boolean isExist = usersService.isExist(user.getAccountId());// TODO: 2016/12/23 注册前先判断账号是否已注册
        if (!isExist) {
            boolean status = usersService.register(user);// TODO: 2016/12/23 用户注册
            return WebCommonUtils.buildStatusJsonObj(status, "注册成功！", "注册失败！");
        } else {
            return WebCommonUtils.buildStatusJsonObj(false, null, "账号已存在！");
        }
    }


}
