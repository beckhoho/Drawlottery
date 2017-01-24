package com.hudongwx.drawlottery.mobile.mappers;

import com.hudongwx.drawlottery.mobile.commn.BaseMapper;
import com.hudongwx.drawlottery.mobile.entitys.ShareImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/30 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/30 17:58　<br/>
 * <p>
 *          晒单Mapper
 * <p>
 * @email 346905702@qq.com
 */
public interface ShareImgMapper extends BaseMapper<ShareImg>{
    List<ShareImg> selectByShare(@Param("shareId") Long shareId);
}
