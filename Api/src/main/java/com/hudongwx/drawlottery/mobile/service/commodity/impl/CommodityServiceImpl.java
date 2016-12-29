package com.hudongwx.drawlottery.mobile.service.commodity.impl;

import com.hudongwx.drawlottery.mobile.entitys.*;
import com.hudongwx.drawlottery.mobile.mappers.*;
import com.hudongwx.drawlottery.mobile.service.commodity.ICommodityService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.hudongwx.drawlottery.mobile.utils.Settings.PAGE_LOAD_SIZE;

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
     * 通过关键字搜索商品
     *
     * @param name 模糊搜索商品名
     * @return
     */
    @Override
    public List<Commoditys> selectByName(String name) {
        return mapper.selectByName("%" + name + "%");
    }

    /**
     * 根据传入类型，返回对应的商品信息集
     *
     * @param ref
     * @param type
     * @param lastcommodityid
     * @return
     */
    @Override
    public List<Map<String, Object>> selectByStyle(Integer ref, Integer type, Long lastcommodityid) {
        List<Commoditys> cList;
        List<Map<String, Object>> infoList = new ArrayList<>();
        if (type.intValue() == Settings.COMMODITY_ORDER_POPULARITY) {
            cList = mapper.selectByTemp1();
        } else if (type == Settings.COMMODITY_ORDER_FASTEST) {
            cList = mapper.selectByTemp2();
        } else if (type == Settings.COMMODITY_ORDER_NEWEST) {
            cList = mapper.selectByTemp3();
        } else {
            cList = mapper.selectByTemp4();
        }
        if (null == lastcommodityid || null == ref || ref == Settings.DROP_DOWN_REFRESH) {
            int s = Settings.PAGE_LOAD_SIZE >= cList.size() ? cList.size() : Settings.PAGE_LOAD_SIZE;
            for (int i = 0; i < s; i++) {
                infoList.add(dealCommSelectByStyle(cList.get(i)));
            }
        } else if (ref == Settings.PULL_TO_REFRESH) {
            for (int i = 0; i < cList.size(); i++) {
                if (cList.get(i).getId().longValue() == lastcommodityid.longValue()) {
                    int size = (i + PAGE_LOAD_SIZE) > cList.size() ? cList.size() : i + PAGE_LOAD_SIZE;
                    for (int j = i + 1; j < size; j++) {
                        infoList.add(dealCommSelectByStyle(cList.get(j)));
                    }
                }
            }
        }
        return infoList;
    }

    private Map<String, Object> dealCommSelectByStyle(Commoditys comm) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", comm.getId());
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
    public List<Map<String, Object>> selectOnLottery() {
        List<Commoditys> list = new ArrayList<>();
        List<Commoditys> list1 = mapper.selectOnLottery();
        List<Map<String, Object>> infoList = new ArrayList<>();
        int s = Settings.PAGE_LOAD_SIZE >= list1.size() ? list1.size() : Settings.PAGE_LOAD_SIZE;
        for (int i = 0; i < s; i++) {
            Commoditys comm = list1.get(i);
            long residualTime = 1000 * 60 * 3;
            Map<String, Object> map = new HashMap<>();
            map.put("id", comm.getId());
            map.put("imgUrl", comm.getCoverImgUrl());
            map.put("residualTime", residualTime);
            map.put("detailUrl", comm.getCommodityDescUrl());
            map.put("state", comm.getState());
            infoList.add(map);
        }
        return infoList;
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
        if (com.getState() == 0) {//如果未开奖
            String roundTime = com.getRoundTime();
            CommodityHistory comh = historyMapper.selectBycommodId(com.getName(), Long.valueOf(roundTime));
            map.put("beforeLottery", mapBefore(comh));//往期开奖揭晓
        }
        if (com.getState() == 1) {//如果已开奖
            CommodityHistory comm = historyMapper.selectBycommod(commodId);
            map.put("beforeLottery", mapBefore(comm));
        }
        map.put("commodId", commodId);//商品ID
        map.put("imgUrl", listUrl(commodId));//添加图片url数组
        map.put("onState", com.getState());//添加是否已开奖状态
        map.put("commodityName", com.getName());//添加商品名
        map.put("buyTotal", com.getBuyTotalNumber());//添加总购买次数
        map.put("buyCurrent", com.getBuyCurrentNumber());//添加当前购买次数
        map.put("roundTime", com.getRoundTime());//获取当前期数
        map.put("user", 0);//是否参与本商品
        map.put("countDown", 18000l);//倒计时
        map.put("descUrl", com.getCommodityDescUrl());//添加商品详情URL
        map.put("partake", listPartake(commodId));//添加参与记录
        return map;
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
        user.setAccountId(comh.getUserAccountId());
        List<User> users = userMapper.select(user);
        historyMap.put("userName", users.get(0).getNickname());//添加用户昵称
        historyMap.put("userAccount", comh.getUserAccountId());//添加用户accountID
        historyMap.put("roundTime", comh.getRoundTime());//添加期数
        historyMap.put("endTime", comh.getEndTime());//添加揭晓时间
        historyMap.put("luckCodes", comh.getLuckCode());//添加幸运号
        historyMap.put("userName", users.get(0).getNickname());//添加用户昵称
        historyMap.put("userAccount", comh.getUserAccountId());//添加用户accountID
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
     * @param lastCommId 当前最后一个显示的商品id
     * @return JSONObject
     */
    @Override
    public List<Map<String,Object>> selectPaging(Integer categoryId, String commName, Long lastCommId) {
//        mapper.selectPaging(commodTypeId, startNum, endNum);

        Map<String,Object> map = new HashMap<>();

        if (null != categoryId && null != commName) {
            //按照商品分类和商品名查询
            type1(categoryId,commName);
        }
        else if (1 == categoryId && null == commName) {
            //默认显示类型id为1的商品
            byType(categoryId);
        }
        else if (null != categoryId && null == commName) {
            byType(categoryId);
        }
        else if (null == categoryId && null != commName) {
            return type4(commName);
        }

        return new ArrayList<>();
    }

    //已分类的商品名搜索
    public List<Map<String,Object>> type1(Integer categoryId,String commName){
        List<Commoditys> list = mapper.selectByTypeAndName(commName,categoryId);
        return forPut(list);
    }

    //已分类商品搜索
    public List<Map<String,Object>> byType(Integer categoryId){
        List<Commoditys> list = mapper.selectByType(categoryId);
        return forPut(list);
    }

    //未分类的商品名搜索
    public List<Map<String,Object>> type4(String commName){
        List<Commoditys> list = mapper.selectByName("%" + commName + "%");
        return forPut(list);
    }


    public List<Map<String,Object>> forPut(List<Commoditys> list){
        List<Map<String,Object>> listMap = new ArrayList<>();
        for (Commoditys com:list){
            Map<String,Object> map = new HashMap<>();
            map.put("id",com.getId());//添加商品ID
            map.put("typeId",com.getCommodityTypeId());//添加商品类型ID
            map.put("commodityName",com.getName());//添加商品名
            map.put("totalNumber",com.getBuyTotalNumber());//添加商品总购买人次
            map.put("currentNumber",com.getBuyCurrentNumber());//添加商品当前购买人次
            map.put("headerImg",com.getCoverImgUrl());//商品封面图URL
            map.put("state",com.getState());//添加商品状态
            listMap.add(map);
        }
        return listMap;
    }

}
