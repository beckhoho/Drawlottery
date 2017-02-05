package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LuckCodesMapper extends BaseMapper<LuckCodes> {


    List<LuckCodes> selectLimit(@Param("commodityId") Long commodityId, @Param("endNum") Integer endNum);

    LuckCodes selectById(@Param("lcId") Long lcId);

    List<LuckCodes> selectByCommodity(@Param("commodityId") Long commodityId);

    LuckCodes selectBytemplate(@Param("tempId") Long tempId, @Param("commodityId") Long commodityId);

    int updateCodesState(@Param("accountId") Long accountId,
                         @Param("commodityId") Long commodityId,
                         @Param("ordersid") Long ordersId,
                         @Param("buyDate") Long buyDate,
                         @Param("buyNum") Integer buyNum);

    //自动生成下一期，复用代码
    int updateNext(@Param("accountId") Long accountId,
                   @Param("commodityId") Long commodityId,
                   @Param("ordersid") Long ordersId,
                   @Param("buyDate") Long buyDate,
                   @Param("nextCommId") Long nextCommId
    );


    //插入下一期商品幸运码
    int insertLuckCode(@Param("endIndex") Integer endIndex, @Param("commId") Long commId);

    //查看商品幸运码
    List<Long> selectCountByCommodity(@Param("commId") Long commId);

    //查看用户幸运码
    List<Long> selectDistinctGroupByCommId(@Param("accountId") Long accountId);

    // List<User>

    LuckCodes selectByOne(@Param("commodityId") Long commodityId, @Param("luckCoudId") Long luckCoudId);

    List<LuckCodes> selectByUserAccountId(@Param("userAccountId") Long userAccountId, @Param("commodityId") Long commodityId);

    List<LuckCodes> selectByAccAndCommId(@Param("userAccountId") Long userAccountId, @Param("commId") Long commId);

    //查看最后购买商品的五十条信息
    List<LuckCodes> selectByBuyDateDesc();


    List<LuckCodes> selectByOrders(@Param("accountId") Long accountId, @Param("commodityId") Long commodityId, @Param("ordersId") Long ordersId);

    //分页显示用户已购买正在进行的商品幸运码
    List<String> selectUserCommLuckCode(@Param("accountId") Long accountId, @Param("commId") Long commId, @Param("lastCode") String lastCode, @Param("pageLoadSize") Integer pageLoadSize);

    void insertCodeList(List<LuckCodes> list);

    /**
     * 查询商品的用户购买量
     *
     * @param accountId
     * @param commId
     * @return
     */
    Integer countUserCommAmount(@Param("accountId") Long accountId, @Param("commId") Long commId);

    /**
     * 获取用户购买的商品Id(包含购买日期)，用于查询用户购买了哪些商品
     *
     * @param userAccountId
     * @param lastDate
     * @param pageLoadSize
     * @return
     */
    List<LuckCodes> selectAllUserPurchasedCommId(@Param("userAccountId") Long userAccountId, @Param("lastDate") Long lastDate, @Param("pageLoadSize") Integer pageLoadSize);
}