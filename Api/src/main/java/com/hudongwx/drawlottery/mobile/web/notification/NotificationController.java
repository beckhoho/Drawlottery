package com.hudongwx.drawlottery.mobile.web.notification;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/24 <br/>
 * @desc 通知管理<p>
 * <p>
 * 创建　wu　2016/12<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "NotificationController", description = "消息通知管理")
public class NotificationController extends BaseController {

    /**
     * 用户中奖消息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/notify/prize", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserPrizeMessage() {
        List<NotificationPrize> nlist = null;// TODO: 2016/12/24 获取通知信息
        return success(nlist);
    }

    /**
     * 快递消息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/notify/deliverymsg", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryDeliveryMessage() {
        List<NotificationPrize> nlist = null;// TODO: 2016/12/24 获取通知信息
        return success(nlist);
    }

    /**
     * 客服反馈消息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/notify/feedback", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryFeedBack() {
        List<NotificationPrize> nlist = null;// TODO: 2016/12/24 获取通知信息
        return success(nlist);
    }

    /**
     * 系统消息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/notify/sys", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject querySystemMessage() {
        List<NotificationPrize> nlist = null;// TODO: 2016/12/24 获取通知信息
        return success(nlist);
    }

    /**
     * 活动消息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/notify/campaign", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryCampaignMessage() {
        List<NotificationPrize> nlist = null;// TODO: 2016/12/24 获取通知信息
        return success(nlist);
    }
}
