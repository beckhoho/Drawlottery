package com.hudongwx.drawlottery.mobile.service.luckcodes;

import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/16 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/16 17:49　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface ILotteryInfoService {
    LotteryInfo selectLottery(Long commodityId);
}
