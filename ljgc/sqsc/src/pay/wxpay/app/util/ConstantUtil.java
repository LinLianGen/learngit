package pay.wxpay.app.util;
/**
* @方法名:微信支付配置信息
* @参数:
* @输出:
* @备注:
* @作者: 林
* @时间: 2017年12月14 2:34:17
* @修改:
*/ 
public class ConstantUtil {
	public static String APP_ID = "wx750c4c4322ee34d6";// 微信开发平台应用id
	public static String APP_SECRET = "c3fe248358d387cf26739bde7a75310b";// 应用对应的凭证
	public static String APP_KEY = "";// 应用对应的密钥
	public static String PARTNER = "1494087802";// 财付通商户号
	public static String PARTNER_KEY = "etiCvfbVPtaXxQFHRXBLinTrnmMQEPC3";// 商户号对应的密钥
	public static String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access_token对应的url
	public static String GRANT_TYPE = "client_credential";// 常量固定值
	public static String EXPIRE_ERRCODE = "42001";// access_token失效后请求返回的errcode
	public static String FAIL_ERRCODE = "40001";// 重复获取导致上一次获取的access_token失效,返回错误码
	public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 获取预支付id的接口url
	public static String ACCESS_TOKEN = "access_token";// access_token常量值
	public static String ERRORCODE = "errcode";// 用来判断access_token是否失效的值
	public static String SIGN_METHOD = "sha1";// 签名算法常量值
	// package常量值
	public static String packageValue = "bank_type=WX&body=%B2%E2%CA%D4&fee_type=1&input_charset=GBK&notify_url=http%3A%2F%2F127.0.0.1%3A8180%2Ftenpay_api_b2c%2FpayNotifyUrl.jsp&out_trade_no=2051571832&partner=1900000109&sign=10DA99BCB3F63EF23E4981B331B0A3EF&spbill_create_ip=127.0.0.1&time_expire=20131222091010&total_fee=1";
	public static String traceid = "testtraceid001";// 测试用户id
	public static String NOYIFY_URL = "api/weixin/weixinCallback";// 异步回调地址APP支付

}