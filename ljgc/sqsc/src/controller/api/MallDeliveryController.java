package controller.api;

/**
 * 配送端
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import model.MtCourier;
import model.MtOrder;
import model.MtOrderAssess;
import utils.linUtils.Utils;
@RouteViewPath("api")
public class MallDeliveryController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/delivery";
	}
	
	/**
	* @方法名: 外卖小哥个人中心
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月7 3:51:07
	* @修改:
	*/ 
	public void courierCenter(){
		String courierId = getPara("courierId");
		MtOrder mtOrder = new MtOrder();
		String[] arr = Utils.getStrFormDate(new Date()).split("-");
		String taday_time = Utils.getStrFormDate(new Date()) + " 00:00:00";
		long taday_num = mtOrder.getDayAndMonthNum(courierId, taday_time);
		String month_time =  arr[0]+"-"+arr[1]+"-01 00:00:00";
		long month_num = mtOrder.getDayAndMonthNum(courierId, month_time);
		MtOrderAssess mtOrderAssess = new MtOrderAssess();
		double star = mtOrderAssess.getStarFromCourier(courierId);
		MtCourier courier = new MtCourier();
		Record info = courier.getCourierInfo(courierId);
		JSONObject js = new JSONObject();
			js.put("courier_info", info);
			js.put("taday_num", taday_num);
			js.put("month_num", month_num);
			js.put("star", star);
		renderJSON(true, "请求成功", js);
	}
	
	/**
	* @方法名: 历史订单
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 9:45:03
	* @修改:
	*/ 
	public void historyOrders(){
		String start_time = getPara("start_time");
		String end_time  =getPara("end_time");
		String courierId = getPara("courierId");
		MtOrder mtOrder = new MtOrder();
		List<Map<String, Object>> orderList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		List<Record> list = mtOrder.getHistoryOrders(courierId, start_time, end_time);
		for (Record record : list) {
			 map = new HashMap<String, Object>();
			 map.put("orderId", record.get("id"));
			 map.put("order_no", record.get("order_no"));
			 map.put("address", record.get("address"));
			 map.put("pay_price", record.get("pay_price"));
			 orderList.add(map); 
		}
		JSONObject js = new JSONObject();
		js.put("orderList", orderList);
		js.put("order_num", list.size());
		renderJSON(true, "请求成功", js);
		
	}
	
	/**
	* @方法名: 外送小哥 申请开工   ，直接停工
	* @参数:Status: 1:申请开工   2：开工中    3：已停工
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 10:26:52
	* @修改:
	*/ 
	public void applicationStart(){
		String courierId = getPara("courierId");
		String status    = getPara("status");
		MtCourier courier = new MtCourier();
		boolean flag = courier.updStatus(status, courierId);
		renderJSON(flag, "请求成功");
	}
	
	
	/**
	* @方法名:  获取该区域 可用任务 /已接单人物  /已完成任务        1、待付款 2、待发货 3、待收货	4、待评价 5、 待退款  6、已退款  7、已完成
	* @参数: type  1 、 未结单    2、已接单   3 、已完成
	* @输出: 
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 11:05:33
	* @修改:
	*/ 
	public void getOrder(){
		String type = getPara("type");
		String areaId = getPara("areaId");
		String courier_id = getPara("courier_id");
		MtOrder mtOrder = new MtOrder();
		List<Map<String, Object>> list = mtOrder.getOrderList( courier_id, areaId ,type);
		JSONObject js = new JSONObject();
		js.put("orderList", list);
		renderJSON(true, "请求成功", js);
	}
	
	
	/**
	* @方法名: 开始接单
	* @参数:status: 1、待付款 2、待发货 3、待收货	4、待评价 5、 待退款  6、已退款  7、已完成
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 12:18:31
	* @修改:
	*/ 
	public void orders(){
		String orderId = getPara("orderId");
		String courierId = getPara("courier_id");
		MtOrder mtOrder = new MtOrder();
		boolean flag =mtOrder.getOderToCourier(orderId, courierId);
		String message = "";
		if(flag){
			message  = "请求成功";
		}else{
			message = "订单已被接";
		}
		renderJSON(flag, message);	
	}
	
	
	/**
	* @方法名: 完成订单
	* @参数:status: 1、待付款 2、待发货 3、待收货	4、待评价 5、 待退款  6、已退款  7、已完成
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 12:18:31
	* @修改:
	*/ 
	public void successOrder(){
		String orderId = getPara("orderId");
		String status = getPara("status");
		MtOrder mtOrder = new MtOrder();
		boolean flag = mtOrder.successOrder(orderId, status);
		renderJSON(flag, "请求成功");
		
	}
	
	
}

