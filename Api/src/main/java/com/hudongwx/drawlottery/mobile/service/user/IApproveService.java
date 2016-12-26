package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.Approve;
import com.hudongwx.drawlottery.mobile.entitys.User;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/21 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/21 16:51　<br/>
 * <p>
 *  认证信息Service
 * <p>
 * @email 294786949@qq.com
 */

public interface IApproveService {

    //添加用户认证信息
    boolean addApproveMassage(Approve approve);

    //查看用户认证信息
    List<Approve> select(Long userAccount);

    //修改用户认证信息
    boolean update(Approve approve);
}
