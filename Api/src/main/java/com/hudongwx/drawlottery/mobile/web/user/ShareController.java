package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.service.user.IShareImgService;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
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

    String fileSavePath = "C:\\Users\\wu\\IdeaProjects\\DrawLottery\\Api\\src\\main\\resources\\static\\imgs\\shareimg";

    /**
     * 用户添加晒单分享信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/upload.do", method = RequestMethod.POST)
    public JSONObject uploadShareInfo(@RequestParam("commId") Long commId, @RequestParam("desc") String desc, @RequestParam("imgs") List<MultipartFile> imgs) {
        System.out.println("share---------->" + commId);
        System.out.println("share---------->" + desc);
        System.out.println("imgFile---------->" + imgs.size());
        shareService.addShare(10000L, commId, desc, imgs);
        return success();
    }

    /**
     * 获取用户晒单列表信息
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/show", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject queryShareInfo(@RequestParam("page") int page) {
        List<Map<String, Object>> shareAll = shareService.selectUserAll(2L);
        return success(shareAll);
    }

    /**
     * 用户添加晒单分享信息
     *
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/upload/test", method = RequestMethod.POST)
    public JSONObject shareInfo(@RequestParam("commId") Long commId, @RequestParam("desc") String desc, @RequestParam("imgs") List<MultipartFile> imgs) {
        System.out.println("share---------->" + commId);
        System.out.println("share---------->" + desc);
        System.out.println("imgFile---------->" + imgs.size());
        return success();
    }
}
