package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Approve;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/22 0016 <br/>
 * @desc 用户认证管理<p>
 * <p>
 * 创建　wu　2016/12/22 0016　<br/>
 * <p>
 * *********
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "ApproveController", description = "用户认证管理（tip:未使用）")
public class ApproveController extends BaseController {

    /**
     * 用户认证申请
     *
     * @param approve 认证信息
     * @return JSONObject
     */
    @RequestMapping(value = "/api/v1/user/approve/add",method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject addApproveInfo(@RequestParam("app") Approve approve) {
        boolean status = true;// TODO: 2016/12/24 提交认证信息
        return response(status);
    }

    /**
     * 用户查看个人认证申请情况
     *
     * @param accountid 用户id
     * @return JSONObject
     */
    @RequestMapping(value = "/api/v1/user/approve/info", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryApproveInfo(@RequestParam("acc") Long accountid) {
        Approve approve = new Approve();// TODO: 2016/12/24 提交认证信息
        return success(approve);
    }

    /**
     * 用户修改个人认证申请情况
     *
     * @param approve 认证信息
     * @return JSONObject
     */
    @RequestMapping(value = "/api/v1/user/approve/up", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject updateApproveInfo(@RequestParam("app") Approve approve) {
        boolean status = true;// TODO: 2016/12/24 提交修改后的认证信息
        return response(status);
    }
}
