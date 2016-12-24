package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.entitys.User;
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
    UserCodesHistoryMapper userCodesHistory;
    @Override
    public boolean addToHistory(UserCodesHistory userhis) {
        int insert = userCodesHistory.insert(userhis);
        if(insert>0){
            return  true;
        }
        return false;
    }

    @Override
    public List<UserCodesHistory> selectByUserAccount(Long accounId) {

        return userCodesHistory.selectByUserAccount(accounId);
    }

    @Override
    public List<UserCodesHistory> selectByCommodId(Long commodId) {

        return userCodesHistory.selectByCommodId(commodId);
    }

    @Override
    public List<UserCodesHistory> selectAll() {
        return userCodesHistory.selectAll();
    }
}
