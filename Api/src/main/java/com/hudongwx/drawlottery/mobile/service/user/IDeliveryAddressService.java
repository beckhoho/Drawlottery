package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;

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
    public boolean addDa(Long accountId,DeliveryAddress address);

    /**
     * 删除指定的收货地址信息
     *
     * @param daId
     * @return
     */
    public boolean deleteDa(Long daId);

    /**
     * 更新收货地址信息
     *
     * @param address 用户地址信息
     * @return boolean
     */
    public boolean updateDa(Long accountId,DeliveryAddress address);

    /**
     * 查看指定用户的收货地址列表
     *
     * @param accountId
     * @return
     */
    public List<Map<String, Object>> selectByAccountId(Long accountId);

}
