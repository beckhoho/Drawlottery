package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.RedPackets;

import java.util.List;
import java.util.Map;

/**
 * 红包
 */
public interface IRedPacketsService {

    /**
     * 用户红包
     *
     * @param accountId
     * @return
     */
    List<Map<String, Object>> selectAllByUserAccountId(Long accountId);

    boolean useRedPacket(Long AccountId, Long rpId);

    /**
     * 查询用户的红包id
     * @param accountId
     * @return
     */
    RedPackets selectOne(Long accountId,Long rpId);

}
