package pay.wxpay.web.services;

public class ConfigUtil {
	//微信公众平台
	public static final String APPID = "wx750c4c4322ee34d6";//AppID(应用ID)
	public static final String APP_SECRECT = "c3fe248358d387cf26739bde7a75310b";//AppID(应用ID)
	public static final String TOKEN = "";
	public static final String MCH_ID = "1494087802";//商户号
	public static final String API_KEY = "kd9JeckYB5qbCl7R1aX84IdBvOSasPZX";//商户秘钥
	public static final String SIGN_TYPE = "MD5";
	public static final String CERT_PATH = "D:/apiclient_cert.p12";
	public static final String NOTIFY_URL = "http://mzy.com28.cn/servlet/WeixinpayPCServlet";//异步回调地址公众号支付和PC扫码支付
	public static final String SUCCESS_URL = "";//同步返回地址
	public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static final String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	public static final String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	public static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	public static final String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	public static final String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	public static final String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	public static final String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
	public static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
}