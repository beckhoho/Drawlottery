package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.RedPackets;

import java.util.List;
import java.util.Map;

/**
 * 红包
 */
public interface IRedPacketsService {

    boolean addRP(RedPackets rp);

    boolean delete(RedPackets redp);

    List<Map<String,Object>> selectUserAll(Long accountId);
}
