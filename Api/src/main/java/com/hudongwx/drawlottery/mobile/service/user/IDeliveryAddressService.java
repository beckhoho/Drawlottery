package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;

import java.util.List;

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
 * *******
 * <p>
 * @email 294786949@qq.com
 */
public interface IDeliveryAddressService {

    /**
     * 添加用户收货地址（每个用户允许最多添加10条收货地址信息）
     *
     * @param address 用户地址信息
     * @return boolean
     */
    public boolean addDa(DeliveryAddress address);

    /**
     * 删除指定的收货地址信息
     *
     * @param id 收貨地址id
     * @return boolean
     */
    public boolean deleteDa(Long id);

    /**
     * 更新收货地址信息
     *
     * @param address 用户地址信息
     * @return boolean
     */
    public boolean updateDa(DeliveryAddress address);

    /**
     * 查看指定用户的收货地址列表
     *
     * @param accountId 用户账号
     * @return List<DeliveryAddress>
     */
    public List<DeliveryAddress> selectByUserAccountId(Long accountId);

    /**
     * 查询单条地址信息
     *
     * @param id 地址信息id
     * @return
     */
    public DeliveryAddress selectById(Long id);

}
