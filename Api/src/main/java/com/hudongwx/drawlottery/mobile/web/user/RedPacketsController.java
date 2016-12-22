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
        JSONObject jo = new JSONObject();
        System.out.println("红包名字：-------->" + rps.getName());
        if (rpService.addRP(rps)) {
            jo.put("msg", "红包创建成功！");
            jo.put("code", 200);
        } else {
            jo.put("msg", "红包创建失败！");
            jo.put("code", -1);
        }
        return jo;
    }
}
