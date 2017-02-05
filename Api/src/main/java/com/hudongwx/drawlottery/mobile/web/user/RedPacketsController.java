package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.order.IOrdersService;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/16 <br/>
 * @desc 用户红包管理
 * <p>
 * <p>
 * 创建　wu　2016/12/16 <br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "RedPacketsController", description = "用户红包管理")
public class RedPacketsController extends BaseController {

    @Autowired
    IRedPacketsService rpService;
    @Autowired
    IOrdersService oService;

    /**
     * 获取用户红包
     *
     * @return JSONObject
     */
    @ResponseBody
    @ApiOperation("获取用户所有红包")
    @RequestMapping(value = "/api/v1/user/redpacket/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserRedPacket() {
        return success(rpService.selectAllByUserAccountId(getUserId()));
    }

    /**
     * 使用红包
     *
     * @return JSONObject
     */
    @ResponseBody
    @ApiOperation("使用红包")
    @RequestMapping(value = "/api/v1/user/redpacket/use", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject updateUserRedPacket(@RequestParam("price") Integer price) {
        return success(oService.selectUsableRedPackets(getUserId(),price));
    }

}
