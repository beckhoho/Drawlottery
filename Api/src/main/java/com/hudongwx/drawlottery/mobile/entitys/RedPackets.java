package com.hudongwx.drawlottery.mobile.entitys;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Table(name = "t_red_packets")
public class RedPackets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_account_id")
    private Long userAccountId;

    /**
     * 红包状态
     */
    @Column(name = "use_state")
    private Integer useState;

    /**
     * 生效日期
     */
    @Column(name = "valid_date")
    private Long validDate;

    /**
     * 失效日期
     */
    @Column(name = "overdue_date")
    private Long overdueDate;

    /**
     * 红包名字
     */
    private String name;

    /**
     * 使用条件,订单高于该价格就可使用
     */
    @Column(name = "use_price")
    private Integer usePrice;

    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }

    /**
     * 红包大小
     */
    private Integer worth;

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
     * 获取生效日期
     *
     * @return valid_date - 生效日期
     */
    public Long getValidDate() {
        return validDate;
    }

    /**
     * 设置生效日期
     *
     * @param validDate 生效日期
     */
    public void setValidDate(Long validDate) {
        this.validDate = validDate;
    }

    /**
     * 获取失效日期
     *
     * @return overdue_date - 失效日期
     */
    public Long getOverdueDate() {
        return overdueDate;
    }

    /**
     * 设置失效日期
     *
     * @param overdueDate 失效日期
     */
    public void setOverdueDate(Long overdueDate) {
        this.overdueDate = overdueDate;
    }

    /**
     * 获取红包名字
     *
     * @return name - 红包名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置红包名字
     *
     * @param name 红包名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取使用条件,订单高于该价格就可使用
     *
     * @return use_price - 使用条件,订单高于该价格就可使用
     */
    public Integer getUsePrice() {
        return usePrice;
    }

    /**
     * 设置使用条件,订单高于该价格就可使用
     *
     * @param usePrice 使用条件,订单高于该价格就可使用
     */
    public void setUsePrice(Integer usePrice) {
        this.usePrice = usePrice;
    }

    /**
     * 获取红包大小
     *
     * @return worth - 红包大小
     */
    public Integer getWorth() {
        return worth;
    }

    /**
     * 设置红包大小
     *
     * @param worth 红包大小
     */
    public void setWorth(Integer worth) {
        this.worth = worth;
    }

    /**
     * 红包是否过期
     * @return
     */
    @JSONField(serialize = false)
    public boolean isExpired(){
        if(validDate != null && overdueDate != null){
            long time = System.currentTimeMillis();
            if(time>overdueDate || validDate<time){//是否在日期使用范围内
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

    /**
     * 红包是否可以使用
     * @return
     */
    @JSONField(serialize = false)
    public boolean isCanUse(Integer miniPrice){
        boolean expired = isExpired();
        if(usePrice>=miniPrice && !expired){//使用最低价格和使用日期
            return true;
        }else{
            return false;
        }
    }

}