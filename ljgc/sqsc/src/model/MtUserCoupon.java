package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.expr.ast.Id;
import com.xiaheng.core.jfinal.BaseModel;

import utils.linUtils.Utils;

/**
 * 用户优惠券表 model
 *
 * @version 1.0
 * @since 2018-2-24 18:10:19
 */
public class MtUserCoupon extends BaseModel<MtUserCoupon> {

	private static final long serialVersionUID = 1L;
	
	public static final MtUserCoupon dao = new MtUserCoupon();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_user_coupon";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	
	/**
	* @方法名: 展示用户优惠券
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 2:14:00
	* @修改:
	*/ 
	public List<Map< String, Object>> getUserCouponList(String userId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select  * from  ");
		sql.append( this.tableName());
		sql.append(" where isDel = 0  and userId = ? ");
		sql.append(" order by status asc , type asc ,addTime desc");
		List<Record> list = Db.find(sql.toString() , userId);
		List<Map< String, Object>> couponList = new ArrayList<Map<String,Object>>();
		Map< String, Object> map  = null; 
		for (Record record : list) {
			map = new HashMap<String, Object>();
			map.put("coupon_no", record.get("coupon_no"));
			map.put("type", record.get("type"));
			map.put("status", record.get("status"));
			map.put("start_time", record.get("start_time"));
			map.put("end_time", record.get("end_time"));
			map.put("full_money", record.get("full_money"));
			map.put("less_money", record.get("less_money"));
			map.put("address", record.get("address"));
			couponList.add(map);
		}
		return couponList;
	}

	
	public String addUserPoupon(String userId , String type ,String start_time , String end_time , 
								double full_money , double less_money , String goods_name 
								,String goods_detail ,String goods_img , String bussiness_address  ){
		String coupon_no = System.currentTimeMillis()+""; 
		String idString =Utils.getUUID();
		Record record = new Record().set("id", idString)
				.set("userId", userId)
				.set("type", type)
				.set("status", "1")
				.set("start_time", start_time)
				.set("end_time", end_time)
				.set("full_money", full_money)
				.set("less_money", less_money)
				.set("goods_name", goods_name)
				.set("goods_detail", goods_detail)
				.set("address", bussiness_address)
				.set("goods_img", goods_img)
				.set("isDel", 0)
				.set("addTime", Utils.getStrFormDateTwo(new Date()))
				.set("coupon_no", coupon_no);
			Db.save(this.tableName(), record);
		return idString;
	}
	
	/**
	* @方法名: 获取用户可用优惠券
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月5 11:12:06
	* @修改:
	*/ 
	public List<Record> getCanUseCoupon(String userId ){
		String end_time = Utils.getStrFormDateTwo(new Date());
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0 and userId = ? and status = 1 end_time < ? ");
		sql.append(" order by  type asc , addTime desc ");
		List<Record> list = Db.find(sql.toString() , userId , end_time);
		return list;
	}
 	
}
