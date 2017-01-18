package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareMapper extends BaseMapper<Share> {

    List<Share> selectByCommId(@Param("commId") Long commId);

    List<Share> selectByIssueDate(@Param("issueDate") Long issueDate);

    List<Share> selectByUserAccountId(@Param("accountId") Long accountId);

    /**
     * 返回主键字段
     * @return
     */
    int insertByGeneratedKeys(Share share);
}