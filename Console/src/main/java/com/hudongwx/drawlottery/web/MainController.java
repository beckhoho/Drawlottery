package com.hudongwx.drawlottery.web;

import com.hudongwx.drawlottery.common.base.BaseController;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.dto.response.AjaxResult;
import com.hudongwx.drawlottery.common.dto.response.MenuResult;
import com.hudongwx.drawlottery.service.commodity.CardService;
import com.hudongwx.drawlottery.service.conf.QiniuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/5 0005
 * Time: 2:20
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@RestController
@RequestMapping(method = RequestMethod.POST)
public class MainController extends BaseController {
    @Resource
    private LangConstants langConstants;
    @Resource
    private CardService cardService;

    @Resource
    private QiniuService qiniuService;

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

    @RequestMapping(method = RequestMethod.POST, value = "/getUploadToken")
    public AjaxResult getQiniuUploadToken(@RequestParam String suffix) {
        return success(qiniuService.getUpToken(suffix));
    }

    @RequestMapping("/cardCount")
    public List<Integer> getCardCount() {
        return cardService.getCounts();
    }
}
