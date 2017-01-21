package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import org.apache.ibatis.annotations.Param;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/15 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/15 16:20　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface CommodityMapper  extends BaseMapper<Commodity>{

    Long selectTempIdByCommId(@Param("commId")Long commId);

    int insertUseGenerated(Commodity commodity);
}
