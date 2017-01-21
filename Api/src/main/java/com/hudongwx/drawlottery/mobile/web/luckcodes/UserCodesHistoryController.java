package com.hudongwx.drawlottery.mobile.web.luckcodes;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserCodesHistoryService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * 用户幸运码历史信息管理
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "UserCodesHistoryController", description = "用户幸运码历史信息管理")
public class UserCodesHistoryController extends BaseController {

    @Autowired
    IUserCodesHistoryService uchService;

    /**
     * 用户查看个人往期幸运码
     *
     * @return
     */
    @RequestMapping(value = "/api/v1/user/codeshistory/show", method = {RequestMethod.POST,RequestMethod.GET})
    public JSONObject queryUserLuckCodes() {
        List<UserCodesHistory> uchlist = uchService.selectByUserAccount(getUserId());
        return success(uchlist);
    }
}
