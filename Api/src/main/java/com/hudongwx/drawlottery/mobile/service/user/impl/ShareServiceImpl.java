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

    /**
     * 添加用户晒单
     * @param share 晒单对象
     * @return  返回添加用户晒单
     */
    @Override
    public boolean addShare(Share share) {
        int insert = mapper.insert(share);
        if(insert>0){
            return true;
        }
        return false;
    }

    /**
     * 删除用户晒单
     * @param shareid 用户晒单id
     * @return  返回删除结果
     */
    @Override
    public boolean deleteShare(Long shareid) {
        return false;
    }

    /**
     * 查看当前用户的所有晒单
     * @param accountid
     * @param lastshareid
     * @param tag
     * @return
     */
    @Override
    public List<Share> selectShare(Long accountid, Long lastshareid, Integer tag) {
        Share share = new Share();
        if (tag == Settings.INITIALIZE_ENTER_STATUS) {
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
