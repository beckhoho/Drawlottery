package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCodesHistoryMapper extends BaseMapper<UserCodesHistory> {
    List<UserCodesHistory> selectByUserAccountId(@Param("accountId") Long accountId);

    int insertHistory(@Param("list") List<UserCodesHistory> list);

    int insertCopy(@Param("commodityId") Long commodity);

    List<String> selectLimitCodeNum(@Param("accountId") Long accountId, @Param("lastCode") String lastCode, @Param("pageLoadSize") Integer pageLoadSize);

    List<UserCodesHistory> selectByOrders(@Param("accountId") Long accountId,
                                          @Param("commodityId") Long commodityId, @Param("ordersId") Long ordersId);


    UserCodesHistory selectById(@Param("id") Long id);

    Integer countUserOrderCommAmount(@Param("accountId") Long accountId, @Param("commId") Long commId);

    List<String> selectUserCommLuckCode(@Param("accountId") Long accountId, @Param("commId") Long commId, @Param("lastCode") String lastCode, @Param("pageLoadSize") Integer pageLoadSize);

    /**
     * 查询商品的用户购买量
     * @param accountId
     * @param commId
     * @return
     */
    Integer countHistoryUserCommAmount(@Param("accountId")Long accountId, @Param("commId")Long commId);
}