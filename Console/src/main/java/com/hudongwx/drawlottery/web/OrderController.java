package com.hudongwx.drawlottery.web;

import com.hudongwx.drawlottery.common.dto.paramBody.OrderParam;
import com.hudongwx.drawlottery.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单相关 controller 类.
 * Date: 2017/1/18 0018
 * Time: 2:49
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Api("订单相关接口")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation("获取订单分页数据")
    @RequestMapping("/all")
    @ResponseBody
    public void getAll(@RequestBody OrderParam param) {
        
    }
}
