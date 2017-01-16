package com.hudongwx.drawlottery.common.dto.paramBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/13 0013
 * Time: 10:57
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@ApiModel("模板管理筛选条件类")
public class TempParam {
    @ApiModelProperty("排序")
    private int order;
    @ApiModelProperty("升降序")
    private int direction;
    @ApiModelProperty("商品类别")
    private List<Integer> genre;
    @ApiModelProperty("商品类型")
    private List<Integer> type;

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

}
