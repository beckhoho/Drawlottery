package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import com.hudongwx.drawlottery.mobile.service.user.IDeliveryAddressService;
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
public class DeliveryAddressController extends BaseController {

    @Autowired
    IDeliveryAddressService addressService;

    /**
     * 添加收货地址
     *
     * @param address 收货地址信息
     * @return JSONObject
     */
    @RequestMapping(value = "/api/v1/user/address/add", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addAddress(@RequestBody DeliveryAddress address) {
        boolean status = addressService.addDa(getUserId(),address);
        return response(status, "最多添加3条收货地址");
    }

    /**
     * 刪除收货地址
     *
     * @param addressId 收货地址id
     * @return JSONObject
     */
    @RequestMapping(value = "/api/v1/user/address/del", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject deleteAddress(@RequestParam("addressId") Long addressId) {
        boolean status = addressService.deleteDa(addressId);
        return response(status);
    }

    /**
     * 修改收货地址(tip:未使用)
     *
     * @param address 客户端传来的地址信息实体类
     * @return JSONObject
     */
    @ApiOperation("修改收货地址（tip；未使用！）")
    @RequestMapping(value = "/api/v1/user/address/up", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject updateAddress(@RequestBody DeliveryAddress address) {
        boolean status = addressService.updateDa(getUserId(),address);
        return response(status);
    }

    /**
     * 获取收货地址信息
     *
     * @return JSONObject
     */
    @ApiOperation("查看收货地址信息")
    @RequestMapping(value = "/api/v1/user/address/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryAddress() {
        List<Map<String, Object>> mapList = addressService.selectByAccountId(getUserId());
        return success(mapList);
    }
}