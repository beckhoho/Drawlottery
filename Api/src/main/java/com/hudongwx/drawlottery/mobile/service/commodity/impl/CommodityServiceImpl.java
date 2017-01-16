package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.utils.ServiceUtils;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    CommoditysMapper commMapper;
    @Autowired
    CommodityImgMapper imgMapper;
    @Autowired
    CommodityHistoryMapper historyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserLuckCodesMapper userluckMapper;
    @Autowired
    LuckCodesMapper luckCodeMapper;
    @Autowired
    CommodityTypeMapper typeMapper;
    @Autowired
    UserCodesHistoryMapper userHistoryMapper;

    /**
     * 添加商品
     *
     * @param commod 商品对象
     * @return 返回添加结果
     */
    @Override
    public boolean addCommodity(Commoditys commod) {
        return commMapper.insert(commod) > 0;
    }

    /**
     * 通过ID删除当前商品
     *
     * @param id 商品ID
     * @return 返回删除结果
     */
    @Override
    public boolean delete(Long id) {
        return commMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 通过ID获取当前ID的商品信息
     *
     * @param id 商品ID
     * @return 返回当前商品信息
     */
    @Override
    public Commoditys selectByid(Long id) {
        return commMapper.selectByPrimaryKey(id);

    }

    /**
     * 查询某个类型的全部商品
     *
     * @param commodType 商品类型名
     * @return 当前类型的所有的商品信息
     */
    @Override
    public List<Commoditys> selectTypeAll(String commodType) {
        Long i = commMapper.selectType(commodType);
        Commoditys com = new Commoditys();
        com.setCommodityTypeId(i);
        return commMapper.select(com);
    }


    /**
     * 查询当前类型商品的总数
     *
     * @param commodityTypeId 商品类型ID
     * @return 返回当前类型的商品总数量
     */
    @Override
    public int selectCount(Integer commodityTypeId) {
        return commMapper.selectTypeCount(commodityTypeId);
    }


    /**
     * 查询所有商品的信息
     *
     * @return 所有的商品信息
     */
    @Override
    public List<Commoditys> selectAll() {
        return commMapper.selectAll();
    }

    /**
     * 根据传入类型，返回对应的商品信息集
     *
     * @param type
     * @return
     */
    @Override
    public List<Map<String, Object>> selectByStyle(Integer type, Integer page) {
        List<Commoditys> cList;
        List<Map<String, Object>> infoList = new ArrayList<>();
        if (type.intValue() == Settings.COMMODITY_ORDER_POPULARITY) {
            cList = ServiceUtils.getPageList(commMapper.selectByTemp1(), page);
        } else if (type == Settings.COMMODITY_ORDER_FASTEST) {
            cList = ServiceUtils.getPageList(commMapper.selectByTemp2(), page);
        } else if (type == Settings.COMMODITY_ORDER_NEWEST) {
            cList = ServiceUtils.getPageList(commMapper.selectByTemp3(), page);
        } else if (type == Settings.COMMODITY_ORDER_HIGHT_RATE) {
            cList = ServiceUtils.getPageList(commMapper.selectHeight(100), page);
        } else {
            cList = ServiceUtils.getPageList(commMapper.selectByTemp4(), page);
        }
        for (Commoditys commoditys : cList) {
            infoList.add(dealCommSelectByStyle(commoditys));
        }
        return infoList;
    }

    private Map<String, Object> dealCommSelectByStyle(Commoditys comm) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", comm.getId());
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
    public Map<String, Object> selectCommodity(Long commodId) {
        Commoditys com = commMapper.selectByKey(commodId);
        Map<String, Object> map = new HashMap<>();
        if (com.getStateId() == 3) {//如果未开奖
            CommodityHistory comh = historyMapper.selectBycommodName(com.getName(), com.getRoundTime());
            map.put("beforeLottery", mapBefore(comh));//往期开奖揭晓
        }
        if (com.getStateId() == 1) {//如果已开奖
            CommodityHistory comm = historyMapper.selectBycommId(commodId);
            map.put("beforeLottery", mapBefore(comm));
        }
        map.put("commId", commodId);//商品ID
        map.put("coverImg", com.getCoverImgUrl());
        map.put("imgUrls", listUrl(commodId));//添加图片url数组
        map.put("onState", com.getStateId());//添加是否已开奖状态
        map.put("commodityName", com.getName());//添加商品名
        map.put("buyTotal", com.getBuyTotalNumber());//添加总购买次数
        map.put("buyCurrent", com.getBuyTotalNumber() - com.getBuyCurrentNumber());//添加当前购买次数
        map.put("roundTime", com.getRoundTime());//获取当前期数
        map.put("user", 0);//是否参与本商品
        map.put("countDown", 18000l);//倒计时
        map.put("descUrl", com.getCommodityDescUrl());//添加商品详情URL
        map.put("partake", listPartake(commodId));//添加参与记录
        map.put("guessLike", listMap3());//添加猜你喜欢商品
        return map;
    }

    public List<Map<String, Object>> listMap3() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Commoditys> coms = commMapper.selectByGuess();
        int i = Settings.PAGE_LOAD_SIZE <= coms.size() ? Settings.PAGE_LOAD_SIZE : coms.size();
        for (int s = 0; s < i; s++) {
            Commoditys com = coms.get(s);
            map.put("name", com.getName());//获取商品名
            map.put("id", com.getId());//获取商品ID
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
        List<CommodityImg> select = imgMapper.selectByCommId(commodId);
        for (CommodityImg imgs : select) {
            listImg.add(Settings.SERVER_URL_PATH + imgs.getUrl());
        }
        return listImg;
    }

    public Map<String, Object> mapBefore(CommodityHistory comh) {
        Map<String, Object> historyMap = new HashMap<>();
        if (comh == null) {
            return historyMap;
        }
        User user1 = userMapper.selectById(comh.getLuckUserAccountId());
        historyMap.put("userName", user1.getNickname());//添加用户昵称
        historyMap.put("userHeaderImg", user1.getHeaderUrl());//添加用户头像
        historyMap.put("roundTime", comh.getRoundTime());//添加期数
        historyMap.put("endTime", comh.getEndTime());//添加揭晓时间
        historyMap.put("luckCodes", comh.getLuckCode());//添加幸运号
        historyMap.put("userName", user1.getNickname());//添加用户昵称
        historyMap.put("userAccount", comh.getLuckUserAccountId());//添加用户accountID
        historyMap.put("endTime", comh.getEndTime());//添加揭晓时间
        return historyMap;
    }

    public List<Map<String, Object>> listPartake(Long commodId) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        List<Long> codes = userluckMapper.selectCountByCommodity(commodId);
        for (Long userLuckCodes : codes) {
            Long userAccountId = userLuckCodes;
            Map<String, Object> map = map1(userAccountId);
            Map<String, Object> map1 = map2(commodId, userAccountId);
            Map<String, Object> listMapMin = new HashMap<>();
            listMapMin.putAll(map);
            listMapMin.putAll(map1);
            listMap.add(listMapMin);
        }
        return listMap;
    }

    public Map<String, Object> map1(Long userAccountId) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.selectById(userAccountId);
        String name = user.getNickname();
        String headerUrl = user.getHeaderUrl();
        map.put("userName", name);//用户名
        map.put("headerUrl", Settings.SERVER_URL_PATH + headerUrl);//头像地址

        return map;
    }

    public Map<String, Object> map2(Long commodId, Long userAccountId) {
        Map<String, Object> map = new HashMap<>();
        List<UserLuckCodes> select = userluckMapper.selectByAccAndCommId(userAccountId, commodId);
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
     * @param page       当前最后一个显示的商品id
     * @return JSONObject
     */
    @Override
    public List<Map<String, Object>> selectPaging(Integer categoryId, String commName, Integer page) {
        if (null != categoryId && null != commName) {
            //按照商品分类和商品名查询
            return type1(categoryId, commName, page);
        } else if (null == categoryId && null == commName) {
            //默认显示类型id为1的商品
            return byType(1, page);
        } else if (null != categoryId && null == commName) {
            return byType(categoryId, page);
        } else if (null == categoryId && null != commName) {
            return type4(commName, page);
        }
        return null;
    }

    //已分类的商品名搜索
    public List<Map<String, Object>> type1(Integer categoryId, String commName, Integer page) {
        List<Commoditys> list = ServiceUtils.getPageList(commMapper.selectByTypeAndName("%" + commName + "%", categoryId, Settings.COMMODITY_STATE_ON_SALE), page);
        return forPut(list);
    }

    //已分类商品搜索
    public List<Map<String, Object>> byType(Integer categoryId, Integer page) {
        List<Commoditys> list = ServiceUtils.getPageList(commMapper.selectByType(categoryId, Settings.COMMODITY_STATE_ON_SALE), page);
        return forPut(list);
    }

    //未分类的商品名搜索
    public List<Map<String, Object>> type4(String commName, Integer page) {
        List<Commoditys> list = ServiceUtils.getPageList(commMapper.selectByName("%" + commName + "%", Settings.COMMODITY_STATE_ON_SALE), page);
        return forPut(list);
    }

    public List<Map<String, Object>> forPut(List<Commoditys> list) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (Commoditys com : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", com.getId());//添加商品ID
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
     * @param number
     * @return
     */
    @Override
    public List<Map<String, Object>> selectHeight(Integer number) {
        List<Commoditys> list = commMapper.selectHeight(number);
        return forPut(list);
    }

    private List<Map<String, Object>> getOnLotteryMapList(Long commId, Integer page) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<Commoditys> onLotteryList = null;
        if (null == commId && null != page) {
            List<Commoditys> list = commMapper.selectOnLottery(Settings.MAX_INFO_SIZE);
            list.addAll(commMapper.selectHasTheLotteryComm());
            onLotteryList = ServiceUtils.getPageList(list, page);
        } else if (null != commId && null == page) {
            onLotteryList = new ArrayList<>();
            onLotteryList.add(commMapper.selectByKey(commId));
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
                commMapper.updateCommState(comm.getId(), Settings.COMMODITY_STATE_HAS_LOTTERY);
                UserLuckCodes ulc = new UserLuckCodes();
                ulc.setLuckCodeId(comm.getLuckCodeId());
                ulc.setCommodityId(comm.getId());
                UserLuckCodes userLuckCodes = userluckMapper.selectByOne(comm.getId(), comm.getLuckCodeId());
                if (null != userLuckCodes) {
                    Long acc = userLuckCodes.getUserAccountId();
                    //insertHistory(comm,acc,userLuckCodes.getLuckCodeId());
                    ulc.setLuckCodeId(null);
                    ulc.setUserAccountId(acc);
                    userPayNum = userluckMapper.selectCount(ulc);
                    User user = userMapper.selectById(acc);
                    userNickName = user.getNickname();
                    userHeadImgUrl = user.getHeaderUrl();
                }
            }
            map.put("residualMinutes", residualMinutes);//剩余开奖秒数
            map.put("userHeadImgUrl", Settings.SERVER_URL_PATH + userHeadImgUrl);//
            map.put("userNickName", userNickName);//
            map.put("userPayNum", userPayNum);//
            map.put("id", comm.getId());//商品id
            map.put("imgUrl", comm.getCoverImgUrl());//封面图片url
            map.put("currentTime", nowTime);//当前时间
            map.put("detailUrl", comm.getCommodityDescUrl());//详情url
            map.put("desc", comm.getCommodityDesc());//商品描述
            map.put("state", comm.getStateId());//上商状态
            map.put("totalNumber", comm.getBuyTotalNumber());//所需总人次
            map.put("roundTime", comm.getRoundTime());//期数
            map.put("desc", comm.getName());//期数

            infoList.add(map);
        }
        return infoList;
    }

    public Long produceLuckCodes(Long commodityId) {
        List<Long> codes = userluckMapper.selectCountByCommodity(commodityId);
        int v = (int) (Math.random() * codes.size());
        Long codeId = codes.get(v);
        return codeId;
    }

    public boolean insertHistory(Commoditys com, Long accountId, Long luckCodeId) {
        UserLuckCodes luckCodes = new UserLuckCodes();
        luckCodes.setUserAccountId(accountId);
        List<UserLuckCodes> select = userluckMapper.select(luckCodes);
        //String s = luckCodeMapper.selectLuckCode(luckCodeId);
        CommodityHistory history = new CommodityHistory();
        history.setCommodityId(com.getId());
        history.setBuyNumber(select.size());
        history.setLuckUserAccountId(accountId);
        history.setBuyTotalNumber(com.getBuyTotalNumber());
        history.setCommodityName(com.getName());
        history.setCoverImgUrl(com.getCoverImgUrl());
        history.setEndTime(new Date().getTime());
        history.setExchangeState(0);
        history.setExchangeWay(null);
        history.setGenre(com.getGenre());
        history.setRoundTime(com.getRoundTime());
        //history.setLuckCode(s);
        return historyMapper.insert(history) > 0;
    }


    /**
     * 商品开奖之后需要改变的数据
     *
     * @param luckCode    幸运号（不是ID）
     * @param commodityId 商品ID
     * @return 回改变结果
     */
    @Override
    public boolean reviseInfo(String luckCode, Long commodityId) {
        Commoditys com = commMapper.selectByKey(commodityId);
        LuckCodes luckCodes = new LuckCodes();
        luckCodes.setLockCode(luckCode);
        luckCodes.setCommodityId(commodityId);
        List<LuckCodes> codes = luckCodeMapper.select(luckCodes);
        Long codeId = codes.get(0).getId();
        UserLuckCodes userLuckCodes = new UserLuckCodes();
        userLuckCodes.setLuckCodeId(codeId);
        List<UserLuckCodes> select = userluckMapper.select(userLuckCodes);

        //将商品表中的商品改变状态

        com.setStateId(1);
        com.setId(commodityId);
        int i = commMapper.updateByPrimaryKeySelective(com);

        //将用户幸运码添加到历史
        Long accountId = select.get(0).getUserAccountId();
        UserLuckCodes user = new UserLuckCodes();
        user.setUserAccountId(accountId);
        List<UserLuckCodes> listUser = userluckMapper.select(userLuckCodes);
        UserCodesHistory userHistory = new UserCodesHistory();
        userHistory.setUserAccountId(accountId);
        userHistory.setCommodityId(commodityId);
        userHistory.setUserLuckCodeId(codeId);
        userHistory.setRoundTime(com.getRoundTime());
        int insert1 = userHistoryMapper.insert(userHistory);

        //将商品添加到历史
        CommodityHistory history = new CommodityHistory();
        history.setCommodityId(commodityId);
        history.setBuyTotalNumber(com.getBuyTotalNumber());
        history.setCoverImgUrl(com.getCoverImgUrl());
        history.setEndTime(new Date().getTime());
        history.setCommodityName(com.getName());
        history.setLuckCode(luckCode);
        history.setRoundTime(com.getRoundTime());
        history.setGenre(com.getGenre());
        history.setLuckUserAccountId(select.get(0).getId());
        history.setLuckUserAccountId(accountId);
        history.setBuyNumber(listUser.size());
        int insert = historyMapper.insert(history);
        return insert > 0 && insert1 > 0 && i > 0;
    }

}
