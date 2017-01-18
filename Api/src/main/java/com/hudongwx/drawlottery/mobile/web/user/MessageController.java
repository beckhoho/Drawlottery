package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.NotificationCampaignMapper;
import com.hudongwx.drawlottery.mobile.mappers.NotificationPrizeMapper;
import com.hudongwx.drawlottery.mobile.mappers.NotificationSystemMapper;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.*;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    NotificationCampaignMapper campaignMapper;
    @Autowired
    NotificationPrizeMapper prizeMapper;
    @Autowired
    NotificationSystemMapper systemMapper;

    /**
     * 查询所有的未读消息数量
     *
     * @return
     */
    @ApiOperation(value = "queryAllMessageSize", notes = "查询所有的消息未读取消息数", httpMethod = "POST,GET", responseContainer = "{object.put(\"0\", 20);//系统消息\n" +
            "        object.put(\"1\", 10);//中奖消息\n" +
            "        object.put(\"2\", 0);//发货消息}")
    @RequestMapping(value = "/query/count", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryAllMessageSize() {
        //0系统消息
        //1中奖消息
        //2发货消息
//        long userId = getUserId();
        long userId = 10000L;
        JSONObject object = new JSONObject();
        object.put("0", systemMapper.countUnreadMsg(userId));//系统消息
        object.put("1", prizeMapper.countUnreadMsg(userId));//中奖消息
        object.put("2", campaignMapper.countUnreadMsg(userId));//发货消息
        return success(object);
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
    public JSONObject queryMessageByType(@RequestParam int typeid,@RequestParam(value = "msgid",required = false) String msgid) {
        JSONArray data = new JSONArray();
        Long userId = getUserId();
        if (typeid == 0) {
            List<NotificationSystem> notificationSystems = systemMapper.selectLimitTen(userId, msgid);
            for (NotificationSystem notificationSystem : notificationSystems) {
                if (addJsonObj(notificationSystem, data)) {//消息未读，则更改状态
                    systemMapper.updateStateById(notificationSystem.getId());
                }
            }
        } else if (typeid == 1) {
            List<NotificationPrize> notificationPrizes = prizeMapper.selectLimitTen(userId, msgid);
            for (NotificationPrize notificationPrize : notificationPrizes) {
                if (addJsonObj(notificationPrize, data)) {
                    prizeMapper.updateStateById(notificationPrize.getId());
                }
            }
        } else if (typeid == 2) {
            List<NotificationCampaign> notificationCampaigns = campaignMapper.selectLimitTen(userId, msgid);
            for (NotificationCampaign notificationCampaign : notificationCampaigns) {
                if (addJsonObj(notificationCampaign, data)) {
                    campaignMapper.updateStateById(notificationCampaign.getId());
                }
            }
        } else {
            return fail(-1, "请求错误");
        }
        return success(data);
    }

    public boolean addJsonObj(Notification notification, JSONArray data) {
        boolean flag = false;
        if (notification.getState() == 0) {//改消息为已读
            notification.setId(notification.getId());
            notification.setState(1);
            flag = true;//消息状态需更改
        }
        JSONObject object = new JSONObject();
        object.put("title", notification.getNoticeTitle());
        object.put("id", notification.getId());
        object.put("content", notification.getNoticeContent());
        object.put("date", notification.getSendDate().toString());
        data.add(object);
        return flag;
    }
}
