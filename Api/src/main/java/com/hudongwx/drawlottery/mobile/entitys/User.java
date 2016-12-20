package com.hudongwx.drawlottery.mobile.entitys;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Table;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
@ApiModel(value = "用户", description = "用户实体")
@Table(name = "users")
public class User {

    @ApiModelProperty(value = "用户id", required = true)
    String id;
    @ApiModelProperty(value = "用户名称", required = true)
    String name;
    @ApiModelProperty(value = "用户密码", required = true)
    String passwd;
    //加盐
    String salt;

    int state = 1;//账号状态,1有效,-1表示被锁定

    public boolean isLocked() {
        return state == -1;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * 数据库保存的盐,加密用的盐通过 {@link}getCredentialsSalt()方法获取
     *
     * @return
     */
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    //获取公盐+私盐
    public String getCredentialsSalt() {
        return this.salt + "294786949@qq.com";
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
