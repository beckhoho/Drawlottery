package com.hudongwx.drawlottery.pojo;

import javax.persistence.*;

@Table(name = "t_file_path")
public class ConnectFilePath {
    /**
     * 文件名
     */
    @Id
    @Column(name = "file_name")
    private String fileName;

    /**
     * 路径
     */
    private String path;

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 获取路径
     *
     * @return path - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}