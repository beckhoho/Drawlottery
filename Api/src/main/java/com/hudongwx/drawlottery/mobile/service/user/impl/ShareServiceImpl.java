package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.mappers.ShareMapper;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
 * 晒单Service实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class ShareServiceImpl implements IShareService {

    @Autowired
    ShareMapper mapper;
    @Override
    public boolean addShare(Share share) {
        return false;
    }

    @Override
    public boolean deleteShare(Long shareid) {
        return false;
    }

    @Override
    public List<Share> selectShare(Long accountid, Long lastshareid, Integer tag) {
        Share share = new Share();
        if (tag == Settings.FIRST_ENTER_STATUS) {
            share.setUserAccountId(accountid);
            return mapper.select(share);
        }else if(tag==Settings.DROP_DOWN_REFRESH){//下拉刷新

            return null;
        }

        return null;
    }

    @Override
    public boolean friendsShare(Long account) {
        return false;
    }

}
