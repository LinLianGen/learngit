package pay.wxpay.web.utils;

//jsapi_ticket是公众号用于调用微信JS接口的临时票据
public class Jsapi_ticket {
	// 调用微信JS接口的临时票据
	private String jsapi_ticket;
	// 凭证有效期，单位：秒
	private int expires_in;

	public String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
}
