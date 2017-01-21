package com.hudongwx.drawlottery.web;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.common.base.BaseController;
import com.hudongwx.drawlottery.common.constants.CommonConstants;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.dto.Node;
import com.hudongwx.drawlottery.common.dto.paramBody.HistoryParam;
import com.hudongwx.drawlottery.common.dto.response.AjaxResult;
import com.hudongwx.drawlottery.common.dto.response.OrderFilters;
import com.hudongwx.drawlottery.common.exceptions.ControllerException;
import com.hudongwx.drawlottery.pojo.ExchangeWay;
import com.hudongwx.drawlottery.pojo.Express;
import com.hudongwx.drawlottery.pojo.History;
import com.hudongwx.drawlottery.service.commodity.ExchangeWayService;
import com.hudongwx.drawlottery.service.history.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping(value = "/order", method = RequestMethod.POST)
public class HistoryController extends BaseController {

    @Resource
    private HistoryService historyService;
    @Resource
    private CommonConstants commonConstants;
    @Resource
    private LangConstants langConstants;
    @Resource
    private ExchangeWayService exchangeWayService;

    @ApiOperation("获取订单分页数据")
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public PageInfo<History> getAll(@ApiParam(defaultValue = "1") @RequestParam(defaultValue = "1") Integer p, @ApiParam() @RequestBody HistoryParam param) {
        return historyService.getHistoryList(p, commonConstants.getMaxPageSize(), param);
    }

    @RequestMapping(value = "/keys", method = RequestMethod.POST)
    public List<String> getKeys(@ApiParam @RequestParam String key) {
        final List<String> roundTimes = historyService.getRoundTimes(1, commonConstants.getMaxPageSize(), key);
        return roundTimes;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public AjaxResult delete(@ApiParam @RequestParam Long id){
        historyService.delete(id);
        return success(langConstants.getLang(langConstants.DELETE_SUCCESS));
    }
    @RequestMapping(value = "/filters", method = RequestMethod.POST)
    public OrderFilters getFilters() {
        OrderFilters filters = new OrderFilters();
        filters.setGenre(commonConstants.createGenres());
        filters.setExchangeState(commonConstants.createExchangeState());
        filters.setCardNotEnough(commonConstants.createCardNotEnough());
        final List<ExchangeWay> all = exchangeWayService.getAll();
        List<Node> exWays = new ArrayList<>();
        for (ExchangeWay exchangeWay : all) {
            exWays.add(new Node(exchangeWay.getName(), exchangeWay.getId()));
        }
        filters.setExchangeWay(exWays);
        return filters;
    }

    /**
     * 通过商品id获取订单细节
     *
     * @param id 商品id
     * @return 详细信息
     */
    @ApiOperation("订单细节")
    @RequestMapping(value = "/details")
    public History details(@RequestParam Long id) {
        if (null == id)
            throw new ControllerException(langConstants.getLang(langConstants.NOT_CHOOSE_ANY_ONE));
        return historyService.getHistory(id);
    }

    @ApiOperation("发货")
    @RequestMapping("/delivery")
    public AjaxResult delivery(@RequestBody Express express) {
        historyService.delivery(express);
        return success(langConstants.getLang(langConstants.DELIVERY_SUCCESS));
    }
}
