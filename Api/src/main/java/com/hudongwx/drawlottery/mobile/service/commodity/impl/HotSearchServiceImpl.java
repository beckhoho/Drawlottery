package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.HotSearch;
import com.hudongwx.drawlottery.mobile.mappers.HotSearchMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.IHotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
 * 热门搜索service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class HotSearchServiceImpl implements IHotSearchService {

    @Autowired
    HotSearchMapper mapper;

    @Override
    public boolean addHotSearch(String keywords) {
        if (null == keywords)
            return false;
        HotSearch hs = new HotSearch();
        hs.setName(keywords);
        HotSearch hotSearch = mapper.selectOne(hs);
        if (null != hotSearch) {
            return mapper.updateRecommend(hotSearch.getId(),hotSearch.getFrequency()+1) > 0;
        } else {
            hs.setFrequency(1L);
            return mapper.insert(hs) > 0;
        }
    }

    @Override
    public List<String> queryRecommend() {
        return mapper.selectRecommend();
    }
}
