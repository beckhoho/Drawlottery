package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/14 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/14 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@Table(name = "t_luck_code_template")
public class LuckCodeTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 幸运码
     */
    @Column(name = "luck_code")
    private String luckCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLuckCode() {
        return luckCode;
    }

    public void setLuckCode(String luckCode) {
        this.luckCode = luckCode;
    }
}
