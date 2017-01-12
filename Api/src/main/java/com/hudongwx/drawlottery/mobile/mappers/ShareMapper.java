package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface ShareMapper extends BaseMapper<Share> {

    @Select("select * from t_share where commodity_id = #{accountId}")
    @Results({
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "issue_date", property = "issueDate"),
            @Result(column = "commodity_id", property = "commodityId")
    })
    List<Share> selectByCommId(@Param("commId") Long accountId);

    @Select("select * from t_share where issue_date = #{issueDate}")
    @Results({
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "issue_date", property = "issueDate"),
            @Result(column = "commodity_id", property = "commodityId")
    })
    List<Share> selectByIssueDate(@Param("issueDate") Date issueDate);
}