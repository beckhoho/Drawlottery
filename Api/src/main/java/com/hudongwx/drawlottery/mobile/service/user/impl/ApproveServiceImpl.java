package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.Approve;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.mappers.ApproveMapper;
import com.hudongwx.drawlottery.mobile.service.user.IApproveService;
import com.sun.org.apache.regexp.internal.RE;
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
 * 创建　kiter　2016/12/21 17:06　<br/>
 * <p>
 *   认证信息service实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class ApproveServiceImpl implements IApproveService {

    @Autowired
    ApproveMapper mapper;

    /**
     * 添加认证信息
     * @param approve   认证信息对象
     * @return  返回添加结果
     */
    @Override
    public boolean addApproveMassage(Approve approve) {
        int insert = mapper.insert(approve);
        if(insert>0){
            return true;
        }
        return false;
    }

    /**
     * 查看用户认证信息
     * @param userAccount   用户accountID
     * @return  返回当前用户认证信息
     */
    @Override
    public List<Approve> select(Long userAccount) {
        Approve a = new Approve();
        a.setUserAccountId(userAccount);
        return mapper.select(a);

    }

    /**
     * 修改用户认证信息
     * @param approve   认证信息对象
     * @return  返回修改结果
     */
    @Override
    public boolean update(Approve approve) {
        int i = mapper.updateByPrimaryKeySelective(approve);
        if(i>0){
            return true;
        }
        return false;
    }
}
