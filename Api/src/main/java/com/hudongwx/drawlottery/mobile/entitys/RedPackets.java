package com.hudongwx.drawlottery.mobile.entitys;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/20 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/20 0016　<br/>
 * <p>
 * 用户红包实体类
 * <p>
 * @email 294786949@qq.com
 */
@ApiModel(value = "红包", description = "红包实体类")
@Table(name = "t_red_packets")
public class RedPackets {
    @Id
    Integer id;
    @ApiModelProperty(value = "用户Id", required = true)
    @Column(name = "user_id")
    Integer userId;
    @ApiModelProperty(value = "红包生效日期", required = true)
    @Column(name = "valid_date")
    Long validDate;
    @ApiModelProperty(value = "红包失效日期", required = true)
    @Column(name = "overdue_date")
    Long overdueDate;
    @ApiModelProperty(value = "红包名称")
    @Column(name = "name")
    String name;
    @ApiModelProperty(value = "使用条件,订单高于该价格就可使用", required = true)
    @Column(name = "use_price")
    Integer usePrice;
    @ApiModelProperty(value = "红包价值（元）", required = true)
    @Column(name = "worth")
    Integer worth;

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

    public Long getValidDate() {
        return validDate;
    }

    public void setValidDate(Long validDate) {
        this.validDate = validDate;
    }

    public Long getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(Long overdueDate) {
        this.overdueDate = overdueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsePrice() {
        return usePrice;
    }

    public void setUsePrice(Integer usePrice) {
        this.usePrice = usePrice;
    }

    public Integer getWorth() {
        return worth;
    }

    public void setWorth(Integer worth) {
        this.worth = worth;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
