package com.hudongwx.drawlottery.mobile.service.user.impl;

import com.hudongwx.drawlottery.mobile.entitys.RedPackets;
import com.hudongwx.drawlottery.mobile.mappers.RedPacketsMapper;
import com.hudongwx.drawlottery.mobile.service.user.IRedPacketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/20 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/20 0016　<br/>
 * <p>
 * 红包service接口实现类
 * <p>
 * @email 294786949@qq.com
 */
@Service
public class RedPacketsServiceImpl implements IRedPacketsService {
    @Autowired
    RedPacketsMapper mapper;

    /**
     * 添加红包
     * @param rp    红包对象
     * @return  添加结果
     */
    @Override
    public boolean addRP(RedPackets rp) {
        if (mapper.insert(rp) == 1)
            return true;
        return false;
    }

    /**
     *  删除红包
     * @param redp  红包对象
     * @return  返回删除结果
     */
    @Override
    public boolean delete(RedPackets redp) {
        int delete = mapper.delete(redp);
        if(delete>0){
            return true;
        }
        return false;
    }

    /**
     * 查询当前用户的所有红包信息
     * @param accountId 用户accountID
     * @return  当前用户的所有红包信息
     */


    /**
     * 查询用户红包
     * @param accountId 用户id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectUserAll(Long accountId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<RedPackets> list = mapper.selectUserAll(accountId);
        for (RedPackets r : list){
            Map<String,Object> map = new HashMap<>();
            map.put("redId",r.getId());//获取红包ID
            map.put("name",r.getName());//获取红包名
            map.put("userAccountId",r.getUserAccountId());//获取用户ID
            map.put("validDate",r.getValidDate());//获取生效时间
            map.put("overdueDate",r.getOverdueDate());//获取失效时间
            map.put("usePrice",r.getUsePrice());//获取使用泛围
            map.put("worth",r.getWorth());//获取红包大小
            map.put("useState",r.getUseState());//获取红包状态（1.已使用，0.未使用）
            if(useDate(r)){
                map.put("state",0);//是否可使用？0：可使用
            }
            else{
                map.put("state",1);//是否可使用？1：不可使用
            }
            mapList.add(map);
        }
        return mapList;
    }
    public boolean useDate(RedPackets r){
        Date date = new Date();
        if(r.getOverdueDate().getTime()>date.getTime()||r.getValidDate().getTime()<date.getTime()){
            return false;
        }
        else{
            return true;
        }
    }

}
