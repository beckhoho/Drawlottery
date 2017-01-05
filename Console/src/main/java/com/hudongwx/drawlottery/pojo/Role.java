package com.hudongwx.drawlottery.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/5 0005
 * Time: 15:16
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    /**
     * 一个角色对应多个用户
     */
    @Transient
    private List<User> userList;
    /**
     * 一个角色对应多个权限
     */
    @Transient
    private List<Permission> permissionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Transient
    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<String>();
        List<Permission> perlist = getPermissionList();
        for (Permission per : perlist) {
            list.add(per.getName());
        }
        return list;
    }
}
