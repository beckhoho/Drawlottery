package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.CommodityUserHistory;
import com.hudongwx.drawlottery.mobile.mappers.CommodityUserHistoryMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityUserHistoryService;
import org.apache.ibatis.annotations.Param;
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
 * @version 1.0, 2017/1/5 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/5 11:29　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class CommodityUserHistoryServiceImpl implements ICommodityUserHistoryService {

    @Autowired
    CommodityUserHistoryMapper mapper;
    @Override
    public boolean addCommodityUserHistory(CommodityUserHistory userHistory) {
        return mapper.insert(userHistory)>0;
    }

    @Override
    public boolean deleteUserHistory(CommodityUserHistory userHistory) {
        return mapper.delete(userHistory)>0;
    }

    @Override
    public boolean updateUserHistory(CommodityUserHistory userHistory) {
        return mapper.updateByPrimaryKeySelective(userHistory)>0;
    }

    @Override
    public List<Map<String, Object>> selectUserHistory(Long accountId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        CommodityUserHistory com = new CommodityUserHistory();
        com.setAccountId(accountId);
        List<CommodityUserHistory> list = mapper.select(com);
        for (CommodityUserHistory c : list){
            Map<String,Object> map = new HashMap<>();
            map.put("id",c.getId());//id
            map.put("accountId",c.getAccountId());//用户ID
            map.put("commodityId",c.getCommodityHistoryId());//商品id
            map.put("partakeDate",c.getPartakeDate());//购买时间
            mapList.add(map);
        }
        return mapList;
    }


}
