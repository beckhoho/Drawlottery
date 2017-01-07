package com.hudongwx.drawlottery.mobile.service.notification.impl;

import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.entitys.LuckNotice;
import com.hudongwx.drawlottery.mobile.mappers.LuckNoticeMapper;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
import com.hudongwx.drawlottery.mobile.service.notification.ILuckNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/6 9:38　<br/>
 * <p>
 *      用户中奖通知service实现类
 * <p>
 * @email 346905702@qq.com
 */

@Service
public class LuckNoticeServiceImpl implements ILuckNoticeService{

    @Autowired
    LuckNoticeMapper mapper;

    @Override
    public boolean addUserLuckNotice(LuckNotice notice) {
        return mapper.insert(notice)>0;
    }

    @Override
    public boolean deleteUserLuckNotice(LuckNotice notice) {
        return mapper.delete(notice)>0;
    }

    @Override
    public List<Map<String, Object>> selectByAccount(Long accountId) {
        return null;
    }

    @Override
    public boolean updateUserLuckNotice(LuckNotice notice) {
        Example e = new Example(LuckNotice.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("id",notice.getId());
        e.or(criteria);
        return mapper.updateByExampleSelective(notice,e)>0;
    }
}