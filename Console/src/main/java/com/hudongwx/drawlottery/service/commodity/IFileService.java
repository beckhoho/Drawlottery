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

    /**
     * 持久化文件和路径到数据库
     *
     * @param fileName 文件名
     * @param path     路径（相对）
     */
    public void rememberFilePath(String fileName, String path);

    /**
     * 通过文件名查找目录
     *
     * @param fileName 文件名
     * @return 目录
     */
    public String getPath(String fileName);
}
