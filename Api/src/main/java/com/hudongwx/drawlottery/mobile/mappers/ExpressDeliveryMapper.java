package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.ExpressDelivery;
import org.apache.ibatis.annotations.Param;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/11 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/11 21:54　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface ExpressDeliveryMapper extends BaseMapper<ExpressDelivery>{

    ExpressDelivery selectByAccountAndCommodity(@Param("accountId")Long accountId,@Param("commodityId")Long commodityId);

}
