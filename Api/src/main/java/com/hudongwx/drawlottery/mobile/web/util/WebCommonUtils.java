package com.hudongwx.drawlottery.mobile.web.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/23 0016 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/23 0016　<br/>
 * <p>
 * controller公用工具
 * <p>
 * @email 294786949@qq.com
 */
public class WebCommonUtils {

    /**
     * 创建提示性响应的json对象
     *
     * @param status 事件处理状态
     * @param successMsg 操作成功的提示信息
     * @param failedMsg 操作失败的提示信息
     * @return JSONObject
     */
    public static JSONObject buildStatusJsonObj(boolean status, String successMsg, String failedMsg) {
        JSONObject jsonObject = new JSONObject();
        if (status) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", successMsg);
        } else {
            jsonObject.put("code", -1);
            jsonObject.put("msg", failedMsg);
        }
        return jsonObject;
    }

    /**
     * 创建带数据的json对象
     *
     * @param list 数据集合
     * @param <T> 泛型
     * @return JSONObject
     */
    public static <T extends Object> JSONObject buildDataJsonObj(List<T> list) {
        JSONObject jsonObject = new JSONObject();
        if (null != list && list.size() != 0) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "");
            jsonObject.put("data", list);
        } else {
            jsonObject.put("code", -1);
            jsonObject.put("msg", "未找到相关信息！");
            jsonObject.put("data", null);
        }
        return jsonObject;
    }

    //    public static Map<String, Integer> dealPageParameter(Integer num, Integer nowPage) {
//        Map<String, Integer> seMap = new HashMap<>();
//        if (null == num || num < 0)
//            num = 0;
//        int maxPage = (int) Math.ceil(num * 1.0 / Common.MAX_INFO_SUM);
//        if (null == nowPage || nowPage <= 0) {
//            nowPage = 1;
//        } else if (nowPage >= maxPage) {
//            nowPage = maxPage;
//        }
//        int startNum = (nowPage - 1) * Common.MAX_INFO_SUM;
//        int endNum = Common.MAX_INFO_SUM;
//        seMap.put("startNum", startNum);
//        seMap.put("endNum", endNum);
//        seMap.put("maxPage", maxPage);
//        return seMap;
//    }

}
