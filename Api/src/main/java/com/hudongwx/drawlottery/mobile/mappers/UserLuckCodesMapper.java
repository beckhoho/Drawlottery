package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLuckCodesMapper extends BaseMapper<UserLuckCodes> {


    //查看商品幸运码
    List<Long> selectCountByCommodity(@Param("commod") Long commod);

    //查看用户幸运码
    List<Long> selectDistinctGroupByCommId(@Param("accountId") Long accountId);


    UserLuckCodes selectByOne(@Param("commodityId")Long commodityId,@Param("luckCoudId")Long luckCoudId);

    List<UserLuckCodes> selectByUserAccountId(@Param("userAccountId")Long userAccountId);

    List<UserLuckCodes> selectByAccAndCommId(@Param("userAccountId")Long userAccountId,@Param("commId")Long commId);
}