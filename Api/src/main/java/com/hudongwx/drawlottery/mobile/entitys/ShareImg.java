package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/30 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/30 17:54　<br/>
 * <p>
 *          晒单照片url
 * <p>
 * @email 346905702@qq.com
 */

@Table(name = "t_share_img")
public class ShareImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 晒单ID
     */
    @Column(name = "share_id")
    private Long shareId;

    /**
     * 晒单照片URL
     */
    @Column(name = "share_img_url")
    private String shareImgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }
}
