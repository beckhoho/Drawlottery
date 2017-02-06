package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.github.pagehelper.PageHelper;
import com.hudongwx.drawlottery.mobile.entitys.CommodityTemplate;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodeTemplate;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.service.commodity.IGenerateService;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Drawlottery.
 * Date: 2017/1/23 0023
 * Time: 15:33
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service("generateService")
public class GenerateServiceImpl implements IGenerateService {

    @Resource
    private ICommodityService commodityService;
    @Resource
    private ILuckCodesService luckCodesService;


    /**
     * 生成单个商品（注意，处于待售状态）
     *
     * @param tempId        模板id
     * @param luckCodeCount 幸运码总量
     */
    @Override
    public void generateCommodity(long tempId, long luckCodeCount) {
        synchronized (((Long) tempId)){
            //检索生成幸运码
            generateLuckCodes(luckCodeCount);
            final Commoditys commodity = new Commoditys();
            commodity.setBuyCurrentNumber(0);
            commodity.setRoundTime("" + generateNewRoundTime());
            commodity.setViewNum(0L);
            commodity.setTempId(tempId);
            commodity.setStateId(4);
            commodityService.addCommodity(commodity);
            //关联幸运码
            connectLuckCodes(commodity.getId(), luckCodeCount);
        }
    }

    /**
     * 生成幸运码（会尝试生成，如果total已经小于或等于目前已有的了，就不再生成）
     *
     * @param total 幸运码总量
     * @return 返回最大值
     */
    @Override
    public String generateLuckCodes(long total) {
        Long count = luckCodesService.getTempCount();
        //单次生成总数
        final int onceCount = 80000;
        //幸运码基数
        final int luckBase = 10000000;
        while (total > count) {
            Set<LuckCodeTemplate> set = new HashSet<>();
            for (long i = count + 1; i <= total && i <= count + onceCount; i++) {
                LuckCodeTemplate temp = new LuckCodeTemplate();
                temp.setLuckCode(luckBase + i + "");
                set.add(temp);
            }
            long i = count + 1;
            for (LuckCodeTemplate t : set) {
                t.setId(i++);
            }
            luckCodesService.addToTemp(set);
            count += onceCount;
        }
        return luckBase + total + "";
    }

    /**
     * 将幸运码关联到商品
     *
     * @param commodityId 商品id
     * @param count       总数
     */
    @Override
    public void connectLuckCodes(long commodityId, long count) {
        final int onceSelect = 500000;
        final int onceCreate = 80000;
        final int base = 10000000;
        for (int currentPage = 0; currentPage * onceSelect < count; currentPage++) {
            PageHelper.startPage(currentPage + 1, onceCreate);
            List<LuckCodes> list = luckCodesService.selectRange(base + count);
            for (LuckCodes code : list) {
                code.setCommodityId(commodityId);
            }
            luckCodesService.insertCodeList(list);
        }
    }

    /**
     * 保证同一模板具有 roundNum 的期数
     *
     * @param roundNum 期数总数
     * @apiNote 默认扫描所有的模板
     */
    @Override
    public void keepRound(long roundNum) {
        List<CommodityTemplate> temps = commodityService.getNotKeepRoundTemplate(roundNum);
        for (CommodityTemplate temp : temps) {
            final Integer count = temp.getCount();
            for (int i = count; i < roundNum; i++) {
                generateCommodity(temp.getId(), temp.getBuyTotalNumber());
            }
        }
    }

    /**
     * 生成新的期数.
     *
     * @return 唯一新期数
     */
    @Override
    public Long generateNewRoundTime() {
        return commodityService.getMaxRoundTime() + 1;
    }
}
