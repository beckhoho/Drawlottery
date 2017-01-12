package com.hudongwx.drawlottery.mobile.entitys;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
 * 晒单上传表单
 * <p>
 * @email 346905702@qq.com
 */
public class ShareFormData {

    private Share share;

    private List<MultipartFile> imgFileList;

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public List<MultipartFile> getImgFileList() {
        return imgFileList;
    }

    public void setImgFileList(List<MultipartFile> imgFileList) {
        this.imgFileList = imgFileList;
    }


}
