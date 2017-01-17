package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_luck_codes")
public class LuckCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 幸运码状态
     */
    private Integer state;

    /**
     * 商品ID
     */
    @Column(name = "luck_code_template_id")
    private Long luckCodeTemplateId;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

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
     * 获取商品ID
     *
     * @return commodity_id - 商品ID
     */
    public Long getCommodityId() {
        return commodityId;
    }

    /**
     * 设置商品ID
     *
     * @param commodityId 商品ID
     */
    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getLuckCodeTemplateId() {
        return luckCodeTemplateId;
    }

    public void setLuckCodeTemplateId(Long luckCodeTemplateId) {
        this.luckCodeTemplateId = luckCodeTemplateId;
    }
}