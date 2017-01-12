package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import java.util.Date;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/3 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/3 11:06　<br/>
 * <p>
 * 签到实体类
 * <p>
 * @email 346905702@qq.com
 */

@Table(name = "t_sign_in")
public class SignIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    @Column(name = "sign_in_day")
    private Integer signInDay;

    @Column(name = "new_sign_date")
    private Long newSignInDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Integer getSignInDay() {
        return signInDay;
    }

    public void setSignInDay(Integer signInDay) {
        this.signInDay = signInDay;
    }

    public Long getNewSignInDate() {
        return newSignInDate;
    }

    public void setNewSignInDate(Long newSignInDate) {
        this.newSignInDate = newSignInDate;
    }
}
