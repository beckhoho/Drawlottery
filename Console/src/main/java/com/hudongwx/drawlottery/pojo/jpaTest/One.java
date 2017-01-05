package com.hudongwx.drawlottery.pojo.jpaTest;

import com.hudongwx.drawlottery.pojo.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/5 0005
 * Time: 23:36
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Entity
@Table(name = "t_test_one")//指定表名
public class One implements Serializable {
    /**
     * @see GeneratedValue :主键生成策略
     * @see GenerationType :主键生成类型
     * TABLE：借助一个表来实现主键自增, 通过一个表来实现主键id的自增，这种方式不依赖于具体的数据库，可以解决数据迁移的问题
     * IDENTITY：identity主键自增,这种方式依赖于具体的数据库，如果数据库不支持自增主键，那么这个类型是没法用的
     * SEQUENCE：sequence主键自增,通过Sequence来实现表主键自增，这种方式依赖于数据库是否有SEQUENCE，如果没有就不能用
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "uid", unique = true, nullable = false)
    private Integer uid;

    /**
     * 设置属性wages对应的字段为user_wages，12位数字可保留两位小数，可以为空
     */
    @Column(name = "user_wages", nullable = true, precision = 12, scale = 2)
    private Double wages;
    /**
     * 设置为时间类型
     */
    @Temporal(TemporalType.DATE)
    private Date joinDate;
    /**
     * 字段排序
     * 在加载数据的时候可以为其指定顺序
     */
    @OrderBy("create_time ASC, name DESC")
    private List books = new ArrayList();
    /**
     * 一对多关系映射
     * 一对多双向关联跟多对一是一样的,在多端生成一个外键,不生成第三张表来管理对应关系,由外键来管理对应关系
     *
     * @see OneToMany   mappedBy指向多的那方的pojo的关联外键字段
     * cascade外键策略
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<User> userList;

    private Date create_time;

    private String autoDir;

    @ManyToMany(mappedBy = "ones")
    private Collection<Three> threes;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "four_id")
    private Four four;
}
