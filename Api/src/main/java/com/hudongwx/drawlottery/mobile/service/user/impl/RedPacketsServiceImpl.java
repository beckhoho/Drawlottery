package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.RedPackets;
import com.hudongwx.drawlottery.mobile.mappers.RedPacketsMapper;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    RedPacketsMapper mapper;

    /**
     * 添加红包
     * @param rp    红包对象
     * @return  添加结果
     */
    @Override
    public boolean addRP(RedPackets rp) {
        if (mapper.insert(rp) == 1)
            return true;
        return false;
    }

    /**
     *  删除红包
     * @param redp  红包对象
     * @return  返回删除结果
     */
    @Override
    public boolean delete(RedPackets redp) {
        int delete = mapper.delete(redp);
        if(delete>0){
            return true;
        }
        return false;
    }

    /**
     * 查询当前用户的所有红包信息
     * @param accountId 用户accountID
     * @return  当前用户的所有红包信息
     */
    @Override
    public List<RedPackets> select(Long accountId) {
        RedPackets rp = new RedPackets();
        rp.setUserAccountId(accountId);
        return mapper.select(rp);
    }

}
