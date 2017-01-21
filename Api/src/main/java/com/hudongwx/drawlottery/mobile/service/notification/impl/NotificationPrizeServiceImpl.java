package com.hudongwx.drawlottery.mobile.service.notification.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.NotificationPrize;
import com.hudongwx.drawlottery.mobile.entitys.User;
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
    NotificationPrizeMapper mapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommoditysMapper commodMapper;

    /**
     * 添加中奖通知对象
     *
     * @param noPrize 中奖通知对象
     * @return
     */
    @Override
    public boolean addPrizeNotification(NotificationPrize noPrize) {
        return mapper.insert(noPrize) > 0;
    }

    /**
     * 查看所有的中奖通知
     *
     * @return
     */
    @Override
    public List<NotificationPrize> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 查看用户的商品开奖通知
     *
     * @param account  用户accountID
     * @param commodId 商品ID
     * @return 返回一个开奖通知对象
     */
    @Override
    public List<NotificationPrize> selectByAccount(Long account, Long commodId) {
        NotificationPrize no = new NotificationPrize();
        no.setAccountId(account);
        no.setCommodityId(commodId);
        return mapper.select(no);
    }


    /**
     * 查询最新的中奖通知
     *
     * @return 返回中奖通知集合；
     */
    @Override
    public List<Map<String, Object>> selectByNew() {
        List<NotificationPrize> no = mapper.selectByNewPrizeNotify();
        int s = Settings.NOTIFY_SHOW_MAX >= no.size() ? no.size() : Settings.NOTIFY_SHOW_MAX;
        List<Map<String, Object>> mapList = null;
        for (int i = 0; i < s; i++) {
            Map<String,Object> map=new HashMap<>();
            NotificationPrize notifi = no.get(i);
            User select = userMapper.selectById(notifi.getAccountId());
            Commoditys comm = commodMapper.selectByKey(notifi.getCommodityId());
            map.put("commId",comm.getId());
            map.put("msg","恭喜：" + select.getNickname() + "获得" + comm.getName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     *
     * @param accountId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectUserPrizeNotify(Long accountId){
        //消息
        List<NotificationPrize> npList = mapper.selectByNewPrizeNotify();
        List<Map<String, Object>> mapList=new ArrayList<>();
        for (NotificationPrize np : npList) {
            Map<String,Object>map=new HashMap();
            map.put("commId",np.getCommodityId());
            map.put("content",np.getNoticeContent());
            mapList.add(map);
        }
        return mapList;
    }

}
