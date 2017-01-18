package com.hudongwx.drawlottery.web;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.common.base.BaseController;
import com.hudongwx.drawlottery.common.constants.ConfigConstants;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.dto.paramBody.AllParamList;
import com.hudongwx.drawlottery.common.dto.paramBody.CardParam;
import com.hudongwx.drawlottery.common.dto.paramBody.TempBody;
import com.hudongwx.drawlottery.common.dto.paramBody.TempParam;
import com.hudongwx.drawlottery.common.dto.response.AjaxResult;
import com.hudongwx.drawlottery.common.dto.response.CommodityFiltersResult;
import com.hudongwx.drawlottery.pojo.*;
import com.hudongwx.drawlottery.service.commodity.*;
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
 * 商品相关 controller 类.
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
    private CommodityService commodityService;

    @Resource
    private ITypeService typeService;
    @Resource
    private ITempService tempService;

    @Resource
    private IStateService stateService;
    @Resource
    private CardService cardService;

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

    @ApiOperation("获取商品模板列表")
    @RequestMapping(value = "/temps", method = RequestMethod.POST)
    public PageInfo<CommodityTemplate> getTemplates(@ApiParam(name = "p", defaultValue = "1") @RequestParam(defaultValue = "1") final Integer p,
                                                    @ApiParam @RequestBody TempParam tempParam) {
        return tempService.getTemplates(p, commonConstants.getMaxPageSize(), tempParam.getType(), tempParam.getGenre(), tempParam.getOrder(), tempParam.getDirection());
    }

    @ApiOperation("批量删除商品")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public AjaxResult deleteCommodities(@ApiParam @RequestBody List<Integer> ids) {
        if (ids.size() == 0)
            return fail(langConstants.getLang(langConstants.NOT_CHOOSE_ANY_ONE));
        tempService.deleteCommodity(ids);
        return success(langConstants.getLang(langConstants.DELETE_SUCCESS));
    }

    @ApiOperation("添加/修改商品模板")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult addCommodityTemplate(@ApiParam("有id则为修改，无id则为删除") @RequestBody TempBody body) {
        final CommodityTemplate commodityTemplate = body.packingMe();
        if (commodityTemplate.getId() == null) {
            tempService.addCommodityTemplate(commodityTemplate);
            return success(langConstants.getLang(langConstants.ADD_SUCCESS));
        }
        tempService.updateCommodity(commodityTemplate);
        tempService.connectImgs(commodityTemplate.getId(), body.getImages());
        return success(langConstants.getLang(langConstants.UPDATE_SUCCESS));
    }

    @ApiOperation("批量上架商品（仅限已下架的）")
    @RequestMapping(value = "/ground", method = RequestMethod.POST)
    public AjaxResult groundCommodities(@ApiParam @RequestBody List<Integer> list) {
        if (list.size() == 0)
            return fail(langConstants.getLang(langConstants.NOT_CHOOSE_ANY_ONE));
        tempService.ground(list);
        return success(langConstants.getLang(langConstants.GROUND_COMMODITY_SUCCESS));
    }

    @ApiOperation("批量上架模板（新建一个新的商品）")
    @RequestMapping(value = "/groundTemp", method = RequestMethod.POST)
    public AjaxResult groundCommodityTemplates(@ApiParam @RequestBody List<Integer> list) {
        if (list.size() == 0)
            return fail(langConstants.getLang(langConstants.NOT_CHOOSE_ANY_ONE));
        tempService.groundNew(list);
        return success(langConstants.getLang(langConstants.GROUND_COMMODITY_SUCCESS));
    }

    @ApiOperation("批量下架")
    @RequestMapping(value = "/under", method = RequestMethod.POST)
    public AjaxResult underCommodities(@ApiParam @RequestBody List<Integer> list) {
        if (list.size() == 0)
            return fail(langConstants.getLang(langConstants.NOT_CHOOSE_ANY_ONE));
        tempService.undercarriage(list);
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
        return tempService.getNames(name);
    }

    @ApiOperation("图片上传地址")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public AjaxResult uploadImage(@RequestParam MultipartFile file) {
        final String s = fileService.fileUpload(file);
        if (s == null) {
            return fail(langConstants.getLang(langConstants.UPLOAD_FAIL));
        }
        return success(langConstants.getLang(langConstants.UPLOAD_SUCCESS), s);
    }

    @ApiOperation("获取卡密集合")
    @RequestMapping(value = "/getCards", method = RequestMethod.POST)
    public PageInfo<Card> getAllCards(@RequestParam(defaultValue = "1") Integer p, @RequestBody CardParam cardParam) {
        return cardService.getCards(p, commonConstants.getMaxPageSize(), cardParam.getCorporation(), cardParam.getOrder(), cardParam.getDirection());
    }

    @ApiOperation("添加卡密")
    @RequestMapping(value = "/addCard", method = RequestMethod.POST)
    public AjaxResult addCard(@RequestBody Card card) {
        if (null == card.getId())
            cardService.insertCard(card);
        else
            cardService.updateCard(card);
        return success(langConstants.getLang(langConstants.ADD_SUCCESS));
    }

    @ApiOperation("删除卡密")
    @RequestMapping(value = "/deleteCards", method = RequestMethod.POST)
    public AjaxResult deleteCards(@RequestBody List<Integer> cardIds) {
        cardService.deleteCard(cardIds);
        return success(langConstants.getLang(langConstants.DELETE_SUCCESS));
    }

    @ApiOperation("添加类型")
    @RequestMapping(value = "/addType", method = RequestMethod.POST)
    public AjaxResult addType(@RequestBody CommodityType type) {
        if (null == type.getId())
            typeService.addType(type);
        else
            typeService.updateType(type);
        return success(langConstants.getLang(langConstants.ADD_SUCCESS));
    }

    @ApiOperation("删除类型")
    @RequestMapping(value = "/deleteTypes", method = RequestMethod.POST)
    public AjaxResult deleteTypes(@RequestBody List<Integer> typeIds) {
        typeService.deleteTypes(typeIds);
        return success(langConstants.getLang(langConstants.DELETE_SUCCESS));
    }
}

