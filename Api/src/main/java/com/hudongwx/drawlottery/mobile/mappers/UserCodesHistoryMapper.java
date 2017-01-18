package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.User;
import com.hudongwx.drawlottery.mobile.entitys.UserCodesHistory;
import com.hudongwx.drawlottery.mobile.entitys.UserLuckCodes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCodesHistoryMapper extends BaseMapper<UserCodesHistory> {
    List<UserCodesHistory> selectByUserAccountId(@Param("accountId") Long accountId);

    int insertHistory(@Param("list") List<UserCodesHistory> list);

    List<String> selectLimitCodeNum(@Param("accountId") Long accountId, @Param("lastCode") String lastCode, @Param("pageLoadSize") Integer pageLoadSize);


}