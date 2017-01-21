package com.hudongwx.drawlottery.mobile.web.test;

import com.hudongwx.drawlottery.mobile.entitys.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

//@Api(value = "IndexController",description = "首页功能",position = 1)
//@RestController
public class IndexController {
    @ApiOperation(value = "index",notes = "显示首页",httpMethod = "POST",response = User.class)
    @ApiImplicitParams(@ApiImplicitParam(name=""))
    @RequestMapping("/index")
    public String index(){
        return  "ok1";
    }

    @ApiOperation(value = "index2",notes = "显示首页2",httpMethod = "POST",response = User.class)
    @ApiImplicitParams(
            {
                @ApiImplicitParam(name="header1",value = "头信息",paramType = "header",dataType = "string"),
                @ApiImplicitParam(name = "query1" ,value = "查询参数",paramType = "query",dataType = "string"),
                @ApiImplicitParam(name = "id",value = "查询id",paramType = "path",dataType = "字符串")
            }
    )
    @RequestMapping("/index2/{id}")
    public String index2(@RequestHeader(name = "header1")String header1,
                          @RequestParam("query1")String query,
                          @PathVariable(name = "id")String id
    ){
        return  "ok1"+"头部:"+header1+",参数:"+query+",path:"+id;
    }

    @ApiOperation(value = "测试接口3", notes = "参数描述", code = 200, produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 500,message = "内部错误",responseContainer = "{code:-1,msg:''}"),
            @ApiResponse(code = 200,message = "请求成功",response = User.class,responseContainer = "{code:200,msg:'ok'}")
    })
    @RequestMapping("/index3/{id}")
    public String index3(@ApiParam(name = "id", value = "编号", required = true) @PathVariable("id") String id){
        return id;
    }


    @ApiOperation(value = "测试接口4", notes = "参数描述",response = User.class, code = 200, produces = "application/json")
    @RequestMapping("/index4/{id}")
    public String index4(@ApiParam(name = "id", value = "编号", required = true) @PathVariable("id") String id){
        return id;
    }

    @ApiOperation(value = "测试接口5", notes = "实体参数",response = User.class, code = 200, produces = "application/json")
    @RequestMapping("/index5")
    public String index4(@RequestBody  User user){
        return "ok";
    }

}
