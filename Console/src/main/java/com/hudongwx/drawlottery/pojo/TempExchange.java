package com.hudongwx.drawlottery.pojo;

public class TempExchange {
    private Long id;

    private Long commodityId;

    private Integer exchangeWayId;

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

    public Integer getExchangeWayId() {
        return exchangeWayId;
    }

    public void setExchangeWayId(Integer exchangeWayId) {
        this.exchangeWayId = exchangeWayId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}