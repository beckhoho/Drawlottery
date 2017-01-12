package com.hudongwx.drawlottery.web;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.common.base.BaseController;
import com.hudongwx.drawlottery.common.constants.ConfigConstants;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.dto.paramBody.AllParamList;
import com.hudongwx.drawlottery.common.dto.paramBody.TempBody;
import com.hudongwx.drawlottery.common.dto.response.AjaxResult;
import com.hudongwx.drawlottery.common.dto.response.CommodityFiltersResult;
import com.hudongwx.drawlottery.pojo.Commodity;
import com.hudongwx.drawlottery.pojo.CommodityState;
import com.hudongwx.drawlottery.pojo.CommodityTemplate;
import com.hudongwx.drawlottery.pojo.CommodityType;
import com.hudongwx.drawlottery.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.service.commodity.IFileService;
import com.hudongwx.drawlottery.service.commodity.IStateService;
import com.hudongwx.drawlottery.service.commodity.ITypeService;
import com.hudongwx.drawlottery.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping(value = "/commodity")
public class CommodityController extends BaseController {
    @Resource
    private ConfigConstants configConstants;
    @Resource
    private LangConstants langConstants;
    private Logger logger = LoggerFactory.getLogger(CommodityController.class);
    @Resource
    private IUserService userService;
    @Resource
    private IFileService fileService;

    @Resource
    private ITypeService typeService;
    @Resource
    private ICommodityService commodityService;

    @Resource
    private IStateService stateService;

    @ApiOperation("获取商品列表")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public PageInfo<Commodity> index(@ApiParam(name = "p", defaultValue = "1") @RequestParam(defaultValue = "1") final Integer p,
                                     @ApiParam() @RequestBody AllParamList condition) {
        return commodityService.getCommodities(p,
                commonConstants.getMaxPageSize(),
                condition.getKey(), condition.getGenre(), condition.getType(), condition.getState(),
                condition.getGroundTimeFront(), condition.getGroundTimeAfter(),
                condition.getUndercarriageTimeFront(),
                condition.getUndercarriageTimeAfter(),
                condition.getOrder(), condition.getDirection(), commonConstants.getVALID());
    }

    @ApiOperation("批量删除商品")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public AjaxResult deleteCommodities(@ApiParam @RequestBody List<Integer> ids) {
        if (ids.size() == 0)
            return fail("删除失败！未选择任何商品！");
        commodityService.deleteCommodity(ids);
        return success(langConstants.getLang(langConstants.DELETE_COMMODITY_SUCCESS));
    }

    @ApiOperation("添加/修改商品")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult addCommodity(@ApiParam("有id则为修改，无id则为删除") @RequestBody TempBody body) {
        final CommodityTemplate commodityTemplate = body.packingMe();
        if (commodityTemplate.getId() == null) {
            commodityService.addCommodityTemplate(commodityTemplate);
            return success(langConstants.getLang(langConstants.ADD_COMMODITY_SUCCESS));
        }
        commodityService.updateCommodity(commodityTemplate);
        return success(langConstants.getLang(langConstants.UPDATE_COMMODITY_SUCCESS));
    }

    @ApiOperation("批量上架")
    @RequestMapping(value = "/ground", method = RequestMethod.POST)
    public AjaxResult groundCommodities(@ApiParam @RequestBody List<Integer> list) {
        commodityService.ground(list);
        return success(langConstants.getLang(langConstants.GROUND_COMMODITY_SUCCESS));
    }

    @ApiOperation("批量下架")
    @RequestMapping(value = "/under", method = RequestMethod.POST)
    public AjaxResult underCommodities(@ApiParam @RequestBody List<Integer> list) {
        commodityService.undercarriage(list);
        return success(langConstants.getLang(langConstants.UNDERCARRIAGE_COMMODITY_SUCCESS));
    }

    @ApiOperation("得到所有的类型")
    @RequestMapping(value = "/allType", method = RequestMethod.POST)
    public List<CommodityType> getAllType() {
        return typeService.getAllTypes();
    }

    @ApiOperation("得到后台所有的可用状态")
    @RequestMapping(value = "/allState", method = RequestMethod.POST)
    public List<CommodityState> getAllState() {
        return stateService.getAllState();
    }

    @ApiOperation("得到筛选条件json数据")
    @RequestMapping(value = "/filters", method = RequestMethod.POST)
    public CommodityFiltersResult getFilters() {
        final CommodityFiltersResult commodityFiltersResult = new CommodityFiltersResult();
        commodityFiltersResult.setStates(stateService.getAllState());
        commodityFiltersResult.setTypes(typeService.getAllTypes());
        return commodityFiltersResult;
    }

    @ApiOperation("关键字搜索自动完成数据源")
    @RequestMapping(value = "/keys", method = RequestMethod.POST)
    public List<String> getKeys(@ApiParam @RequestParam("key") String name) {
        return commodityService.getNames(name);
    }

    @ApiOperation("图片上传地址")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public AjaxResult uploadImage(@RequestParam MultipartFile file) {
        final String s = fileService.fileUpload(file);
        return success("上传成功", s);
    }
}
