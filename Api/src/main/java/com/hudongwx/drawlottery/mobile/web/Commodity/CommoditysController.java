package com.hudongwx.drawlottery.mobile.web.commodity;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityTypeService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
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
     * @param lastCommId 当前最后一个显示的商品id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/commodity/search", method = RequestMethod.POST)
    public JSONObject queryCommoditys(@RequestParam("categoryId") int categoryId, @RequestParam("commName") String commName, @RequestParam("commId") long lastCommId) {
        List<Map<String, Object>> infoList = cService.selectPaging(categoryId, commName, lastCommId);
        return success(infoList);
    }

    /**
     * 查看单件商品详情
     *
     * @param commodityid 商品id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/commodity/info")
    public JSONObject queryCommoditysInfo(@RequestParam("commid") Long commodityid) {
        Map<String, Object> map = cService.selectCommodity(commodityid);
        return success(map);
    }

    /**
     * 查看单件商品详情
     *
     * @param commodityid 商品id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/commodity/onlottery")
    public JSONObject queryOnLotteryInfo(@RequestParam("commid") Long commodityid) {
        Map<String, Object> map = cService.selectCommodity(commodityid);
        return success(map);
    }

}


