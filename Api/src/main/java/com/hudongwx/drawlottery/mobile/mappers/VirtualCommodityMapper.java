package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.VirtualCommodity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/5 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/5 9:12　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface VirtualCommodityMapper extends BaseMapper<VirtualCommodity> {


    //通过商品Id查询虚拟商品
    List<VirtualCommodity> selectByCommId(@Param("commId") Long commId);


}
