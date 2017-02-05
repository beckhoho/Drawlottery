package com.hudongwx.drawlottery.mobile.web.commodity;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityTypeService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/29 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/29 <br/>
 * <p>
 * 商品类别管理
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "CommodityTypeController", description = "商品类别管理")
public class CommodityTypeController extends BaseController {

    @Autowired
    ICommodityTypeService ctService;

    /**
     * 获取商品类型信息
     *
     * @return
     */
    @ResponseBody
    @ApiOperation("获取商品类型信息")
    @RequestMapping(value = "/api/v1/pub/commtype/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryAvailableCommType() {
        List<CommodityType> ctList = ctService.selectAvailable();
        return success(ctList);
    }
}
