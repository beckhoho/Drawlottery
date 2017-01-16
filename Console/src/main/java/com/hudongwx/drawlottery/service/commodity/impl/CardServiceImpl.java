package com.hudongwx.drawlottery.service.commodity.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hudongwx.drawlottery.common.exceptions.ServiceException;
import com.hudongwx.drawlottery.dao.CardMapper;
import com.hudongwx.drawlottery.pojo.Card;
import com.hudongwx.drawlottery.service.commodity.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Drawlottery.
 * Date: 2017/1/16 0016
 * Time: 1:50
 *
 * @author <a href="http://userwu.github.io">wuhongxu</a>.
 * @version 1.0.0
 */
@Service
public class CardServiceImpl implements CardService {
    private CardMapper cardMapper;

    /**
     * 添加卡密
     *
     * @param card 充值卡对象
     */
    @Override
    public void insertCard(Card card) {
        if (card.getCardNum() == null)
            throw new ServiceException("卡号不能为空");
        if (card.getCorporation() == null)
            throw new ServiceException("运营商不能为空");
        if (card.getPassword() == null)
            throw new ServiceException("卡密不能为空");
        cardMapper.insertSelective(card);
    }

    /**
     * 修改卡密
     *
     * @param card 充值卡
     */
    @Override
    public void updateCard(Card card) {
        cardMapper.updateByPrimaryKeySelective(card);
    }

    /**
     * 删除卡密
     *
     * @param cardIds id集合
     */
    @Override
    public void deleteCard(List<Integer> cardIds) {
        cardMapper.delete(cardIds);
    }

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
    @Override
    public PageInfo<Card> getCards(int currentPage, int pageSize, List<Integer> corporation, int order, int direction) {
        PageHelper.startPage(currentPage, pageSize);
        List<Card> cards = cardMapper.selectAll(corporation,order,direction);
        return new PageInfo<>(cards);
    }
}
