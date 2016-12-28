package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/27 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/27 14:07　<br/>
 * <p>
 *      图片实体类
 * <p>
 * @email 346905702@qq.com
 */

@Table(name = "t_images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 图片url
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 描述
     */
    private String depict;

    /**
     *跳转界面（广告图跳转）
     */
    @Column(name = "skip_url")
    private String skipUrl;

    /**
     * 图片类别（1：小图标，2：广告图）
     */
    @Column(name = "img_genre")
    private Integer imgGenre;

    /**
     *当前状态（1：可用，0：不可用）
     */
    private Integer state;



    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public Integer getImgGenre() {
        return imgGenre;
    }

    public void setImgGenre(Integer imgGenre) {
        this.imgGenre = imgGenre;
    }
}
