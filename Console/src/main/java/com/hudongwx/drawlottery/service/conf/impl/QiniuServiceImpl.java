package com.hudongwx.drawlottery.service.conf.impl;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.common.constants.ConfigConstants;
import com.hudongwx.drawlottery.service.conf.QiniuService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 22:22
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service
public class QiniuServiceImpl implements QiniuService {
    @Resource
    private ConfigConstants configConstants;

    /**
     * 得到七牛的upToken
     *
     * @param suffix 文件后缀名
     * @return upToken
     */
    @Override
    public String getUpToken(String suffix) {
        //获取AccessKey
        final String ak = configConstants.getQiniuAccessKey();
        //获取SecretKey
        final String qk = configConstants.getQiniuSecretKey();
        //上传空间
        String bucketName = configConstants.getBucketName();
        if (!suffix.startsWith("."))
            suffix = "." + suffix;
        //上传到七牛后保存的文件名
        String key = UUID.randomUUID().toString() + suffix;

        //密钥配置
        Auth auth = Auth.create(ak, qk);

        auth.uploadToken(bucketName);
        //生成token
        return auth.uploadToken(bucketName, key, configConstants.getExpries(), null);

    }

    /**
     * 上传文件到七牛云
     *
     * @param file 文件
     * @return 保存成功的文件名
     */
    @Override
    public String uploadToQiniu(MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        final String s = fileName.substring(fileName.indexOf("."), fileName.length());
        String key = UUID.randomUUID().toString() + s;
        final String upToken = getUpToken();
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(c);
        try {
            //调用put方法上传
            Response res = uploadManager.put(file.getBytes(), key, upToken);
            //打印返回的信息
            final JSONObject obj = JSONObject.parseObject(res.bodyString());
            return obj.get("key").toString();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到七牛的upToken
     *
     * @return upToken
     */
    @Override
    public String getUpToken() {
        //获取AccessKey
        final String ak = configConstants.getQiniuAccessKey();
        //获取SecretKey
        final String qk = configConstants.getQiniuSecretKey();
        //上传空间
        String bucketName = configConstants.getBucketName();

        //密钥配置
        Auth auth = Auth.create(ak, qk);

        auth.uploadToken(bucketName);
        //生成token
        return auth.uploadToken(bucketName);

    }

    /**
     * 判断七牛是否就绪
     *
     * @return
     */
    @Override
    public boolean isReady() {
        return !(null == configConstants.getQiniuAccessKey() || null == configConstants.getQiniuSecretKey());
    }
}
