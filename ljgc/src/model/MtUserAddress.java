package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

import utils.linUtils.Utils;

/**
 * 用户地址表 model
 *
 * @version 1.0
 * @since 2018-2-24 18:03:07
 */
public class MtUserAddress extends BaseModel<MtUserAddress> {

	private static final long serialVersionUID = 1L;
	
	public static final MtUserAddress dao = new MtUserAddress();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_user_address";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	
	/**
	* @方法名: 添加用户收件地址
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:47:49
	* @修改:
	*/ 
	public String addUserAddress(String userId , String consignee , String phone 
				, String address , String is_default , String communityId){
		String idString = Utils.getUUID();
		Record record = new Record().set("id", idString)
				.set("userId", userId)
				.set("consignee", consignee)
				.set("phone", phone)
				.set("address", address)
				.set("is_default", is_default)
				.set("communityId", communityId)
				.set("isDel", 0)
				.set("addTime", Utils.getStrFormDateTwo(new Date()));
			Db.save(this.tableName(), record);
			return idString;
	}
	
	/**
	* @方法名:  删除用户地址
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:54:36
	* @修改:
	*/ 
	public boolean delAddress(String addressId){
		StringBuffer sql = new StringBuffer();
		sql.append(" update ");
		sql.append(this.tableName() );
		sql.append("  set isDel = 1 ");
		sql.append("  where id = ? " );
		int i  = Db.update(sql.toString() , addressId);
		//boolean flag = Db.deleteById(this.tableName(), addressId);
		return i>0;
	}
	
	/**
	* @方法名:  查询用户地址
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月28 11:10:40
	* @修改:
	*/ 
	public List<Map<String, Object>> getUserAddress(String userId){
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where userId = ? and isDel = 0 ");
		List<Map<String, Object>> addressList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		List<Record> list = Db.find(sql.toString() , userId);
		for (Record record : list) {
			map = new HashMap<String, Object>();
			map.put("address", record.getStr("address"));
			map.put("consignee", record.getStr("consignee"));
			map.put("phone", record.getStr("phone"));
			map.put("is_default", record.getStr("is_default"));
			map.put("communityId", record.getStr("communityId"));
			map.put("userId", record.getStr("userId"));
			map.put("addressId", record.getStr("id"));
			addressList.add(map);
		}
		return addressList;
	}
	
	/**
	* @方法名: 修改默认地址
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 2:03:24
	* @修改:
	*/ 
	public boolean updateDefaultAddress(String userId , String addressId){
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(this.tableName());
		sql.append(" set is_default = 2 ");
		sql.append(" where  userId = ? and id != ?  ");
		int i = Db.update(sql.toString() , userId ,addressId);
		 i = i + Db.update("update " +this.tableName() + "  set is_default = 1 where id = ? " , addressId); 
		 return i>0;
	}
}

