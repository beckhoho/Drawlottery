package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import com.hudongwx.drawlottery.mobile.entitys.ShareImg;
import com.hudongwx.drawlottery.mobile.service.user.IShareImgService;
import com.hudongwx.drawlottery.mobile.service.user.IShareService;
import com.hudongwx.drawlottery.mobile.utils.Settings;
import com.hudongwx.drawlottery.mobile.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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

    /**
     * 用户添加晒单分享信息
     *
     * @param share
     * @param imgFileList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/add", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addShareInfo(@RequestBody Share share, @RequestParam("imgFile") MultipartFile[] imgFileList) {
        share.setIssueDate(new Date());
        boolean status = shareService.addShare(share);
        Date date = new Date();
        for (MultipartFile m : imgFileList) {
            try {
                String fileName = "shareImg" + date.getTime() + ".png";
                byte[] bytes = m.getBytes();
                BufferedOutputStream out =
                        new BufferedOutputStream(new FileOutputStream(new File(Settings.IMG_PATH_SHARE, fileName)));
                out.write(bytes);
                out.close();
                ShareImg shareImg = new ShareImg();
                shareImg.setShareId(share.getId());
                shareImg.setShareImgUrl(fileName);
                shareImgService.addShareImg(shareImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response(status);
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
     * 获取用户晒单列表信息
     *
     * @return JSONObject
     */
    @ResponseBody
    @RequestMapping(value = "/api/v1/user/share/upload.do", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject addShareImg(@RequestParam("page") int page) {
        List<Map<String, Object>> shareAll = shareService.selectUserAll(2L);
        return success(shareAll);
    }
}
