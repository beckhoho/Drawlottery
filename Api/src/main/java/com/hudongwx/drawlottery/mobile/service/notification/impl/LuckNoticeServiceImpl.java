package com.hudongwx.drawlottery.mobile.service.notification.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
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

    @Override
    public Map<String,Object> addUserLuckNotice(Long commodityId) {
        Commodity com = new Commodity();
        com.setId(commodityId);
        com.setStateId(1);
        int i = comMapper.updateByPrimaryKeySelective(com);
        boolean b = addHistory(commodityId);
        CommodityHistory history = historyMapper.selectBycommId(commodityId);
        User user = userMapper.selectById(history.getLuckUserAccountId());
        Map<String,Object> map = new HashMap<>();
        if(i>0 && b && user!=null){
            map.put("userName",user.getNickname());//添加用户名
            map.put("buyNum",history.getBuyNumber());//添加购买数量
            map.put("endTime",history.getEndTime());//添加揭晓时间
        } else{
            map.put("userName",null);//添加用户名
            map.put("buyNum",null);//添加购买数量
            map.put("endTime",null);//添加揭晓时间
        }

        return map;
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
        LuckCodeTemplate byCode = luckCodeTemplateMapper.selectByCode(lotteryId + "");
        LuckCodes luckCodes = luckMapper.selectBytemplate(byCode.getId(),commodityId);
        List<LuckCodes> id = luckMapper.selectByUserAccountId(luckCodes.getUserAccountId(),commodityId);

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
        com.setLuckUserAccountId(lotteryInfo.getUserAccountId());
        com.setTempId(key.getTempId());
        //

        int insert = historyMapper.insert(com);

        int i = codesHistoryMapper.insertCopy(commodityId);

        return insert>0 && i>0;
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