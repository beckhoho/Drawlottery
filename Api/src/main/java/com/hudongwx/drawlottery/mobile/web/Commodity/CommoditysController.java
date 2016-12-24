package com.hudongwx.drawlottery.mobile.web.commodity;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityTypeService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
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
     * @param category        商品类别
     * @param name            商品名称
     * @param lastcommodityid 当前最后一个显示的商品id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/commodity/search",method = RequestMethod.POST)
    public JSONObject queryCommoditys(@RequestParam("category") String category, @RequestParam("name") String name, @RequestParam("commid") Long lastcommodityid) {
        List<Commoditys> clist = new ArrayList<>();// TODO: 2016/12/24  根据商品id查询单件商品详情 参数(String category,String name , Long commodityid)
        return success(clist);
    }

    /**
     * 查看单件商品详情
     *
     * @param commodityid 商品id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/commodity/info",method = RequestMethod.POST)
    public JSONObject queryCommoditysInfo(@RequestParam("commid") Long commodityid) {
        Commoditys com = new Commoditys();// TODO: 2016/12/24 根据商品id查询单件商品详情 参数(Long commodityid)
        return success(com);
    }

}


