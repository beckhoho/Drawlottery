package com.hudongwx.drawlottery.service.commodity;

import org.springframework.web.multipart.MultipartFile;

/**
 * Drawlottery.
 * Date: 2017/1/11 0011
 * Time: 14:32
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface IFileService {
    /**
     * 文件上传功能
     *
     * @param file 上传文件
     * @return 文件名
     */
    public String fileUpload(MultipartFile file);
}
