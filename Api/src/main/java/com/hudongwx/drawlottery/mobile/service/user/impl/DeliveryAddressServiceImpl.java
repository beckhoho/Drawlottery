package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.DeliveryAddress;
import com.hudongwx.drawlottery.mobile.mappers.DeliveryAddressMapper;
import com.hudongwx.drawlottery.mobile.service.user.IDeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class DeliveryAddressServiceImpl implements IDeliveryAddressService {


    public boolean insert(DeliveryAddress address) {
        return false;
    }

    public boolean delete(String id) {
        return false;
    }

    public List<DeliveryAddress> selectAll() {
        return null;
    }

    public DeliveryAddress selectById() {
        return null;
    }
}
