package com.hudongwx.drawlottery.mobile.web.verify;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import com.hudongwx.drawlottery.mobile.utils.VerifyCodeUtils;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DATE:2016-12-2016/12/14 0014-23:52
 * Author: origin
 * DESC:主要用于发送验证码
 */
@RestController
@Api(value = "VerifyCodeController", description = "验证码管理")
public class VerifyCodeController extends BaseController {

    /**
     * 图形验证码
     * @param req  请求
     * @param resp 响应
     * @throws IOException
     *
     */
    @RequestMapping(value = "/api/v1/user/register/imgcode", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Session session = getSession();
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
    }

    /**
     * 验证图形验证码
     * @param imgCode
     * @throws IOException
     */
    @RequestMapping(value = "/api/v1/user/register/imgcode/conf", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject judgeVerifyCode(@RequestParam("imgCode") String imgCode) {
        if (null == imgCode || imgCode.equals(""))
            return fail("验证码格式不正确！");
        Session session = getSession();
        String sessionIdStr = session.getId().toString();
        String code = session.getAttribute(sessionIdStr).toString();
        return response(imgCode.equals(imgCode));
    }
}
