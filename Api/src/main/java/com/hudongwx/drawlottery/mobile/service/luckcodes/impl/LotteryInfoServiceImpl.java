package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import com.hudongwx.drawlottery.mobile.mappers.LotteryInfoMapper;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILotteryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/16 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/16 19:04　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class LotteryInfoServiceImpl implements ILotteryInfoService {
    @Autowired
    LotteryInfoMapper mapper;
    @Override
    public LotteryInfo selectLottery(Long commodityId) {
        return mapper.selectByComId(commodityId);
    }
}
