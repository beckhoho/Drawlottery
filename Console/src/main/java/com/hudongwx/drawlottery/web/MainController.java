package com.hudongwx.drawlottery.web;

import com.hudongwx.drawlottery.common.base.BaseController;
import com.hudongwx.drawlottery.common.constants.ConfigConstants;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.dto.response.AjaxResult;
import com.hudongwx.drawlottery.common.dto.response.MenuResult;
import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.commodity.IFileService;
import com.hudongwx.drawlottery.service.user.IUserService;
import com.qiniu.util.Auth;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * Drawlottery.
 * Date: 2017/1/5 0005
 * Time: 2:20
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Controller
public class MainController extends BaseController {
    @Resource
    private IUserService userService;
    @Resource
    private LangConstants langConstants;
    @Resource
    private ResourceLoader resourceLoader;
    @Resource
    private ConfigConstants configConstants;
    @Resource
    private IFileService fileService;

    @RequestMapping("/")
    public ModelAndView main() {
        final ModelAndView modelAndView = new ModelAndView("index");
        final User user = userService.queryUserByPhoneNum("13990949387");
        final Map<String, Object> model = modelAndView.getModel();
        model.put("user", user);
        return modelAndView;
    }

    @RequestMapping("/getMenuResult")
    @ResponseBody
    public MenuResult getMenuResult() {
        return new MenuResult(new String[]{
                langConstants.getLang(langConstants.MAIN),
                langConstants.getLang(langConstants.COMMODITY_MANAGEMENT),
                langConstants.getLang(langConstants.ORDER_MANAGEMENT),
                langConstants.getLang(langConstants.USER_MANAGEMENT),
                langConstants.getLang(langConstants.SHARE_MANAGEMENT),
                langConstants.getLang(langConstants.INTEGRAL_MANAGEMENT),
                langConstants.getLang(langConstants.AD_MANAGEMENT),
                langConstants.getLang(langConstants.FEEDBACK),
                langConstants.getLang(langConstants.MESSAGE_MANAGEMENT)
        });
    }

    /*@RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        //return ResponseEntity.notFound().build();
        final String path = fileService.getPath(filename);
        if (path == null)
            return ResponseEntity.notFound().build();
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + configConstants.getUploadPath() + filename));
        } catch (RuntimeException ignored) {
            return ResponseEntity.notFound().build();
        }
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/getUploadToken")
    @ResponseBody
    public AjaxResult getQiniuUploadToken(@RequestBody String suffix) {
        //获取AccessKey
        final String ak = configConstants.getQiniuAccessKey();
        //获取SecretKey
        final String qk = configConstants.getQiniuSecretKey();
        //上传空间
        String bucketName = configConstants.getBucketName();
        if (!suffix.startsWith("."))
            suffix = "." + suffix;
        //上传到七牛后保存的文件名
        String key = UUID.randomUUID().toString() + suffix;

        //密钥配置
        Auth auth = Auth.create(ak, qk);

        auth.uploadToken(bucketName);
        //生成token
        return success(auth.uploadToken(bucketName, key, configConstants.getExpries(), null));
    }
}