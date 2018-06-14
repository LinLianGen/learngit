package controller.api;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

import pay.alipay.PayUtils;
import pay.alipay.app.util.AlipayNotify;
import pay.wxpay.web.services.MD5Util;
@RouteViewPath("api")
public class AliPayController extends BaseController  {
	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/aliPay";
	}
	
	/**
	* @方法名:  支付宝回调
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月27 3:30:07
	* @修改:
	*/ 
	public void alipayCallback() {
		try {
		    // 获取支付宝POST过来反馈信息
		    Map<String, String> params = new HashMap<String, String>();
		    Map requestParams = getParaMap();
		    for (Iterator iter = requestParams.keySet().iterator(); iter
			    .hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
			    valueStr = (i == values.length - 1) ? valueStr + values[i]
				    : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
			// "gbk");
			params.put(name, valueStr);
		    }

		    // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		    // 商户订单号
		    String out_trade_no = new String(getPara("out_trade_no").getBytes(
			    "ISO-8859-1"), "UTF-8");
 
		    // 交易状态
		    String trade_status = new String(getPara("trade_status").getBytes(
			    "ISO-8859-1"), "UTF-8");

		    // 交易金额
		    String total_fee = new String(getPara("total_amount").getBytes(
			    "ISO-8859-1"), "UTF-8");

		    // 异步通知ID
		    String notify_id =new String(getPara("notify_id").getBytes(
				    "ISO-8859-1"), "UTF-8");
		    if (notify_id != "" && notify_id != null) {// //判断接受的post通知中有无notify_id，如果有则是异步通知。
	/*		if (AlipayNotify.verifyResponse(notify_id).equals("true"))// 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
	*/
			    // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			    if (trade_status.equals("TRADE_FINISHED")) {

			    } else if (trade_status.equals("TRADE_SUCCESS")) {
		    	/*UserSum model = new UserSum();
				success= model.getSum(out_trade_no);*/
				//System.out.println("--------------------total_fee:"+total_fee+",success="+success);
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			renderJson("success");
		    } else// 验证是否来自支付宝的通知失败
		    {
			renderJson("response fail");
		    }
	    }
	} catch (Exception e) {
	    renderJson("response fail");
	}
    }
}
