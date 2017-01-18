package com.hudongwx.drawlottery.common.dto.paramBody;

import java.util.Date;
import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/18 0018
 * Time: 10:07
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public class OrderParam {
    int currentPage;
    int pageSize;
    List<Integer> payModeId;
    Date submitDateFront;
    Date submitDateAfter;
    int order;
    int direction;
}
