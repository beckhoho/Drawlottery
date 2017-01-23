package com.hudongwx.drawlottery.mobile.service.commodity;

/**
 * Drawlottery.
 * Date: 2017/1/23 0023
 * Time: 15:26
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface IGenerateService {
    /**
     * 生成单个商品（注意，处于待售状态）
     *
     * @param tempId        模板id
     * @param luckCodeCount 幸运码总量
     */
    public void generateCommodity(final long tempId, final long luckCodeCount);

    /**
     * 生成幸运码（会尝试生成，如果total已经小于或等于目前已有的了，就不再生成）
     *
     * @param total 幸运码总量
     * @return 返回最大值
     */
    public String generateLuckCodes(final long total);

    /**
     * 将幸运码关联到商品
     *
     * @param commodityId 商品id
     * @param count       总数
     */
    public void connectLuckCodes(long commodityId, long count);

    /**
     * 保证同一模板具有 roundNum 的期数
     *
     * @param roundNum 期数总数
     * @apiNote 默认扫描所有的模板
     */
    public void keepRound(final long roundNum);

    /**
     * 生成新的期数.
     *
     * @return 唯一新期数
     */
    public Long generateNewRoundTime();

}
