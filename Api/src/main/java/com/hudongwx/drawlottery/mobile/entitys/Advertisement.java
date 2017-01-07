package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/5 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/5 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@Table(name = "t_advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 图片url
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 详情url
     */
    private String url;

    /**
     * 广告状态
     */
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
