package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.PromoterProfit;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.PromoterProfitMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.service.user.IPromoterProfitService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/20 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/20 <br/>
 * <p>
 * 推广员收益详情
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class PromoterProfitServiceImpl implements IPromoterProfitService {

    @Autowired
    PromoterProfitMapper promProfMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 查询推广员收益详情
     *
     * @param accountId
     * @return
     */
    @Override
    public Map<String, Object> selectPromoterProfitInfo(Long accountId, Long lastTime) {
        Map<String, Object> map = new HashMap<>();
        //基本数据
        User user = userMapper.selectById(accountId);
        map.put("accountId", accountId);//推广Id
        map.put("lv", user.getLv());//等级
        map.put("balance", user.getBalance());//余额
        map.put("amIncome", promProfMapper.countUserIncome(accountId).doubleValue());//累计收益
        List<PromoterProfit> profitList = promProfMapper.selectPageProfitByAccountId(accountId, lastTime, Settings.PAGE_LOAD_SIZE_06);
        if (profitList.isEmpty())
            return null;
        PromoterProfit pp = profitList.get(0);
        List<Map<String, Object>> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("YIncome", new Date().getTime() - pp.getOperateTime() > (Settings.ONE_DAY_LONG_VALUE) ? 0.00d : pp.getIncome().doubleValue());//昨日收益
        for (PromoterProfit profit : profitList) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("date", sdf.format(profit.getOperateTime()));
            map1.put("income", profit.getIncome().doubleValue());
            list.add(map1);
        }
        map.put("incomeHistory", list);
        return map;
    }
}
