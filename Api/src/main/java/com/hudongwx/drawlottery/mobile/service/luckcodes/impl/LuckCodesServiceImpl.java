package com.hudongwx.drawlottery.mobile.service.luckcodes.impl;

import com.hudongwx.drawlottery.mobile.entitys.LuckCodeTemplate;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodeTemplateMapper;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodesMapper;
import com.hudongwx.drawlottery.mobile.service.luckcodes.ILuckCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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
    @Resource
    private LuckCodeTemplateMapper luckCodeTemplateMapper;

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
        return null;
    }

    /**
     * 得到模板总数
     *
     * @return 总数
     */
    @Override
    public Long getTempCount() {
        return luckCodeTemplateMapper.getCount();
    }

    /**
     * 添加模板集合到模板表中
     *
     * @param set 模板集合（注意是set，为了打乱顺序）
     */
    @Override
    public void addToTemp(Set<LuckCodeTemplate> set) {
        luckCodeTemplateMapper.insertSet(set);
    }

    /**
     * 查询范围内的幸运码
     *
     * @param count 总数
     * @return 幸运码集合(只对应了模板id)
     */
    @Override
    public List<LuckCodes> selectRange(long count) {
        return luckCodeTemplateMapper.selectRange(count);
    }

    /**
     * 插入幸运码集合
     *
     * @param list 集合
     */
    @Override
    public void insertCodeList(List<LuckCodes> list) {
        mapper.insertCodeList(list);
    }

    /**
     * 查询用于参与总数
     *
     * @param accountId   用户id
     * @param commodityId 商品id
     * @return 参与总数
     */
    @Override
    public int getCount(Long accountId, Long commodityId) {
        return mapper.countUserCommAmount(accountId,commodityId);
    }

}
