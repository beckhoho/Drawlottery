package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/api/v1/user/info", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserInfo() {
        User user = getUser();//获取当前用户信息
        Map<String, Object> userInfo = userService.getUserInfo(userService.queryUserByPhoneNum("13990949387"));
        return success(userInfo);
    }

    /**
     * 获取用户中奖记录
     *
     * @param page 页码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/win", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserWinningHistory(@RequestParam("page") int page) {
        User user = getUser();//获取当前用户信息
        List<Map<String, Object>> historyLottery = userService.selectHistoryLottery(2L);
        return success(historyLottery);
    }

    /**
     * 获取用户夺宝记录
     *
     * @param item 获取数据形式
     * @param page 页码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/usercomm", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserCommRecord(@RequestParam("item") int item, @RequestParam("page") int page) {
        User user = getUser();//获取当前用户信息
        List<Map<String, Object>> historyLottery = userService.selectHistoryPay(2L, item);
        return success(historyLottery);
    }

}
