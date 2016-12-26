package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityType;
import com.hudongwx.drawlottery.mobile.entitys.Commoditys;
import com.hudongwx.drawlottery.mobile.mappers.CommodityTypeMapper;
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


    /**
     *  添加商品
     * @param commod    商品对象
     * @return  返回添加结果
     */
    @Override
    public boolean addCommodity(Commoditys commod) {
        int insert = commodMapper.insert(commod);
        if(insert>0){
            return true;
        }
        return false;

    }

    /**
     * 通过ID删除当前商品
     * @param id    商品ID
     * @return  返回删除结果
     */
    @Override
    public boolean delete(Long id) {
        int i = commodMapper.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }
        return false;
    }

    /**
     * 通过ID获取当前ID的商品信息
     * @param id    商品ID
     * @return  返回当前商品信息
     */
    @Override
    public Commoditys selectByid(Long id) {
        return commodMapper.selectByPrimaryKey(id);

    }

    /**
     * 查询某个类型的全部商品
     * @param commodType   商品类型名
     * @return  当前类型的所有的商品信息
     */
    @Override
    public List<Commoditys> selectTypeAll(String commodType) {
        Long i = commodMapper.selectType(commodType);
        Commoditys com = new Commoditys();
        com.setCommodityTypeId(i);
        return commodMapper.select(com);
    }

    /**
     * 更新修改商品信息
     * @param commod    商品对象
     * @return  返回修改结果
     */
    @Override
    public boolean update(Commoditys commod) {
        int i = commodMapper.updateByPrimaryKeySelective(commod);
        if(i>0){
            return true;
        }
        return false;
    }

    /**
     *  查询当前类型商品的总数
     * @param commodTypeId  商品类型ID
     * @return  返回当前类型的商品总数量
     */
    @Override
    public int selectCount(Integer commodTypeId) {
        return commodMapper.selectTypeCount(commodTypeId);
    }

    /**
     * 分页查询方法
     * @param commodTypeId  商品类型ID
     * @param startNum  limit开始下标
     * @param endNum    limit结束下标
     * @return  返回指定位置的商品信息集
     */
    @Override
    public List<Commoditys> selectPaging(Integer commodTypeId, Integer startNum, Integer endNum) {
        return commodMapper.selectPaging(commodTypeId,startNum,endNum);
    }

    /**
     * 查询所有商品的信息
     *
     * @return 所有的商品信息
     */
    @Override
    public List<Commoditys> selectAll() {
        return commodMapper.selectAll();
    }

    /**
     * 通过关键字搜索商品
     * @param name  模糊搜索商品名
     * @return
     */
    @Override
    public List<Commoditys> selectByName(String name) {
        return commodMapper.selectByName("%"+name+"%");
    }

}
