package com.hudongwx.drawlottery.mobile.web;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.entitys.NotificationCampaign;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILotteryInfoService;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
import com.hudongwx.drawlottery.mobile.service.notification.INotificationCampaignService;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.service.user.IUserService;
import com.hudongwx.drawlottery.mobile.utils.toolbox.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private IShareService shareService;
    @Resource
    private IUserService userService;
    @Resource
    private ICommodityService commodityService;
    @Resource
    private ILuckCodesService luckCodesService;

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

    @RequestMapping("/share/view/{id}")
    public String share(@PathVariable Long id, Model model) {
        final Share share = shareService.getShare(id);
        if (share.getImgList() == null) {
            share.setImgList(new ArrayList<>());
        }
        final Long userAccountId = share.getUserAccountId();
        final Map<String, Object> userInfo = userService.getUserInfo(userAccountId);

        final Long commodityId = share.getCommodityId();
        final Commoditys commoditys = commodityService.getDetails(commodityId);
        final int count = luckCodesService.getCount(userAccountId, commodityId);
        final LotteryInfo lotteryInfo = lotteryInfoService.selectLottery(commodityId);
        final Long lotteryId = lotteryInfo.getLotteryId();
        final Date endDate = lotteryInfo.getEndDate();
        model.addAttribute("nickname",userInfo.get("nickname"));
        model.addAttribute("headUrl",userInfo.get("headUrl"));
        model.addAttribute("shareTime", DateUtil.formatDateTime(new Date(share.getIssueDate())));
        model.addAttribute("name",commoditys.getName());
        model.addAttribute("roundTime",commoditys.getRoundTime());
        model.addAttribute("count",count);
        model.addAttribute("luckCodes",lotteryId);
        model.addAttribute("endDate", DateUtil.formatDateTime(endDate));
        model.addAttribute("content",share.getParticulars());
        model.addAttribute("imgList",share.getImgList());
        return "share";
    }
}
