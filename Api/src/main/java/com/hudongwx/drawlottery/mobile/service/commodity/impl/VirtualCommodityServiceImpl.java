package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commodity;
import com.hudongwx.drawlottery.mobile.entitys.CommodityHistory;
import com.hudongwx.drawlottery.mobile.entitys.VirtualCommodity;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.IVirtualCommodityService;
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
 * @version 1.0, 2017/1/5 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/5 9:33　<br/>
 * <p>
 * 充值卡service实现类
 * <p>
 * @email 346905702@qq.com
 */

@Service
public class VirtualCommodityServiceImpl implements IVirtualCommodityService {
    @Autowired
    VirtualCommodityMapper vcMapper;
    @Autowired
    CommodityHistoryMapper chMapper;
    @Autowired
    CommodityTemplateMapper tempMapper;
    @Autowired
    LuckCodesMapper codesMapper;
    @Autowired
    CommodityMapper comMapper;

    @Override
    public boolean addCard(VirtualCommodity card) {
        return vcMapper.insert(card) > 0;
    }

    @Override
    public boolean deleteCard(VirtualCommodity card) {
        return vcMapper.delete(card) > 0;
    }

    @Override
    public List<Map<String, Object>> selectUserCard(Long accountId,Long commodityId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        VirtualCommodity card = new VirtualCommodity();
        card.setCommodityId(accountId);
        List<VirtualCommodity> list = vcMapper.select(card);
        for (VirtualCommodity car : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("userAccountId", car.getCommodityId());//获取用户ID
            map.put("id", car.getId());//获取充值卡ID
            map.put("cardNumber", car.getCardNumber());//获取卡号
            map.put("operator", car.getOperator());//运营商
            map.put("password", car.getPassword());//卡密
            map.put("state", car.getState());//状态
            map.put("worth", car.getWorth());//面额
            mapList.add(map);
        }
        return mapList;
    }



    @Override
    public String updateCardStateByCardNumber(String cardNumber, int state) {
        VirtualCommodity vir = new VirtualCommodity();
        vir.setCardNumber(cardNumber);
        VirtualCommodity vc = vcMapper.selectOne(vir);
        vc.setState(state);
        Commodity commodity = comMapper.selectByPrimaryKey(vc.getCommodityId());
        boolean allExchanged=true;
        List<VirtualCommodity> vcList = vcMapper.selectByCommId(vc.getCommodityId());
        for (VirtualCommodity virtualCommodity : vcList) {
            if(virtualCommodity.getState()==Settings.PASSWORD_NOT_VIEWED)
                allExchanged=false;
        }
        if(allExchanged){
            commodity.setExchangeState(Settings.COMMODITY_STATE_EXCHANGED);
            comMapper.updateByPrimaryKeySelective(commodity);
        }
        String pwd = null;
        if (vcMapper.updateByPrimaryKeySelective(vc) > 0)
            pwd = vc.getPassword();
        return pwd;
    }
}
