package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.ShareImg;
import com.hudongwx.drawlottery.mobile.mappers.ShareImgMapper;
import com.hudongwx.drawlottery.mobile.service.user.IShareImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/30 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/30 18:06　<br/>
 * <p>
 *          晒单service实现类
 * <p>
 * @email 346905702@qq.com
 */
@Service
public class ShareImgServiceImpl implements IShareImgService {


    @Autowired
    ShareImgMapper mapper;

    @Override
    public boolean addShareImg(ShareImg shareImg) {
        return mapper.insert(shareImg)>0;
    }

    @Override
    public boolean deleteImg(ShareImg shareImg) {
        return mapper.delete(shareImg)>0;
    }
}
