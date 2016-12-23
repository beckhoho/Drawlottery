package com.hudongwx.drawlottery.mobile.web.commodity;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityTypeService;
import com.hudongwx.drawlottery.mobile.web.util.WebCommonUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public class CommoditysController {
    @Autowired
    ICommodityService cService;
    @Autowired
    ICommodityTypeService CtService;

    /**
     * 搜索商品信息
     *
     * @param category        商品类别
     * @param name            商品名称
     * @param lastcommodityid 当前最后一个显示的商品id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/commodity/search")
    public JSONObject searchCommoditys(@RequestParam("category") String category, @RequestParam("name") String name, @RequestParam("commid") Long lastcommodityid) {
        boolean categoryIsNull = (null == category || category.trim().equals(""));
        boolean nameIsNull = (null == name || name.trim().equals(""));
        List<Commoditys> clist = null;
        CommodityType ct;
        if (!categoryIsNull && nameIsNull) {/*商品类别category，商品名null*/
            ct = null;// TODO: 2016/12/23 根据category查询商品类型信息并判断当前字段是否可用state；参数(String category);

            if (null == ct || ct.getState() == 0)
                return WebCommonUtils.buildStatusJsonObj(false, null, "无相关商品类别信息！");

            clist = null;// TODO: 2016/12/23 根据商品类型id查询商品信息 参数(Long ctid, Long lastcommodityid);
        } else if (!categoryIsNull && !nameIsNull) {/*商品类别category，商品名name*/
            ct = null;// TODO: 2016/12/23 根据category查询商品类型信息并判断当前商品类型是否可用state；参数(String category);

            if (null == ct || ct.getState() == 0)
                return WebCommonUtils.buildStatusJsonObj(false, null, "无相关商品类别信息！");

            clist = null;// TODO: 2016/12/23 根据商品类型id查询指定name的商品信息 参数(Long ctid, String name , Long lastcommodityid);
        } else if (categoryIsNull && !nameIsNull) {/*商品类别null，商品名name*/

            clist = null;// TODO: 2016/12/23 根据name查询商品信息 参数( String name ,Long lastcommodityid);

        } else if (categoryIsNull && nameIsNull) {/*商品类别null，商品名null*/

            clist = null;// TODO: 2016/12/23 全查询 参数( Long lastcommodityid);

        }

        return WebCommonUtils.buildDataJsonObj(clist);
    }


}


