package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IMessageService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/13 0013 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/13 0013　<br/>
 * <p>
 * 用户消息
 * <p>
 * @email 294786949@qq.com
 */
@Api(value = "MessageController", description = "用户消息管理")
@RequestMapping(value = "/api/v1/priv/user/message/")
@RestController
public class MessageController extends BaseController {
    @Autowired
    IMessageService messageService;

    /**
     * 查询所有的未读消息数量
     *
     * @return
     */
    @ApiOperation(value = "queryAllMessageSize", notes = "查询所有的消息未读取消息数", responseContainer = "{object.put(\"0\", 20);//系统消息\n" +
            "        object.put(\"1\", 10);//中奖消息\n" +
            "        object.put(\"2\", 0);//发货消息}")
    @RequestMapping(value = "/query/count", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryAllMessageSize() {
        return success(messageService.queryAllMessageSize(getUserId()));
    }

    /**
     * 指定类型的消息
     *
     * @return
     */
    @ApiOperation(value = "消息列表查询", notes = "支持分页查询", responseContainer = "{title:标题,id:'1000',content:'内容',date:'日期'}", code = 200, produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "typeid", dataType = "int", required = true),
            @ApiImplicitParam(paramType = "query", name = "msgid", dataType = "int", required = false)
    })
    @RequestMapping(value = "/query/list", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryMessageByType(@RequestParam int typeid, @RequestParam(value = "msgid", required = false) String msgid) {
        JSONArray data = messageService.queryMessageByType(getUserId(), msgid, typeid);
        if (data == null) {
            return fail(-1, "请求错误");
        }
        return success(data);
    }

}
