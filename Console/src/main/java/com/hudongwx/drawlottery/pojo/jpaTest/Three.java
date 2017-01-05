package com.hudongwx.drawlottery.pojo.jpaTest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Drawlottery.
 * Date: 2017/1/6 0006
 * Time: 0:05
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Entity
@Table(name = "t_test_three")
public class Three implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "one_three", joinColumns = {@JoinColumn(name = "three_id", referencedColumnName = "one_id")})
    private Collection<One> ones;
}
