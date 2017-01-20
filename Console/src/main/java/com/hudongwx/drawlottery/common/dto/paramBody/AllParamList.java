package com.hudongwx.drawlottery.common.dto.paramBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品列表筛选条件实体类.
 * Date: 2017/1/6 0006
 * Time: 15:23
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@ApiModel("商品管理筛选条件类")
public class AllParamList implements Serializable {
    public static Long serialVersionUID = 1L;

    @ApiModelProperty("关键字搜索，支持模糊搜索")
    private String key;
    @ApiModelProperty("商品类别")
    private List<Integer> genre;
    @ApiModelProperty("商品类型")
    private List<Integer> type;
    @ApiModelProperty("商品状态")
    private List<Integer> state;
    @ApiModelProperty("最早上架时间")
    private Date groundTimeFront;
    @ApiModelProperty("最晚上架时间")
    private Date groundTimeAfter;
    @ApiModelProperty("最早下架时间")
    private Date undercarriageTimeFront;
    @ApiModelProperty("最晚下架时间")
    private Date undercarriageTimeAfter;
    @ApiModelProperty("排序")
    private int order;
    @ApiModelProperty("升降序")
    private int direction;

    /**
     * 获取商品列表.
     * <p>
     * key                    搜索关键字
     * genre                 商品属性（数据库对应商品类别）
     * type                  商品类型
     * state               商品状态
     * groundTimeFront        上架时间（开始）
     * groundTimeAfter        上架时间（结束）
     * undercarriageTimeFront 下架时间（开始）
     * undercarriageTimeAfter 下架时间（结束）
     * history                  排序字段
     * direction              排序方向（0：降序，1：升序）
     */

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Integer> getGenre() {
        return genre;
    }

    public void setGenre(List<Integer> genre) {
        this.genre = genre;
    }

    public List<Integer> getType() {
        return type;
    }

    public void setType(List<Integer> type) {
        this.type = type;
    }

    public List<Integer> getState() {
        return state;
    }

    public void setState(List<Integer> state) {
        this.state = state;
    }

    public Date getGroundTimeFront() {
        return groundTimeFront;
    }

    public void setGroundTimeFront(Date groundTimeFront) {
        this.groundTimeFront = groundTimeFront;
    }

    public Date getGroundTimeAfter() {
        return groundTimeAfter;
    }

    public void setGroundTimeAfter(Date groundTimeAfter) {
        this.groundTimeAfter = groundTimeAfter;
    }

    public Date getUndercarriageTimeFront() {
        return undercarriageTimeFront;
    }

    public void setUndercarriageTimeFront(Date undercarriageTimeFront) {
        this.undercarriageTimeFront = undercarriageTimeFront;
    }

    public Date getUndercarriageTimeAfter() {
        return undercarriageTimeAfter;
    }

    public void setUndercarriageTimeAfter(Date undercarriageTimeAfter) {
        this.undercarriageTimeAfter = undercarriageTimeAfter;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
