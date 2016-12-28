package com.hudongwx.drawlottery.mobile.web.index;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.Images;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.images.ImagesService;
import com.hudongwx.drawlottery.mobile.service.notification.INotificationPrizeService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/27 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/27 <br/>
 * <p>
 * 手机客户端首页
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "AppIndexController", description = "客户端首页信息管理")
public class AppIndexController extends BaseController {

    @Autowired
    ImagesService iService;
    @Autowired
    ICommodityService cService;
    @Autowired
    INotificationPrizeService npService;

    /**
     * 客户端首页的banner（广告栏）图片
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/index/banner")
    public JSONObject banner() {
        List<Images> iList = iService.selectAdvert();
        // TODO: 2016/12/27 获取广告图片url（genre==0）
        return success(iList);
    }

    /**
     * 客户端首页的快捷图标
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/index/quick")
    public JSONObject quick() {
        List<Images> iconsList = iService.selectIcon();
        return success(iconsList);
    }

    /**
     * 手机端主页最新通知消息
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/index/notice", method = RequestMethod.GET)
    public JSONObject notice() {
        List<String> nList = npService.selectByNew();// TODO: 2016/12/27 获取通知（genre==2）
        return success(nList);
    }

    /**
     * 客户端首页最新揭晓板块信息（10条数据）
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/index/announce")
    public JSONObject announceCommodityInfo() {
        List<Commoditys> cList = cService.selectOnLottery();
        return success(cList);
    }

    /**
     * 客户端首页商品板块信息（默认人气搜索）
     *
     * @param ref             刷新方式(0、初始刷新，1、下拉刷新，2、上拉加载)
     * @param type            排序类型(1、按人气，2、按最快，3、最新，4、高价)
     * @param lastcommodityid 末尾商品id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/index/commodity")
    public JSONObject orderCommodityInfo(@RequestParam("ref") int ref, @RequestParam("type") int type, @RequestParam("commid") long lastcommodityid) {
        List<Commoditys> cList = cService.selectByStyle(ref, type, lastcommodityid);
        return success(cList);
    }

}
