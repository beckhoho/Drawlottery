package com.hudongwx.drawlottery.mobile.service.user;

import com.hudongwx.drawlottery.mobile.entitys.ShareImg;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/30 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/30 18:02　<br/>
 * <p>
 *
 * <p>
 * @email 346905702@qq.com
 */
public interface IShareImgService {

    //添加分享图片
    boolean addShareImg(ShareImg shareImg);

    //删除分享图片
    boolean deleteImg(ShareImg shareImg);


}
