package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Card;
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
 * 创建　kiter　2017/1/16 20:37　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface CardMapper extends BaseMapper<Card>{

    //查看卡密
    String selectCardPassword(@Param("cardNumber") String cardNumber);

    //更新充值卡密码
    int updateCardState(@Param("cardNumber") String cardNumber,@Param("commodityId")Long commodityId);

    List<Card> selectByCommodityId(@Param("commodityId")Long commodityId);
}
