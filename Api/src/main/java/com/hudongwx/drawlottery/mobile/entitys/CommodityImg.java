package com.hudongwx.drawlottery.mobile.entitys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_commoditys_imags")
public class CommodityImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 图片url地址
     */
    private String url;

    /**
     * 是否有效
     */
    private Integer state;

    /**
     * 添加时间
     */
    @Column(name = "time")
    private Date ime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取图片url地址
     *
     * @return url - 图片url地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置图片url地址
     *
     * @param url 图片url地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取是否有效
     *
     * @return state - 是否有效
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置是否有效
     *
     * @param state 是否有效
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取添加时间
     *
     * @return time - 添加时间
     */
    public Date getIme() {
        return ime;
    }

    /**
     * 设置添加时间
     *
     * @param ime 添加时间
     */
    public void setIme(Date ime) {
        this.ime = ime;
    }
}