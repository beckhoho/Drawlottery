package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityExchangeService;
import com.hudongwx.drawlottery.mobile.service.commodity.IExchangeMethodService;
import com.hudongwx.drawlottery.mobile.service.commodity.IVirtualCommodityService;
import com.hudongwx.drawlottery.mobile.service.user.IDeliveryAddressService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/9 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/9 <br/>
 * <p>
 * 商品兑换方式
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "CommodityExchangeController", description = "商品兑换方式")
public class CommodityExchangeController extends BaseController {

    @Autowired
    ICommodityExchangeService ceService;
    @Autowired
    IExchangeMethodService emService;
    @Autowired
    IVirtualCommodityService vcService;
    @Autowired
    IDeliveryAddressService daService;

    /**
     * 查询商品兑换方式
     *
     * @param commId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commodity/commexchways", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryCommExchangeWays(@RequestParam("commId") Long commId) {

        return success(ceService.selectByCommodityId(commId));
    }

    /**
     * 兑换现金
     *
     * @param commId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commodity/exchange/cash", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject exchangeToCash(@RequestParam("commId") Long commId) {
        return success();
    }

    /**
     * 兑换闪币
     *
     * @param commId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commodity/exchange/gold", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject exchangeToGold(@RequestParam("commId") Long commId) {
        return success(ceService.selectByCommodityId(commId));
    }

    /**
     * 兑换充值卡(查看卡密)
     *
     * @param cardNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commodity/exchange/rcard/exc", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject exchangeToRechargeCard(@RequestParam("cardNumber") String cardNumber) {
        return success("操作成功！", vcService.updateCardStateByCardNumber(cardNumber, Settings.PASSWORD_VIEWED));
    }

    /**
     * 可兑换充值卡方式详情
     *
     * @param commId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commodity/exchange/rcard/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryExchangeToRechargeCard(@RequestParam("commId") Long commId) {
        // TODO: 2017/1/10 更换用户Id!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return success(emService.selectUserRechargeCardPrize(10000L, commId));
    }

    /**
     * 可兑换充值卡方式详情
     *
     * @param commId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commodity/exchange/rcard/info", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryExchangeToRechargeCardInfo(@RequestParam("commId") Long commId) {
        // TODO: 2017/1/10 更换用户Id!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return success(emService.selectUserRechargeCardExchangeProcess(10000L, commId));
    }

    /**
     * 快递收货
     *
     * @param commId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commodity/exchange/express", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject exchangeToExpress(@RequestParam("commId") Long commId, @RequestParam("addressId") Integer addressId) {
        return success();
    }

    /**
     * 到店领取
     *
     * @param commId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/commodity/exchange/locale", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject exchangeToLocale(@RequestParam("commId") Long commId) {
        return success(ceService.selectByCommodityId(commId));
    }
}
