package com.hudongwx.drawlottery.mobile.utils;

import com.hudongwx.drawlottery.mobile.entitys.LuckCodes;
import com.hudongwx.drawlottery.mobile.mappers.CommoditysMapper;
import com.hudongwx.drawlottery.mobile.mappers.LuckCodesMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/29 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/29 <br/>
 * <p>
 * service工具类
 * <p>
 * @email 294786949@qq.com
 */
public class ServiceUtils {
    /**
     * 将集合处理成分页数据
     *
     * @param list 直接从数据库的获取到的list集合
     * @param page 查询的当前page
     * @param <T>  泛型
     * @return
     */
    public static <T extends Object> List<T> getPageList(List<T> list, Integer page) {
        List<T> newList = new ArrayList<>();
        if (null == page || page <= 0)
            page = 1;
        int startPosition = Settings.PAGE_LOAD_SIZE * (page - 1);
        int endPosition = Settings.PAGE_LOAD_SIZE + startPosition;
        int maxPosition = endPosition >= list.size() ? list.size() : endPosition;
        for (int i = startPosition; i < maxPosition; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

    public static void createLuckCode(LuckCodesMapper mapper, CommoditysMapper commMapper, Long commId) {
        //期数+总购买人次（随机）201707101532000001
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
        int total= commMapper.selectByPrimaryKey(commId).getBuyTotalNumber();
        for (int i = 0; i < total; i++) {
                LuckCodes lc=new LuckCodes();
                lc.setCommodityId(commId);
            int tl = String.valueOf(total).length();
            int il = String.valueOf(i).length();
            StringBuffer s=new StringBuffer();
            for(int j=0;j<tl-il;i++){
                s.append("0");
            }
            s.append(i);
            lc.setLockCode(sdf.format(date)+s);
                mapper.insert(lc);
        }
    }

//    private static int[] createCodeArray(int total) {
//        int startArray[] = {0,1,2,3,4,5,6,7,8,9};//seed array
//        int N = total;//随机数个数
//        int resultArray[] = new int [total];//结果存放在里面
//        for(int i = 0; i < N; i++)
//        {
//            int seed = random(0, startArray.length - i);//从剩下的随机数里生成
//            resultArray[i] = startArray[seed];//赋值给结果数组
//            startArray[seed] = startArray[startArray.length - i - 1];//把随机数产生过的位置替换为未被选中的值。
//        }
//        return resultArray;
//    }
}
