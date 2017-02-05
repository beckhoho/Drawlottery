package com.hudongwx.drawlottery.mobile.web.commodity;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityTypeService;
import com.hudongwx.drawlottery.mobile.service.commodity.IHotSearchService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @version 1.0, 2016/12/22 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/22 0016　<br/>
 * <p>
 * 商品管理
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "CommodityController", description = "商品管理")
public class CommoditysController extends BaseController {
    @Autowired
    ICommodityService cService;
    @Autowired
    ICommodityTypeService ctService;
    @Autowired
    IHotSearchService hsService;

    /**
     * 搜索商品信息
     * 根据商品类别category、商品名称name 搜索
     * 搜索的四种情况：
     * 1、name和category都有值，两者并列搜索
     * 2、category无值，按name搜索
     * 3、name无值，按category搜索
     * 4、name和category都无值，搜索所有
     *
     * @param categoryId 商品类别
     * @param commName   商品名称
     * @param lastCommId 页数
     * @return JSONObject
     */
    @ResponseBody
    @ApiOperation("搜索商品信息（默认搜所有）参数：category 类型；commName 商品名; lastCommName 最后一个商品Id")
    @RequestMapping(value = "/api/v1/pub/commodity/search", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryCommoditys(@RequestParam(name = "categoryId", required = false) Integer categoryId, @RequestParam(name = "commName", required = false) String commName, @RequestParam(name = "lastCommId", required = false) Long lastCommId) {
        hsService.addHotSearch(commName);//统计商品搜索次数
        List<Map<String, Object>> infoList = cService.selectPaging(categoryId, commName, lastCommId);
        return success(infoList);
    }

    /**
     * 热门推荐
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/commodity/recommend", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryRecommend() {
        return success(hsService.queryRecommend());
    }

    /**
     * 按商品类型category搜索
     *
     * @param categoryId 商品类别
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/commodity/search/category", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryCommoditys(@RequestParam("categoryId") Integer categoryId, @RequestParam("lastCommId") Long lastCommId) {
        List<Map<String, Object>> infoList = cService.selectPaging(categoryId, null, lastCommId);
        return success(infoList);
    }

    /**
     * 按商品名称name搜索
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/commodity/search/name", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryCommoditys(@RequestParam("name") String name, @RequestParam("lastCommId") Long lastCommId) {
        hsService.addHotSearch(name);//统计商品搜索次数
        List<Map<String, Object>> infoList = cService.selectPaging(null, name, lastCommId);
        return success(infoList);
    }

    /**
     * 查看单件商品详情
     *
     * @param commodityid 商品id
     * @return
     */
    @ResponseBody
    @ApiOperation("查看单件商品详情")
    @RequestMapping(value = "/api/v1/pub/commodity/info", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryCommoditysInfo(@RequestParam("commid") Long commodityid) {
        Map<String, Object> map = cService.selectCommodity(getUser(),commodityid);
        return success(map);
    }

    /**
     * 查看开奖的商品
     *
     * @param page 页码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/commodity/onlottery", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryOnLotteryInfo(@RequestParam("page") int page) {
        List<Map<String, Object>> mapList = cService.selectOnLottery(page);
        return success(mapList);
    }

    /**
     * 查看单件正在开奖的商品
     *
     * @param commId 商品id
     * @return
     */
    @ResponseBody
    @ApiOperation("查看单件正在开奖的商品")
    @RequestMapping(value = "/api/v1/pub/commodity/onlottery/one", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryOnLotteryInfo(@RequestParam("commId") Long commId) {
        List<Map<String, Object>> mapList = cService.selectOneOnLottery(commId);
        return success(mapList);
    }

    /**
     * 最新揭晓
     *
     * @param lastCommId 商品id
     * @return
     */
    @ResponseBody
    @ApiOperation("最新揭晓")
    @RequestMapping(value = "/api/v1/pub/commodity/announce", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryNewestAnnounced(@RequestParam("lastCommId") Long lastCommId) {
        List<Map<String, Object>> mapList = cService.selectNewestAnnounceComm(lastCommId);
        return success(mapList);
    }
}