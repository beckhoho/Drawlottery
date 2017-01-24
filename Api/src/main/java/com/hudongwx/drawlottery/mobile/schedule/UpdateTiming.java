package com.hudongwx.drawlottery.mobile.schedule;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;

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
public class UpdateTiming extends DelayTask{

    LotteryInfoMapper lotteryInfoMapper;

    LotteryInfo lotteryInfo;

    CommodityMapper commMapper;

    CommoditysMapper commsMapper;

    LuckCodeTemplateMapper luckCodeTemplateMapper;

    LuckCodesMapper luckCodesMapper;
    public UpdateTiming(CommodityMapper commMapper,CommoditysMapper commsMapper,LuckCodeTemplateMapper luckCodeTemplateMapper,LuckCodesMapper luckCodesMapper, LotteryInfoMapper lotteryInfoMapper, LotteryInfo lotteryInf){
        this.lotteryInfo = lotteryInf;
        this.lotteryInfoMapper=lotteryInfoMapper;
        this.commMapper=commMapper;
        this.commsMapper=commsMapper;
        this.luckCodeTemplateMapper=luckCodeTemplateMapper;
        this.luckCodesMapper=luckCodesMapper;
    }

    @Override
    public void todo() {
        int i = lotteryInfoMapper.insertSelective(lotteryInfo);
        System.out.println("开奖信息插入数据库情况：---------->"+i);
        Long commodityId = lotteryInfo.getCommodityId();
        Commoditys key = commsMapper.selectByKey(commodityId);
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
        System.out.println("更新商品信息！"+j);
    }
}
