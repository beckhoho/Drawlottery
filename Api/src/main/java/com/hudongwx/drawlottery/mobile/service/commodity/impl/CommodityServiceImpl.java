package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.utils.ServiceUtils;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2016/12/21 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2016/12/21 17:38　<br/>
 * <p>
 * 商品service实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class CommodityServiceImpl implements ICommodityService {

    @Autowired
    CommoditysMapper commsMapper;
    @Autowired
    CommodityMapper commMapper;
    @Autowired
    CommodityImgMapper imgMapper;
    @Autowired
    CommodityHistoryMapper historyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    LuckCodesMapper luckCodeMapper;
    @Autowired
    CommodityTypeMapper typeMapper;
    @Autowired
    UserCodesHistoryMapper userHistoryMapper;
    @Autowired
    LotteryInfoMapper lotteryInfoMapper;
    @Resource
    private CommodityTemplateMapper commodityTemplateMapper;
    @Autowired
    NotificationPrizeMapper npMapper;
    @Autowired
    OrdersMapper ordersMapper;

    @Resource
    OrdersCommoditysMapper ordersCommoditysMapper;

    /**
     * 添加商品
     *
     * @param commod 商品对象
     * @return 返回添加结果
     */
    @Override
    public boolean addCommodity(Commoditys commod) {
        return commsMapper.insertCommodity(commod) > 0;
    }

    /**
     * 通过ID删除当前商品
     *
     * @param id 商品ID
     * @return 返回删除结果
     */
    @Override
    public boolean delete(Long id) {
        return commsMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 通过ID获取当前ID的商品信息
     *
     * @param id 商品ID
     * @return 返回当前商品信息
     */
    @Override
    public Commoditys selectByid(Long id) {
        return commsMapper.selectByKey(id);

    }

    /**
     * 查询某个类型的全部商品
     *
     * @param commodType 商品类型名
     * @return 当前类型的所有的商品信息
     */
    @Override
    public List<Commoditys> selectTypeAll(String commodType) {
        Long i = commsMapper.selectType(commodType);
        Commoditys com = new Commoditys();
        com.setCommodityTypeId(i);
        return commsMapper.select(com);
    }


    /**
     * 查询当前类型商品的总数
     *
     * @param commodityTypeId 商品类型ID
     * @return 返回当前类型的商品总数量
     */
    @Override
    public int selectCount(Integer commodityTypeId) {
        return commsMapper.selectTypeCount(commodityTypeId);
    }


    /**
     * 查询所有商品的信息
     *
     * @return 所有的商品信息
     */
    @Override
    public List<Commoditys> selectAll() {
        return commsMapper.selectAll();
    }

    /**
     * 根据传入类型，返回对应的商品信息集
     *
     * @param type
     * @return
     */
    @Override
    public List<Map<String, Object>> selectByStyle(Integer type, Long lastCommId) {
        List<Commoditys> cList;
        List<Map<String, Object>> infoList = new ArrayList<>();
        if (null == type)
            type = 0;
        if (null == lastCommId)
            lastCommId = 0L;
        if (type == Settings.COMMODITY_ORDER_POPULARITY) {
            cList = commsMapper.selectByTemp1(lastCommId, Settings.PAGE_LOAD_SIZE_10);//按人气搜索
        } else if (type == Settings.COMMODITY_ORDER_FASTEST) {
            cList = commsMapper.selectByTemp2(lastCommId, Settings.PAGE_LOAD_SIZE_10);
        } else if (type == Settings.COMMODITY_ORDER_NEWEST) {
            cList = commsMapper.selectByTemp3(lastCommId, Settings.PAGE_LOAD_SIZE_10);
        } else if (type == Settings.COMMODITY_ORDER_HIGHT_RATE) {
            cList = commsMapper.selectHeight(lastCommId, Settings.PAGE_LOAD_SIZE_10);
        } else if (type == 14) {
            cList = commsMapper.selectThreeCampaign(lastCommId, Settings.PAGE_LOAD_SIZE_10);
        } else {
            cList = commsMapper.selectByTemp4(lastCommId, Settings.PAGE_LOAD_SIZE_10);
        }
        for (Commoditys commoditys : cList) {
            infoList.add(dealCommSelectByStyle(commoditys));
        }
        return infoList;
    }

    private Map<String, Object> dealCommSelectByStyle(Commoditys comm) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", comm.getId());
        map.put("minNum", comm.getMinimum());
        map.put("state", comm.getStateId());
        map.put("name", comm.getName());
        map.put("imgUrl", comm.getCoverImgUrl());
        map.put("currentNumber", comm.getBuyTotalNumber() - comm.getBuyCurrentNumber());
        map.put("totalNumber", comm.getBuyTotalNumber());
        map.put("detailUrl", comm.getCommodityDescUrl());
        return map;
    }

    /**
     * 查询当前正在开奖的商品
     *
     * @return 返回正在开奖的商品
     */
    @Override
    public List<Map<String, Object>> selectOnLottery(Integer page) {
        return getOnLotteryMapList(null, page);
    }

    @Override
    public List<Map<String, Object>> selectOneOnLottery(Long commId) {
        return getOnLotteryMapList(commId, null);
    }

    /**
     * 查看商品详情
     *
     * @return 返回一个map集合
     */
    @Override
    public Map<String, Object> selectCommodity(User user, Long commodId) {
        Commoditys com = commsMapper.selectByKey(commodId);
        if (null == com)
            return null;
        commMapper.updateViewNum(commodId, com.getViewNum() + 1);//浏览量
        Map<String, Object> map = new HashMap<>();
        String nextRound = null;
        Long nextRoundId = null;
        if (com.getStateId() == 3 || com.getStateId() == 2) {//如果未开奖
            Commoditys comh = null;
            List<Long> longs = commMapper.selectBefore(com.getTempId(), com.getId());
            if (longs.size() != 0) {
                comh = commsMapper.selectByKey(longs.get(0));
            }
            if (comh == null) {
                map.put("beforeLottery", new HashMap<>());
            } else if (com.getStateId() == 3) {
                map.put("beforeLottery", mapBefore(comh));//往期开奖揭晓
            }
        }
        if (com.getStateId() == 2) {
            List<Commoditys> commsList = commsMapper.selectNextRoundComm(com.getTempId(), Settings.COMMODITY_STATE_ON_SALE);
            if (!commsList.isEmpty()) {
                Commoditys commoditys = commsList.get(0);
                nextRound = commoditys.getRoundTime() + "期正在火热进行中";
                nextRoundId = commoditys.getId();
            }
        }
        if (com.getStateId() == 1) {//如果已开奖或是在等待开奖
            Commoditys byKey = commsMapper.selectByKey(commodId);
            map.put("beforeLottery", mapBefore(byKey));
            List<Commoditys> commsList = commsMapper.selectNextRoundComm(com.getTempId(), Settings.COMMODITY_STATE_ON_SALE);
            if (!commsList.isEmpty()) {
                Commoditys commoditys = commsList.get(0);
                nextRound = commoditys.getRoundTime() + "期正在火热进行中";
                nextRoundId = commoditys.getId();
            }
        }
        String msg = "0";
        if (null != user) {
            Integer amount1 = luckCodeMapper.countUserCommAmount(user.getAccountId(), commodId);
            Integer amount2 = userHistoryMapper.countHistoryUserCommAmount(user.getAccountId(), commodId);
            msg = amount1 + amount2 + "";
        }
        map.put("user", msg);//是否参与本商品
        map.put("nextRound", nextRound);
        map.put("nextRoundId", nextRoundId);
        map.put("commId", commodId);//商品ID
        map.put("minNum", com.getMinimum());//商品ID
        map.put("coverImg", com.getCoverImgUrl());
        map.put("imgUrls", listUrl(commodId));//添加图片url数组
        map.put("onState", com.getStateId());//添加是否已开奖状态
        map.put("commodityName", com.getName());//添加商品名
        map.put("buyTotal", com.getBuyTotalNumber());//添加总购买次数
        map.put("buyCurrent", com.getBuyTotalNumber() - com.getBuyCurrentNumber());//添加当前购买次数
        map.put("roundTime", com.getRoundTime());//获取当前期数
        map.put("countDown", ServiceUtils.getResidualLotterySeconds(com));//倒计时
        map.put("descUrl", com.getCommodityDescUrl());//添加商品详情URL
        map.put("partake", listPartake(commodId));//添加参与记录
        map.put("guessLike", listMap3());//添加猜你喜欢商品
        return map;
    }

    public List<Map<String, Object>> listMap3() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Commoditys> coms = commsMapper.selectByGuess();
        int i = Settings.PAGE_LOAD_SIZE_10 <= coms.size() ? Settings.PAGE_LOAD_SIZE_10 : coms.size();
        for (int s = 0; s < i; s++) {
            Commoditys com = coms.get(s);
            map.put("name", com.getName());//获取商品名
            map.put("id", com.getId());//获取商品ID
            map.put("minNum", com.getMinimum());//获取商品ID
            map.put("state", com.getStateId());//获取状态
            map.put("buyCurrentNumber", com.getBuyTotalNumber() - com.getBuyCurrentNumber());//获取当前剩余人次
            map.put("buyTotalNumber", com.getBuyTotalNumber());//获取总购买人次
            map.put("coverImgUrl", com.getCoverImgUrl());//获取商品封面图片URL
            list.add(map);
        }
        return list;
    }

    public List<String> listUrl(Long commodId) {
        List<String> listImg = new ArrayList<>();
        Long tempId = commMapper.selectTempIdByCommId(commodId);
        List<String> imgUrlList = imgMapper.selectImgUrlListByTempId(tempId, 1);
        for (String imgUrl : imgUrlList) {
            listImg.add(imgUrl);
        }
        return listImg;
    }

    public Map<String, Object> mapBefore(Commoditys comh) {
        if (comh == null)
            return null;
        LotteryInfo lotteryInfo = lotteryInfoMapper.selectByComId(comh.getId());//查询中奖信息
        User user1 = null;
        if (null != lotteryInfo)
            user1 = userMapper.selectById(lotteryInfo.getUserAccountId());
        Map<String, Object> historyMap = new HashMap<>();
        historyMap.put("preCommId", comh.getId());//上一期商品id
        historyMap.put("userName", user1 == null ? null : user1.getNickname());//添加用户昵称
        historyMap.put("userHeaderImg", user1 == null ? null : user1.getHeaderUrl());//添加用户头像
        historyMap.put("roundTime", comh.getRoundTime());//添加期数
        historyMap.put("endTime", lotteryInfo == null ? null : lotteryInfo.getEndDate());//添加揭晓时间
        historyMap.put("luckCodes", lotteryInfo == null ? null : lotteryInfo.getLotteryId());//添加幸运号
        historyMap.put("userName", user1 == null ? null : user1.getNickname());//添加用户昵称
        historyMap.put("userAccount", lotteryInfo == null ? null : lotteryInfo.getUserAccountId());//添加用户accountID
        return historyMap;
    }

    public List<Map<String, Object>> listPartake(Long commodId) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        List<Long> userIds = luckCodeMapper.selectCountByCommodity(commodId);
        System.out.println(userIds.size());

        for (Long userId : userIds) {
            if (userId != 0) {
                System.out.println(userId);
                User user = userMapper.selectById(userId);
                Map<String, Object> map = map1(user.getAccountId());
                Map<String, Object> map1 = map2(commodId, user.getAccountId());
                Map<String, Object> listMapMin = new HashMap<>();
                listMapMin.putAll(map);
                listMapMin.putAll(map1);
                listMap.add(listMapMin);
            }
        }
        return listMap;
    }

    public Map<String, Object> map1(Long userAccountId) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.selectById(userAccountId);
        String name = null;
        String headerUrl = null;
        if (null != user) {
            name = user.getNickname();
            headerUrl = user.getHeaderUrl();
        }
        map.put("userName", name);//用户名
        map.put("headerUrl", headerUrl);//头像地址
        return map;
    }

    public Map<String, Object> map2(Long commodId, Long userAccountId) {
        Map<String, Object> map = new HashMap<>();
        List<LuckCodes> select = luckCodeMapper.selectByAccAndCommId(userAccountId, commodId);
        Long buyDate = null;
        int size = 0;
        if (!select.isEmpty()) {
            size = select.size();
            buyDate = select.get(0).getBuyDate();
        }
        map.put("partakeNumber", size);//参与人次
        map.put("buyDate", buyDate);//添加时间
        return map;
    }

    /**
     * 搜索商品信息
     * 根据商品类别category、商品名称name 搜索
     * 搜索的四种情况：
     * 1、name和category都有值，两者并列搜索
     * 2、category无值，按name搜索
     * 3、name无值，按category搜索
     * 4、name和category都无值，搜索所有
     *
     * @param categoryId 商品类别
     * @param commName   商品名称
     * @return JSONObject
     */
    @Override
    public List<Map<String, Object>> selectPaging(Integer categoryId, String commName, Long lastCommId) {
        if (null != categoryId && null != commName) {
            //按照商品分类和商品名查询
            return type1(categoryId, commName, lastCommId);
        } else if (null == categoryId && null == commName) {
            //默认显示类型id为1的商品
            return byType(1, lastCommId);
        } else if (null != categoryId && null == commName) {
            return byType(categoryId, lastCommId);
        } else if (null == categoryId && null != commName) {
            return type4(commName, lastCommId);
        }
        return null;
    }

    //已分类的商品名搜索
    public List<Map<String, Object>> type1(Integer categoryId, String commName, Long lastCommId) {
        List<Commoditys> list = commsMapper.selectByTypeAndName("%" + commName + "%", categoryId, Settings.COMMODITY_STATE_ON_SALE, lastCommId, Settings.PAGE_LOAD_SIZE_10);
        return forPut(list);
    }

    //已分类商品搜索
    public List<Map<String, Object>> byType(Integer categoryId, Long lastCommId) {
        List<Commoditys> list = commsMapper.selectByType(categoryId, Settings.COMMODITY_STATE_ON_SALE, lastCommId, Settings.PAGE_LOAD_SIZE_10);
        return forPut(list);
    }

    //未分类的商品名搜索
    public List<Map<String, Object>> type4(String commName, Long lastCommId) {
        List<Commoditys> list = commsMapper.selectByName("%" + commName + "%", Settings.COMMODITY_STATE_ON_SALE, lastCommId, Settings.PAGE_LOAD_SIZE_10);
        return forPut(list);
    }

    public List<Map<String, Object>> forPut(List<Commoditys> list) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (Commoditys com : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", com.getId());//添加商品ID
            map.put("minNum", com.getMinimum());//商品最低购买量
            map.put("typeId", com.getCommodityTypeId());//添加商品类型ID
            map.put("commodityName", com.getName());//添加商品名
            map.put("totalNumber", com.getBuyTotalNumber());//添加商品总购买人次
            map.put("currentNumber", com.getBuyTotalNumber() - com.getBuyCurrentNumber());//商品剩余
            map.put("headerImg", com.getCoverImgUrl());//商品封面图URL
            map.put("state", com.getStateId());//添加商品状态
            listMap.add(map);
        }
        return listMap;
    }


    /**
     * 查找高中奖率商品
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectHeight(Long lastCommId) {
        List<Commoditys> list = commsMapper.selectHeight(lastCommId, Settings.PAGE_LOAD_SIZE_10);
        return forPut(list);
    }

    private List<Map<String, Object>> getOnLotteryMapList(Long commId, Integer page) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<Commoditys> onLotteryList = null;
        if (null == commId && null != page) {
            List<Commoditys> list = commsMapper.selectOnLottery();
            list.addAll(commsMapper.selectHasTheLotteryComm());
            onLotteryList = ServiceUtils.getPageList(list, page);
        } else if (null != commId && null == page) {
            onLotteryList = new ArrayList<>();
            onLotteryList.add(commsMapper.selectByKey(commId));
        }
        long nowTime = new Date().getTime();
        for (int i = 0; i < onLotteryList.size(); i++) {
            Commoditys comm = onLotteryList.get(i);
            Map<String, Object> map = new HashMap<>();
            long sellOutTime = null == comm.getSellOutTime() ? 0 : comm.getSellOutTime();
            long endTime = sellOutTime + Settings.LOTTERY_ANNOUNCE_TIME_INTERVAL;
            int residualMinutes = 0;//开奖剩余秒数
            String userHeadImgUrl = null;
            String userNickName = null;
            Integer userPayNum = null;
            if (nowTime < endTime) {
                residualMinutes = (int) ((endTime - nowTime) / 1000);
            } else {
                commsMapper.updateCommState(comm.getId(), Settings.COMMODITY_STATE_HAS_LOTTERY);
                LotteryInfo lotteryInfo = lotteryInfoMapper.selectByComId(comm.getId());
                if (null != lotteryInfo) {
                    Long acc = lotteryInfo.getUserAccountId();
                    userPayNum = lotteryInfo.getBuyNum();
                    User user = userMapper.selectById(acc);
//                    ServiceUtils.insertNotificationPrizeInfo(npMapper, comm, lotteryInfo, user);
                    userNickName = user.getNickname();
                    userHeadImgUrl = user.getHeaderUrl();
                }
            }
            map.put("residualMinutes", residualMinutes);//剩余开奖秒数
            map.put("userHeadImgUrl", Settings.SERVER_URL_PATH + userHeadImgUrl);//中奖者头像
            map.put("userNickname", userNickName);// 中奖者昵称
            map.put("userPayNum", userPayNum);//中奖者购买数量
            map.put("id", comm.getId());//商品id
            map.put("minNum", comm.getMinimum());//商品id
            map.put("imgUrl", comm.getCoverImgUrl());//封面图片url
            map.put("currentTime", nowTime);//当前时间
            map.put("endTime", endTime);//当前时间
            map.put("detailUrl", comm.getCommodityDescUrl());//详情url
            map.put("desc", comm.getCommodityDesc());//商品描述
            map.put("state", comm.getStateId());//上商状态
            map.put("totalNumber", comm.getBuyTotalNumber());//所需总人次
            map.put("roundTime", comm.getRoundTime());//期数
            map.put("desc", comm.getName());//描述
            infoList.add(map);
        }
        return infoList;
    }

    /**
     * 生成新的期数
     *
     * @return 期数
     */
    @Override
    public Long generateNewRoundTime() {
        synchronized (this) {
            return commsMapper.selectMaxRoundTime() + 1;
        }
    }

    /**
     * 最新揭曉
     *
     * @param lastCommId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectNewestAnnounceComm(Long lastCommId) {
        return getFormAnnounceCommData(commsMapper.selectPageAnnounceComm(lastCommId, Settings.PAGE_LOAD_SIZE_10));
    }

    private List<Map<String, Object>> getFormAnnounceCommData(List<Commoditys> list) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        long nowTime = new Date().getTime();
        for (int i = 0; i < list.size(); i++) {
            Commoditys comms = list.get(i);
            Map<String, Object> map = new HashMap<>();
            long sellOutTime = null == comms.getSellOutTime() ? 0 : comms.getSellOutTime();
            long endTime = sellOutTime + Settings.LOTTERY_ANNOUNCE_TIME_INTERVAL;
            int residualMinutes = 0;//开奖剩余秒数
            String userHeadImgUrl = null;
            String userNickName = null;
            Integer userPayNum = null;
            if (nowTime < endTime) {
                residualMinutes = (int) ((endTime - nowTime) / 1000);
            } else {
                commsMapper.updateCommState(comms.getId(), Settings.COMMODITY_STATE_HAS_LOTTERY);
                LotteryInfo lotteryInfo = lotteryInfoMapper.selectByComId(comms.getId());
                if (null != lotteryInfo) {
                    Long acc = lotteryInfo.getUserAccountId();
                    User user = userMapper.selectById(acc);
//                    ServiceUtils.insertNotificationPrizeInfo(npMapper, comms, lotteryInfo, user);
                    userPayNum = lotteryInfo.getBuyNum();
                    userNickName = user == null ? null : user.getNickname();
                    userHeadImgUrl = user == null ? null : user.getHeaderUrl();
                }
            }
            map.put("residualSeconds", residualMinutes);//剩余开奖秒数
            map.put("userHeadImgUrl", userHeadImgUrl);//中奖者头像
            map.put("userNickname", userNickName);// 中奖者昵称
            map.put("userPayNum", userPayNum);//中奖者购买数量
            map.put("id", comms.getId());//商品id
            map.put("minNum", comms.getMinimum());//商品id
            map.put("imgUrl", comms.getCoverImgUrl());//封面图片url
            map.put("currentTime", nowTime);//当前时间
            map.put("endTime", endTime);//当前时间
            map.put("detailUrl", comms.getCommodityDescUrl());//详情url
            map.put("desc", comms.getCommodityDesc());//商品描述
            map.put("state", comms.getStateId());//上商状态
            map.put("totalNumber", comms.getBuyTotalNumber());//所需总人次
            map.put("roundTime", comms.getRoundTime());//期数
            map.put("desc", comms.getName());//描述
            infoList.add(map);
        }
        return infoList;
    }


    /**
     * 往期揭晓
     *
     * @param commId 商品ID
     * @return
     */
    @Override
    public List<Map> selectThePastAnnouncedCommList(Long commId) {
        List<Map> maps = commMapper.selectPastLottery(commId);
        return maps;
    }

    /**
     * 通过商品id 得到商品图文详情
     *
     * @param commodityId 商品id
     * @return 模板中的图文详情
     */
    @Override
    public String getContent(Long commodityId) {
        return commsMapper.selectContent(commodityId);
    }

    /**
     * 查询当前最大期数
     *
     * @return 期数
     */
    @Override
    public long getMaxRoundTime() {
        return commMapper.selectMaxRoundTime();
    }

    /**
     * 查询未满足期数数量需求的商品
     *
     * @param roundNum 期数数量
     * @return 模板
     */
    @Override
    public List<CommodityTemplate> getNotKeepRoundTemplate(long roundNum) {
        return commodityTemplateMapper.selectByRoundNum(roundNum);
    }

    /**
     * 得到下一期商品
     *
     * @param id 商品id
     * @return 商品
     */
    @Override
    public Commodity getNextCommodity(Long id) {
        return commMapper.selectNext(id);
    }

    /**
     * 生成下一期商品
     *
     * @param id
     */
    @Override
    public Commodity groundNext(Long id) {
        final Commodity next = getNextCommodity(id);
        commMapper.updateState(next.getId(), Commodity.ON_SELL);
        //此处只是为了统一设置了状态
        next.setStateId(3);
        return next;
    }

    /**
     * 得到商品细节信息，整合模板信息
     *
     * @param id
     * @return
     */
    @Override
    public Commoditys getDetails(Long id) {
        return commsMapper.selectDetails(id);
    }

    /**
     * 通过商品id获取同模板的正在销售的商品
     *
     * @param id 商品id
     * @return 商品
     */
    @Override
    public Commoditys selectOnSellCommodities(Long id) {
        return commsMapper.selectOnSell(id);
    }

    /**
     * 通过订单id获取商品购买信息
     *
     * @param orderId 订单id
     * @return 购买信息
     */
    @Override
    public List<CommodityAmount> selectAmounts(Long orderId) {
        return ordersCommoditysMapper.selectAmountOrders(orderId);
    }

}
