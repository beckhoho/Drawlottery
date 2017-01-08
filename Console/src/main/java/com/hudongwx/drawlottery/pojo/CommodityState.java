package com.hudongwx.drawlottery.pojo;

import javax.persistence.*;

@Table(name = "t_commodity_states")
public class CommodityState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 状态名
     */
    private String name;

    /**
     * 预留
     */
    private String message;

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
     * 获取状态名
     *
     * @return name - 状态名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置状态名
     *
     * @param name 状态名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取预留
     *
     * @return message - 预留
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置预留
     *
     * @param message 预留
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}