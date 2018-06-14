package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sun.org.apache.xerces.internal.util.Status;
import com.xiaheng.core.jfinal.BaseModel;

import utils.linUtils.Utils;

/**
 * 订单表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:40:33
 */
public class MtOrder extends BaseModel<MtOrder> {

	private static final long serialVersionUID = 1L;
	
	public static final MtOrder dao = new MtOrder();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_order";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	/**
	* @方法名: 用户订单表
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 1:00:24
	* @修改:
	*/ 
	public Record userOrder(String userId , double date){
		StringBuffer sql = new StringBuffer();
		sql.append(" select   * from  ");
		sql.append( this.tableName()  );
		sql.append( " where  userId = ? and isDel = 0 and status in (3 , 6) " );
		List<Record> list = Db.find(sql.toString() ,userId);
		//总金额
		double allPrice = 0.0;
		//购买商品件数
		int goods_num = 0 ; 
		//购买次数
		int buy_num = list.size();
		for (Record record : list) {
			allPrice =allPrice+record.getDouble("pay_price");
			String orderId = record.getStr("id");
			//MtOrderGoods goods = new MtOrderGoods();
			List<Record> orderList = Db.find(" select * from  2018a_01_mt_order_goods  where orderId = ? and isDel = 0 " ,orderId);
			goods_num = goods_num+orderList.size();
		}
		//平均每次消费    =      消费总金额/订单购买次数
		double onePrice =0;
		if(allPrice>0&&buy_num>0){
			 onePrice =allPrice/buy_num;			
		}
		//平均购买件数    =      总件数 / 订单购买次数
		double oneNum   = 0;
		if(goods_num>0&&buy_num>0){
			 oneNum   = goods_num/buy_num;
		}
			
		//频次                   =   总次数 /在网天数
		
		double frequency = 0;
		if(date>0&&buy_num>0){
			frequency   = buy_num /date; 
		}
				
		//全网排名分数
		double userPoint = 0;
		if(frequency>0&&buy_num>0&&oneNum>0&&onePrice>0&&allPrice>0){
			userPoint= (frequency+oneNum+onePrice+allPrice+buy_num)/5;	
		}
		
		Record record = new Record()
				.set("id", userId)
				.set("one_price", onePrice)
				.set("one_num", oneNum)
				.set("frequency", frequency)
				.set("user_point", userPoint)
				.set("all_price", allPrice)
				.set("buy_num", buy_num);
		//Db.update("update 2018a_01_mt_user set one_price = ? ,one_num = ? ,frequency = ? ,user_point = ? ,all_price = ? ,buy_num = ? where id = ? " , 
			//								   onePrice     , oneNum ,     frequency ,    userPoint ,     allPrice , buy_num,userId );
		Db.update("2018a_01_mt_user", record);
		return record;
	}
	
	/**
	* @方法名: 通过外送人员ID  和  时间查询完成订单数
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月7 4:26:42
	* @修改:
	*/ 
	public Long getDayAndMonthNum(String courierId , String success_time){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(id) as num from ");
		sql.append(this.tableName());
		sql.append(" where courierId = ? and isDel = 0 and status in (4,5,6,7)  ");
		sql.append(" and success_time > ? ");
		Record record = Db.findFirst(sql.toString() , courierId ,success_time);
		long orderNum = record.getLong("num");
		return orderNum;
	}
	
	/**
	* @方法名: 通过外送人员ID  和  时间查询完成订单数信息 获取历史订单
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月7 4:26:42
	* @修改:
	*/ 
	public List<Record> getHistoryOrders(String courierId , String start_time , String end_time ){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where courierId = ? and isDel = 0 and status in (4,5,6,7)  ");
		sql.append(" and success_time between ? and  ?    ");
		sql.append(" order by addTime desc ");
		List<Record> list = Db.find(sql.toString(),
					courierId ,start_time ,end_time);
		return list;
	}
	
	/**
	* @方法名:   查询订单  未结单状态的订单
	* @参数:1、待付款 2、待发货 3、待收货	4、待评价 5、 待退款  6、已退款  7、已完成
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 11:32:04
	* @修改:
	*/ 
	public List<Map<String, Object>> getOrderList(String courier_id, String areaId ,String type){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  ");
		sql.append(this.tableName());
		sql.append(" where areaId = ? and isDel = 0 ");
		if(type.equals("1")){
			sql.append("   and status = 2  ");
		}else if(type.equals("2")){
			sql.append(" and status = 3 and courier_id = ? ");
		}else{
			sql.append(" and status in (4,5,6,7 ) and courier_id = ?");
		}
		sql.append(" order by addTime desc ");
		List<Map<String, Object>> orderList =new  ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		List<Record> list = null;
		if(type.equals("1")){
			list= Db.find(sql.toString() ,areaId);
		}else {
			list = Db.find(sql.toString() ,areaId ,courier_id);
		}
		for (Record record : list) {
			if(record.getStr("courierId").equals("")||record.getStr("courierId")==null){
				map = new HashMap<String, Object>();
				map.put("orderId", record.get("id"));
				map.put("order_no", record.get("order_no"));
				map.put("userId", record.get("userId"));
				map.put("consignee", record.get("consignee"));
				map.put("phone", record.get("phone"));
				map.put("address", record.get("address"));
				map.put("pick_up_code", record.get("pick_up_code"));
				map.put("pay_price", record.get("pay_price"));
				map.put("postage", record.get("postage"));
				orderList.add(map);
			}
		}
		return orderList;
	}
	
	/**
	* @方法名: 获取订单信息，接单
	* @参数:1、待付款 2、待发货 3、待收货	4、待评价 5、 待退款  6、已退款  7、已完成
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 12:49:14
	* @修改:
	*/ 
	public boolean getOderToCourier(String orderId ,String courierId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where id = ? ");
		Record record = Db.findFirst(sql.toString() , orderId);
		boolean flag =false;
		if(!record.getStr("status").equals("2")){
			return flag;
		}else{
			MtCourier courier = new MtCourier();
			Record courierRecord = courier.getCourierInfo(courierId);
			record.set("status", "3")
				  .set("courier_id", courierId)
				  .set("courier_phone", courierRecord.getStr("phone"))
				  .set("courier_name", courierRecord.getStr("user_name"));
			flag = Db.update(this.tableName(), record);
		}	
		return flag;
	}
	
	
	/**
	* @方法名: 获取订单信息
	* @参数:   status:1、待付款 2、待发货 3、待收货	4、待评价 5、 待退款  6、已退款  7、已完成
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 1:53:38
	* @修改:
	*/ 
	public Record getOrderInfo(String orderId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  ");
		sql.append(this.tableName());
		sql.append(" where  id = ? ");
		Record record = Db.findFirst(sql.toString() , orderId);
		return record;
	}

	/**
	* @方法名: 完成订单 修改订单状态
	* @参数:status:1、待付款 2、待发货 3、待收货	4、待评价 5、 待退款  6、已退款  7、已完成
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 1:53:38
	* @修改:
	*/ 
	public boolean successOrder(String orderId ,String status ){
		StringBuffer sql = new StringBuffer();
		sql.append(" update  ");
		sql.append(this.tableName());
		sql.append(" set status = ? ,updTime = ? ,success_time = ?  ");
		sql.append(" where  id = ? ");
		int i  = Db.update(sql.toString() , status,Utils.getStrFormDateTwo(new Date())
											      ,Utils.getStrFormDateTwo(new Date()) ,orderId);
		return i>0;
	}
	
}
