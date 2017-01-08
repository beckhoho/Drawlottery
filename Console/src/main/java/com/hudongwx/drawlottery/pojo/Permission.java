package com.hudongwx.drawlottery.pojo;

import javax.persistence.*;

@Table(name = "t_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 对应角色id
     */
    @Column(name = "role_id")
    private Long roleId;

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
     * 获取权限名
     *
     * @return name - 权限名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限名
     *
     * @param name 权限名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取对应角色id
     *
     * @return role_id - 对应角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置对应角色id
     *
     * @param roleId 对应角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}