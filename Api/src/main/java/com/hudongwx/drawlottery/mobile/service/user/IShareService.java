package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.Share;

import java.util.List;
import java.util.Map;

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
 * 用户晒单service
 * <p>
 * @email 294786949@qq.com
 */
public interface IShareService {

    /**
     * 用户添加晒单信息
     *
     * @param share 晒单信息
     * @return
     */
    boolean addShare(Share share);

    /**
     * 用户删除晒单信息
     *
     * @param shareid 晒单id
     * @return
     */
    boolean deleteShare(Long shareid);

    /**
     * 用户晒单列表
     *
     * @param accountid
     * @return
     */
    List<Share> selectShare(Long accountid, Long lastshareid, Integer tag);

    //用户分享晒单
    boolean friendsShare(Long account);

    //查看用户晒单
    List<Map<String,Object>> selectUserAll(Long account);
}
