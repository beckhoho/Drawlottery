package com.hudongwx.drawlottery.mobile.entitys;

import javax.persistence.*;
import javax.websocket.OnClose;
import java.util.Date;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/5 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/5 11:01　<br/>
 * <p>
 *          商品用户对应历史表实体类
 * <p>
 * @email 346905702@qq.com
 */

@Table(name = "t_commodity_user_history")
public class CommodityUserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_account_id")
    private Long accountId;

    /**
     * 商品历史ID
     */
    @Column(name = "commodity_id")
    private Long commodityId;

    /**
     * 购买商品时间
     */
    @Column(name = "partake_date")
    private Long partakeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCommodityHistoryId() {
        return commodityId;
    }

    public void setCommodityHistoryId(Long commodityHistoryId) {
        this.commodityId = commodityHistoryId;
    }

    public Long getPartakeDate() {
        return partakeDate;
    }

    public void setPartakeDate(Long partakeDate) {
        this.partakeDate = partakeDate;
    }
}
