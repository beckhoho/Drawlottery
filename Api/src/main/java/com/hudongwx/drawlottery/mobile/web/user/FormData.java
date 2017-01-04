package com.hudongwx.drawlottery.mobile.web.user;

import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.springframework.web.multipart.MultipartFile;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/4 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/4 20:57　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class FormData {
    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    private Share share;
    private MultipartFile multipartFile;
}
