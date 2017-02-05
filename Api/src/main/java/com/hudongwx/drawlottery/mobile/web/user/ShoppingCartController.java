package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IShoppingCartService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/4 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/4 <br/>
 * <p>
 * 用户购物车管理
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "ShoppingCartController", description = "用户购物车后台管理（未使用）")
public class ShoppingCartController extends BaseController {

    @Autowired
    IShoppingCartService scService;

    /**
     * 用户添加购物车记录
     *
     * @return
     */
    @ResponseBody
    @ApiOperation("用户添加购物车记录")
    @RequestMapping(value = "/api/v1/user/shoppingcart/add", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addCartInfo(@ApiParam("商品id") @RequestParam("commId") Long commId, @ApiParam("预购买量") @RequestParam("count") Integer count) {
        return response(scService.addCommodityToCart(getUserId(), commId, count));
    }

    /**
     * 用户删除购物车记录
     *
     * @return
     */
    @ResponseBody
    @ApiOperation("删除购物车记录")
    @RequestMapping(value = "/api/v1/user/shoppingcart/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject deleteCartInfo(@RequestParam("commId") Long commId) {
        return response(scService.deleteCommodity(getUserId(), commId));
    }

    /**
     * 用户修改购物车购买数量
     *
     * @return
     */
    @ResponseBody
    @ApiOperation("更新购物车信息")
    @RequestMapping(value = "/api/v1/user/shoppingcart/update", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject updateCartInfo(@ApiParam("要修改的购物车商品对应的id") @RequestParam("commId") Long commId, @ApiParam("最新购买量") @RequestParam("count") Integer count) {
        return response(scService.updateCommodity(getUserId(), commId, count));
    }

    /**
     * 用户查看购物车购买数量
     *
     * @return
     */
    @ResponseBody
    @ApiOperation("购物车信息")
    @RequestMapping(value = "/api/v1/user/shoppingcart/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryCartInfo() {
        return success(scService.selectByAccount(getUserId()));
    }
}
