package com.hudongwx.drawlottery.mobile.service.images.impl;

import com.hudongwx.drawlottery.mobile.entitys.Images;
import com.hudongwx.drawlottery.mobile.mappers.ImagesMapper;
import com.hudongwx.drawlottery.mobile.service.images.ImagesService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
 * 创建　kiter　2016/12/27 14:24　<br/>
 * <p>
 * 图片service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    ImagesMapper imagesMapper;

    /**
     * 添加图片对象
     *
     * @param img 图片对象
     * @return 返回添加结果
     */
    @Override
    public boolean addImage(Images img) {
        int insert = imagesMapper.insert(img);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除图片对象
     *
     * @param id 图片ID
     * @return 返回删除结果
     */
    @Override
    public boolean deleteImage(Long id) {
        int i = imagesMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询图标
     *
     * @return 返回所有图标信息
     */
    @Override
    public List<Map<String, Object>> selectIcon() {
        List<Images> imagesList = imagesMapper.selectUsingImgs(1, 1);
        return dealImgInfo(imagesList);
    }

    private List<Map<String, Object>> dealImgInfo(List<Images> imagesList) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        for (Images images : imagesList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", images.getId());
            map.put("imgUrl", Settings.SERVER_URL_PATH + images.getImgUrl());
            map.put("desc", images.getDepict());
            map.put("skipUrl", images.getSkipUrl());
            infoList.add(map);
        }
        return infoList;
    }

    /**
     * 查询广告图
     *
     * @return 返回所有的广告图信息
     */
    @Override
    public List<Map<String, Object>> selectAdvert() {
        Images i = new Images();
        i.setImgGenre(2);
        i.setState(1);
        List<Images> imagesList = imagesMapper.select(i);
        return dealImgInfo(imagesList);
    }

    /**
     * 通过id修改图片信息
     *
     * @param img 图片对象（必须指定ID）
     * @return 返回修改结果
     */
    @Override
    public boolean update(Images img) {
        int i = imagesMapper.updateByPrimaryKeySelective(img);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询广告图
     *
     * @return 返回所有的广告图信息
     */
    @Override
    public List<Map<String, Object>> selectEvent() {
        Images i = new Images();
        i.setImgGenre(3);
        i.setState(1);
        List<Images> imagesList = imagesMapper.select(i);
        return dealImgInfo(imagesList);
    }

    @Override
    public List<String> selectWelcomeImg() {
        return getImgUrlList(imagesMapper.selectUsingImgs(1, 0));
    }

    @Override
    public List<String> selectnavigeteImg() {
        return getImgUrlList(imagesMapper.selectUsingImgs(1, 4));
    }

    private List<String> getImgUrlList(List<Images> imageList) {
        if (imageList.isEmpty())
            return null;
        List<String> imgUrlList = new ArrayList<>();
        for (Images img : imageList) {
            imgUrlList.add(Settings.SERVER_URL_PATH+img.getImgUrl());
        }
        return imgUrlList;
    }
}
