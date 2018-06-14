package pay.alipay.wap.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */
public class AlipayConfig {
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088421404504253";// pay@xiaheng.net

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串
	public static String seller_id = partner;

	// 商户的私钥 (PKCS8格式的)
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJpW9WvSVR0uoku1jqAfPFLlGG5AQF6GMKeZZ4Yjn2Cy66I6884pXqBzyV2LkOIgRYqM1PqU2tJMTS2x/PrzUcNIPjhgSljC97tdz474lp4VmLqVP3q7+14pklwoK2FZFjyHsrzuOXpz6HsBHcxSO2OZxhdXkIDF5r2yw/dTjh7vAgMBAAECgYAZJ/dUnbHV0dYONQeMPcsdT84NmldjkMe5aZ2gFYpSvpb9mFJI1iFgUb8OikMROGVdUdsdBMot4NXrjslHNgWrZ/wVQ0g3w/gmcKqSjkMSDxiVKnZuWtcB7xzy11/Vecqr9aDMVJmXMvAzMA0TkgMOoB5ATmeAHlnzLTL126a7MQJBAMiWIynvf5FuazJaEu14kSwek+mLj4YG8sg5neYN/rT4gjq+VLcqOSA0A7yK2zKSPDj5ea/wMktQB5qpL8MoISsCQQDE+iiwBDY5KNFrnUk3aihG1ODbpMDC4zGSjP07Y2RsaqnB1yuMeYXznhQ8d6FXGhy1kYGx++PufAZmLluoD+9NAkEAx3tIjTP5khBV20Vo/XGiLlZakzCQE6JlYGqsdVYFsMRT/0e5yE7dcQFJlgAh7jEwYxj3w9kEq5FzfoQzU8pc8QJARNcIkTtnl+JkDjAR/oPjP532V8zkcTflg0oy1iaOWjWMzmnclmOCJPoCKH/GqyI9Fleb9BAMQLSnp95bugiXoQJBAJETXYp0wIyY4JwKoBXq0MYFvpFG23Db+W/B/LA64TyHXzP1xe35MYmgvykn6o61It6Rt3l2GEipGgN9MHFf6n4=";

	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";
}
