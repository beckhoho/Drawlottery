package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.CommodityImg;
import com.hudongwx.drawlottery.mobile.mappers.CommodityImgMapper;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityImgService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
 *          商品图片service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class CommodityImgServiceImlpl implements ICommodityImgService {

    @Autowired
    CommodityImgMapper commodityImg;
    @Override
    public boolean addImage(CommodityImg commodImg) {
        int insert = commodityImg.insert(commodImg);
        if(insert>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteImage(Integer id) {
        int i = commodityImg.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public CommodityImg selectImg(Integer id) {
        return commodityImg.selectByPrimaryKey(id);
    }
}
