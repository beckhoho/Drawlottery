package com.hudongwx.drawlottery.mobile.service.advertisement.impl;

import com.hudongwx.drawlottery.mobile.entitys.Advertisement;
import com.hudongwx.drawlottery.mobile.mappers.AdvertisementMapper;
import com.hudongwx.drawlottery.mobile.service.advertisement.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/6 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class AdvertisementServiceImpl implements IAdvertisementService {

    @Autowired
    AdvertisementMapper adMapper;

    @Override
    public List<Advertisement> queryAdvertisement() {
        return adMapper.selectAll();
    }
}
