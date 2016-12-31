package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import com.hudongwx.drawlottery.mobile.mappers.DeliveryAddressMapper;
import com.hudongwx.drawlottery.mobile.service.user.IDeliveryAddressService;
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
 * @author origin
 * @version 1.0, 2016/12/16 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2016/12/16 0016　<br/>
 * <p>
 * 用户收货地址service接口实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class DeliveryAddressServiceImpl implements IDeliveryAddressService {
    @Autowired
    DeliveryAddressMapper damapper;

    /**
     * 添加用户收货地址（每个用户允许最多添加10条收货地址信息）
     *
     * @param address 用户地址信息
     * @return boolean
     */
    @Override
    public boolean addDa(DeliveryAddress address) {
        DeliveryAddress da = new DeliveryAddress();
        da.setUserId(address.getUserId());
        if (damapper.selectCount(da) >= Settings.ADDRESS_ADD_MAX) return false;
        return damapper.insert(address) > 0;
    }

    /**
     * 删除指定的收货地址信息
     *
     * @param id 收貨地址id
     * @return boolean
     */
    @Override
    public boolean deleteDa(Long id) {
        return damapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 更新收货地址信息
     *
     * @param address 用户地址信息
     * @return boolean
     */
    @Override
    public boolean updateDa(DeliveryAddress address) {
        return damapper.updateByPrimaryKey(address) > 0;
    }

    /**
     * 查看指定用户的收货地址列表
     *
     * @param accountId 用户账号
     * @return List<DeliveryAddress>
     */
    @Override
    public List<Map<String,Object>> selectByUserAccountId(Long accountId) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        DeliveryAddress da = new DeliveryAddress();
        da.setUserId(accountId);
        List<DeliveryAddress> list = damapper.select(da);
        for (DeliveryAddress de : list){
            Map<String,Object> map = new HashMap<>();
            map.put("id",de.getId());//获取收货地址ID
            map.put("receiverName",de.getReceiverName());//收货人姓名
            map.put("address",de.getAddress());//收货地址
            map.put("phone",de.getPhone());//手机号码
            map.put("userAccountId",de.getUserId());//用户id
            map.put("state",de.getState());//是否是默认地址（1：是，0：不是）
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 查询单条地址信息
     *
     * @param id 地址信息id
     * @return
     */
    @Override
    public DeliveryAddress selectById(Long id) {
        return damapper.selectByPrimaryKey(id);
    }

}
