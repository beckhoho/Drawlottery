package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.service.user.ISignInService;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.utils.TestHttp;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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

    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/info", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserInfo() {
        Map<String, Object> userInfo = userService.getUserInfo(userService.queryUserByPhoneNum("13990949387"));
        return success(userInfo);
    }

    /**
     * 获取用户中奖记录
     *
     * @param page 页码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/win", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserWinningHistory(@RequestParam("page") int page) {
        User user = getUser();//获取当前用户信息
        List<Map<String, Object>> historyLottery = userService.selectHistoryLottery(10000L);
        return success(historyLottery);
    }

    /**
     * 获取用户夺宝记录
     *
     * @param item 获取数据形式
     * @param page 页码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/usercomm/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserCommRecord(@RequestParam("item") Integer item, @RequestParam("page") Integer page) {
        System.out.println(item+"------------------"+page);
        List<Map<String, Object>> historyLottery = userService.selectHistoryPay(10000L, item);
        return success(historyLottery);
    }

    /**
     * 用户签到
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/sign/add", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addUserSign() {
        User user = getUser();//获取当前用户信息
        return success(signInService.selectUserSign(user.getAccountId()));
    }

    /**
     * 用户签到信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/sign/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryUserSign() {
        User user = getUser();//获取当前用户信息
        return success(signInService.selectUserSign(user.getAccountId()));
    }

    /**
     * 用户上传头像
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/upload/headimg.do", method = RequestMethod.POST)
    public JSONObject uploadUserImg(HttpServletRequest request) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
        byte[] bytes = TestHttp.readInputStream(bis);
        String path="C:\\Users\\wu\\IdeaProjects\\DrawLottery\\Api\\src\\main\\resources\\static\\imgs\\userportrait";
        File file=new File(path,new Date().getTime()+".jpg");
        if(!file.exists())
            file.getParentFile().createNewFile();
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "/api/v1/user/update/nickname", method = RequestMethod.POST)
    public JSONObject updateUserNickName(@RequestParam("nickname")String userNickName) throws IOException {

        return response(true);
    }
}
