package com.hudongwx.drawlottery.mobile.entitys;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/21 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/21 15:57　<br/>
 * <p>
 *   晒单实体类
 * <p>
 * @email 294786949@qq.com
 */
public class Share {
    Integer id;
    Integer userId;
    Long isscueDate;
    Integer commodityId;
    String particulars;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getIsscueDate() {
        return isscueDate;
    }

    public void setIsscueDate(Long isscueDate) {
        this.isscueDate = isscueDate;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }
}
