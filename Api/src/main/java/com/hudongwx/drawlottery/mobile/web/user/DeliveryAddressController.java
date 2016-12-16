package com.hudongwx.drawlottery.mobile.web.user;


import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import com.hudongwx.drawlottery.mobile.service.user.IDeliveryAddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 0016 <br/>
 * @desc
 * 用户收货地址
 * <p>
 * <p>
 * 创建　origin　2016/12/16 0016　<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "DeliveryAddressController",description = "用户收货地址管理")
public class DeliveryAddressController {

    @Autowired
    IDeliveryAddressService addressService;

    /**
     * 添加收货地址
     */
    @RequestMapping(value = "/user/delivery/add")
    public JSONObject add(@RequestBody DeliveryAddress address){
        JSONObject object = new JSONObject();
        object.put("msg","");
        object.put("code",300);
        addressService.insert(address);
        return object;
    }


}
