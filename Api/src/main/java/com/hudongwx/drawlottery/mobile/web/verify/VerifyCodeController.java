package com.hudongwx.drawlottery.mobile.web.verify;

import com.hudongwx.drawlottery.mobile.utils.Settings;
import com.hudongwx.drawlottery.mobile.utils.VerifyCodeUtils;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(value = "VerifyCodeController", description = "验证码")
public class VerifyCodeController extends BaseController {

    @RequestMapping("/api/v1/user/register/code")
    public void getVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VerifyCodeUtils.saveCodeAndOutputImage(Settings.VERIFY_CODE_IMG_WIDTH, Settings.VERIFY_CODE_IMG_HEIGHT, resp.getOutputStream(), Settings.VERIFY_CODE_LENGTH);
    }
}
