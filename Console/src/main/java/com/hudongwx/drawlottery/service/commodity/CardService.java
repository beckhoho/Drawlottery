package com.hudongwx.drawlottery.service.commodity;

import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.pojo.Card;

import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/16 0016
 * Time: 1:32
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
public interface CardService {
    /**
     * 添加卡密
     *
     * @param card 充值卡对象
     */
    public void insertCard(Card card);

    /**
     * 修改卡密
     *
     * @param card 充值卡
     */
    public void updateCard(Card card);

    /**
     * 删除卡密
     *
     * @param cardIds id集合
     */
    public void deleteCard(List<Integer> cardIds);

    /**
     * * 获取卡密列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页数量
     * @param corporation 运营商
     * @param order       排序
     * @param direction   方向
     * @return 分页
     */
    public PageInfo<Card> getCards(final int currentPage, final int pageSize, final List<Integer> corporation, final int order, final int direction);

    /**
     * 获取统计信息
     *
     * @return 统计信息集合
     */
    List<Integer> getCounts();
}
