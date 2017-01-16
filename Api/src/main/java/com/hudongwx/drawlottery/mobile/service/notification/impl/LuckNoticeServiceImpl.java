package com.hudongwx.drawlottery.mobile.service.notification.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
import com.hudongwx.drawlottery.mobile.service.notification.ILuckNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    LuckNoticeMapper mapper;
    @Autowired
    CommodityMapper comMapper;
    @Autowired
    LotteryInfoMapper lotteryMapper;
    @Autowired
    CommoditysMapper commMapper;
    @Autowired
    UserLuckCodesMapper userLuckMapper;
    @Autowired
    LuckCodesMapper luckMapper;
    @Autowired
    CommodityHistoryMapper historyMapper;
    @Autowired
    UserCodesHistoryMapper codesHistoryMapper;

    @Override
    public boolean addUserLuckNotice(Long commodityId) {
        Commodity com = new Commodity();
        com.setId(commodityId);
        com.setStateId(1);
        int i = comMapper.updateByPrimaryKeySelective(com);
        boolean b = addHistory(commodityId);
        return i>0 && b;
    }

    @Override
    public boolean deleteUserLuckNotice(LuckNotice notice) {
        return mapper.delete(notice)>0;
    }

    @Override
    public Map<String, Object> selectByCommodityId(Long commodityId) {


        return null;
    }
    public boolean addHistory(Long commodityId){

        Commoditys key = commMapper.selectByKey(commodityId);
        LotteryInfo lotteryInfo = lotteryMapper.selectByComId(commodityId);
        Long lotteryId = lotteryInfo.getLotteryId();
        LuckCodes codes = luckMapper.selectByCode(lotteryId + "");
        UserLuckCodes luckCodes = userLuckMapper.selectByLuckId(codes.getId());
        List<UserLuckCodes> id = userLuckMapper.selectByUserAccountId(luckCodes.getUserAccountId());

        CommodityHistory com = new CommodityHistory();
        com.setLuckCode(lotteryInfo.getLotteryId()+"");
        com.setRoundTime(key.getRoundTime());
        com.setGenre(key.getGenre());
        com.setBuyNumber(id.size());
        com.setBuyTotalNumber(key.getBuyTotalNumber());
        com.setCommodityId(commodityId);
        com.setCommodityName(key.getName());
        com.setCoverImgUrl(key.getCoverImgUrl());
        com.setEndTime(new Date().getTime());
        com.setLuckUserAccountId(luckCodes.getUserAccountId());
        com.setTempId(key.getTempId());
        int insert = historyMapper.insert(com);

        for (UserLuckCodes u : id){
            UserCodesHistory userHistory = new UserCodesHistory();
            userHistory.setRoundTime(key.getRoundTime());
            userHistory.setUserLuckCodeId(u.getUserAccountId());
            userHistory.setCommodityId(u.getCommodityId());
            userHistory.setAddressIp(u.getAddressIp());
            userHistory.setBuyDate(u.getBuyDate());
            userHistory.setUserLuckCodeId(u.getLuckCodeId());
            codesHistoryMapper.insert(userHistory);
        }


        return insert>0;
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