package com.hudongwx.drawlottery.web;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.common.base.BaseController;
import com.hudongwx.drawlottery.common.constants.CommonConstants;
import com.hudongwx.drawlottery.common.dto.AjaxResult;
import com.hudongwx.drawlottery.common.dto.paramBody.AllParamList;
import com.hudongwx.drawlottery.pojo.Commodity;
import com.hudongwx.drawlottery.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/4 0004
 * Time: 14:45
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Api("商品相关接口")
@RestController
@RequestMapping(value = "/commodity", method = RequestMethod.POST)
public class CommodityController extends BaseController {

    @Resource
    private IUserService userService;

    @Resource
    private ICommodityService commodityService;

    @ApiOperation("获取商品列表")
    @RequestMapping("/")
    public PageInfo<Commodity> index(@ApiParam(name = "p", defaultValue = "1") @RequestParam(defaultValue = "1") final int p,
                                     @RequestBody AllParamList condition) {
        return commodityService.getCommodities(p,
                CommonConstants.MAX_PAGE_SIZE,
                condition.getKey(), condition.getGenres(), condition.getTypes(), condition.getStatuses(),
                condition.getGroundTimeFront(), condition.getGroundTimeAfter(),
                condition.getUndercarriageTimeFront(),
                condition.getUndercarriageTimeAfter(),
                condition.getOrder(), condition.getDirection(), CommonConstants.VALID);
    }

    @ApiOperation("批量删除商品")
    @RequestMapping("/del")
    public AjaxResult deleteCommodities(@ApiParam @RequestBody List<Integer> ids) {
        if (ids.size() == 0)
            return fail("删除失败！未选择任何商品！");
        commodityService.deleteCommodity(ids);
        return success("删除成功！");
    }

    @ApiOperation("添加/修改商品")
    @RequestMapping("/save")
    public AjaxResult addCommodity(@ApiParam("有id则为修改，无id则为删除") @RequestBody Commodity commodity) {
        if (commodity.getId() == null) {
            commodityService.addCommodity(commodity);
            return success("添加成功！");
        }
        commodityService.updateCommodity(commodity);
        return success("修改成功！");
    }

    @ApiOperation("批量上架")
    @RequestMapping("/ground")
    public AjaxResult groundCommodities(@ApiParam @RequestBody List<Integer> list) {
        commodityService.ground(list);
        return success("上架成功！");
    }

    @ApiOperation("批量下架")
    @RequestMapping("/under")
    public AjaxResult underCommodities(@ApiParam @RequestBody List<Integer> list) {
        commodityService.undercarriage(list);
        return success("下架成功");
    }
}
