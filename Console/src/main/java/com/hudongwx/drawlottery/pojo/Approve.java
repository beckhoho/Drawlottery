package com.hudongwx.drawlottery.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_approve")
public class Approve {
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证号
     */
    @Column(name = "real_number")
    private String realNumber;

    /**
     * 常住地址
     */
    private String site;

    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private Long phoneNumber;

    /**
     * 认证日期
     */
    @Column(name = "approve_date")
    private Date approveDate;

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
     * 获取用户id
     *
     * @return user_account_id - 用户id
     */
    public Long getUserAccountId() {
        return userAccountId;
    }

    /**
     * 设置用户id
     *
     * @param userAccountId 用户id
     */
    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取身份证号
     *
     * @return real_number - 身份证号
     */
    public String getRealNumber() {
        return realNumber;
    }

    /**
     * 设置身份证号
     *
     * @param realNumber 身份证号
     */
    public void setRealNumber(String realNumber) {
        this.realNumber = realNumber == null ? null : realNumber.trim();
    }

    /**
     * 获取常住地址
     *
     * @return site - 常住地址
     */
    public String getSite() {
        return site;
    }

    /**
     * 设置常住地址
     *
     * @param site 常住地址
     */
    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone_number - 手机号
     */
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号
     *
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取认证日期
     *
     * @return approve_date - 认证日期
     */
    public Date getApproveDate() {
        return approveDate;
    }

    /**
     * 设置认证日期
     *
     * @param approveDate 认证日期
     */
    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }
}