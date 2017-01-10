package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.CommodityExchange;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/9 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/9 20:32　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public interface ICommodityExchangeService {

    //添加商品兑换方式对应
    boolean addExchange(CommodityExchange exchange);

    //删除商品兑换方式对应
    boolean deleteExchange(CommodityExchange exchange);

    //修改商品兑换方式对应
    boolean updateExchange(CommodityExchange exchange);

    //查看商品兑换方式对应
    List<Map<String, Object>> selectByCommodityId(Long commId);
}
