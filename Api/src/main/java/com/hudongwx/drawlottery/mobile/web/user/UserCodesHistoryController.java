package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@Api(value = "UserCodesHistoryController",description = "用户幸运码历史信息管理")
public class UserCodesHistoryController extends BaseController {

    /**
     * 用户查看个人往期幸运码
     *
     * @param accountid 用户账号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/codeshistory/show", method = RequestMethod.POST)
    public JSONObject queryUserLuckCodes(@RequestParam("acc") Long accountid) {
        List<UserCodesHistory> uchlist = new ArrayList<>();// TODO: 2016/12/24 添一组幸运码
        return success(uchlist);
    }
}
