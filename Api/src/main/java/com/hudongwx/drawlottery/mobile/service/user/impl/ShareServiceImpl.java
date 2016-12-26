package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.mappers.ShareMapper;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
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
 *      晒单Service实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class ShareServiceImpl implements IShareService{

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
     * @param share 用户晒单对象
     * @return  返回删除结果
     */
    @Override
    public boolean deleteShare(Share share) {
        int delete = mapper.delete(share);
        if(delete>0){
            return true;
        }
        return false;
    }

    /**
     *  查看当前用户的所有晒单
     * @param account   用户accountID
     * @return  返回当前用户所有的晒单信息
     */
    @Override
    public List<Share> selectShare(Long account) {
        Share s = new Share();
        s.setUserAccountId(account);
        return mapper.select(s);
    }

}
