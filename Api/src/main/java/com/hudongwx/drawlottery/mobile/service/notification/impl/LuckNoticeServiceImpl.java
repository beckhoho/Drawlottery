package com.hudongwx.drawlottery.mobile.service.notification.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.schedule.DelayTask;
import com.hudongwx.drawlottery.mobile.service.notification.ILuckNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/6 9:38　<br/>
 * <p>
 *      用户中奖通知service实现类
 * <p>
 * @email 346905702@qq.com
 */

@Service
public class LuckNoticeServiceImpl implements ILuckNoticeService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    LuckNoticeMapper mapper;
    @Autowired
    CommodityMapper comMapper;
    @Autowired
    LotteryInfoMapper lotteryMapper;
    @Autowired
    CommoditysMapper commMapper;
    @Autowired
    LuckCodesMapper luckMapper;
    @Autowired
    CommodityHistoryMapper historyMapper;
    @Autowired
    UserCodesHistoryMapper codesHistoryMapper;
    @Autowired
    LuckCodeTemplateMapper luckCodeTemplateMapper;
    @Autowired
    LotteryInfoMapper lotteryInfoMapper;

    @Override
    public Map<String,Object> addUserLuckNotice(Long commodityId) {

        Commodity commodity = comMapper.selectByKey(commodityId);
        LotteryInfo lotteryInfo = lotteryInfoMapper.selectByComId(commodityId);
        User user = userMapper.selectById(lotteryInfo.getUserAccountId());
        Map<String,Object> map = new HashMap<>();

        map.put("userName",user.getNickname());//添加用户名
        map.put("buyNum",commodity.getBuyNumber());//添加购买数量
        map.put("endTime",commodity.getEndTime());//添加揭晓时间

        if(commodity.getStateId()!=1){
            Commodity com = new Commodity();
            com.setId(commodityId);
            com.setStateId(1);
            comMapper.updateByPrimaryKeySelective(com);
            return map;
        }
        else {
            return map;
        }






    }

    @Override
    public boolean deleteUserLuckNotice(LuckNotice notice) {
        return mapper.delete(notice)>0;
    }

    @Override
    public Map<String, Object> selectByCommodityId(Long commodityId) {


        return null;
    }


    @Override
    public boolean updateUserLuckNotice(LuckNotice notice) {
        Example e = new Example(LuckNotice.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("id",notice.getId());
        e.or(criteria);
        return mapper.updateByExampleSelective(notice,e)>0;
    }
}