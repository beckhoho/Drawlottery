package com.hudongwx.drawlottery.mobile.service.notification.impl;

import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.NotificationPrizeMapper;
import com.hudongwx.drawlottery.mobile.mappers.UserMapper;
import com.hudongwx.drawlottery.mobile.service.notification.INotificationPrizeService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/27 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/27 17:51　<br/>
 * <p>
 * 开奖通知service接口
 * <p>
 * @email 346905702@qq.com
 */

@Service
public class NotificationPrizeServiceImpl implements INotificationPrizeService {

    @Autowired
    NotificationPrizeMapper npMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommoditysMapper commMapper;

    /**
     * 查询用户中奖消息（APP首页提示）
     *
     * @param accountId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectUserPrizeNotify(Long accountId) {
        //消息
        List<NotificationPrize> npList = npMapper.selectByNewPrizeNotify(Settings.MAX_INFO_SIZE_05);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (NotificationPrize np : npList) {
            Map<String, Object> map = new HashMap();
            map.put("commId", np.getCommodityId());
            map.put("content", np.getNoticeContent());
            mapList.add(map);
        }
        return mapList;
    }

}
