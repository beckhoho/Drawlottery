package com.hudongwx.drawlottery.mobile.web.commodity;/**
 * Created by wu on 2016/12/22.
 */

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class CommodityController {


    /**
     * 添加商品信息
     */
    @RequestMapping(value = "/commodity/add")
    public JSONObject add(@RequestBody Commoditys commodity) {
        JSONObject jsonObject = new JSONObject();
        if (true) {
            jsonObject.put("msg", "商品信息添加成功！");
            jsonObject.put("code", 200);
        } else {
            jsonObject.put("msg", "商品信息添加失败！");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }

    /**
     * 搜索商品信息
     */
    @RequestMapping(value = "/commodity/search")
    public JSONObject search(@RequestParam("keyword") String keyword) {
        JSONObject jsonObject = new JSONObject();
        if (true) {
            jsonObject.put("commodityList", null);
            jsonObject.put("msg", "共有" + /*daList.size() +*/ "条收货地址信息！");
            jsonObject.put("code", 200);
        } else {
            jsonObject.put("commodityList", null);
            jsonObject.put("msg", "未找到相关信息！");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }
}
