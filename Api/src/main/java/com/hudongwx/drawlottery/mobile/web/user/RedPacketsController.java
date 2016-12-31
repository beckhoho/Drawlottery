package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.RedPackets;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 获取用户可用的红包
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/redpacket/usable", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryUsableRedPacket() {
        getUser();
        List<RedPackets> rplist = new ArrayList<>();//// TODO: 2016/12/23  获取用户可用的红包的集合
        return success(rplist);
    }

    /**
     * 获取用户不可用的红包
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/redpacket/unusable", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryUnusableRedPacket() {
        List<RedPackets> rplist = new ArrayList<>();//// TODO: 2016/12/23  获取用户不可用的红包的集合
        return success(rplist);
    }

    /**
     * 用户使用红包
     *
     * @param rp 客户端发来的红包信息修改状态
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/redpacket/up", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject updateRedPacket(@RequestParam("packet") RedPackets rp) {
        boolean status = true;//// TODO: 2016/12/23  修改红包状态？？？
        return response(status);
    }

}
