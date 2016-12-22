package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/20 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/20 0016　<br/>
 * <p>
 * 红包service接口实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class RedPacketsServiceImpl implements IRedPacketsService {
    @Autowired
    RedPacketsMapper rpMapper;

    @Override
    public boolean addRP(RedPackets rp) {
        if (rpMapper.insert(rp) == 1)
            return true;
        return false;
    }
}
