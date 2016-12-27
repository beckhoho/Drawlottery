package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityImg;
import com.hudongwx.drawlottery.mobile.mappers.CommodityImgMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * 创建　kiter　2016/12/22 15:58　<br/>
 * <p>
 * 商品图片service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class CommodityImgServiceImlpl implements ICommodityImgService {

    @Autowired
    CommodityImgMapper commodityImg;

    /**
     * 添加商品图片
     *
     * @param commodImg 商品图片对象
     * @return 返回添加结果
     */
    @Override
    public boolean addImage(CommodityImg commodImg) {
        int insert = commodityImg.insert(commodImg);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过ID删除商品图片
     *
     * @param id 商品图片ID
     * @return 返回删除结果
     */
    @Override
    public boolean deleteImage(Long id) {
        return commodityImg.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 通过商品图片ID查看
     *
     * @param id 商品图片ID
     * @return 返回一个商品图片对象
     */
    @Override
    public CommodityImg selectImg(Long id) {
        return commodityImg.selectByPrimaryKey(id);
    }

    /**
     * 查看所有的商品图片
     *
     * @return 返回所有的商品图片信息
     */
    @Override
    public List<CommodityImg> selectAll() {
        return commodityImg.selectAll();
    }
}
