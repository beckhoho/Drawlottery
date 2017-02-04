package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareMapper extends BaseMapper<Share> {

    Share selectByCommId(@Param("commId") Long commId);

    Share selectPassedCommShare(@Param("commId") Long commId);

    List<Share> selectByIssueDate(@Param("issueDate") Long issueDate);

    /**
     * 查询用户中奖商品的发现记录
     *
     * @param accountId
     * @param lastCommId
     * @return
     */
    List<Share> selectByUserAccountId(@Param("accountId") Long accountId, @Param("lastCommId") Long lastCommId, @Param("pageLoadSize") Integer pageLoadSize);

    /**
     * 返回主键字段
     *
     * @return
     */
    int insertByGeneratedKeys(Share share);

    List<Share> selectAllWithPage(@Param("lastCommId") Long lastCommId, @Param("pageLoadSize") Integer pageLoadSize);

    /**
     * 得到晒单详情
     * @param shareId 分享id
     * @return 详情
     */
    Share selectDetailsById(@Param("shareId") Long shareId);
}