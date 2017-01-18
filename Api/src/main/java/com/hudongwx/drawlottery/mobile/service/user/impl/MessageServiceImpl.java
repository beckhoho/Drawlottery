package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Notification;
import com.hudongwx.drawlottery.mobile.entitys.NotificationCampaign;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import com.hudongwx.drawlottery.mobile.entitys.NotificationSystem;
import com.hudongwx.drawlottery.mobile.mappers.NotificationCampaignMapper;
import com.hudongwx.drawlottery.mobile.mappers.NotificationPrizeMapper;
import com.hudongwx.drawlottery.mobile.mappers.NotificationSystemMapper;
import com.hudongwx.drawlottery.mobile.service.user.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 11 on 2017/1/18.
 */
@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    NotificationCampaignMapper campaignMapper;
    @Autowired
    NotificationPrizeMapper prizeMapper;
    @Autowired
    NotificationSystemMapper systemMapper;

    @Override
    public JSONObject queryAllMessageSize(Long userId) {
        //0系统消息
        //1中奖消息
        //2发货消息
        JSONObject object = new JSONObject();
        object.put("0", systemMapper.countUnreadMsg(userId));//系统消息
        object.put("1", prizeMapper.countUnreadMsg(userId));//中奖消息
        object.put("2", campaignMapper.countUnreadMsg(userId));//发货消息
        return object;
    }

    @Override
    public JSONArray queryMessageByType(Long userId, String msgId, int typeid) {
        JSONArray data = new JSONArray();
        if (typeid == 0) {
            List<NotificationSystem> notificationSystems = systemMapper.selectLimitTen(userId, msgId);
            for (NotificationSystem notificationSystem : notificationSystems) {
                if (addJsonObj(notificationSystem, data)) {//消息未读，则更改状态
                    systemMapper.updateStateById(notificationSystem.getId());
                }
            }
        } else if (typeid == 1) {
            List<NotificationPrize> notificationPrizes = prizeMapper.selectLimitTen(userId, msgId);
            for (NotificationPrize notificationPrize : notificationPrizes) {
                if (addJsonObj(notificationPrize, data)) {
                    prizeMapper.updateStateById(notificationPrize.getId());
                }
            }
        } else if (typeid == 2) {
            List<NotificationCampaign> notificationCampaigns = campaignMapper.selectLimitTen(userId, msgId);
            for (NotificationCampaign notificationCampaign : notificationCampaigns) {
                if (addJsonObj(notificationCampaign, data)) {
                    campaignMapper.updateStateById(notificationCampaign.getId());
                }
            }
        } else {
            return null;
        }
        return data;
    }

    public boolean addJsonObj(Notification notification, JSONArray data) {
        boolean flag = false;
        if (notification.getState() == 0) {//改消息为已读
            notification.setId(notification.getId());
            notification.setState(1);
            flag = true;//消息状态需更改
        }
        JSONObject object = new JSONObject();
        object.put("title", notification.getNoticeTitle());
        object.put("id", notification.getId());
        object.put("content", notification.getNoticeContent());
        object.put("date", notification.getSendDate().toString());
        data.add(object);
        return flag;
    }
}
