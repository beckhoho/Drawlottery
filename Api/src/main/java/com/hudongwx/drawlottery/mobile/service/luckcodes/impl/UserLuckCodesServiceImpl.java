package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.UserLuckCodesMapper;
import com.hudongwx.drawlottery.mobile.service.luckcodes.IUserLuckCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.chrono.IsoEra;
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
 * 创建　kiter　2016/12/22 11:05　<br/>
 * <p>
 *  用户幸运码service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class UserLuckCodesServiceImpl implements IUserLuckCodesService{

    @Autowired
    UserLuckCodesMapper userLuckCodes;

    /**
     * 添加用户幸运码
     * @param userLCodes    用户幸运码对象
     * @return  添加结果
     */
    @Override
    public boolean addNewLockCodes(UserLuckCodes userLCodes) {
        int insert = userLuckCodes.insert(userLCodes);
        if(insert>0){
            return true;
        }
        return false;
    }

    /**
     * 查询当前用户的所有幸运码
     * @param accountId 用户accountID
     * @return  返回当前用户的所有幸运码
     */
    @Override
    public List<UserLuckCodes> selectByUserId(Long accountId) {
        UserLuckCodes user = new UserLuckCodes();
        user.setUserAccountId(accountId);
        return userLuckCodes.select(user);
    }

    /**
     * 通过id删除幸运码
     * @param id   幸运码ID
     * @return  删除结果
     */
    @Override
    public boolean delete(Long id) {
        int i = userLuckCodes.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }
        return false;
    }
}
