package com.hudongwx.drawlottery.mobile.service.images.impl;

import com.hudongwx.drawlottery.mobile.entitys.Images;
import com.hudongwx.drawlottery.mobile.mappers.ImagesMapper;
import com.hudongwx.drawlottery.mobile.service.images.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/27 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/27 14:24　<br/>
 * <p>
 *          图片service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    ImagesMapper mapper;

    /**
     * 添加图片对象
     * @param img   图片对象
     * @return 返回添加结果
     */
    @Override
    public boolean addImage(Images img) {
        int insert = mapper.insert(img);
        if(insert>0){
            return true;
        }
        return false;
    }

    /**
     * 删除图片对象
     * @param id    图片ID
     * @return  返回删除结果
     */
    @Override
    public boolean deleteImage(Long id) {
        int i = mapper.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }
        return false;
    }

    /**
     * 查询图标
     * @return  返回所有图标信息
     */
    @Override
    public List<Images> selectIcon() {
        Images i = new Images();
        i.setImgGenre(1);
        return mapper.select(i);
    }

    /**
     * 查询广告图
     * @return  返回所有的广告图信息
     */
    @Override
    public List<Images> selectAdvert() {
        Images i = new Images();
        i.setImgGenre(2);
        return mapper.select(i);
    }

    /**
     * 通过id修改图片信息
     * @param img   图片对象（必须指定ID）
     * @return  返回修改结果
     */
    @Override
    public boolean update(Images img) {
        int i = mapper.updateByPrimaryKeySelective(img);
        if(i>0){
            return true;
        }
        return false;
    }

}
