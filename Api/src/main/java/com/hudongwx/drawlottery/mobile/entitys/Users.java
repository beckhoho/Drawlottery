package com.hudongwx.drawlottery.mobile.entitys;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
@ApiModel(value = "用户", description = "用户实体")
@Table(name = "t_users")
public class Users {
    @Column(name = "id")
    @ApiModelProperty(value = "用户id")
    Integer id;

    @Column(name = "account_id")
    @ApiModelProperty(value = "用户账号", required = true)
    Integer accountId;

    @Column(name = "password")
    @ApiModelProperty(value = "用户密码", required = true)
    String password;

    @Column(name = "real_name")
    @ApiModelProperty(value = "用户真实姓名")
    String name;

    @Column(name = "nickname")
    @ApiModelProperty(value = "用户昵称")
    String nickName;

    @Column(name = "phone_number")
    @ApiModelProperty(value = "用户电话")
    String phoneNumber;

    @Column(name = "salt")
    @ApiModelProperty(value = "用户盐")
    String salt; //加盐

    @Column(name = "user_integral")
    @ApiModelProperty(value = "用户积分")
    Integer userIntegral;

    @Column(name = "header_id")
    @ApiModelProperty(value = "用户头像id")
    Integer headerId;

    @Column(name = "gold_number")
    @ApiModelProperty(value = "账户余额")
    Integer goldNumber;

    @Column(name = "current_state")
    @ApiModelProperty(value = "账户状态")
    Integer state = 1;//账号状态,1有效,-1表示被锁定

    public boolean isLocked() {
        return state == -1;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //获取公盐+私盐
    public String getCredentialsSalt() {
        return this.salt + "294786949@qq.com";
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public Integer getGoldNumber() {
        return goldNumber;
    }

    public void setGoldNumber(Integer goldNumber) {
        this.goldNumber = goldNumber;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
