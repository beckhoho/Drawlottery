package com.hudongwx.drawlottery.service.conf;

import org.springframework.web.multipart.MultipartFile;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 22:21
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface QiniuService {
    /**
     * 得到七牛的upToken
     *
     * @param suffix 文件后缀名
     * @return upToken
     */
    public String getUpToken(String suffix);

    /**
     * 上传文件到七牛云
     *
     * @param file 文件
     * @return 保存成功的文件名
     */
    public String uploadToQiniu(MultipartFile file);

    /**
     * 得到七牛的upToken
     *
     * @return upToken
     */
    public String getUpToken();

    /**
     * 判断七牛是否就绪
     *
     * @return
     */
    public boolean isReady();
}
