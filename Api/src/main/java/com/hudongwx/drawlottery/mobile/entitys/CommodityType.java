package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

@Table(name = "t_commodity_type")
public class CommodityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 当前状态是否可用（1为可用，0为不可用）
     */
    private Integer state;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类型名称
     *
     * @return name - 类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型名称
     *
     * @param name 类型名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取当前状态是否可用（1为可用，0为不可用）
     *
     * @return state - 当前状态是否可用（1为可用，0为不可用）
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置当前状态是否可用（1为可用，0为不可用）
     *
     * @param state 当前状态是否可用（1为可用，0为不可用）
     */
    public void setState(Integer state) {
        this.state = state;
    }
}