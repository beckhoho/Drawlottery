package com.hudongwx.drawlottery.mobile.service.images;

import com.hudongwx.drawlottery.mobile.entitys.Images;

import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/27 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/27 14:23　<br/>
 * <p>
 * 图片service接口
 * <p>
 * @email 346905702@qq.com
 */

public interface ImagesService {

    //添加图片
    boolean addImage(Images img);

    //删除图片
    boolean deleteImage(Long id);

    //查看图标
    List<Map<String, Object>> selectIcon();

    //查看广告图
    List<Map<String, Object>> selectAdvert();

    //修改图片信息
    boolean update(Images img);

    /**
     * 活动
     *
     * @return
     */
    List<Map<String, Object>> selectEvent();

    List<String> selectWelcomeImg();

    List<String> selectnavigeteImg();
}
