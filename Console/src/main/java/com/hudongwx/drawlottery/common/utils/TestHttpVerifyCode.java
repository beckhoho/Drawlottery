package com.hudongwx.drawlottery.common.utils;

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
public class TestHttpVerifyCode {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://192.168.6.199:8080/api/v1/user/register/code");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = conn.getInputStream();   //通过输入流获得网站数据
        byte[] getData = readInputStream(inputStream);     //获得网站的二进制数据
        String data = new String(getData, "utf-8");
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

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
