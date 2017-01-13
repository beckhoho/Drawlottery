package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShareMapper extends BaseMapper<Share> {

    @Select("select * from t_share where commodity_id = #{commId}")
    @Results({
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "issue_date", property = "issueDate"),
            @Result(column = "commodity_id", property = "commodityId")
    })
    List<Share> selectByCommId(@Param("commId") Long commId);

    @Select("select * from t_share where issue_date = #{issueDate}")
    @Results({
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "issue_date", property = "issueDate"),
            @Result(column = "commodity_id", property = "commodityId")
    })
    List<Share> selectByIssueDate(@Param("issueDate") Long issueDate);

    @Select("select * from t_share where user_account_id = #{accountId}")
    @Results({
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "issue_date", property = "issueDate"),
            @Result(column = "commodity_id", property = "commodityId")
    })
    List<Share> selectByUserAccountId(@Param("accountId") Long accountId);
}