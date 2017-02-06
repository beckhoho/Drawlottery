package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.*;

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
     * @param category 商品类别
     * @param commName 商品名称
     * @return JSONObject
     */
    List<Map<String, Object>> selectPaging(Integer category, String commName, Long lastCommId);

    //查看所有的商品信息
    List<Commoditys> selectAll();


    //通过商品的价格，人气，最新进行查询
    List<Map<String, Object>> selectByStyle(Integer type, Long lastCommId);

    //查询正在开奖的商品
    List<Map<String, Object>> selectOnLottery(Integer page);

    //查询正在开奖的商品
    List<Map<String, Object>> selectOneOnLottery(Long commId);

    //商品详情
    Map<String, Object> selectCommodity(User user, Long commodId);

    //查询高中奖率商品
    List<Map<String, Object>> selectHeight(Long lastCommId);

    /**
     * 生成新的期数
     *
     * @return 期数
     */
    public Long generateNewRoundTime();

    /**
     * 最新揭晓
     *
     * @param lastCommId
     * @return
     */
    List<Map<String, Object>> selectNewestAnnounceComm(Long lastCommId);

    List<Map> selectThePastAnnouncedCommList(Long commId);

    /**
     * 通过商品id 得到商品图文详情
     *
     * @param commodityId 商品id
     * @return 模板中的图文详情
     */
    String getContent(Long commodityId);

    /**
     * 查询当前最大期数
     *
     * @return 期数
     */
    long getMaxRoundTime();

    /**
     * 查询未满足期数数量需求的商品
     *
     * @param roundNum 期数数量
     * @return 模板
     */
    List<CommodityTemplate> getNotKeepRoundTemplate(long roundNum);

    /**
     * 得到下一期商品
     *
     * @param id 商品id
     * @return 商品
     */
    Commodity getNextCommodity(Long id);

    /**
     * 生成下一期商品
     *
     * @param id
     */
    Commodity groundNext(Long id);

    /**
     * 得到商品细节信息，整合模板信息
     *
     * @param id
     * @return
     */
    Commoditys getDetails(Long id);

    /**
     * 通过商品id获取同模板的正在销售的商品
     * @param id 商品id
     * @return 商品
     */
    Commoditys selectOnSellCommodities(Long id);

    /**
     * 通过订单id获取商品购买信息
     * @param orderId 订单id
     * @return  购买信息
     */
    List<CommodityAmount> selectAmounts(Long orderId);
}
