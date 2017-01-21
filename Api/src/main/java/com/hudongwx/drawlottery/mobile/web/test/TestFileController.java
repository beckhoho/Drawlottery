package com.hudongwx.drawlottery.mobile.web.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件上传测试
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/11 0011 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/11 0011　<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
public class TestFileController extends BaseController {

    @RequestMapping("/upload.do")
    public JSONObject doUpload(@RequestParam("name")String name, @RequestParam("file")List<MultipartFile> f){

            System.out.println("========================");
            System.out.println(name);

        System.out.println(f.size());
//        f.get(0).transferTo(new File(""));

       /* System.out.println(file1.size());
        if(file1 !=  null){
            for(int i=0;i<file1.size();i++){
                System.out.println(file1.get(i).getName());
            }
        }*/

        System.out.println("========================");
        return null;
    }



    /**
     * 测试缓存
     *
     * @param userNickName
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/cache/test", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject updateCache(@RequestParam("nickname") String userNickName) {
        // TODO: 2017/1/12  测试缓存
        System.out.println();
        return response(true);
    }
    @RequestMapping("/html5.do")
    public JSONObject html5(){
        JSONArray array = new JSONArray();
        for(int i=0;i<300;i++){
            JSONObject object = new JSONObject();
            object.put("id",i);
            object.put("name","消息通知"+i);
            array.add(object);
        }
        JSONObject object = new JSONObject();
        object.put("code",200);
        object.put("msg","成功");
        object.put("data",array);
        System.out.println(object);
        return object;
    }
}
