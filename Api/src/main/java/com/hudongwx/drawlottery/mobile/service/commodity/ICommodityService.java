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

    /**
     * 搜索商品信息
     * 根据商品类别category、商品名称name 搜索
     * 搜索的四种情况：
     * 1、name和category都有值，两者并列搜索
     * 2、category无值，按name搜索
     * 3、name无值，按category搜索
     * 4、name和category都无值，搜索所有
     *
     * @param category        商品类别
     * @param commName        商品名称
     * @param lastCommId 当前最后一个显示的商品id
     * @return JSONObject
     */
    List<Map<String,Object>> selectPaging(Integer category, String commName, Long lastCommId);

    //查看所有的商品信息
    List<Commoditys> selectAll();


    //通过商品的价格，人气，最新进行查询
    List<Map<String, Object>> selectByStyle(Integer ref, Integer type, Long lastcommodityid);

    //查询正在开奖的商品
    List<Map<String, Object>> selectOnLottery();

    //商品详情
    Map<String, Object> selectCommodity(Long commodId);
}
