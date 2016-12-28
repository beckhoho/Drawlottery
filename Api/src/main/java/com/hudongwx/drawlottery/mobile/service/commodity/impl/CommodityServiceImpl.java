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
     * 分页查询方法
     *
     * @param commodTypeId 商品类型ID
     * @param startNum     limit开始下标
     * @param endNum       limit结束下标
     * @return 返回指定位置的商品信息集
     */
    @Override
    public List<Commoditys> selectPaging(Integer commodTypeId, Integer startNum, Integer endNum) {
        return mapper.selectPaging(commodTypeId, startNum, endNum);
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
     * @param type 商品热度，最新，高价分类
     * @return
     */
    @Override
    public List<Commoditys> selectByStyle(Integer ref, Integer type, Long lastcommodityid) {
        List<Commoditys> cList;
        List<Commoditys> newList = new ArrayList<>();
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
                Commoditys commoditys = cList.get(i);
                if (null == commoditys)
                    break;
                newList.add(commoditys);
            }
        } else if (ref == Settings.PULL_TO_REFRESH) {
            for (int i = 0; i < cList.size(); i++) {
                Commoditys commoditys = cList.get(i);
                if (null == commoditys)
                    break;
                if (commoditys.getId().longValue() == lastcommodityid.longValue()) {
                    for (int j = i; j < i + PAGE_LOAD_SIZE; j++) {
                        newList.add(commoditys);
                    }
                }
            }
        }
        return newList;
    }

    /**
     * 查询当前正在开奖的商品
     * @return  返回正在开奖的商品
     */
    @Override
    public List<Commoditys> selectOnLottery() {
        List<Commoditys> list = new ArrayList<>();
        List<Commoditys> list1 = mapper.selectOnLottery();
        int s = Settings.PAGE_LOAD_SIZE>=list1.size()?list1.size():Settings.PAGE_LOAD_SIZE;
        for(int i=0;i<s;i++){
            Commoditys com = list1.get(i);
            list.add(com);
        }
        return list;
    }

    /**
     * 查看商品详情
     * @return  返回一个map集合
     */
    @Override
    public Map<String, Object> selectCommodity(Long commodId) {
        Commoditys com = mapper.selectByPrimaryKey(commodId);
        CommodityHistory comh = historyMapper.selectBycommodId(commodId,com.getRoundTime());
        Map<String,Object> map = new HashMap<>();

        map.put("imgURL",listUrl(commodId));//添加图片url数组
        map.put("onState",com.getState());//添加是否已开奖状态
        map.put("commodityName",com.getName());//添加商品名
        map.put("buyTotal",com.getBuyTotalNumber());//添加总购买次数
        map.put("buyCurrent",com.getBuyCurrentNumber());//添加当前购买次数
        map.put("beforeLottery",mapBefore(comh));//往期开奖揭晓
        map.put("descUrl",com.getCommodityDescUrl());//添加商品详情URL
        map.put("partake",listPartake(commodId));//添加参与记录

        return map;
    }

    public List<String> listUrl(Long commodId){
        List<String> listImg = new ArrayList<>();
        CommodityImg img = new CommodityImg();
        img.setCommodityId(commodId);
        List<CommodityImg> select = imgMapper.select(img);
        for(CommodityImg imgs:select){
            listImg.add(imgs.getUrl());
        }
        return listImg;
    }

    public Map<String,Object> mapBefore(CommodityHistory comh){
        Map<String,Object> historyMap = new HashMap<>();
        User user = new User();
        user.setAccountId(comh.getUserAccountId());
        List<User> users = userMapper.select(user);
        historyMap.put("userName",users.get(0).getNickname());//添加用户昵称
        historyMap.put("userAccount",comh.getUserAccountId());//添加用户accountID
        historyMap.put("roundTime",comh.getRoundTime());//添加期数
        historyMap.put("endTime",comh.getEndTime());//添加揭晓时间
        historyMap.put("luckCodes",comh.getLuckCode());//添加幸运号
        return historyMap;
    }

    public List<Map<String,Object>> listPartake(Long commodId){
        List<Map<String,Object>> listMap = new ArrayList<>();
        List<UserLuckCodes> codes = userluckMapper.selectCountByCommodity(commodId);
        for (UserLuckCodes userLuckCodes:codes){
            Long userAccountId = userLuckCodes.getUserAccountId();
            Map<String,Object> map = map1(userAccountId);
            Map<String,Object> map1 = map2(commodId,userAccountId);
            Map<String,Object> listMapMin = new HashMap<>();
            listMapMin.putAll(map);
            listMapMin.putAll(map1);
            listMap.add(listMapMin);
        }
        return listMap;
    }

    public Map<String,Object> map1(Long userAccountId){
        Map<String,Object> map = new HashMap<>();
        User user = new User();
        user.setAccountId(userAccountId);
        List<User> select1 = userMapper.select(user);
        String name = select1.get(0).getNickname();
        String headerUrl = select1.get(0).getHeaderUrl();
        map.put("userName",name);
        map.put("headerUrl",headerUrl);
        return map;
    }

    public Map<String,Object> map2(Long commodId,Long userAccountId){
        UserLuckCodes userLuck = new UserLuckCodes();
        userLuck.setCommodityId(commodId);
        userLuck.setUserAccountId(userAccountId);
        List<UserLuckCodes> select = userluckMapper.select(userLuck);
        Date buyDate = select.get(0).getBuyDate();
        int size = select.size();
        Map<String,Object> map = new HashMap<>();
        map.put("partakeNumber",size);
        map.put("buyDate",buyDate);
        return map;
    }

}
