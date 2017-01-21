package com.hudongwx.drawlottery.mobile.web.pay.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：1.0
 *日期：2016-06-06
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public final class AlipayConfig {
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	//合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String partner = "2088221564947196";
//    public static String APP_ID = "2016120703992842";
	//public static String seller = "2667678829@qq.com";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAISlZt/XQ+ArcqvvkewtufpTpyafEynlSBI2IC188hcuY0qB6f2nX3pXhRqmpYumMnOD5W47ln8QxgGCaJfY+UgR+wXPpOGeDtl6IGXBKVC9xiRr1HHsES8biliGNC5ylqACpXD5BG5JnR7ZPwHGD2l8+zsdP9e2P/0QBD1XjxHfAgMBAAECgYAhDT3HBNKK5wiZwwYvPW2b7IA35WdXBySgNRBrhTilgg+Hnzt40t29lFU8QM37MBxeID1o7DwhyUItWhtJyuzCJbf03M2xh54XKmaO19mzALLCXFfIOVbm+UgYv9hHI5NrBEH6LVSKa0hGWrM2AMf8FGODEXUWO7ienpUuuHJWkQJBANVDoVz4qaCbExYm2VkXlLlbUuwpktnebA7WRXKd31nDSdBujb+8GNTaOQCelD3pywYCm/7Y/j7qDu2I1fIHmJUCQQCfOhhneIsOS4FHssu73kUuj1QRbUjW9EURq/wgdQ8ba0wK8ccsYpSawC0AxAjDJ3SSISuA+WZa+pe0v5u7K3+jAkEAnZR4YgMXC6YqRz7zcKI88W0qa3gYm0V/IMNw000V4PSs0QcvVBI/Yt/QKMYEEW9z5X/+FiPuUTyPRTLLqyeztQJALqhTZl6IQNQXOe9Gi/KByzbsp36ijXpekuMWqlwi5SH30m0us+7fJMmTZloLR0RUgE1/9fce6yHrOme7Nol0iQJBAIjMcVR7R/mt+DmvOUT8AF5JqBcPeRf+1GGQUUPgLdFtlIi+D6miu4VEhlIgrCW/PtKju9cWSNCt5Iq2CGtD2oM=";
	//支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAblfgMMzTJ6u7l3A+Dfq0zZ0lNsq1qgNU1Axse6E3EWl6qK5eKemJ00TUm72I5kfO3Aa0fkq+lkLaVAtYOydSWXBkqckCa2DyMBR2c96t5HZp2BY6Q+POkPePDqXSgHOGgtswphoCsf1hY97lw/OUHAjKCE1rBNeIR4EUqZC3OwIDAQAB";

	// 签名方式
	public static final String sign_type = "RSA";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static final String input_charset = "utf-8";
	// 接收通知的接口名
	public static final String service = "mobile.securitypay.pay";

	public static String NOTIFY_URL = "http://192.168.6.115:8080/api/v1/user/order/alipay/callback";

	//public static String log_path = "";

}

