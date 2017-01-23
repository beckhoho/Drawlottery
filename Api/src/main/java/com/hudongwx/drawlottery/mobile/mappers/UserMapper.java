package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {


    /**
     * 通过手机号查询用户信息
     * @param phone
     * @return
     */
    User selectByPhoneNumber(@Param("phone") String phone);

    /**
     * 通過微信openId查找用户
     *
     * @param openId
     * @return
     */
    User selectByWxOpenId(@Param("openId") String openId);

    /**
     * 通過QQOpenId查找用户
     * @param openId
     * @return
     */
    User selectByQQOpenId(@Param("openId") String openId);


    User selectById(@Param("accountId")Long accountId);


    String selectUserNicknameById(@Param("accountId")Long accountId);

    /**
     * 更新推广人id
     * @param accountId
     * @param promId
     * @return
     */
    Integer updateUserPromteId(@Param("accountId")Long accountId,@Param("promId")Long promId,@Param("promoteDate")Long promoteDate);

    /**
     * 更新QQ号
     * @param accountId
     * @param qqNumber
     * @return
     */
    Integer updateUserQQ(@Param("accountId")Long accountId,@Param("qqNumber")String qqNumber);

    Long selectUserRegisterDate(@Param("accountId") Long accountId);

    Long countUsers();

}