package com.hudongwx.drawlottery.pojo;

import javax.persistence.*;

@Table(name = "t_luck_code_template")
public class LuckCodeTemplate {
    /**
     * 注意主键不自增，序列排序
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 幸运码
     */
    @Column(name = "luck_code")
    private String luckCode;

    /**
     * 获取注意主键不自增，序列排序
     *
     * @return id - 注意主键不自增，序列排序
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置注意主键不自增，序列排序
     *
     * @param id 注意主键不自增，序列排序
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取幸运码
     *
     * @return luck_code - 幸运码
     */
    public String getLuckCode() {
        return luckCode;
    }

    /**
     * 设置幸运码
     *
     * @param luckCode 幸运码
     */
    public void setLuckCode(String luckCode) {
        this.luckCode = luckCode == null ? null : luckCode.trim();
    }
}