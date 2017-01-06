package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/6 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/6 9:15　<br/>
 * <p>
 *      中奖通知实体类
 * <p>
 * @email 346905702@qq.com
 */

@Table(name = "t_luck_notice")
public class LuckNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 用户ID
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 图片url
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 是否可用
     */
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

