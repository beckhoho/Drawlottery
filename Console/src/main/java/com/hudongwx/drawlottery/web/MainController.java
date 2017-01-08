package com.hudongwx.drawlottery.web;

import com.hudongwx.drawlottery.common.base.BaseController;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.dto.response.MenuResult;
import com.hudongwx.drawlottery.pojo.User;
import com.hudongwx.drawlottery.service.user.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Drawlottery.
 * Date: 2017/1/5 0005
 * Time: 2:20
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@RestController
public class MainController extends BaseController {
    @Resource
    private IUserService userService;
    @Resource
    private LangConstants langConstants;

    @RequestMapping("/")
    public ModelAndView main() {
        final ModelAndView modelAndView = new ModelAndView("index");
        final User user = userService.queryUserByPhoneNum("13990949387");
        final Map<String, Object> model = modelAndView.getModel();
        model.put("user", user);
        return modelAndView;
    }

    @RequestMapping("/getMenuResult")
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
}
