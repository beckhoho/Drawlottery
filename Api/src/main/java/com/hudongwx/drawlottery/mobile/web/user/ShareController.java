package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IShareImgService;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/23 <br/>
 * @desc 用户晒单分享管理<p>
 * <p>
 * 创建　wu　2016/12/23 <br/>
 * <p>
 * **********
 * <p>
 * @email 294786949@qq.com
 */
@RestController
@Api(value = "ShareController", description = "用户晒单分享管理")
public class ShareController extends BaseController {

    @Autowired
    IShareService shareService;
    @Autowired
    IShareImgService shareImgService;

//    /**
//     * 用户添加晒单分享信息
//     *
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/api/v1/user/share/token", method = RequestMethod.POST)
//    public JSONObject uploadShareInfo(@RequestParam("suffix") String suffix) {
//        Properties p = new Properties();
//
//        //设置好账号的ACCESS_KEY和SECRET_KEY
//        String ACCESS_KEY = "F5kk6Wp3aSKV5ViXVd-hH0YZvoEeYrI_3dLx4SbQ";
//        String SECRET_KEY = "6a182dZ4k2fl9DvJj4iLOeJlhgwKdopPPhjsk6oi";
//        //要上传的空间
//        String bucketname = "Bucket_Name";
//        //上传到七牛后保存的文件名
//        String key = "my-java.png";
//        //上传文件的路径
//        String FilePath = "/.../...";
//
//        //密钥配置
//        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
//
//        ///////////////////////指定上传的Zone的信息//////////////////
//        //第一种方式: 指定具体的要上传的zone
//        //注：该具体指定的方式和以下自动识别的方式选择其一即可
//        //要上传的空间(bucket)的存储区域为华东时
//        // Zone z = Zone.zone0();
//        //要上传的空间(bucket)的存储区域为华北时
//        // Zone z = Zone.zone1();
//        //要上传的空间(bucket)的存储区域为华南时
//        // Zone z = Zone.zone2();
//
//        //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
//        Zone z = Zone.autoZone();
////        Configuration c = new Configuration(z);
//        return success();
//    }

    /**
     * 用户添加晒单分享信息
    *
     * @return
     */
    @ResponseBody
    @ApiOperation("用户添加晒单分享信息（tip:审核通过（state==1）方能显示）")
    @RequestMapping(value = "/api/v1/user/share/upload.do", method = RequestMethod.POST)
    public JSONObject uploadShareInfo(@RequestParam("commId") Long commId, @RequestParam("desc") String desc, @ApiParam("上传的图片集")@RequestParam("imgs") List<MultipartFile> imgs) {
        if (shareService.addShare(getUserId(), commId, desc, imgs)) {
            return success(true);
        } else {
            return fail("用户已晒过单");
        }
    }

    /**
     * 获取用户晒单信息
     *
     * @return JSONObject
     */
    @ResponseBody
    @ApiOperation("获取用户晒单信息")
    @RequestMapping(value = "/api/v1/user/share/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryShareInfoByAccount(@ApiParam("回传最后一个商品Id") @RequestParam("lastCommId") Long lastCommId) {
        List<Map<String, Object>> shareAll = shareService.selectByUserAccountId(getUserId(), lastCommId);
        return success(shareAll);
    }

    /**
     * 获取单件晒单信息
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/pub/share/comm", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryShareInfoByCommId(@RequestParam("commId") Long commId, @RequestParam("lastCommId") Long lastCommId) {
        List<Map<String, Object>> shareAll = shareService.selectByCommId(commId, lastCommId);
        return success(shareAll);
    }

    /**
     * 获取所有用户晒单信息
     *
     * @return JSONObject
     */
    @ResponseBody
    @ApiOperation("获取所有用户晒单信息(带分页)")
    @RequestMapping(value = "/api/v1/pub/share/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryAllShareInfo(@ApiParam("回传当前显示的最后一个商品id")@RequestParam("lastCommId") Long lastCommId) {
        List<Map<String, Object>> shareAll = shareService.selectAll(lastCommId);
        return success(shareAll);
    }

}
