package com.hudongwx.drawlottery.mobile.web.test;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Arrays;
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
@RestController
public class TestFileController extends BaseController {

    @RequestMapping("/upload.do")
    public JSONObject doUpload(@RequestParam("name")String name, @RequestParam("file")List<MultipartFile> f){

            System.out.println("========================");
            System.out.println(name);

        System.out.println(f.size());

       /* System.out.println(file1.size());
        if(file1 !=  null){
            for(int i=0;i<file1.size();i++){
                System.out.println(file1.get(i).getName());
            }
        }*/

        System.out.println("========================");
        return null;
    }

}
