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

import com.hudongwx.drawlottery.mobile.utils.payutils.UtilDate;

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String partner = "2088221564947196";

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    public static String seller_id = partner;

    public static String private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCT7lS5C9gGKwb+0iPjv5fYn8kX/df8+XgJvYhsK0XGyvCZEPykXVnVs1gAfsR6wGfpcrwaYFUrZJ/Qj9XEIZ274hI2bUzwIWmf12vKtN9Zd0dEH7WWKcepaZLBVL7B32Qeyn1LQaZpMIP1FEmjpnzB0r3rv8vRdWxQuWo26WaaDO1D4H7fys6y0heFMixkYUyeOfpwZrZQB//1ba8zODTda8a6NsGO9sJxI4vT/IvvYMgQRnKwAUFOpaqeKmT/UOetL4TS688eY6X1Cd1M5j5TjBpS+ZOpQojkBsgCRbibOX1OOtAzzbSNs9wQn3MsESwKqFgUdeSA88gkWRX2UvgTAgMBAAECggEBAIX6FVteobC8aWKCOaT8Ee9Mj9kduu9PlWrk8FhfPJvVkuBeZSILtd/4xNv188bk2XexxTDyTFC3mpJOoqE2fN/72NjYVxUofbwZY3O8KoihobbOLSyp/D2v8w0LzpRR2SCV7bIp7ChamzhsvmDr2RPF5OJN26+BdK8Lrmm7K4SajY8tjla7uwLgPxkPVE2gbAXLMFlFSjI3BDE/mSz4kJ+WjmP3Z/oC8HDDKL8IGlEfGO7EOrRZJszOj2Snk9+RuKBLneKwEeP8v1y7OG7QSeAUCd048GUKRpnqZRCEjKITI2ShPS4hUZE4t0YvTNnusWBE6Yhk5hDdBq2e+Q6l6BECgYEAzSjnGVz/mRITKhWrkI7AoqYsQIDKYd32hLQLOqRQJ4kN6bRirdQQPeviE9+uAUFbiMqkAgZY6dBD/n8GaL7gbdxFIeQB54MaMCNwedbNvW62OaELI8mrlrwcWXszvU7DwMLtmL69dunL+HhRxr2AeIQzseo0a3dHLxGq713JtDkCgYEAuJbmaaMh8hZolKjA61qiUT2G6uZcW2IQ5tn56ySAMi3vz+itcguwLzf7GoQXS2pN2j1lXHiPA9e1Cb99lzN4yUVLBgH27FrgsNyxr/ukLZTv2d3LuAAUxGk8k+0hlMrfHWK56QBrbrl7kcXYt4ha1aDm5L3UHkMvDC0mnpPQRqsCgYAfxiP0k6iqZlg9flmx68n95sqXutqdSakyaEgM7wHS/bD/7wYRcfSKAoxjE5N5ckvg5bF89sqye6TsN3QgqN9NZH34f72jw3Q83GBBHnmCyzDBwCVfh45hrASf/yrGOPqsUgRGUdQ6JH+InencY+EPyTnQZjt0IWcO7SeSJu5kUQKBgQCnkaVd5I/eWuVZftCTV1Xe9kHSYIkbFC1PxDWSdzJBW15GmW1LCL1KQEGd0qfq+HfwVKf9iBwcWueoD8QNTNarsFHpBEOKUP2E0PQlVAW4s2oBl81mHfy8SOSLTUuc7YYaARPQJ2dfvtYkgTy5b/k10bmJkaJMTqUZIqvabUk3vQKBgQCOCAyIckjO+DZ4bft4QAmCwLrL8OZP8Igm5prXBcWNg2SDr2oeqyCmYvpD9BVaZdBaoOs5CSmO6f5ZsJy7GgkXC+3gtKY6BWoWgPN8d09vvYY5KVzfFXI7mzfZgt6fioprVoHKAZo+dk190dQLurZf/ZtzmGZ8zc9yVUtA2UpXOw==";

    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    // MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String key = "f4wwwk7vos9xe8i0k75zuaw64eeconh9";


    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://192.168.6.199:8080/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://192.168.6.199:8080/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "MD5";

    // 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
    public static String log_path = "C:\\Users\\wu\\IdeaProjects\\DrawLottery\\Api\\src\\main\\resources\\alipaylog";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    //退款日期 时间格式 yyyy-MM-dd HH:mm:ss
    public static String refund_date = UtilDate.getDateFormatter();

    // 调用的接口名，无需修改
//	public static String service = "refund_fastpay_by_platform_pwd";

    // 调用的接口名，无需修改
    public static String service = "http://192.168.6.199:8080/api/v1/pub/orders/callbacks.do";

    // 调用的接口名，无需修改
    public static String app_id = "2016120703992842";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

