package com.hudongwx.drawlottery.mobile.service.luckcodes;

import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:33　<br/>
 * <p>
 *  幸运码service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface ILuckCodesService {

    //添加幸运码
    boolean createLuckCode(Long commId,boolean rebuild);

    //查询商品幸运码
    List<LuckCodes> select(Long commodId);

    //删除幸运码
    boolean delete(Long id);
}
