package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.Commoditys;

import java.util.List;
import java.util.Map;


/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:41　<br/>
 * <p>
 * 商品service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface ICommodityService {

    //添加商品
    boolean addCommodity(Commoditys commod);

    //删除商品
    boolean delete(Long id);

    //查看商品详情
    Commoditys selectByid(Long id);

    //查看全部商品
    List<Commoditys> selectTypeAll(String commodType);

    //修改商品信息
    boolean update(Commoditys commod);

    //获取当前类型商品的总数（分页使用）
    int selectCount(Integer commodTypeId);

    //获取当前指定条数的数据
    List<Commoditys> selectPaging(Integer commodTypeId,Integer startNum,Integer endNum);

    //查看所有的商品信息
    List<Commoditys> selectAll();

    //通过商品名称模糊搜索商品
    List<Commoditys> selectByName(String name);


    //通过商品的价格，人气，最新进行查询
    List<Commoditys> selectByStyle(Integer ref,Integer type, Long lastcommodityid);

    //查询正在开奖的商品
    List<Commoditys> selectOnLottery();

    //商品详情
    Map<String,Object> selectCommodity(Long commodId);
}
