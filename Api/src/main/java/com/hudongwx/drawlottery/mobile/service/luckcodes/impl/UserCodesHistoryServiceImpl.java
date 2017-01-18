package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;
import com.hudongwx.drawlottery.mobile.mappers.UserCodesHistoryMapper;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserCodesHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:27　<br/>
 * <p>
 *      用户历史幸运码service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class UserCodesHistoryServiceImpl implements IUserCodesHistoryService {



    @Autowired
    UserCodesHistoryMapper uchMapper;

    /**
     * 添加用户历史幸运码
     * @param userhis   用户历史幸运码对象
     * @return  添加结果
     */
    @Override
    public boolean addToHistory(UserCodesHistory userhis) {
        int insert = uchMapper.insert(userhis);
        if(insert>0){
            return  true;
        }
        return false;
    }

    /**
     * 通过用户accountID查询当前用户的历史幸运码
     * @param accounId  用户accountID
     * @return  当前用户历史幸运码集
     */
    @Override
    public List<UserCodesHistory> selectByUserAccount(Long accounId) {
        UserCodesHistory user = new UserCodesHistory();
        user.setUserAccountId(accounId);
        return uchMapper.select(user);
    }

    /**
     * 通过商品ID查询当前商品的历史幸运码
     * @param commodId  商品ID
     * @return  当前商品历史幸运码集
     */
    @Override
    public List<UserCodesHistory> selectByCommodId(Long commodId) {
        UserCodesHistory user = new UserCodesHistory();
        user.setCommodityId(commodId);
        return uchMapper.select(user);
    }

    /**
     * 查询所有的历史幸运码信息
     * @return  所有的历史幸运码信息
     */
    @Override
    public List<UserCodesHistory> selectAll() {
        return uchMapper.selectAll();
    }
}
