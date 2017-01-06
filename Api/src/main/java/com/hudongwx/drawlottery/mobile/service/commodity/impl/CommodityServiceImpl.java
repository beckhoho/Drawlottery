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
    CommoditysMapper mapper;
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

    /**
     * 添加商品
     *
     * @param commod 商品对象
     * @return 返回添加结果
     */
    @Override
    public boolean addCommodity(Commoditys commod) {
        return mapper.insert(commod) > 0;

    }

    /**
     * 通过ID删除当前商品
     *
     * @param id 商品ID
     * @return 返回删除结果
     */
    @Override
    public boolean delete(Long id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 通过ID获取当前ID的商品信息
     *
     * @param id 商品ID
     * @return 返回当前商品信息
     */
    @Override
    public Commoditys selectByid(Long id) {
        return mapper.selectByPrimaryKey(id);

    }

    /**
     * 查询某个类型的全部商品
     *
     * @param commodType 商品类型名
     * @return 当前类型的所有的商品信息
     */
    @Override
    public List<Commoditys> selectTypeAll(String commodType) {
        Long i = mapper.selectType(commodType);
        Commoditys com = new Commoditys();
        com.setCommodityTypeId(i);
        return mapper.select(com);
    }

    /**
     * 更新修改商品信息
     *
     * @param commod 商品对象
     * @return 返回修改结果
     */
    @Override
    public boolean update(Commoditys commod) {
        int i = mapper.updateByPrimaryKeySelective(commod);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * 查询当前类型商品的总数
     *
     * @param commodTypeId 商品类型ID
     * @return 返回当前类型的商品总数量
     */
    @Override
    public int selectCount(Integer commodTypeId) {
        return mapper.selectTypeCount(commodTypeId);
    }


    /**
     * 查询所有商品的信息
     *
     * @return 所有的商品信息
     */
    @Override
    public List<Commoditys> selectAll() {
        return mapper.selectAll();
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
            cList = ServiceUtils.getPageList(mapper.selectByTemp1(), page);
        } else if (type == Settings.COMMODITY_ORDER_FASTEST) {
            cList = ServiceUtils.getPageList(mapper.selectByTemp2(), page);
        } else if (type == Settings.COMMODITY_ORDER_NEWEST) {
            cList = ServiceUtils.getPageList(mapper.selectByTemp3(), page);
        } else {
            cList = ServiceUtils.getPageList(mapper.selectByTemp4(), page);
        }
        for (Commoditys commoditys : cList) {
            infoList.add(dealCommSelectByStyle(commoditys));
        }
        return infoList;
    }

    private Map<String, Object> dealCommSelectByStyle(Commoditys comm) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", comm.getId());
        map.put("name", comm.getName());
        map.put("imgUrl", comm.getCoverImgUrl());
        map.put("currentNumber", comm.getBuyCurrentNumber());
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
    public List<Map<String, Object>> selectOnLottery(Long accountId, Integer page) {
        return getOnLotteryMapList(accountId, null, page);
    }

    @Override
    public List<Map<String, Object>> selectOneOnLottery(Long accountId, Long commId) {
        return getOnLotteryMapList(accountId, commId, null);
    }

    /**
     * 查看商品详情
     *
     * @return 返回一个map集合
     */
    @Override
    public Map<String, Object> selectCommodity(Long commodId) {
        Commoditys com = mapper.selectByPrimaryKey(commodId);
        Map<String, Object> map = new HashMap<>();
        if (com.getStateId() == 0) {//如果未开奖
            String roundTime = com.getRoundTime();
            CommodityHistory comh = historyMapper.selectBycommodId(com.getName(), Long.valueOf(roundTime));
            map.put("beforeLottery", mapBefore(comh));//往期开奖揭晓
        }
        if (com.getStateId() == 1) {//如果已开奖
            CommodityHistory comm = historyMapper.selectBycommod(commodId);
            map.put("beforeLottery", mapBefore(comm));
        }
        map.put("commId", commodId);//商品ID
        map.put("imgUrls", listUrl(commodId));//添加图片url数组
        map.put("onState", com.getStateId());//添加是否已开奖状态
        map.put("commodityName", com.getName());//添加商品名
        map.put("buyTotal", com.getBuyTotalNumber());//添加总购买次数
        map.put("buyCurrent", com.getBuyCurrentNumber());//添加当前购买次数
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
        List<Commoditys> coms = mapper.selectByGuess();
        int i = Settings.PAGE_LOAD_SIZE <= coms.size() ? Settings.PAGE_LOAD_SIZE : coms.size();
        for (int s = 0; s < i; s++) {
            Commoditys com = coms.get(s);
            map.put("name", com.getName());//获取商品名
            map.put("id", com.getId());//获取商品ID
            map.put("state", com.getStateId());//获取状态
            map.put("buyCurrentNumber", com.getBuyCurrentNumber());//获取当前购买人次
            map.put("buyTotalNumber", com.getBuyTotalNumber());//获取总购买人次
            map.put("coverImgUrl", com.getCoverImgUrl());//获取商品封面图片URL
            list.add(map);
        }
        return list;
    }


    public List<String> listUrl(Long commodId) {
        List<String> listImg = new ArrayList<>();
        CommodityImg img = new CommodityImg();
        img.setCommodityId(commodId);
        List<CommodityImg> select = imgMapper.select(img);
        for (CommodityImg imgs : select) {
            listImg.add(imgs.getUrl());
        }
        return listImg;
    }

    public Map<String, Object> mapBefore(CommodityHistory comh) {
        Map<String, Object> historyMap = new HashMap<>();
        if (comh == null) {
            return historyMap;
        }
        User user = new User();
        user.setAccountId(comh.getLuckUserAccountId());
        List<User> users = userMapper.select(user);
        historyMap.put("userName", users.get(0).getNickname());//添加用户昵称
        historyMap.put("userAccount", comh.getLuckUserAccountId());//添加用户accountID
        historyMap.put("roundTime", comh.getRoundTime());//添加期数
        historyMap.put("endTime", comh.getEndTime());//添加揭晓时间
        historyMap.put("luckCodes", comh.getLuckCode());//添加幸运号
        historyMap.put("userName", users.get(0).getNickname());//添加用户昵称
        historyMap.put("userAccount", comh.getLuckUserAccountId());//添加用户accountID
        historyMap.put("beforeRoundTime", comh.getRoundTime());//添加期数
        historyMap.put("endTime", comh.getEndTime());//添加揭晓时间
        historyMap.put("luckCodes", comh.getLuckCode());//添加幸运号
        return historyMap;
    }

    public List<Map<String, Object>> listPartake(Long commodId) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        List<UserLuckCodes> codes = userluckMapper.selectCountByCommodity(commodId);
        for (UserLuckCodes userLuckCodes : codes) {
            Long userAccountId = userLuckCodes.getUserAccountId();
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
        User user = new User();
        user.setAccountId(userAccountId);
        List<User> select1 = userMapper.select(user);
        String name = select1.get(0).getNickname();
        String headerUrl = select1.get(0).getHeaderUrl();
        map.put("userName", name);
        map.put("headerUrl", headerUrl);
        map.put("userName", name);//用户名
        map.put("headerUrl", headerUrl);//头像地址

        return map;
    }

    public Map<String, Object> map2(Long commodId, Long userAccountId) {
        UserLuckCodes userLuck = new UserLuckCodes();
        Map<String, Object> map = new HashMap<>();
        userLuck.setCommodityId(commodId);
        userLuck.setUserAccountId(userAccountId);
        List<UserLuckCodes> select = userluckMapper.select(userLuck);
        if (select.size() == 0) {
            return map;
        }
        Date buyDate = select.get(0).getBuyDate();
        int size = select.size();
        map.put("partakeNumber", size);
        map.put("buyDate", buyDate);
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
        return new ArrayList<>();
    }

    //已分类的商品名搜索
    public List<Map<String, Object>> type1(Integer categoryId, String commName, Integer page) {
        List<Commoditys> list = ServiceUtils.getPageList(mapper.selectByTypeAndName("%" + commName + "%", categoryId), page);
        return forPut(list);
    }

    //已分类商品搜索
    public List<Map<String, Object>> byType(Integer categoryId, Integer page) {
        List<Commoditys> list = ServiceUtils.getPageList(mapper.selectByType(categoryId), page);
        return forPut(list);
    }

    //未分类的商品名搜索
    public List<Map<String, Object>> type4(String commName, Integer page) {
        List<Commoditys> list = ServiceUtils.getPageList(mapper.selectByName("%" + commName + "%"), page);
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
            map.put("currentNumber", com.getBuyCurrentNumber());//添加商品当前购买人次
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
        List<Commoditys> list = mapper.selectHeight(number);
        return forPut(list);
    }

    private List<Map<String, Object>> getOnLotteryMapList(Long accountId, Long commId, Integer page) {
        List<Map<String, Object>> infoList = new ArrayList<>();
        List<Commoditys> list = null;
        if (null == commId && null != page) {
            list = ServiceUtils.getPageList(mapper.selectOnLottery(), page);
        } else if (null != commId && null == page) {
            list = new ArrayList<>();
            list.add(mapper.selectByPrimaryKey(commId));
        }
        long nowTime = new Date().getTime();
        for (int i = 0; i < list.size(); i++) {
            Commoditys comm = list.get(i);
            Map<String, Object> map = new HashMap<>();
            long sellOutTime = null == comm.getSellOutTime() ? 0 : comm.getSellOutTime().getTime();
            long endTime = sellOutTime + Settings.LOTTERY_ANNOUNCE_TIME_INTERVAL;
            int residualMinutes = 0;//开奖剩余秒数
            String userHeadImgUrl = null;
            String userNickName = null;
            Integer userPayNum = null;
            if (nowTime < endTime) {
                residualMinutes = (int) ((endTime - nowTime) / 1000);
            } else {
                UserLuckCodes ulc = new UserLuckCodes();
                ulc.setLockCodeId(comm.getLuckCodeId());
                ulc.setCommodityId(comm.getId());
                UserLuckCodes userLuckCodes = userluckMapper.selectOne(ulc);
                if (null != userLuckCodes) {
                    Long acc = userLuckCodes.getUserAccountId();
                    ulc.setLockCodeId(null);
                    ulc.setUserAccountId(acc);
                    userPayNum = userluckMapper.selectCount(ulc);
                    User user = userMapper.selectByPrimaryKey(acc);
                    userNickName = user.getNickname();
                    userHeadImgUrl = user.getHeaderUrl();
                }
            }
            map.put("residualMinutes", residualMinutes);//剩余开奖秒数
            map.put("userHeadImgUrl", userHeadImgUrl);//
            map.put("userNickName", userNickName);//
            map.put("userPayNum", userPayNum);//
            map.put("id", comm.getId());//商品id
            map.put("imgUrl", comm.getCoverImgUrl());//封面图片url
            map.put("currentTime", nowTime);//当前时间
            map.put("detailUrl", comm.getCommodityDescUrl());//详情url
            map.put("desc", comm.getCommodityDesc());//商品描述
            map.put("state", comm.getStateId());//上商品是否可用
            map.put("totalNumber", comm.getBuyTotalNumber());//所需总人次
            map.put("roundTime", comm.getRoundTime());//期数
            infoList.add(map);
        }
        return infoList;
    }
}
