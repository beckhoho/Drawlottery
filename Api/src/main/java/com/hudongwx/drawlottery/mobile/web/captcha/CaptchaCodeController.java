package com.hudongwx.drawlottery.mobile.web.captcha;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.captcha.ICaptchaService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import com.hudongwx.drawlottery.mobile.utils.VerifyCodeUtils;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import io.swagger.annotations.Api;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * DATE:2016-12-2016/12/14 0014-23:52
 * Author: origin
 * DESC:主要用于发送验证码
 *
 */
@Api(value = "CaptchaCodeController", description = "验证码管理")
@RestController
@RequestMapping("/api/v1/public/captcha/")
public class CaptchaCodeController extends BaseController {

    //验证码服务
    @Autowired
    ICaptchaService captchaService;

    /**
     * 图形验证码
     * @param req  请求
     * @throws IOException
     */
    ///api/v1/user/register/imgcode
    @RequestMapping(value = "/get/imgcode", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryVerifyCode(HttpServletRequest req, HttpServletResponse response) throws IOException {
        //设置头信息
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");

        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        BufferedImage challenge = captchaService.getBufferedCaptchImage(getSessionId());
        ImageIO.write(challenge, "jpeg", jpegOutputStream);
        byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        ServletOutputStream respOs = response.getOutputStream();
        respOs.write(captchaChallengeAsJpeg);
        respOs.flush();
        respOs.close();

      /*  Session session = getSession();
        String sessionIdStr = session.getId().toString();
        session.setTimeout(Settings.SESSION_TIME_OUT);
        Object frequency = session.getAttribute("vcf");
        int freq = 0;
        if (null != frequency)
            freq = (int) frequency;
        if (20 < freq) {
            return;
        }
        session.setAttribute("vcf", freq + 1);
        String verifyCode = VerifyCodeUtils.generateVerifyCode(Settings.VERIFY_CODE_LENGTH);
        session.setAttribute(sessionIdStr, verifyCode);
        VerifyCodeUtils.outputImage(Settings.VERIFY_CODE_IMG_WIDTH, Settings.VERIFY_CODE_IMG_HEIGHT, resp.getOutputStream(), verifyCode);
    */
    }

    /**
     *
     * 图形验证码校验
     * @return
     *
     */
    @RequestMapping(value = "/check/imgcode", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject checkImgCaptchaCode(@RequestParam("imgcode") String imgcode){
        captchaService.validatorImageCode(getSessionId(),imgcode);
        return  success();
    }

    /**
     *
     * 发送手机验证码
     * @return
     *
     */
    @RequestMapping(value = "/sendsms", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject sendPhoneCaptchaCode(@RequestParam("imgCode") String imgCode){
        if(captchaService.validatorImageCode(getSessionId(),imgCode)){//验证成功
            captchaService.sendPhoneCaptchCode(getSessionId());
            return  success();
        }else{
            return  fail();
        }
    }

    /**
     * 验证图形验证码
     *
     * @param imgCode
     * @throws IOException
     */
/*    @RequestMapping(value = "/api/v1/user/register/imgcode/conf", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject judgeVerifyCode(@RequestParam("imgCode") String imgCode) {
       *//* if (null == imgCode || imgCode.equals(""))
            return fail("验证码格式不正确！");
        Session session = getSession();
        String sessionIdStr = session.getId().toString();
        String code = session.getAttribute(sessionIdStr).toString();*//*
        return response(imgCode.equals(imgCode));
    }*/

}
