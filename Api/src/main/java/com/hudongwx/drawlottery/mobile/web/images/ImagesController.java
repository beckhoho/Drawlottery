package com.hudongwx.drawlottery.mobile.web.images;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.images.ImagesService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/29 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/29 <br/>
 * <p>
 * 图文管理
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@RequestMapping("/api/v1/pub/img/")
@Api(value = "ImagesController", description = "图文管理")
public class ImagesController extends BaseController {

    @Autowired
    ImagesService iService;

    /**
     * 客户端首页的活动宣传图片
     *
     * @return JSONObject
     */
    @ApiOperation("客户端首页的活动宣传图片")
    @RequestMapping(value = "event", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject event() {
        List<Map<String, Object>> infoList = iService.selectEvent();
        return success(infoList);
    }

    /**
     * 客户端欢迎页
     *
     * @return JSONObject
     */
    @ApiOperation("客户端欢迎页")
    @RequestMapping(value = "welcome", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject wellcome() {
        return success(iService.selectWelcomeImg());
    }

    /**
     * 客户端导航页
     *
     * @return JSONObject
     */
    @ApiOperation("客户端导航页")
    @RequestMapping(value = "nav", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject navigate() {
        return success(iService.selectnavigeteImg());
    }
}
