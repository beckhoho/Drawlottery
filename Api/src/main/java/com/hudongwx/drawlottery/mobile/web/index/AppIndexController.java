package com.hudongwx.drawlottery.mobile.web.index;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.advertisement.IAdvertisementService;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.images.ImagesService;
import com.hudongwx.drawlottery.mobile.service.notification.INotificationPrizeService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
@Api(value = "AppIndexController", description = "android APP 首页信息")
public class AppIndexController extends BaseController {

    @Autowired
    ImagesService iService;
    @Autowired
    IAdvertisementService adService;
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
    @RequestMapping(value = "/api/v1/index/banner", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject banner() {
        List<Map<String, Object>> infoList = adService.selectAdvertisement();
        return success(infoList);
    }

    /**
     * 客户端首页的快捷图标
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/index/quick", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject quick() {
        List<Map<String, Object>> infoList = iService.selectIcon();
        return success(infoList);
    }

    /**
     * 手机端主页最新通知消息
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/index/notice", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject notice() {
        return success(npService.selectUserPrizeNotify(getUserId()));
    }

    /**
     * 客户端首页最新揭晓板块信息（10条数据）
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/index/announce", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject announceCommodityInfo() {
        List<Map<String, Object>> infoList = cService.selectOnLottery(1);
        return success(infoList);
    }

    /**
     * 客户端首页商品板块信息（默认人气搜索）
     *
     * @param type 排序类型(1、按人气，2、按最快，3、最新，4、高价，5高中奖率，14、三人夺宝)
     * @return JSONObject
     */
    @ResponseBody
    @ApiOperation("客户端首页商品板块信息（默认人气搜索）参数：type 排序类型(1、按人气，2、按最快，3、最新，4、高价，5高中奖率，14,三人夺宝，其他数字默认人气)")
    @RequestMapping(value = "/api/v1/index/commodity", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject orderCommodityInfo(@ApiParam("排序类型") @RequestParam(name = "type",required = false) Integer type, @ApiParam("当前页最后一个item的id") @RequestParam(name = "lastCommId",required = false) Long lastCommId) {
        List<Map<String, Object>> infoList = cService.selectByStyle(type, lastCommId);
        return success(infoList);
    }

}
