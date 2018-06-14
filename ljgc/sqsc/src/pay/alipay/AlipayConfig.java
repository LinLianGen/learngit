package pay.alipay;

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

public class AlipayConfig {

	public static String APP_ID = "2017120200315182";// 商户支付宝账号
		
	public static String seller_id = "";// 商户支付宝账号

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String partner = "2088821423299925";

	// 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDkIEwWXYTg5O/YmvSSaTylonZ6UYzP/bRuzcwRyC7iHPuhqQmjj6jLMN6Npwf2hcmczWHGLbNm1gYrcRsQDOjeLnqprzGLkPeJ/POyxnnHneRQsY1yv7T+fhNERWm3ypUb/z4VXhOy/PXZoqd9J68K1zLyEDJCgXho/j5vcpypHGZDbIs7J7StjD2X3Ke/dERpQgp3ADx5EaF4+pKVGPzUJMh6x8qa0OHvpKZMV+bgYhwU91bue4DWvyD0SB7kSSDmiQRHLJdLTrjG2ccOfiUgN/EpBn2ikQ4fbQ5jmVoYefLarvFvi0zjxJWGSCudFkZp0SNNeXzHCIjr06TpzJdvAgMBAAECggEBAJaBkHF5Bb/yiyMXKKCn+bE1vID66LIYqkAoWqior/XSNVn00j0s/KoB9fLy7lgA8IEZJ5a+dAw2zB9BIh+2xLRBagAla8SuwgIx9v5N3+6JBycN3UqFnH4C4+c4/ISbjUDJZ0ZlqQ1/wxvxKhtyYRP/4sa9VMt3698pTjvtG9qdeRmA12ZXgBbNh9Iwdz7kXjjo6exZ5P4LJ+MN9z86Vkttp/EoqqUGOt7w9jYod3mK3QodZnKonUUL9rIKdsz7GQfuxajo5ed+YpcTlJ9/1QsRzHREQNCGFDhAJhfWea2rHRHP0kwRVVcYdEERMw/3yZs1CwAWt0coXqXGB67iH0ECgYEA9TPZLuZ6x1pyLneQpUHRuiRI16ffc59U//ZECaGiD6TgWrnqjvDdFo9sk092+uTHjf7OLRg+B8mzlM0kGY1Lrox2b/7Oh3rTNmuzQLmaoGhnP3PBcerGJcb0kkn1varLKGujaysYIs/VNpPM23SBlJpQAgGuJoJWDPMf9YGDbn8CgYEA7ivys9sL+ceu19EETSSZ+N9qIBSONA0pzzGQhaz8nn/ra535yK90EsXU1guO1tJ27rsGyM7MI47L15IrKADC5AYF8mNyC5i1JBbuU7oMJKernA/B/X3lAHDisEYKhx/2dmzWVQT+aUv4TZ06Ab/cq1nhLLprLSrfhLWp3CmfPxECgYEArq4hKqhHD33/mdrT0Oby4gi3W+rb5SK9CxwFnFbpfPuYWQnZb7tnxYqnbspNmK8i+qVre/SpPCMVLAx4izt9jvvB5EdCGoIFtd9D8/hFj8DQzQNJ7D20q3TxBsZpqpTh5gnIgbBRoXsuCZOBfVWiepr0JHnN9uEjLFaHjf6WA0ECgYB6IhFAR7iZattvEmEZSganB7I9Jy1Q5dslw63vzqhwgvpvYkA/uDFWmxosdRrXcG/Kth+hg+JM7aH8Nplo1xMZqTkNdg96gOGK0zN9ve8SMOUgjzgUTKXNFxY3/ILTjgFt8xvp1zi7OFjpPBIN6Zw4RkvhCuuA6vZZ8GpdWv6tcQKBgFEyeLWJ5YEeBgvNuRiRMnwXzPhYhBARJhpaBGz2+2KPeE3r0qWWceUPlG00WjlibmOD7VXBhIthw9UnAVDb+rGN5IsUAvEoqzC+AmUBCu2S5btYLV6/yPVlMYx23s4TqMbio9FuCXu75K0kAt93BGsm6IqpAWng7xKXHoa2ex/B";

	// 支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAodKVfd90vZtgyOOr0MHemaNhF/fA1Cmczi+g5BCye4e7UOBitrA68yPub4Evgz2Vz1i/rds9CWcJvqldhHYddFyFE5PUP1UBgIpT3YJWl3IBjwbkY9Q3NY+rx1NHMDZix6Waaoe/ylbjzODU+eKsZuvfk21Q6SRHClU2HF3zvQVZMYlGQF90QtYbTHaZx1k6hdXkFnD5/Gz4NRwfWuvBjyMRvgV+fgrOw4uzt6xXMaImb/ppCimKQJmPGbDY6H8eWZoS/zQDlAFXVClmrXw4TzT2fM+jIyV6MMqnnU3Uaj2X6V18vmcfnYjAF6yGqVTbDb7XMbufgaa8Au5f17m9sQIDAQAB";

	// md5秘钥
	public static String Md5_key = "ts38y9wq09r270v0wr34kulz0i5ja3i2";

	// 签名方式
	public static String sign_type = "RSA";

	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "D://";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 接收通知的接口名
	public static String service = "mobile.securitypay.pay";
	
	// 商户的私钥
	public static String key = "ts38y9wq09r270v0wr34kulz0i5ja3i2";

	
	// 回调地址
	public static String notify_URL = "api/aliPay/alipayCallback";

}
