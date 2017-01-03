package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/23 <br/>
 * @desc 用户晒单分享管理<p>
 * <p>
 * 创建　wu　2016/12/23 <br/>
 * <p>
 * **********
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "ShareController", description = "用户晒单分享管理")
public class ShareController extends BaseController {

    @Autowired
    IShareService shareService;

    /**
     * 用户添加晒单分享信息
     *
     * @param share 客户端传来的晒单信息
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/add", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addShareInfo(@RequestBody Share share) {
        boolean status = shareService.addShare(share);
        return response(status);
    }

    /**
     * 获取用户晒单列表信息
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryShareInfo(@RequestParam("page") int page) {
        List<Map<String, Object>> shareAll = shareService.selectUserAll(2L);
        return success(shareAll);
    }

    /**
     * 获取用户晒单列表信息
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/upload.do", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addShareImg(@RequestParam("page") int page) {
        List<Map<String, Object>> shareAll = shareService.selectUserAll(2L);
        return success(shareAll);
    }
}
