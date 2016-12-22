package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wu on 2016/12/20.
 */
@RestController
@Api(value = "RedPacketsController", description = "红包管理")
public class RedPacketsController {
    @Autowired
    IRedPacketsService rpService;

    @RequestMapping(value = "/user/rPacket/add")
    public JSONObject add(@RequestBody RedPackets rps) {
        JSONObject json = new JSONObject();
        System.out.println("红包信息：-------->" + rps.toString());
        if (rpService.addRP(rps)) {
            json.put("msg", "红包创建成功！");
            json.put("code", 200);
        } else {
            json.put("msg", "红包创建失败！");
            json.put("code", -1);
        }
        return json;
    }

    @RequestMapping(value = "/user/rPacket/search")
    public JSONObject search(@RequestBody RedPackets rps) {
        JSONObject json = new JSONObject();
        System.out.println("红包使用条件：-------->" + rps.getUsePrice());

        if (rpService.addRP(rps)) {
            json.put("data","");
            json.put("msg", "无相关信息！");
            json.put("code", 200);
        } else {
            json.put("data",null);
            json.put("msg", "无相关信息！");
            json.put("code", -1);
        }
        return json;
    }

}
