package com.hudongwx.drawlottery.mobile.web.luckcodes;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserLuckCodesService;
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
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/24 <br/>
 * <p>
 * 用户幸运码管理
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "UserLuckCodesController", description = "用户幸运码管理")
public class UserLuckCodesController extends BaseController {

    @Autowired
    IUserLuckCodesService ulcService;

    /**
     * 用户查看个人幸运码
     *
     * @param accountid 用户账号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/luckcode/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserLuckCodes(@RequestParam("acc") Long accountid) {
        List<UserLuckCodes> ulclist = ulcService.selectByUserId(accountid);
        return success(ulclist);
    }
}
