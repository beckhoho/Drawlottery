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

    /**
     * 添加商品
     *
     * @param commod 商品对象
     * @return 返回添加结果
     */
    @Override
    public boolean addCommodity(Commoditys commod) {
        return commsMapper.insert(commod) > 0;
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
        return commsMapper.selectByPrimaryKey(id);

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
            cList = commsMapper.selectByTemp1(lastCommId, Settings.PAGE_LOAD_SIZE);//按人气搜索
        } else if (type == Settings.COMMODITY_ORDER_FASTEST) {
            cList = commsMapper.selectByTemp2(lastCommId, Settings.PAGE_LOAD_SIZE);
        } else if (type == Settings.COMMODITY_ORDER_NEWEST) {
            cList = commsMapper.selectByTemp3(lastCommId, Settings.PAGE_LOAD_SIZE);
        } else if (type == Settings.COMMODITY_ORDER_HIGHT_RATE) {
            cList = commsMapper.selectHeight(100, lastCommId, Settings.PAGE_LOAD_SIZE);
        } else {
            cList = commsMapper.selectByTemp4(lastCommId, Settings.PAGE_LOAD_SIZE);
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
    public Map<String, Object> selectCommodity(Long commodId) {
        Commoditys com = commsMapper.selectByKey(commodId);
        Map<String, Object> map = new HashMap<>();
        String nextRound = null;
        Long nextRoundId = null;
        if (com.getStateId() == 3) {//如果未开奖
            List<CommodityHistory> comh = historyMapper.selectBycommodName(com.getName(), com.getRoundTime());
            if(comh.size()==0){
                map.put("beforeLottery",new HashMap<>());
            }
            else {
                map.put("beforeLottery", mapBefore(comh.get(0)));//往期开奖揭晓
            }
        }
        if (com.getStateId() == 1) {//如果已开奖
            CommodityHistory comm = historyMapper.selectBycommId(commodId);
            map.put("beforeLottery", mapBefore(comm));
            Commoditys commoditys = commsMapper.selectNextRoundComm(com.getTempId(), Settings.COMMODITY_STATE_ON_SALE);
            nextRound = commoditys.getRoundTime() + "期正在火热进行中";
            nextRoundId = commoditys.getId();
        }
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
        map.put("user", 0);//是否参与本商品
        map.put("countDown", 180l);//倒计时
        map.put("descUrl", com.getCommodityDescUrl());//添加商品详情URL
        map.put("partake", listPartake(commodId));//添加参与记录
        map.put("guessLike", listMap3());//添加猜你喜欢商品
        return map;
    }

    public List<Map<String, Object>> listMap3() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Commoditys> coms = commsMapper.selectByGuess();
        int i = Settings.PAGE_LOAD_SIZE <= coms.size() ? Settings.PAGE_LOAD_SIZE : coms.size();
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
        List<Long> codes = luckCodeMapper.selectCountByCommodity(commodId);
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
        String name = null;
        String headerUrl = null;
        if (null != user) {
            name = user.getNickname();
            headerUrl = Settings.SERVER_URL_PATH + user.getHeaderUrl();
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
        List<Commoditys> list = commsMapper.selectByTypeAndName("%" + commName + "%", categoryId, Settings.COMMODITY_STATE_ON_SALE, lastCommId, Settings.PAGE_LOAD_SIZE);
        return forPut(list);
    }

    //已分类商品搜索
    public List<Map<String, Object>> byType(Integer categoryId, Long lastCommId) {
        List<Commoditys> list = commsMapper.selectByType(categoryId, Settings.COMMODITY_STATE_ON_SALE, lastCommId, Settings.PAGE_LOAD_SIZE);
        return forPut(list);
    }

    //未分类的商品名搜索
    public List<Map<String, Object>> type4(String commName, Long lastCommId) {
        List<Commoditys> list = commsMapper.selectByName("%" + commName + "%", Settings.COMMODITY_STATE_ON_SALE, lastCommId, Settings.PAGE_LOAD_SIZE);
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
     * @param number
     * @return
     */
    @Override
    public List<Map<String, Object>> selectHeight(Integer number, Long lastCommId) {
        List<Commoditys> list = commsMapper.selectHeight(number, lastCommId, Settings.PAGE_LOAD_SIZE);
        return forPut(list);
    }

    private List<Map<String, Object>> getOnLotteryMapList(Long commId, Integer page) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<Commoditys> onLotteryList = null;
        if (null == commId && null != page) {
            List<Commoditys> list = commsMapper.selectOnLottery(Settings.MAX_INFO_SIZE);
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
                    Long acc =lotteryInfo.getUserAccountId();
                    //insertHistory(comm,acc,userLuckCodes.getLuckCodeId());

                    userPayNum = lotteryInfo.getBuyNum();
                    User user = userMapper.selectById(acc);
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
    public List<Map<String, Object>> selectAnnounceComm(Long lastCommId) {
        //正在开奖的商品
        List<Commoditys> lotcommList = null;
        //已开奖的商品
        List<Commoditys> annCommList = commsMapper.selectAnnouncedComm(lastCommId, Settings.PAGE_LOAD_SIZE);

        return null;
    }

}
