package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.Share;

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
 * 创建　kiter　2016/12/21 15:56　<br/>
 * <p>
 *      用户晒单service
 * <p>
 * @email 294786949@qq.com
 */
public interface IShareService {

    //用户添加晒单
    boolean addShare(Share share);

    //用户删除晒单
    boolean deleteShare(Share share);

    //用户查看晒单
    List<Share> selectShare(Long account);


}
