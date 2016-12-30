package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
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
    @RequestMapping(value = "/api/v1/user/share/add", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject addShareInfo(@RequestBody Share share) {

        boolean status = shareService.addShare(share);
        return response(status);
    }

    /**
     * 获取用户晒单分享列表信息
     *
     * @param accountid   账号id
     * @param ref         刷新的状态标记（0 初始，1 下拉刷新，2 上拉刷新）
     * @param lastshareid 客户端最后显示的晒单id
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/show", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryShareInfo(@RequestParam("acc") Long accountid, @RequestParam("ref") int ref, @RequestParam("lastshareid") Long lastshareid) {
        List<Share> slist = shareService.selectShare(accountid, lastshareid, ref);// TODO: 2016/12/24 获取share集合数据 參數(Long accountid)
        return success(slist);
    }
}
