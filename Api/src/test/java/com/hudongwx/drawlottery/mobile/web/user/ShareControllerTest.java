package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSONObject;
import com.hudongwx.drawlottery.mobile.TestBaseMapper;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.*;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author Kiter
 * @version 1.0, 2017/1/4 <br/>
 * @desc <p>
 * <p>
 * 创建　kiter　2017/1/4 19:15　<br/>
 * <p>
 * 什么类？
 * <p>
 * @email 346905702@qq.com
 */
public class ShareControllerTest extends TestBaseWeb {

    @Test
    public void testAddShareInfo() throws Exception {
        Share share = new Share();
        share.setIssueDate(new Date());
        share.setUserAccountId(1l);
        share.setParticulars("这是我写的晒单详情！！！！");
        share.setCommodityId(10l);
        FileInputStream fis = new FileInputStream("C:\\Users\\hdwx\\Desktop\\4-15012G52133.jpg");
        File f = new File("C:\\Users\\hdwx\\Desktop\\4-15012G52133.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);
        String url = "/api/v1/user/share/add";
        JSONObject json = new JSONObject();
        json.put("share",share);
        json.put("multipartFile",multipartFile);
        /*mvc.perform(MockMvcRequestBuilders.fileUpload(url)
                .file(multipartFile)).andExpect(status().isOk());*/
        mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
        .content(json.toJSONString())).andExpect(status().isOk());
    }

    @Test
    public void testQueryShareInfo() throws Exception {

    }

    @Test
    public void testAddShareImg() throws Exception {

    }
@Resource
    private ShareController shareController;
    @Override
    public Object getController() {
        return shareController;
    }
}