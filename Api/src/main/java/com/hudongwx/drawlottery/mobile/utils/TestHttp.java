package com.hudongwx.drawlottery.mobile.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author wu
 * @version 1.0, 2016/12/30 <br/>
 * @desc <p>
 * <p>
 * 创建　wu　2016/12/30 <br/>
 * <p>
 * 用户收货地址
 * <p>
 * @email 294786949@qq.com
 */
public class TestHttp {

    public static void main(String[] args) throws IOException {
        testImgUpload();
//        testVerifyCode();
    }

    private static void testImgUpload() throws IOException {
        String path = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg";
        URL url = new URL("http://192.168.6.199:8080/api/v1/user/upload/headimg");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.connect();
        OutputStream os = conn.getOutputStream();
        File file = new File(path);
        if (!file.exists())
            file.getParentFile().createNewFile();
        FileInputStream fis = new FileInputStream(file);
        byte[] getData = readInputStream(fis);
        System.out.println("图片数组---------->" + getData.length);
        os.write(getData);
        os.close();
    }

    private static void testVerifyCode() throws IOException {
        URL url = new URL("http://192.168.6.199:8080/api/v1/user/register/imgcode");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = conn.getInputStream();   //通过输入流获得网站数据
        byte[] getData = readInputStream(inputStream);     //获得网站的二进制数据
        String data = new String(getData);
        System.out.println(data);
        File dir = new File("C:\\Users\\wu\\Desktop");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "aaa.jpg");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        fos.close();
    }

    public static byte[] readInputStream(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

}
