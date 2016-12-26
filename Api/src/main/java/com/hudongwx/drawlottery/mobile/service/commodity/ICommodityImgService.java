package com.hudongwx.drawlottery.mobile.service.commodity;

import com.hudongwx.drawlottery.mobile.entitys.CommodityImg;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/22 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/22 11:59　<br/>
 * <p>
 *          商品图片service接口
 * <p>
 * @email 346905702@qq.com
 */
public interface ICommodityImgService {

    //添加商品图片
    boolean addImage(CommodityImg commodImg);

    //删除商品图片
    boolean deleteImage(Long id);

    //查看商品图片
    CommodityImg selectImg(Long id);

    //查看所有商品图片
    List<CommodityImg> selectAll();
}
