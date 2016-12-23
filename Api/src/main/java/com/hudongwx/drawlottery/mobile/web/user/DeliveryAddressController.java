package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import com.hudongwx.drawlottery.mobile.service.user.IDeliveryAddressService;
import com.hudongwx.drawlottery.mobile.web.util.WebCommonUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 <br/>
 * @desc 用户收货地址管理
 * <p>
 * <p>
 * 创建　origin　2016/12/16 <br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "DeliveryAddressController", description = "用户收货地址管理")
public class DeliveryAddressController {

    @Autowired
    IDeliveryAddressService addressService;

    /**
     * 添加收货地址
     *
     * @param address 收货地址信息
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/address/add", method = RequestMethod.POST)
    public JSONObject addAddress(@RequestBody DeliveryAddress address) {
        boolean status = addressService.addDA(address);// TODO: 2016/12/23 添加收货地址
        return WebCommonUtils.buildStatusJsonObj(status, "添加成功！", "添加失败！");
    }

    /**
     * 刪除收货地址
     *
     * @param addressid 收货地址id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/address/del", method = RequestMethod.POST)
    public JSONObject deleteAddress(@RequestParam("addrid") Long addressid) {
        boolean status = false;// TODO: 2016/12/23 刪除收货地址
        return WebCommonUtils.buildStatusJsonObj(status, "刪除成功！", "刪除失败！");
    }

    /**
     * 修改收货地址
     *
     * @param address 客户端传来的地址信息实体类
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/address/up", method = RequestMethod.POST)
    public JSONObject updateAddress(@RequestBody DeliveryAddress address) {
        boolean status = addressService.addDA(address);// TODO: 2016/12/23 修改收货地址
        return WebCommonUtils.buildStatusJsonObj(status, "修改成功！", "修改失败！");
    }

    /**
     * 获取收货地址信息
     *
     * @param accountid 用户账号
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/address/info", method = RequestMethod.POST)
    public JSONObject queryAddress(@RequestParam("acc") Long accountid) {
        List<DeliveryAddress> daList = addressService.selectAllByUserId(accountid);// TODO: 2016/12/23 获取收货地址信息
        return WebCommonUtils.buildDataJsonObj(daList);
    }

}
