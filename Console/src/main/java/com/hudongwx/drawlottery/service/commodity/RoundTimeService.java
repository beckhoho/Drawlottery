package com.hudongwx.drawlottery.service.commodity;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 17:17
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface RoundTimeService {

    public Long generateNewRoundTime();

    /**
     * 得到幸运码总数量
     *
     * @return 总量
     */
    public long getMaxRoundTime();

}
