package com.hudongwx.drawlottery.mobile.service.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by 11 on 2017/1/18.
 */
public interface IMessageService {
    /**
     * 查询所有的未读消息数量
     *
     */
    JSONObject queryAllMessageSize(Long userId);

    /**
     * 指定类型的消息
     *
     */
    JSONArray queryMessageByType(Long userId, String msgId, int typeid);

}
