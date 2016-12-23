package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
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
 * 创建　kiter　2016/12/21 17:38　<br/>
 * <p>
 *          商品service实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class CommodityServiceImpl implements ICommodityService {

    @Autowired
    CommoditysMapper commodMapper;

    @Override
    public boolean addCommodity(Commoditys commod) {
        int insert = commodMapper.insert(commod);
        if(insert>0){
            return true;
        }
        return false;

    }

    @Override
    public boolean delete(Long id) {
        int i = commodMapper.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public Commoditys selectByid(Long id) {
        //查看单个商品详情
        return commodMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<Commoditys> selectTypeAll(String commodType) {
        //查看某个类型全部商品
        CommodityTypeServiceImpl cts = new CommodityTypeServiceImpl();
        CommodityType commodityType = cts.selectType(commodType);
        int typeId = commodityType.getId();
        List<Commoditys> commods = commodMapper.selectTypeAll(typeId);
        return commods;
    }

    @Override
    public boolean update(Commoditys commod, Long id) {
        int i = commodMapper.updateByExample(commod, id);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public int selectCount(Integer commodTypeId) {
        //返回当前类型的商品总数量
        return commodMapper.selectTypeCount(commodTypeId);
    }

    @Override
    public List<Commoditys> selectPaging(Integer commodTypeId, Integer startNum, Integer endNum) {
        //查询指定数量的商品信息（分页）
        return commodMapper.selectPaging(commodTypeId,startNum,endNum);
    }



}
