package com.hudongwx.drawlottery.pojo.jpaTest;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Drawlottery.
 * Date: 2017/1/5 0005
 * Time: 23:57
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Entity
@Table(name = "t_test_two")
public class Two implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "DESCRIPTION")
    private String description;
    /**
     * 一对多双向关联跟多对一是一样的,在多端生成一个外键,不生成第三张表来管理对应关系,由外键来管理对应关系
     *
     * @see JoinColumn name为字段名，referencedColumnName为指向表的字段名
     */
    @JoinColumn(name = "one_id", referencedColumnName = "id")//设置对应数据表的列名和引用的数据表的列名
    @ManyToOne//设置在“一方”pojo的外键字段上
    private One oneId;
}
