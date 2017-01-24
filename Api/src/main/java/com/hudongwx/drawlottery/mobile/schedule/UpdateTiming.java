package com.hudongwx.drawlottery.mobile.schedule;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.utils.ServiceUtils;

import java.util.Date;
import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/22 2:17　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class UpdateTiming extends DelayTask {

    LotteryInfoMapper lotteryInfoMapper;

    LotteryInfo lotteryInfo;

    CommodityMapper commMapper;

    CommoditysMapper commsMapper;

    LuckCodeTemplateMapper luckCodeTemplateMapper;

    LuckCodesMapper luckCodesMapper;

    NotificationPrizeMapper npMapper;

    UserMapper userMapper;

    public UpdateTiming(NotificationPrizeMapper npMapper, UserMapper userMapper, CommodityMapper commMapper, CommoditysMapper commsMapper, LuckCodeTemplateMapper luckCodeTemplateMapper, LuckCodesMapper luckCodesMapper, LotteryInfoMapper lotteryInfoMapper, LotteryInfo lotteryInf) {
        this.lotteryInfo = lotteryInf;
        this.lotteryInfoMapper = lotteryInfoMapper;
        this.commMapper = commMapper;
        this.commsMapper = commsMapper;
        this.luckCodeTemplateMapper = luckCodeTemplateMapper;
        this.luckCodesMapper = luckCodesMapper;
        this.npMapper = npMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void todo() {
        LotteryInfo li = lotteryInfoMapper.selectByComId(this.lotteryInfo.getCommodityId());
        if (null == li)
            lotteryInfoMapper.insertSelective(this.lotteryInfo);
        Long commodityId = this.lotteryInfo.getCommodityId();
        Commoditys key = commsMapper.selectByKey(commodityId);
        User user = userMapper.selectById(this.lotteryInfo.getUserAccountId());
        boolean b = ServiceUtils.insertNotificationPrizeInfo(npMapper, key, this.lotteryInfo, user);
        LotteryInfo lotteryInfo1 = lotteryInfoMapper.selectByComId(commodityId);
        Long lotteryId1 = lotteryInfo1.getLotteryId();
        LuckCodeTemplate byCode = luckCodeTemplateMapper.selectByCode(lotteryId1 + "");
        LuckCodes luckCodes = luckCodesMapper.selectBytemplate(byCode.getId(), commodityId);
        List<LuckCodes> id = luckCodesMapper.selectByUserAccountId(luckCodes.getUserAccountId(), commodityId);

        Commodity com = new Commodity();
        com.setId(key.getId());
        com.setBuyNumber(id.size());
        com.setEndTime(new Date().getTime());
        int j = commMapper.updateByPrimaryKeySelective(com);
    }
}
