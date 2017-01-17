package com.hudongwx.drawlottery.mobile.conf.alipay;
/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

import com.hudongwx.drawlottery.mobile.utils.alipay.UtilDate;

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String partner = "2088221564947196";

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    public static String seller_id = partner;

    //商户私钥
    public static String private_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk+5UuQvYBisG/tIj47+X2J/JF/3X/Pl4Cb2IbCtFxsrwmRD8pF1Z1bNYAH7EesBn6XK8GmBVK2Sf0I/VxCGdu+ISNm1M8CFpn9dryrTfWXdHRB+1linHqWmSwVS+wd9kHsp9S0GmaTCD9RRJo6Z8wdK967/L0XVsULlqNulmmgztQ+B+38rOstIXhTIsZGFMnjn6cGa2UAf/9W2vMzg03WvGujbBjvbCcSOL0/yL72DIEEZysAFBTqWqnipk/1DnrS+E0uvPHmOl9QndTOY+U4waUvmTqUKI5AbIAkW4mzl9TjrQM820jbPcEJ9zLBEsCqhYFHXkgPPIJFkV9lL4EwIDAQAB";

    //支付宝公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAggau0Ykitjaad5fsIuy+HEKMHF06Bw5oqS0PobOjCCRwkbqUTl22JXX1twGJxd2l+PclPlA9r8d6QsqZYVfIw/czsRZYBFKEQLm/yTaypTUI6PHaY0qTNO0wMItlNigxyVAHpJVklDPwFsbzYaENrfVByQC2LVBkBrjQiCdCX4lyr+DwSRLYvGEaeGfSlsVvQhYC8wKZWrjPkvJQJCB0vIZGsRz6+fQq5Q+L5OC//f1kcMAyI+W5h15+ShPCpMuHI5xh1ZkFK+zRTArIBj7cHggG1Oqay1ms2LzWFSxR4AwOXPvvAxlYls3LvHVk4g3w1h3DKUZP03h779EWkjXW5wIDAQAB";

    //商户安全校验码
    // MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String key = "f4wwwk7vos9xe8i0k75zuaw64eeconh9";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://192.168.6.115:8080/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://192.168.6.115:8080/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";


    // 签名方式
    public static String sign_type = "MD5";

    // 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
    public static String log_path = "d:\\alipaylog";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    //退款日期 时间格式 yyyy-MM-dd HH:mm:ss
    public static String refund_date = UtilDate.getDateFormatter();

    // 调用的接口名，无需修改
//	public static String service = "refund_fastpay_by_platform_pwd";

    // 调用的接口名，无需修改
    public static String service = "http://192.168.6.115:8080/api/v1/pub/orders/callbacks.do";

    // 调用的接口名，无需修改
    public static String APP_ID = "2016120703992842";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

