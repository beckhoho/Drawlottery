package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLuckCodesMapper extends BaseMapper<UserLuckCodes> {


    //查看商品幸运码
    List<UserLuckCodes> selectCountByCommodity(@Param("commod") Long commod);

    //查看用户幸运码
    List<Long> selectDistinctGroupByCommId(@Param("accountId") Long accountId);


}