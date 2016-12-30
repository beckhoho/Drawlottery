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
@Api(value = "NotificationController",description = "通知管理")
public class NotificationController extends BaseController {

    /**
     * 通知列表信息
     *
     * @param accountid 用户账号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/notify/show", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryUserNotification(@RequestParam("acc") Long accountid) {
        List<NotificationPrize> nlist = null;// TODO: 2016/12/24 获取通知信息
        return success(nlist);
    }

    /**
     * 通知列表信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/notify/show", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryNotification() {
        List<NotificationPrize> nlist = null;// TODO: 2016/12/24 获取通知信息
        return success(nlist);
    }
}
