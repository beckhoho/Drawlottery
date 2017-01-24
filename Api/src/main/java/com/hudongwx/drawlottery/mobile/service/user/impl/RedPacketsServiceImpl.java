package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.RedPackets;
import com.hudongwx.drawlottery.mobile.mappers.RedPacketsMapper;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/20 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/20 0016　<br/>
 * <p>
 * 红包service接口实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class RedPacketsServiceImpl implements IRedPacketsService {

    @Autowired
    RedPacketsMapper mapper;

    /**
     * 查询用户红包
     *
     * @param accountId 用户id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAllByUserAccountId(Long accountId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<RedPackets> list = mapper.selectByAccount(accountId);
        for (RedPackets r : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", r.getId());//获取红包ID
            map.put("name", r.getName());//获取红包名
            map.put("userAccountId", r.getUserAccountId());//获取用户ID
            map.put("validDate", r.getValidDate());//获取生效时间
            map.put("overdueDate", r.getOverdueDate());//获取失效时间
            map.put("usePrice", r.getUsePrice());//获取使用泛围
            map.put("worth", r.getWorth());//获取红包大小
            Integer useState = r.getUseState();
            map.put("useState", useState);//获取红包状态（1.已使用，0.未使用）
            if (overdue(r)) {
                map.put("overdue", Settings.RED_PACKET_overdue);//是否过期？0：未过期
            } else {
                map.put("overdue", Settings.RED_PACKET_overdue_not);//是否过期？1：已过期
            }
            if (useState == Settings.RED_PACKET_USE_STATE_USED || overdue(r)) {
                map.put("usable", Settings.STATE_DISABLE);
            } else {
                map.put("usable", Settings.STATE_AVAILABLE);
            }
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public boolean useRedPacket(Long AccountId, Long rpId) {
        RedPackets rp = new RedPackets();
        rp.setUserAccountId(AccountId);
        rp.setId(rpId);
        RedPackets redPacket = mapper.selectOne(rp);
        redPacket.setUseState(Settings.RED_PACKET_USE_STATE_USED);//1、已使用；0、未使用
        return mapper.updateByPrimaryKey(redPacket) > 0;
    }

    @Override
    public RedPackets selectOne(Long accountId,Long rpId) {
        RedPackets packets = new RedPackets();
        packets.setUserAccountId(accountId);
        packets.setId(rpId);
        return mapper.selectOne(packets);
    }


    public boolean overdue(RedPackets r) {
        Date date = new Date();
        if (r.getOverdueDate() > date.getTime())
            return false;
        return true;
    }

}
