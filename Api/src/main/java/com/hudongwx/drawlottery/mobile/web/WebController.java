package com.hudongwx.drawlottery.mobile.web;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.entitys.NotificationCampaign;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILotteryInfoService;
import com.hudongwx.drawlottery.mobile.service.notification.INotificationCampaignService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 页面渲染相关 controller 类.
 * Date: 2017/1/21 0021
 * Time: 23:09
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 * @apiNote 关于thymeleaf配置 {@link org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties}
 */
@Controller
@RequestMapping("/web")
public class WebController {
    @Resource
    private ICommodityService cService;
    @Resource
    private ILotteryInfoService lotteryInfoService;
    @Resource
    private INotificationCampaignService notificationCampaignService;

    @RequestMapping("/commodityInfo/{id}")
    public String commodityInfo(@PathVariable("id") Long id, Map<String, Object> model) {
        String s = cService.getContent(id);
        model.put("content", s);
        return "commodity";
    }

    @RequestMapping("/commodityCalc/{id}")
    public String calc(@PathVariable("id") Long id, Model model) {
        final LotteryInfo lotteryInfo = lotteryInfoService.selectLottery(id);
        model.addAttribute("info", lotteryInfo == null ? new LotteryInfo() : lotteryInfo);
        if (null == lotteryInfo) {
            final Commoditys commoditys = cService.getDetails(id);
            if (commoditys == null) {
                model.addAttribute("buyTotalNumber", "未能获取到相关信息");
            } else
                model.addAttribute("buyTotalNumber", commoditys.getBuyTotalNumber());
        }
        return "calc";
    }

    @RequestMapping("/activities")
    public String getActivities(Model model) {
        final List<NotificationCampaign> activities = notificationCampaignService.selectNews();
        model.addAttribute("activities", activities);
        return "news_activity";
    }
}
