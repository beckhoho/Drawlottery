package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/4 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/4 11:09　<br/>
 * <p>
 * 热门搜索实体类
 * <p>
 * @email 346905702@qq.com
 */

@Table(name = "t_hot_search")
public class HotSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关键字名
     */
    private String name;

    /**
     * 搜索次数
     */
    private Long frequency;

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

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }
}
