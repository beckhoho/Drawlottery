package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.SignIn;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/3 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/3 11:10　<br/>
 * <p>
 * 签到mapper
 * <p>
 * @email 346905702@qq.com
 */
public interface SignInMapper extends BaseMapper<SignIn> {

    @Select("select * from t_sign_in where user_account_id = #{accountId} order by new_sign_date desc")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "user_account_id", property = "userAccountId"),
            @Result(column = "sign_in_day", property = "signInDay"),
            @Result(column = "new_sign_date", property = "newSignInDate"),
    })
    List<SignIn> selectSignByAccountId(@Param("accountId") Long accountId);
}
