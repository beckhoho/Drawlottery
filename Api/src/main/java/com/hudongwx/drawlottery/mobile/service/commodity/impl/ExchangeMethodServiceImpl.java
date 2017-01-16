package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.IExchangeMethodService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2017/1/11 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2017/1/11 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class ExchangeMethodServiceImpl implements IExchangeMethodService {

    @Autowired
    ExchangeWayMapper ewMapper;
    @Resource
    CommodityHistoryMapper chMapper;
    @Autowired
    VirtualCommodityMapper vcMapper;
    @Autowired
    ExchangeCashMessageMapper cashMapper;
    @Autowired
    CommodityTemplateMapper templateMapper;
    @Autowired
    CommoditysMapper comMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ExpressDeliveryMapper expressMapper;
    @Autowired
    ExpressDeliveryMapper exDeMapper;
    @Autowired
    ShareMapper shareMapper;




    /**
     * 兑换现金
     *
     * @param exchangeTerraceName 第三方提现账号
     * @param terraceAccount      平台区分（微信，银联，支付宝）
     * @param commodityId         商品ID
     * @param accountId           用户ID
     * @return
     */
    @Override
    public boolean exchangeToCash(String exchangeTerraceName, String terraceAccount, Long commodityId, Long accountId) {

        Commoditys com = comMapper.selectByKey(commodityId);
        CommodityTemplate template = templateMapper.selectByPrimaryKey(com.getTempId());
        Integer money = template.getWithdrawalsMoney();//得到该商品提现金额；
        User user = userMapper.selectByPrimaryKey(accountId);
        String realName = user.getRealName();//得到该幸运用户真实姓名

        ExchangeCashMessage exCash = new ExchangeCashMessage();
        exCash.setUserAccountId(accountId);//添加用户ID
        exCash.setExchangeAmount(money);//添加该商品提现金额
        exCash.setExchangeTerraceName(exchangeTerraceName);//添加第三方提现平台名
        exCash.setState(0);//添加处理状态（1：已处理，0：未处理）
        exCash.setTerraceAccount(terraceAccount);//添加第三方提现账号
        exCash.setUserRealName(realName);//添加用户真实姓名

        return cashMapper.insert(exCash) > 0;
    }



    /**
     * 领奖过程
     *
     * @param accountId   用户ID
     * @param commodityId 商品ID
     * @return 返回接口
     */
    @Override
    public List<Map<String, Object>> selectUserRechargeCardPrize(Long accountId, Long commodityId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<CommodityHistory> list = chMapper.selectComIdAndUser(accountId, commodityId);
        for (CommodityHistory comHis : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("commodityName", comHis.getCommodityName());//添加商品名
            map.put("roundTime", comHis.getRoundTime());//添加期数
            map.put("commodityId", comHis.getId());//添加商品ID
            map.put("endTime", comHis.getEndTime());//添加揭晓时间；
            map.put("coverImgUrl", Settings.SERVER_URL_PATH + comHis.getCoverImgUrl());//添加商品封面图
            map.put("exchangeState", comHis.getExchangeState());
            //添加兑换状态（1：已选择兑换方式，2：卡密兑换方式派发成功，3：商品派发，4：晒单）
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 是否派发充值卡
     *
     * @param accountId
     * @param commodityId
     * @return
     */
    @Override
    public boolean orTrue(Long accountId, Long commodityId) {
        List<Map<String, Object>> maps = selectUserRechargeCardPrize(accountId, commodityId);
        if (maps != null && maps.size() != 0) {
            return true;
        }
        return false;
    }

    /**
     * 查看兑奖流程进度
     *
     * @param commodityId 商品ID
     * @return
     */
    @Override
    public Map<String, Object> selectUserRechargeCardExchangeProcess(Long accountId, Long commodityId) {

        Map<String, Object> map = new HashMap<>();

        CommodityHistory history = chMapper.selectBycommId(commodityId);
        ExchangeWay way = ewMapper.selectById(history.getExchangeWay());

        CommodityTemplate template = templateMapper.selectById(history.getTempId());

        Share s = new Share();
        s.setUserAccountId(accountId);
        s.setCommodityId(commodityId);
        List<Share> select = shareMapper.select(s);

        map.put("commodityName", history.getCommodityName());//商品名
        map.put("coverImgUrl", Settings.SERVER_URL_PATH + history.getCoverImgUrl());//商品封面图
        map.put("exchangeState", history.getExchangeState());//兑奖流程进度状态
        map.put("userBuyNumber", history.getBuyNumber());//添加用户购买人次
        map.put("genre", history.getGenre());//添加商品实体虚拟
        map.put("commodityId", commodityId);//添加商品ID
        map.put("prizeState", "正在兑奖中");//奖品状态
        map.put("size", null);//几张充值卡
        map.put("cardNumberList", null);//充值卡卡号集合
        map.put("worth", null);//充值卡面额
        map.put("expressNumber", null);//快递单号
        map.put("expressName", null);//获取快递名
        map.put("expressState", null);//添加快递状态
        map.put("ContactName", null);//添加领奖联系人姓名
        map.put("ContactPhone", null);//添加领奖联系人电话
        map.put("ContactAddress", null);//添加领奖地址
        map.put("state", 2);//添加兑换流程状态

        if (select.size() > 0) {//晒单状态
            map.put("shareState", 1);
        } else {
            map.put("shareState", 0);
        }
        if (way != null) {
            map.put("exchangeName", way.getName());//兑换方式名
        } else {
            map.put("exchangeName", "未选择兑换方式");//如果未选择兑换方式
        }

        int f = history.getExchangeState();//商品兑换状态
        if (f == 1) {
            int g = history.getExchangeWay();//商品兑换方式
            if (g == 1) {//兑换充值卡
                map.putAll(demo2(commodityId));
            } else if (g == 2) {//快递领取
                ExpressDelivery delivery = exDeMapper.selectByAccountAndCommodity(accountId, commodityId);
                if (delivery.getDeliveryName() == null) {
                    map.put("expressNumber", "未派发快递");//快递单号
                    map.put("expressName", "空！");//获取快递名
                    map.put("expressState", "未派发快递");//添加快递状态
                    map.put("state", 3);//添加兑换流程状态
                } else {
                    map.put("expressNumber", delivery.getDeliveryNumber());//快递单号
                    map.put("expressName", delivery.getDeliveryName());//获取快递名
                    map.put("expressState", delivery.getState());//添加快递状态
                    map.put("state", 3);//添加兑换流程状态
                }
            } else if (g == 5) {//到店领取
                map.put("ContactName", template.getContactName());//添加领奖联系人姓名
                map.put("ContactPhone", template.getContactPhone());//添加领奖联系人电话
                map.put("ContactAddress", template.getContactAddress());//添加领奖地址
                map.put("state", 3);//添加兑换流程状态
            }
        }
        return map;
    }


    /**
     * 查询对应商品的充值卡
     * @param commodityId
     * @return
     */
    public Map<String, Object> demo2(Long commodityId) {
        Map<String, Object> map = new HashMap<>();
        List<VirtualCommodity> vc = vcMapper.selectByCommId(commodityId);
        map.put("size", vc.size());//添加有几张充值卡
        List<String> number = new ArrayList<>();
        for (VirtualCommodity vir : vc) {
            number.add(vir.getCardNumber());
        }
        map.put("cardNumberList", number);//添加卡号
        map.put("worth", vc.get(0).getWorth());//添加面额
        return map;
    }

    /**
     * 选择兑换充值卡方式
     * @param accountId
     * @param commodityId
     * @return
     */
    @Override
    public Map<String,Object> temp1(Long accountId,Long commodityId){
        CommodityHistory com = new CommodityHistory();
        com.setCommodityId(commodityId);
        com.setExchangeState(1);
        com.setExchangeWay(1);
        chMapper.updateByPrimaryKeySelective(com);//更新历史商品兑换状态

        //调用查询方法，去查询响应数据
        Map<String, Object> map = selectUserRechargeCardExchangeProcess(accountId, commodityId);

        return map;
    }

    /**
     * 选择快递领取兑换
     * @param accountId
     * @param commodityId
     * @param addressId
     * @return
     */
    @Override
    public Map<String,Object> temp2(Long accountId,Long commodityId,Long addressId){

        ExpressDelivery ex = new ExpressDelivery();
        ex.setCommodityId(commodityId);
        ex.setUserAccountId(accountId);
        ex.setAddressId(addressId);
        ex.setState(0);
        expressMapper.insert(ex);//添加快递对象

        CommodityHistory com = new CommodityHistory();
        com.setCommodityId(commodityId);
        com.setExchangeState(1);
        com.setExchangeWay(2);
        chMapper.updateByPrimaryKeySelective(com);//更新历史商品兑换状态

        //调用查询方法，去查询响应数据
        Map<String, Object> map = selectUserRechargeCardExchangeProcess(accountId, commodityId);
        return map;
    }

    /**
     * 兑换现金
     * @param accountId
     * @param commodityId
     * @return
     */
    @Override
    public Map<String, Object> temp3(Long accountId, Long commodityId) {

        return null;
    }

    /**
     * 兑换闪币
     * @param accountId
     * @param commodityId
     * @return
     */
    @Override
    public boolean temp4(Long accountId, Long commodityId) {
        User key = userMapper.selectByPrimaryKey(accountId);
        Integer number = key.getGoldNumber();//获得用户当前闪币数量
        Commoditys com = comMapper.selectByKey(commodityId);
        CommodityTemplate template = templateMapper.selectByPrimaryKey(com.getTempId());
        Integer money = template.getExchangeMoney();//获得折换闪币金额
        User user = new User();
        user.setAccountId(accountId);
        user.setGoldNumber(number + money);//修改用户闪币数额
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    /**
     * 到店领取
     * @param accountId
     * @param commodityId
     * @return
     */
    @Override
    public Map<String, Object> temp5(Long accountId, Long commodityId) {

        CommodityHistory com = new CommodityHistory();
        com.setCommodityId(commodityId);
        com.setExchangeState(1);
        com.setExchangeWay(2);
        chMapper.updateByPrimaryKeySelective(com);//更新历史商品兑换状态

        //调用查询方法，去查询响应数据
        Map<String, Object> map = selectUserRechargeCardExchangeProcess(accountId, commodityId);

        return map;
    }
}
