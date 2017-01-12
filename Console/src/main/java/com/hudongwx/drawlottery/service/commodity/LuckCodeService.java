package com.hudongwx.drawlottery.service.commodity;

/**
 * Drawlottery.
 * Date: 2017/1/12 0012
 * Time: 18:59
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface LuckCodeService {
    /**
     * 生成幸运码（会尝试生成，如果total已经小于或等于目前已有的了，就不再生成）
     *
     * @param total 幸运码总量
     * @return 返回最大值
     */
    public String generate(final long total);

    /**
     * 得到幸运码模板中的幸运码总量
     *
     * @return 总量
     */
    public Long getCountCode();

    /**
     * 将幸运码关联到商品
     *
     * @param commodityId 商品id
     * @param count     总数
     */
    public void connect(long commodityId, long count);
}
