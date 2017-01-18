package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodesMapper;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
import com.hudongwx.drawlottery.mobile.utils.ServiceUtils;
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
 * 创建　kiter　2016/12/22 11:40　<br/>
 * <p>
 * 幸运码service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class LuckCodesServiceImpl implements ILuckCodesService {

    @Autowired
    CommoditysMapper commoditysMapper;
    @Autowired
    LuckCodesMapper mapper;

    /**
     * 添加幸运码(内部使用)
     *
     * @return 返回添加结果
     */
    @Override
    public boolean createLuckCode(Long commId, boolean rebuild) {
        ServiceUtils.createLuckCode(mapper, commoditysMapper, commId, rebuild);
        return true;
    }

    /**
     * 通过商品id查询幸运码集
     *
     * @param commodId 商品id
     * @return 返回商品的幸运码集
     */
    @Override
    public List<LuckCodes> select(Long commodId) {
        LuckCodes luck = new LuckCodes();
        luck.setCommodityId(commodId);
        return this.mapper.select(luck);
    }

    /**
     * 通过id删除幸运码
     *
     * @param id 幸运码id
     * @return 返回删除结果
     */
    @Override
    public boolean delete(Long id) {
        int i = mapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<LuckCodes> selectByUserId(Long accountId) {

       // return mapper.selectByUserAccountId(accountId);
        return  null;
    }

}
