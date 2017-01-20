package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IPromoterProfitService;
import com.hudongwx.drawlottery.mobile.service.user.ISignInService;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2016/12/16 <br/>
 * @desc 用户管理
 * <p>
 * <p>
 * 创建　origin　2016/12/16 <br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "UserController", description = "用户管理")
public class UserController extends BaseController {

    @Autowired
    IUserService userService;
    @Autowired
    ISignInService signInService;
    @Autowired
    IPromoterProfitService promoterProfitService;

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/center", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserCenter() {
        Map<String, Object> userInfo = userService.queryPersonalInfo(getUserId());
        return success(userInfo);
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/info", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserInfo() {
        Map<String, Object> userInfo = userService.getUserInfo(getUserId());
        return success(userInfo);
    }

    /**
     * 获取用户中奖记录
     *
     * @return
     */
    @ResponseBody
    @ApiOperation("获取用户中奖记录（带分页）")
    @RequestMapping(value = "/api/v1/user/win", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserWinningHistory(@ApiParam("当前页最后一item的实际id") @RequestParam("lastItemId") Long lastItemId) {
        List<Map<String, Object>> historyLottery = userService.selectHistoryLottery(getUserId());
        return success(historyLottery);
    }

    /**
     * 获取用户夺宝记录
     *
     * @param item 获取数据形式
     * @return
     */
    @ResponseBody
    @ApiOperation("获取用户夺宝记录（带分页）")
    @RequestMapping(value = "/api/v1/user/usercomm/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserCommRecord(@ApiParam("显示形式：1、进行中；2、已揭晓；其他数字、显示全部") @RequestParam("item") Integer item, @ApiParam("最后一个商品Id") @RequestParam("lastCommId") Long lastCommId) {
        List<Map<String, Object>> historyLottery = userService.selectPurchaseRecords(item, getUserId(), lastCommId);
        return success(historyLottery);
    }

    /**
     * 获取用户夺宝记录中的luckCode带分页
     *
     * @param lastCode 获取数据形式
     * @return
     */
    @ResponseBody
    @ApiOperation("获取用户夺宝记录中的luckCode带分页")
    @RequestMapping(value = "/api/v1/priv/user/usercomm/code/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserLuckCode(@ApiParam("回传最后一个Code码") @RequestParam("commId") Long commId, @ApiParam("回传最后一个Code码") @RequestParam("lastCode") String lastCode) {
        return success(userService.selectUserLuckCode(getUserId(), commId, lastCode));
    }

    /**
     * 用户签到
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/sign/add", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addUserSign() {
        return success(signInService.selectUserSign(getUserId()));
    }

    /**
     * 用户签到信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/sign/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserSign() {
        return success(signInService.selectUserSign(getUserId()));
    }

    /**
     * 用户上传头像
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/upload/headimg.do", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject uploadUserImg(@RequestParam("accountId") Long accountId, @RequestParam("photo") MultipartFile imgFile) {
        return response(true);
    }

    /**
     * 修改昵称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/update/nickname", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject updateUserNickName(@RequestParam("nickname") String nickname) {

        return response(true);
    }

    /**
     * 修改电话号码
     *
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/update/phone", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject updatePhone(@RequestParam("phone") String phone, @RequestParam("imgCode") String imgCode, @RequestParam("SMSCode") String SMSCode) {
        return response(true);
    }

    /**
     * 修改QQ号
     *
     * @param QQ
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/update/qq", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject updateQQNumber(@RequestParam("qq") String QQ) {
        return response(userService.addQQNumber(getUserId(), QQ));
    }

    /**
     * 推广员收益信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/promoter/profit/info", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryPromoterProfit() {
        return success(promoterProfitService.selectPromoterProfitInfo(getUserId()));
    }

    /**
     * 添加推广员Id
     *
     * @param promId 推广员Id
     * @return
     */
    @ResponseBody
    @ApiOperation("添加推广员Id")
    @RequestMapping(value = "/api/v1/user/promoter/add/promote", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addPromoter(@ApiParam("推广员Id") @RequestParam("promId") Long promId) {
        int re = userService.addPromoter(promId, getUserId());
        if (re == 1) {
            return success();
        } else if (re == -1) {
            return fail("推广人id不存在");
        } else {
            return fail("已有推广人或用户注册时间早于推广人");
        }
    }
}
