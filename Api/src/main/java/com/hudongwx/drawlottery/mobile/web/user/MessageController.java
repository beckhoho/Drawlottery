package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/13 0013 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/13 0013　<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
@RequestMapping(value = "/api/v1/user/message")
@RestController
public class MessageController extends BaseController {
    /**
     * 查询所有的消息
     * @return
     */
    @RequestMapping("/count")
    public JSONObject queryAllMessage(){
        JSONArray data = new JSONArray();
       data.add(10);//系统消息
       data.add(25);//中奖消息
       data.add(0);//发货消息
       return success(data);
    }

}
