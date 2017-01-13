package com.hudongwx.drawlottery.service.commodity.impl;

import com.hudongwx.drawlottery.common.constants.ConfigConstants;
import com.hudongwx.drawlottery.common.constants.LangConstants;
import com.hudongwx.drawlottery.common.exceptions.ServiceException;
import com.hudongwx.drawlottery.service.commodity.IFileService;
import com.hudongwx.drawlottery.service.conf.QiniuService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Drawlottery.
 * Date: 2017/1/11 0011
 * Time: 14:34
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service
public class IFileServiceImpl implements IFileService {
    @Resource
    private ConfigConstants configConstants;
    @Resource
    private LangConstants langConstants;
    @Resource
    private QiniuService qiniuService;

    /**
     * 文件上传功能
     *
     * @param file 上传文件
     * @return 远程url
     */
    @Override
    public String fileUpload(MultipartFile file) {
        if (qiniuService.isReady())
            return configConstants.getQiniuhost() + qiniuService.uploadToQiniu(file);
        try {
            byte[] bytes = file.getBytes();
            final String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            /*final int hash = fileName.hashCode();
            final String hashChar = Integer.toHexString(hash);//转成十六进制（长度为8） 每一位生成一个文件夹（每一级最多16个目录）
            char[] hss = hashChar.toCharArray();//转为char型数组，用于分目录
            String path = configConstants.getUploadPath();
            String contain = "";
            for (char hs : hss) {
                contain += hs+"/";
            }

            if (!new File(path+contain).mkdirs()) {
                throw new ServiceException("文件目录生成失败！");
            }
            final BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(path + contain, fileName)));*/
            String path = configConstants.getUploadPath();
            if (!new File(path).exists() && !new File(path).mkdirs()) {
                throw new ServiceException("文件目录生成失败！");
            }
            final BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(path, fileName)));
            stream.write(bytes);
            stream.close();
//            rememberFilePath(fileName, contain);
            return fileName;
        } catch (IOException e) {
            throw new ServiceException("文件上传失败");
        }
    }

    public String qiniuFileUpload(MultipartFile file) {
        return null;
    }
}
