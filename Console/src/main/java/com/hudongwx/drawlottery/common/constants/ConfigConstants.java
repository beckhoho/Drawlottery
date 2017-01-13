package com.hudongwx.drawlottery.common.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置常量类.
 * Date: 2017/1/5 0005
 * Time: 11:08
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
@ConfigurationProperties(locations = {"classpath:project/config.properties"})
public class ConfigConstants {
    private Logger logger = LoggerFactory.getLogger(ConfigConstants.class);
    /**
     * 项目路径
     */
    private String contextPath;
    /**
     * 静态资源
     */
    private String staticServePath;
    /**
     * 七牛ak
     */
    private String qiniuAccessKey;
    /**
     * 七牛sk
     */
    private String qiniuSecretKey;
    /**
     * 七牛上传空间
     */
    private String bucketName;
    /**
     * token有效时间
     */
    private Long expries;
    /**
     * 七牛远程显示地址 注意要在最后加/
     */
    private String qiniuhost;

    /**
     * 文件上传路径
     */
    @Value("uploadPath")
    private String uploadPath;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getStaticServePath() {
        return staticServePath;
    }

    public void setStaticServePath(String staticServePath) {
        this.staticServePath = staticServePath;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getQiniuAccessKey() {
        return qiniuAccessKey;
    }

    public void setQiniuAccessKey(String qiniuAccessKey) {
        this.qiniuAccessKey = qiniuAccessKey;
    }

    public String getQiniuSecretKey() {
        return qiniuSecretKey;
    }

    public void setQiniuSecretKey(String qiniuSecretKey) {
        this.qiniuSecretKey = qiniuSecretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Long getExpries() {
        return expries;
    }

    public void setExpries(Long expries) {
        this.expries = expries;
    }

    public String getQiniuhost() {
        return qiniuhost;
    }

    public void setQiniuhost(String qiniuhost) {
        this.qiniuhost = qiniuhost;
    }
}
