package com.hudongwx.drawlottery.mobile.service.user;

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
}
