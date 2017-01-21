package com.hudongwx.drawlottery.mobile.web.auth;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.ThirdPartyLoginToken;
import com.hudongwx.drawlottery.mobile.service.captcha.ICaptchaService;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.shiro.CaptchaUsernamePasswordToken;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户认证
 */
@Api(value = "AuthorController", description = "用户登录认证", position = 1)
@RestController
public class AuthorController extends BaseController {

    @Autowired
    IUserService usersService;

    @Autowired
    ICaptchaService captchaService;


    @RequestMapping(value = "/api/v1/pub/error/403", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject error403() {
        return fail(403, "当前没有登录");
    }


    /**
     * 用户第三方登录,post
     *
     * @return
     */
    @ApiOperation(value = "partyLogin", notes = "第三方登录", httpMethod = "POST")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "openid", value = "openid", paramType = "query", dataType = "string"),
                    @ApiImplicitParam(name = "access_token", value = "access_token", paramType = "query", dataType = "string"),
                    @ApiImplicitParam(name = "platform", value = "平台类型,1=QQ,2=微信", paramType = "query", dataType = "int"),
                    @ApiImplicitParam(name = "nickName", value = "昵称", paramType = "query", dataType = "string"),
                    @ApiImplicitParam(name = "headImg", value = "平台类型,1=QQ,2=微信", paramType = "query", dataType = "string")
            }
    )
    @RequestMapping(value = "/api/v1/pub/party/login", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject partyLogin(
            @RequestParam(value = "openid", required = true) String openid,
            @RequestParam(value = "access_token", required = true) String accessToken,
            @RequestParam(value = "platform", required = true) int platform,
            @RequestParam(value = "nickName", required = true) String nickName,
            @RequestParam(value = "headImg", required = true) String headImg
    ) {
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            ThirdPartyLoginToken token = new ThirdPartyLoginToken(openid, accessToken, platform,nickName,headImg);
            token.setPassword(openid.toCharArray());
            //判断是否来自QQ或微信平台
            if(token.isQQPlatform() || token.isWeixinPlatform()){
                try {
                    subject.login(token); //调用登录,去认证器匹配
                    return success();
                } catch (UnknownAccountException e) {
                    e.printStackTrace();
                    return fail(-1,"第三方登录错误");
                }
            }else{
                return fail(-1,"第三方登录错误");
            }
        }
        return success();
    }

    /**
     * 用户登录,post
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/auth/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody(required = true) CaptchaUsernamePasswordToken token) {
        //获取用户信息
        Subject subject = SecurityUtils.getSubject();
        token.setRememberMe(false);
        if (!subject.isAuthenticated()) {//是否已经验证过
            String msg = null;
            try {
                subject.login(token);
                return success();
            } catch (UnknownAccountException e) {//账号不存在
                msg = "账号错误";
                return fail(-1, msg);
            } catch (LockedAccountException e) {//账号锁定了
                msg = "账号被禁用";
                return fail(-1, msg);
            } catch (ExcessiveAttemptsException e) {//错误次数太多
                msg = e.getMessage();
                return fail(-2, msg);
            } catch (AuthenticationException e) {
                msg = "用户名或密码错误";
                return fail(-1, msg);
            }
        } else {
            return success();
        }
    }

    /**
     * 用户登出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/auth/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public void logout() {
        super.logout();
    }

    /**
     *
     * 用户注册
     * @param phone    注册账号
     * @param password 注册密码
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/auth/register", method = RequestMethod.POST)
    public JSONObject register(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("SMSCode") String smscode) {
            boolean isOk = captchaService.validatorImageCode(phone, smscode);
            if (isOk) {
                fail(-1, "验证码无效");
            } else {
                isOk = usersService.register(phone, password);
                if (!isOk) {
                   return fail(-1, "注册失败");
                }
            }
        return success();
    }

}
