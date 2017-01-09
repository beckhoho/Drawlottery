package com.hudongwx.drawlottery.mobile.web.user;

import com.hudongwx.drawlottery.mobile.TestBaseWeb;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.FileInputStream;

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
//        Share share = new Share();
//        share.setIssueDate(new Date());
//        share.setCommodityId(1l);
//        share.setParticulars("这是我写的晒单详情！！！！");
//        share.setCommodityId(10l);
//        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
//        File f = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
//        MockMultipartFile multipartFile = new MockMultipartFile("file", fis);
//        String url = "/api/v1/user/share/add";
//        JSONObject json = new JSONObject();
//        json.put("share", share);
//        json.put("multipartFile", multipartFile);
//        /*mvc.perform(MockMvcRequestBuilders.fileUpload(url)
//                .file(multipartFile)).andExpect(status().isOk());*/
//        mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
//                .content(json.toJSONString())).andExpect(status().isOk());

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest() ;
        String strTEAMPHOTOCLASSID = "strTEAMPHOTOCLASSID";
        String strSPECIFICATIONID = "SPECIFICATIONID";
        final FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        MockMultipartFile multipartFile = new MockMultipartFile("photopath","Tulips.jpg","image/jpeg",fis);
        request.addFile(multipartFile);
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");
        request.setRequestURI("http://192.168.6.199:8080/api/v1/user/share/uploadTest");
        request.addParameter( "share", "lzj update" ) ;
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