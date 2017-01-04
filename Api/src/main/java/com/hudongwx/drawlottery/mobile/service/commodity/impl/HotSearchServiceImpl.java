package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.HotSearch;
import com.hudongwx.drawlottery.mobile.mappers.HotSearchMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.IHotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/4 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/4 11:34　<br/>
 * <p>
 *      热门搜索service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class HotSearchServiceImpl implements IHotSearchService {

    @Autowired
    HotSearchMapper mapper;
    @Override
    public boolean addHotSearch(HotSearch search) {
        return mapper.insert(search)>0;
    }

    @Override
    public boolean deleteHotSearch(HotSearch search) {
        return mapper.delete(search)>0;
    }

    @Override
    public boolean updateHotSearch(HotSearch search) {
        return mapper.updateByPrimaryKeySelective(search)>0;
    }
}
