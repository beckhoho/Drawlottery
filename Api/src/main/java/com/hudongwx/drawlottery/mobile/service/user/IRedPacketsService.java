package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.RedPackets;

import java.util.List;

/**
 * 红包
 */
public interface IRedPacketsService {

    boolean addRP(RedPackets rp);

    boolean delete(RedPackets redp);

    List<RedPackets> select(Long accountId);

}
