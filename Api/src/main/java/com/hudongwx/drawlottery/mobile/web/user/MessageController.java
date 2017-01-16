package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.Date;

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

    /**
     * 查询所有的未读消息数量
     * @return
     */
    @ApiOperation(value = "queryAllMessageSize", notes = "查询所有的消息未读取消息数", httpMethod = "POST,GET", responseContainer = "{object.put(\"0\", 20);//系统消息\n" +
            "        object.put(\"1\", 10);//中奖消息\n" +
            "        object.put(\"2\", 0);//发货消息}")
    @RequestMapping(value = "/query/count",method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryAllMessageSize() {
        //0系统消息
        //1中奖消息
        //2发货消息
        JSONObject object = new JSONObject();
        object.put("0", 20);//系统消息
        object.put("1", 10);//中奖消息
        object.put("2", 0);//发货消息
        return success(object);
    }

    /**
     * 指定类型的消息
     * @return
     */
    @ApiOperation(value = "消息列表查询", notes = "支持分页查询",responseContainer = "{title:标题,id:'1000',content:'内容',date:'日期'}",code = 200, produces = "application/json")
    @RequestMapping(value = "/query/list/{typeid}/{msgid}",method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryMessageByType(
            @ApiParam(name = "typeid", value = "消息类型id", required = true) @PathVariable("typeid") int typeid,
            @ApiParam(name = "msgid", value = "消息id", required = false) @PathVariable(value = "msgid",required = false)String msgid){
        JSONArray data = new JSONArray();
        if (typeid == 0) {
            for (int i = 0; i < 30; i++) {
                JSONObject object = new JSONObject();
                object.put("title", "系统消息标题");
                object.put("id", 1000+i);
                object.put("content", "消息内容.....................第" + i);
                object.put("date", new Date().toString());
                data.add(object);
            }
        } else if (typeid == 1) {
            for (int i = 0; i < 30; i++) {
                JSONObject object = new JSONObject();
                object.put("title", "中奖消息标题");
                object.put("id", 1000+i);
                object.put("content", "消息内容.....................第" + i);
                object.put("date", new Date().toString());
                data.add(object);
            }
        } else if (typeid == 2) {
            for (int i = 0; i < 30; i++) {
                JSONObject object = new JSONObject();
                object.put("title", "发货消息标题");
                object.put("id", 1000+i);
                object.put("content", "消息内容.....................第" + i);
                object.put("date", new Date().toString());
                data.add(object);
            }
        } else {
            return fail(-1, "请求错误");
        }
        return success(data);
    }

}
