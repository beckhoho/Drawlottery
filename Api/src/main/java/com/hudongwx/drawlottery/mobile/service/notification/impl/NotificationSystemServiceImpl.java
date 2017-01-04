package com.hudongwx.drawlottery.mobile.service.notification.impl;

import com.hudongwx.drawlottery.mobile.entitys.NotificationSystem;
import com.hudongwx.drawlottery.mobile.mappers.NotificationSystemMapper;
import com.hudongwx.drawlottery.mobile.service.notification.INotificationSystemService;
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
 * @version 1.0, 2017/1/3 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/3 15:12　<br/>
 * <p>
 *      系统通知service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class NotificationSystemServiceImpl implements INotificationSystemService {

    @Autowired
    NotificationSystemMapper  mapper;

    /**
     * 添加系统通知
     * @param system    系统通知对象
     * @return
     */
    @Override
    public boolean addSystemNotice(NotificationSystem system) {
        return mapper.insert(system)>0;
    }

    /**
     * 删除系统通知
     * @param system    系统通知对象
     * @return
     */
    @Override
    public boolean deleteNotice(NotificationSystem system) {
        return mapper.delete(system)>0;
    }
    /**
     * 查看系统通知
     * @param accountId     用户ID
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAllSystemNotice(Long accountId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<NotificationSystem> systems = mapper.selectAllSystemNotice(accountId);
        for (NotificationSystem system : systems){
            Map<String,Object> map = new HashMap<>();
            map.put("id",system.getId());//添加系统通知ID
            map.put("accountId",system.getUserAccountId());//添加用户ID
            map.put("title",system.getNoticeTitle());//添加通知标题
            map.put("noticeUrl",system.getNoticeUrl());//添加通知详情URL
            map.put("sendDate",system.getSendDate());//添加发送时间
            mapList.add(map);
        }
        return mapList;
    }
}
