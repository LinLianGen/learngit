package model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

import utils.linUtils.Utils;

/**
 * 外送小哥表 model
 *
 * @version 1.0
 * @since 2018-2-25 14:24:36
 */
public class MtCourier extends BaseModel<MtCourier> {

	private static final long serialVersionUID = 1L;
	
	public static final MtCourier dao = new MtCourier();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_courier";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	public Record login(String phone , String password){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  ");
		sql.append(this.tableName());
		sql.append(" where phone = ? and password = ? and isDel = 0 ");
		Record record = Db.findFirst(sql.toString() , phone ,password);
		return record;
	} 

	
	
	/**
	* @方法名: 修改密码
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月28 10:23:54
	* @修改:
	*/ 
	public boolean updatePwd(String userId , String newPwd){
		StringBuffer sql = new StringBuffer();
		sql.append(" update  ");
		sql.append(this.tableName());
		sql.append(" set password = ? ");
		sql.append(" where  id = ? and isDel = 0 ");
		int i =  Db.update(sql.toString() , newPwd , userId);
		return i>0;
	}
	
	/**
	* @方法名: 修改
	* @参数:Status: 1:申请开工   2：开工中    3：已停工
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 10:34:11
	* @修改:
	*/ 
	public boolean updStatus(String status ,String courierId){
		StringBuffer sql = new StringBuffer();
		sql.append(" update ");
		sql.append(this.tableName() );
		sql.append(" set status = ?  , updTime = ?  where id = ? ");
		int i = Db.update(sql.toString() , status , Utils.getStrFormDateTwo(new Date()),courierId);	
		return i>0;
	}
	
	
	/**
	* @方法名:获取外卖小哥信息
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月8 11:07:18
	* @修改:
	*/ 
	public Record getCourierInfo(String courierId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  ");
		sql.append(this.tableName());
		sql.append(" where id = ? and isDel = 0 ");
		Record record = Db.findFirst(sql.toString() ,courierId);
		return record;
	} 
}
