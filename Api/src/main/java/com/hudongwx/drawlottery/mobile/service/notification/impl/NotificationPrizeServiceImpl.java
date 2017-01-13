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
import java.util.List;

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
 *          开奖通知service接口
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
     * @param noPrize   中奖通知对象
     * @return
     */
    @Override
    public boolean addPrizeNotification(NotificationPrize noPrize) {
        return mapper.insert(noPrize)>0;
    }

    /**
     * 查看所有的中奖通知
     * @return
     */
    @Override
    public List<NotificationPrize> selectAll() {
        return mapper.selectAll();
    }

    /**
     *  查看用户的商品开奖通知
     * @param account   用户accountID
     * @param commodId  商品ID
     * @return  返回一个开奖通知对象
     */
    @Override
    public List<NotificationPrize> selectByAccount(Long account, Long commodId) {
        NotificationPrize no = new NotificationPrize();
        no.setAccountId(account);
        no.setCommodityId(commodId);
        return mapper.select(no);
    }

    /**
     * 删除单个开奖通知
     * @param id    开奖id
     * @return  返回一个开奖结果
     */
    @Override
    public boolean delete(Long id) {
        return mapper.deleteByPrimaryKey(id)>0;
    }

    /**
     * 查询最新的中奖通知
     * @return  返回中奖通知集合；
     */
    @Override
    public List<String> selectByNew() {
        List<NotificationPrize> no = mapper.selectByNew();
        List<String> noti = new ArrayList<>();
        int s = Settings.NOTIFY_SHOW_MAX>=no.size()?no.size():Settings.NOTIFY_SHOW_MAX;
        for(int i=0;i<s;i++){
            NotificationPrize notifi = no.get(i);
            User select = userMapper.selectById(notifi.getAccountId());
            Commoditys select1 = commodMapper.selectByKey(notifi.getCommodityId());
            noti.add("恭喜："+select.getNickname()+"获得"+select1.getName());
        }
        return noti;
    }


}
