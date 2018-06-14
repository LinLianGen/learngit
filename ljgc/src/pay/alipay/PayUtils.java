package pay.alipay;


import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import controller.admin.wxback.ClientCustomSSL;
import pay.wxpay.app.util.ConstantUtil;
import pay.wxpay.app.util.Sign;
import pay.wxpay.web.services.CommonUtil;
import pay.wxpay.web.services.ConfigUtil;
import pay.wxpay.web.services.PayCommonUtil;
import pay.wxpay.web.services.XMLUtil;
import utils.linUtils.Utils;
import utils.project.IP4;

public  class PayUtils {

	
	/**
	 * 支付宝支付
	 * @param orderInfo 订单情况  
	 */
	// 当学路上返回 return  "charset=utf-8&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%2288.00%22%2C%22subject%22%3A%22%E6%94%BE%E5%AD%A6%E8%B7%AF%E4%B8%8A%E6%B6%88%E8%B4%B9%E5%B9%B3%E5%8F%B01%22%2C%22body%22%3A%22%E6%94%BE%E5%AD%A6%E8%B7%AF%E4%B8%8AAPP%E6%B6%88%E8%B4%B9%22%2C%22out_trade_no%22%3A%22541d5w1d54sw4d5%22%2C%22notify_url%22%3A%22http%3A%2F%2F192.168.1.99%3A8080%2Ftongmj%2Fdata%2FalipayCallback%22%7D&method=alipay.trade.app.pay&app_id=2017111509950584&sign_type=RSA2&version=1.0&timestamp=2017-12-17+17%3A18%3A39&sign=WWXIyVeGl5KoJkZGmtQeacfoK4zf%2BVYlcGCWOy%2FzR5wR6UOge02BYcWJ6%2FM3Ha8GdsofnyHRRsI%2BSSDTMbLsqZRgTUvuUUZKe4bAMIMFX7nnIUikRf99S54JtXe3piXq5%2Be6z3adRA3wCD4NjPRRABDUWnGuC0C1JWws8fFGuqDYPADWjkqgt4Wqhz6T9JifUSnhwn1U3rnV09WgXnDM2ZyS0WtgDU84c0uMwDt%2BTKYJ2zRJRxmLaxHD8ZJG36491T8KLJNRxwpAnLKaQeQ5edAVit9jorKanXjaxWUdDxNVG8gsAKDyP3oQu4dVtSoCbOIRNFa2kxwnsmRRlwzVXg%3D%3D";
	public static String doAlipay(JSONObject orderInfo,HttpServletRequest request){
		System.out.println(orderInfo.get("orderId") +"," + orderInfo.getByteValue("totalStr"));
		String private_rsa = AlipayConfig.private_key;
		String yzfString = "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\""+orderInfo.get("totalStr") +"\",\"subject\":\"驿家家超市购物付费\",\"body\":\"驿家家超市购物付费\",\"out_trade_no\":\"" + orderInfo.get("orderId") +  "\",\"notify_url\":\"" + Utils.getLoaclURL(request)+ AlipayConfig.notify_URL+  "\"}";
		boolean rsa2 = (private_rsa.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(AlipayConfig.APP_ID, rsa2,yzfString);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, private_rsa, rsa2);
		System.out.println("PayUtils:"+orderParam + "&" + sign );
        return  orderParam + "&" + sign;
	}
	/**
	 * 微信支付
	 * @param orderInfo 订单情况
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String doWeixinPay(JSONObject orderInfo,HttpServletRequest request){
		String totalStr="0.0";
		totalStr = String.valueOf((int) (Double.parseDouble(orderInfo.getString("totalStr")) * 100));
		SortedMap parameters = new TreeMap();
		parameters.put("appid", ConstantUtil.APP_ID);
		parameters.put("mch_id", ConstantUtil.PARTNER);
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
		parameters.put("body", "驿家家超市购物付费");
		parameters.put("out_trade_no", orderInfo.getString("orderId"));
		parameters.put("total_fee", totalStr);
		parameters.put("spbill_create_ip", IP4.getIpAddr(request));
		parameters.put("notify_url", Utils.getLoaclURL(request)
			+ ConstantUtil.NOYIFY_URL);
		parameters.put("trade_type", "APP");
		String sign = Sign.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		System.out.println("签名XML:"+requestXML);
		String result = CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL,
			"POST", requestXML);
		System.out.println("result:"+result);
		Map<String, String> map = new Hashtable<String, String>();
		try {
		    map = XMLUtil.doXMLParse(result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		SortedMap params = new TreeMap();
		String noncestr = PayCommonUtil.CreateNoncestr();
		String timestamp = Long.toString((new Date().getTime()) / 1000);
		String prepay_id = (String) map.get("prepay_id");
		params.put("appid", ConstantUtil.APP_ID);
		params.put("noncestr", noncestr);
		params.put("package", "Sign=WXPay");
		params.put("partnerid", ConstantUtil.PARTNER);
		params.put("prepayid", prepay_id); // 存订单中
		params.put("timestamp", timestamp);// 存订单中
		String paySign = Sign.createSign("UTF-8", params);
		JSONObject payDataObj = new JSONObject();
		payDataObj.put("appid", ConstantUtil.APP_ID);
		payDataObj.put("noncestr", noncestr);
		payDataObj.put("package", "Sign=WXPay");
		payDataObj.put("partnerid", ConstantUtil.PARTNER);
		payDataObj.put("prepayid", prepay_id);
		payDataObj.put("timestamp", timestamp);
		payDataObj.put("sign", paySign);// 存订单中
		payDataObj.put("wxappid", ConstantUtil.APP_ID);
		payDataObj.put("wxappsecret", ConstantUtil.APP_SECRET);		
		return  Utils.toJson(payDataObj);
	}
	
	
	/**
	 * 微信退款
	 * @param orderInfo 订单情况
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean  WXbackMoney(JSONObject orderInfo,HttpServletRequest request){
		boolean flag=false;
		String totalStr="0.0";
		totalStr = String.valueOf((int) (Double.parseDouble(orderInfo.getString("totalStr")) * 100));
		String refund_price = "0.0";
		totalStr = String.valueOf((int) (Double.parseDouble(orderInfo.getString("refund_price")) * 100));
		SortedMap parameters = new TreeMap();
		parameters.put("appid", ConstantUtil.APP_ID);
		parameters.put("mch_id", ConstantUtil.PARTNER);
		parameters.put("out_trade_no", orderInfo.getString("orderId"));
		parameters.put("out_refund_no", orderInfo.getString("refundId"));
		parameters.put("total_fee", totalStr);
		parameters.put("refund_fee", totalStr);
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
		String sign = Sign.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		System.out.println("签名XML:"+requestXML);
		try {
			//调用微信退款demo 将数据传入
			flag =  ClientCustomSSL.start(requestXML , orderInfo.getString("orderId"));
			//ClientCustomSSL.start(params);
		} catch (Exception e) {
		}
		return flag;
	}
	
	
	/**
	* @方法名: 支付宝退款
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年1月10 1:43:41
	* @修改:
	*/ 
	public static boolean doAliCallBack(JSONObject orderInfo){
		//支付宝公钥
		boolean flag = false;
		
		String userId = orderInfo.getString("userId");
		
		String ali_Public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnozYupfuQDLTz2930pF1EIbAdgIFRi1HJt6VfGI14datWSM0jus4du+E+wsOliywN219Tvnwt3Y7pRZfPR4ue6WsdHzmITm3ekfxrSJD6ORGbaERJeeDnue5zbexY4R6vzLaifM5s2ja3aB4Zx9Uc/gPPmf2pIZFm+2PPEl7umuMaH2awDaREQCd6hD1rd8oraDW5EqlwiuGuNPwfNmN6UerTrQIgHq0jzO2tKbfBcyHPo4zQEMKLX7zKbV/W50EGWN9ZjxD2Ql+F8WAJnhT7MFL7rdssIQfX6FTOcpMEnTLMnq5S9z3K199/A+Kqappt87IlGJYhohwrAl0I8yOWQIDAQAB";

		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.APP_ID,AlipayConfig.private_key,"json","UTF-8",ali_Public_key,"RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" +
        "\"out_trade_no\":\""+orderInfo.getString("out_trade_no")+"\"," +
        "\"refund_amount\":\""+orderInfo.getString("refund_amount")+"\"," +
        "\"refund_reason\":\"正常押金退还\"" +
        "  }");
        
        AlipayTradeRefundResponse response;
       /* System.out.println("biz_content="+request.getBizContent());*/
		try {
			response = alipayClient.execute(request);
        /*if(response.isSuccess()){
        	System.out.println("调用成功");
        	UserRecord ur = new UserRecord();
        	String orderId = orderInfo.getString("out_trade_no");
        	try {
        		ur.letMyBackMoney(orderId , "2");				
			} catch (Exception e) {
				// TODO: handle exception
			}
		    Map<String, String> map =  response.getParams();
		    System.out.println("map:"+map.toString());
		    flag=true;
        } else {
        	System.out.println("调用失败");
        	UserRecord ur = new UserRecord();
        	String orderId = orderInfo.getString("out_trade_no");
        	ur.letMyBackMoney(orderId , "3");	
        	}*/
		} catch (AlipayApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return flag;
	}
	
}
