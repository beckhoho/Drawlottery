package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:41　<br/>
 * <p>
 *      商品service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface ICommodityService {

    //添加商品
    boolean addCommodity(Commoditys commod);

    //删除商品
    boolean delete(Commoditys commod);

    //查看商品
    Commoditys select(String name,Long roundTime);

    //修改商品信息
    boolean update(Commoditys commod,Integer id);
}
