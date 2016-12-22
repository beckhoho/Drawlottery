package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/21 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/21 16:48　<br/>
 * <p>
 *      晒单Service实现类
 * <p>
 * @email 294786949@qq.com
 */
public class ShareServiceImpl implements IShareService{

    @Override
    public boolean addShare(Share share) {
        return false;
    }

    @Override
    public boolean deleteShare(Integer account) {
        return false;
    }

    @Override
    public Share selectShare(Integer account) {
        return null;
    }

    @Override
    public boolean friendsShare(Integer account) {
        return false;
    }
}
