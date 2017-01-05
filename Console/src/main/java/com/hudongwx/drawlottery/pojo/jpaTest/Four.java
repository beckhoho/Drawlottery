package com.hudongwx.drawlottery.pojo.jpaTest;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Drawlottery.
 * Date: 2017/1/6 0006
 * Time: 0:46
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Entity
@Table(name = "t_test_four")
public class Four implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(name = "description", length = 30)
    private String description;

    @JoinColumn(name = "one_id", unique = true, referencedColumnName = "id", insertable = false)
//设置从方指向主方的关联外键，这个ONEA_ID其实是表T_ONEA的主键
    @OneToOne
    private One one;

}
