package com.hudongwx.drawlottery.mobile.web.commodity;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.CommodityImg;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
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
@Api(value = "CommodityImgController", description = "商品图片管理")
public class CommodityImgController extends BaseController{

    /**
     * 商品图片
     * @param urlarr 图片url数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "api/v1/pub/commodityimg/ushow", method = RequestMethod.POST)
    public JSONObject queryCommodityImgInfoByUrl(@RequestParam("urlarr") String[] urlarr) {
        List<CommodityImg> cilist = new ArrayList<>();// TODO: 2016/12/24 商品图片 参数(String[] imgarr)
        return success(cilist);
    }

    /**
     * 商品图片
     *
     * @param imgarr 图片id数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "api/v1/pub/commodityimg/ishow", method = RequestMethod.POST)
    public JSONObject queryCommodityImgInfoById(@RequestParam("idarr") Long[] imgarr) {
        List<CommodityImg> cilist = new ArrayList<>();// TODO: 2016/12/24 商品图片 参数(String[] imgarr)
        return success(cilist);
    }
}
