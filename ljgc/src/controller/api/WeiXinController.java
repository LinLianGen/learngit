package controller.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.jdom.JDOMException;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import pay.wxpay.app.util.ConstantUtil;
import pay.wxpay.web.services.XMLUtil;
@RouteViewPath("api")
public class WeiXinController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/weixin";
	}

	 @SuppressWarnings({ "unchecked", "unused" })
	    public void weixinCallback() {
		System.out.println("in_weixinCallback___________________________");
		try {
		    String out_trade_no = "";// 订单编号
		    String total_fee = "";// 总金额(单位：分)
		    String openid = "";// 微信用户在商户appid下的唯一标识
		    String appid = "";// 应用ID
		    String mch_id = "";// 商户号
		    String transaction_id = "";// 流水号
		    
		    InputStream inStream = getRequest().getInputStream();

		    ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		    byte[] buffer = new byte[1024];
		    int len = 0;
		    while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		    }
		    outSteam.close();
		    inStream.close();
		    String result = new String(outSteam.toByteArray(), "utf-8");
		    Map<Object, Object> map = null;
		    try {
			map = XMLUtil.doXMLParse(result);
		    } catch (JDOMException e) {
			e.printStackTrace();
		    }
		    if (map != null && map.size() > 0) {
			for (Object keyValue : map.keySet()) {
			    if (keyValue.equals("out_trade_no")) {
				out_trade_no = (String) map.get(keyValue);// 订单编号
				continue;
			    }
			    if (keyValue.equals("total_fee")) {
				total_fee = (String) map.get(keyValue);// 总金额(单位：分)
				continue;
			    }
			    if (keyValue.equals("openid")) {
				openid = (String) map.get(keyValue);// 微信用户在商户appid下的唯一标识
				continue;
			    }
			    if (keyValue.equals("appid")) {
				appid = (String) map.get(keyValue);
				continue;
			    }
			    if (keyValue.equals("mch_id")) {
				mch_id = (String) map.get(keyValue);
				continue;
			    }
			    if (keyValue.equals("transaction_id")) {
				transaction_id = (String) map.get(keyValue);
				continue;

			    }
			}
		    }
		    // 验证
		    if (appid.equals(ConstantUtil.APP_ID)
			    && mch_id.equals(ConstantUtil.PARTNER)) {
			if (out_trade_no != null && !out_trade_no.equals("")) {
				
			//	UserSum model = new UserSum();
			//	success= model.getSum(out_trade_no);
				
				if(success){
					renderJson("success");					
				}else {
				    System.out.println("充值未成功");
				    renderJson("fail");
				}
			} else {
			    System.out.println("wx通知订单为空");
			    renderJson("fail");
				}
		    } else {
			System.out.println("商户appid和商户号验证失败:");
			System.out.println("appid:" + appid);
			System.out.println("mch_id:" + mch_id);
			renderJson("fail");
		    }
		} catch (IOException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	}
	 
	 
	 @SuppressWarnings({ "unchecked", "unused" })
	    public void wxRefund() {
		System.out.println("in_wxRefund___________________________");
		try {
		    String out_trade_no = "";// 订单编号
		    String out_refund_no = "";// 退款编号
		    String total_fee = "";// 总金额(单位：分)
		    String openid = "";// 微信用户在商户appid下的唯一标识
		    String appid = "";// 应用ID
		    String mch_id = "";// 商户号
		    System.out.println("out_trade_no:"+out_trade_no+",total_fee"+total_fee+",out_refund_no"+out_refund_no);
		    
		    InputStream inStream = getRequest().getInputStream();

		    ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		    byte[] buffer = new byte[1024];
		    int len = 0;
		    while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		    }
		    outSteam.close();
		    inStream.close();
		    String result = new String(outSteam.toByteArray(), "utf-8");
		    Map<Object, Object> map = null;
		    try {
			map = XMLUtil.doXMLParse(result);
		    } catch (JDOMException e) {
			e.printStackTrace();
		    }
		    if (map != null && map.size() > 0) {
			for (Object keyValue : map.keySet()) {
			    if (keyValue.equals("out_trade_no")) {
				out_trade_no = (String) map.get(keyValue);// 订单编号
				continue;
			    }
			    if (keyValue.equals("total_fee")) {
				total_fee = (String) map.get(keyValue);// 总金额(单位：分)
				continue;
			    }
			    if (keyValue.equals("openid")) {
				openid = (String) map.get(keyValue);// 微信用户在商户appid下的唯一标识
				continue;
			    }
			    if (keyValue.equals("appid")) {
				appid = (String) map.get(keyValue);
				continue;
			    }
			    if (keyValue.equals("mch_id")) {
				mch_id = (String) map.get(keyValue);
				continue;
			    }
			}
		    }
		    // 验证
		    if (appid.equals(ConstantUtil.APP_ID)
			    && mch_id.equals(ConstantUtil.PARTNER)) {
			if (out_refund_no != null && !out_refund_no.equals("")) {
				System.out.println("if:out_refund_no:"+out_refund_no);
				renderJson("success");					
			
			} else {
			    System.out.println("wx通知订单为空");
			    System.out.println("else:out_refund_no:"+out_refund_no);
			    
			    renderJson("success");	
			    //renderJson("fail");
				}
		    } else {
			System.out.println("商户appid和商户号验证失败:");
			System.out.println("appid:" + appid);
			System.out.println("mch_id:" + mch_id);
			renderJson("fail");
		    }
		} catch (IOException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	}
	 
	 
	 
	 public void test(){
		 String out_trade_no = getPara("out_trade_no");
		// UserSum model = new UserSum();
		//	success= model.getSum(out_trade_no);
	 }
	 
}
