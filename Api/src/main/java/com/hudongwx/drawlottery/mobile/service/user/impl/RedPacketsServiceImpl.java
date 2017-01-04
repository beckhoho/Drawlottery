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
        RedPackets redPackets = new RedPackets();
        redPackets.setUserAccountId(accountId);
        List<RedPackets> list = mapper.select(redPackets);
        for (RedPackets r : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("rpId", r.getId());//获取红包ID
            map.put("name", r.getName());//获取红包名
            map.put("userAccountId", r.getUserAccountId());//获取用户ID
            map.put("validDate", r.getValidDate());//获取生效时间
            map.put("overdueDate", r.getOverdueDate());//获取失效时间
            map.put("usePrice", r.getUsePrice());//获取使用泛围
            map.put("worth", r.getWorth());//获取红包大小
            map.put("useState", r.getUseState());//获取红包状态（1.已使用，0.未使用）
            if (overdue(r)) {
                map.put("overdue", Settings.RED_PACKET_overdue_not);//是否可使用？1：可使用
            } else {
                map.put("overdue", Settings.RED_PACKET_overdue);//是否可使用？0：不可使用
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

    public boolean overdue(RedPackets r) {
        Date date = new Date();
        if (r.getOverdueDate().getTime() > date.getTime() || r.getValidDate().getTime() < date.getTime())
            return false;
        return true;
    }

}
