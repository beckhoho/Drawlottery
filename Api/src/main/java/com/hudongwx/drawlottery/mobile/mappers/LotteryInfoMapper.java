package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.LotteryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/16 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/16 16:17　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface LotteryInfoMapper extends BaseMapper<LotteryInfo>{

    LotteryInfo selectByComId(@Param("commodityId")Long commodityId);

    List<LotteryInfo> selectByUserAccountId(@Param("accountId")Long accountId,@Param("lastCommId")Long lastCommId,@Param("pageLoadSize")Integer pageLoadSize);
}
