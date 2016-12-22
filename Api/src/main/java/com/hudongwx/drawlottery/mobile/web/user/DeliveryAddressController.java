package com.hudongwx.drawlottery.mobile.web.user;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import com.hudongwx.drawlottery.mobile.service.user.IDeliveryAddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 0016 <br/>
 * @desc 用户收货地址
 * <p>
 * <p>
 * 创建　origin　2016/12/16 0016　<br/>
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
     */
    @RequestMapping(value = "/user/Address/add")
    public JSONObject add(@RequestBody DeliveryAddress address) {
        JSONObject object = new JSONObject();
        System.out.println("address.getId()---------->" + address.getId());
        if (addressService.addDA(address)) {
            object.put("msg", "收货地址信息添加成功！");
            object.put("code", 200);
        } else {
            object.put("msg", "收货地址信息添加失败！");
            object.put("code", -1);
        }
        return object;
    }

    /**
     * 获取收货地址信息
     */
    @RequestMapping(value = "/user/Address/search")
    public JSONObject search(@RequestParam("acc") String acc) {
        JSONObject object = new JSONObject();
        System.out.println("userId---------->" + acc);
        List<DeliveryAddress> daList = addressService.selectAllByUserId(acc);
        if (daList != null && daList.size() != 0) {
            object.put("addressList", JSON.toJSONString(daList));
            object.put("msg", "共有" + daList.size() + "条收货地址信息！");
            object.put("code", 200);
        } else {
            object.put("addressList", null);
            object.put("msg", "未找到相关信息！");
            object.put("code", -1);
        }
        return object;
    }

}
