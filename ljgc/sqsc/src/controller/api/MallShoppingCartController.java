package controller.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

import model.MtFullReduction;
import model.MtUserCoupon;
import utils.linUtils.Utils;
@RouteViewPath("api")
public class MallShoppingCartController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/shoppingCart";
	}
	
	/**
	* @方法名: 获取满?免邮
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月5 10:27:07
	* @修改:
	*/ 
	public void getFullReduction(){
		MtFullReduction fullReduction = new MtFullReduction();
		Record record = fullReduction.getFullReduction();
		double price = record.getDouble("price");
		JSONObject js = new JSONObject();
		js.put("price", price);
		renderJSON(true, "请求成功", js);
	}
	
	
	/**
	* @方法名: 生成订单
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月5 11:02:57
	* @修改:
	*/ 
	public  void  generateOrder(){
		String communityId = getPara("communityId");
		String userId = getPara("userId");
		String order_img = getPara("order_img");
		String postage = getPara("postage");
		String point = getPara("point");
		String courier_id = getPara("courier_id");
		String courier_name = getPara("courier_name");
		String courier_phone = getPara("courier_phone");
		String address_id = getPara("address_id");
		String goodsId = getPara("goodsId");
		String order_price = getPara("order_price");
		String orderId = Utils.getUUID();
		
	}
	
	/**
	* @方法名: 获取用户可用优惠券
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月5 11:24:31
	* @修改:
	*/ 
	public  List<Map<String , Object>>  getCanUseCoupon(){
		String userId = getPara("userId");
		MtUserCoupon mtUserCoupon = new MtUserCoupon();
		List<Record> list = mtUserCoupon.getCanUseCoupon(userId);
		List<Map<String , Object>> canUseCoupon = new ArrayList<Map<String,Object>>();
		Map<String , Object> map = null;
		for (Record record : list) {
			map = new HashMap<String, Object>();
			map.put("coupon_no", record.getStr("coupon"));
			map.put("type", record.getStr("type"));
			map.put("status", record.getStr("status"));
			map.put("start_time", record.getStr("start_time"));
			map.put("end_time", record.getStr("end_time"));
			map.put("full_money", record.getStr("full_money"));
			map.put("less_money", record.getStr("less_money"));
			canUseCoupon.add(map);
		}
		return  canUseCoupon;
	}	
}
