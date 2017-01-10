package com.hudongwx.drawlottery.common.base;

/**
 * Drawlottery.
 * Date: 2017/1/10 0010
 * Time: 15:38
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface PoParamBase<T> {
    /**
     * 作为实体类的po参数包，需要有打包自己的成为一个实体类的能力
     * @return 封装后的实体类对象
     */
    public T packingMe();
}
