package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Orders;
import com.hudongwx.drawlottery.mobile.service.oder.IOdersService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/24 <br/>
 * @desc 用户订单管理<p>
 * <p>
 * 创建　wu　2016/12/24 <br/>
 * <p>
 * *********
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "OrdersController", description = "用户订单管理")
public class OrdersController extends BaseController {

    @Autowired
    IOdersService ordersService;
    /**
     * 用户添加订单信息
     *
     * @param order 订单信息
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/orders/add", method = RequestMethod.POST)
    public JSONObject addOrders(@RequestBody Orders order) {
        boolean status = ordersService.addOder(order);
        return response(status);
    }

    /**
     * 用户删除指定订单信息
     *
     * @param orderid 订单id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/orders/del", method = RequestMethod.POST)
    public JSONObject deleteOrder(@RequestParam("orderid") Long orderid) {
        boolean status = ordersService.deleteOder(orderid);
        return response(status);
    }

    /**
     * 用户查看指定订单信息
     *
     * @param orderid 订单id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/orders/info", method = RequestMethod.POST)
    public JSONObject queryOrder(@RequestParam("orderid") Long orderid) {
        Orders order = new Orders();// TODO: 2016/12/24 查看单条订单？？？
        return success(order);
    }

    /**
     * 用户查看所有个人订单信息
     *
     * @param accountid 用户id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/user/orders/show", method = RequestMethod.POST)
    public JSONObject queryAllUserOrders(@RequestParam("acc") Long accountid) {
        List<Orders> olist = ordersService.selectByUserAccount(accountid);
        return success(olist);
    }
}
