package com.hudongwx.drawlottery.mobile.service.advertisement.impl;

import com.hudongwx.drawlottery.mobile.entitys.Advertisement;
import com.hudongwx.drawlottery.mobile.mappers.AdvertisementMapper;
import com.hudongwx.drawlottery.mobile.service.advertisement.IAdvertisementService;
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
    public List<Map<String, Object>> selectAdvertisement() {
        List<Advertisement> adList = adMapper.selectAdByState(1);
        return dealImgInfo(adList);
    }

    private List<Map<String, Object>> dealImgInfo(List<Advertisement> adList) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        for (Advertisement ad : adList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", ad.getId());
            map.put("imgUrl", Settings.SERVER_URL_PATH + ad.getImgUrl());
            map.put("desc", ad.getDescription());
            map.put("skipUrl", Settings.SERVER_URL_PATH + ad.getUrl());
            infoList.add(map);
        }
        return infoList;
    }
}
