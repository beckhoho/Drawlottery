package com.hudongwx.drawlottery.mobile.web.test;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
//@Api(value = "HelloController",description = "首页所有Api",position = 0)
//@RestController
public class HelloController {

    @ApiOperation(value = "显示首页",notes = "显示首页notes")
    @RequestMapping("/hello")
    public String index(){
        return  "spring boot 成功啦!!";
    }


    @ApiOperation(value = "显示名字",notes = "打印用户传递过来的名字")
//    @ApiImplicitParam(name = "name",value = "显示名字1",required=true,dataType = "String")
    @RequestMapping("/hello2")
    public String index2(@ApiParam(required = true, value = "名字",name = "name") @RequestBody String name) throws Exception {
        if(1==1){
            throw new Exception("错误测试");
        }
        return "你好:"+name;
    }

    //需要重新编译文件 ctl+shift+f9
}
