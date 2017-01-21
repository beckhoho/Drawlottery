package com.hudongwx.drawlottery.mobile.web.commodity;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityHistoryService;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.notification.ILuckNoticeService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @version 1.0, 2016/12/22 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/22 0016　<br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "CommodityHistoryController", description = "商品历史信息管理")
public class CommodityHistoryController extends BaseController {

    @Autowired
    ICommodityHistoryService chService;

    @Autowired
    ICommodityService commodityService;

    @Autowired
    ILuckNoticeService noticeService;

    /**
     * 查看商品历史
     *
     * @param accountid  账号
     * @param lastcommid 客户端先手的最后一个商品id
     * @param tag        判断是上拉还是下拉刷新（状态标记：0 第一次计入页面，1下拉刷新，2上拉刷新）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commhistory/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryCommodityHistoryInfo(@RequestParam("acc") Long accountid, @RequestParam("chid") Long lastcommid, @RequestParam("tag") int tag) {
        List<CommodityHistory> chlist = new ArrayList<>();// TODO: 2016/12/24 查询用户购买的商品 参数(Long accountid)
        return success(chlist);
    }

    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commhistory/lottery", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addUserLuckNotice(@RequestParam("commodityId") Long commodityId) {
        return success(noticeService.addUserLuckNotice(commodityId));
    }
    /**
     * 通过商品Id(commId)浏览往期揭晓
     *
     * @param commId
     * @return
     */
    @ResponseBody
    @ApiOperation("通过商品Id(commId)浏览往期揭晓")
    @RequestMapping(value = "/api/v1/user/commhistory/announced/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryThePastAnnouncedCommList(@ApiParam("商品Id")@RequestParam("commId") Long commId) {
        return success(commodityService.selectThePastAnnouncedCommList(commId));
    }

    /**
     * 浏览商品中奖信息
     * @return
     */
    @ResponseBody
    @ApiOperation("通过商品Id(commId)浏览中奖信息")
    @RequestMapping(value = "/api/v1/user/commhistory/announced/lottertyinfo", method = {RequestMethod.POST, RequestMethod.GET})
    public LotteryInfo queryLotteryInfo(@ApiParam("商品Id")@RequestParam("commId") Long commId){
        return chService.selectLotteryInfo(commId);
    }
}
