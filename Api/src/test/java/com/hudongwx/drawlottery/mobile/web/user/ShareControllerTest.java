package com.hudongwx.drawlottery.mobile.web.user;

import com.alibaba.fastjson.JSON;
import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import com.hudongwx.drawlottery.mobile.entitys.ShareFormData;
import com.hudongwx.drawlottery.mobile.entitys.Share;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testShareInfo() throws Exception {
//        String url = "/api/v1/user/share/upload/test";
//        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
//        Share share = new Share();
//        share.setCommodityId(4L);
//        share.setParticulars("aefawe");
//        List<MultipartFile> list = new ArrayList<>();
//        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);
//        list.add(multipartFile);
//        ShareFormData sfd = new ShareFormData();
//        sfd.setShare(share);
//        sfd.setImgFileList(list);
//        post(url, JSON.toJSONString(sfd));

        /***************************************************************/
        String url = "/api/v1/user/share/upload/test";
        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);
        Share share = new Share();
        share.setParticulars("awerWAef");
        post(url,"");
    }

    @Test
    public void testAddShareInfo() throws Exception {
        String url = "/api/v1/user/share/upload.do";
        ShareFormData fd = new ShareFormData();
        Share share = new Share();
        share.setIssueDate(new Date().getTime());
        share.setCommodityId(1l);
        share.setParticulars("这是我写的晒单详情！！！！");
        List<MultipartFile> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);
        System.out.println("multipartFile---------->"+multipartFile.getOriginalFilename());
        list.add(multipartFile);
        fd.setShare(share);
        fd.setImgFileList(list);
        /*mvc.perform(MockMvcRequestBuilders.fileUpload(url)
                .file(multipartFile)).andExpect(status().isOk());*/
        mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(fd))).andExpect(status().isOk());
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