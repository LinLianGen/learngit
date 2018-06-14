package model;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.poi.hslf.record.RecordContainer;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sun.jndi.dns.ResourceRecord;
import com.xiaheng.core.jfinal.BaseModel;

import utils.linUtils.Utils;

/**
 * 用户表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:47:41
 */
public class MtUser extends BaseModel<MtUser> {

	private static final long serialVersionUID = 1L;
	
	public static final MtUser dao = new MtUser();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_user";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	/**
	* @方法名: 登入
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 10:23:54
	* @修改:
	*/ 
	public Record login(String phone , String pwd , String inType){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where 1 = 1 and phone = ? and password = ?  and isDel = 0  ");
		//sql.append(" and status = 1 ");
		Record record =  Db.findFirst(sql.toString() , phone , pwd);
		if(record!=null){
			Db.update(" update "+this.tableName()+
		" set inTime = ? , in_type = ? where phone = ?  and password = ? "
					,new Date().getTime() , inType , phone , pwd);
		}
		return record;
	}

	
	/**
	* @方法名: 修改密码
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 10:23:54
	* @修改:
	*/ 
	public boolean updatePwd(String userId , String newPwd){
		StringBuffer sql = new StringBuffer();
		sql.append(" update  ");
		sql.append(this.tableName());
		sql.append(" set password = ? ");
		sql.append(" where 1 = 1 and id = ? and isDel = 0 ");
		int i =  Db.update(sql.toString() , newPwd , userId);
		return i>0;
	}
	
	
	/**
	* @方法名: 个人中心 计算各类数据
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 11:58:32
	* @修改:
	*/ 
	public JSONObject userCenter(String userId){
		JSONObject js = new JSONObject();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from " );
		sql.append( this.tableName() );
		sql.append(" where id = ? and isDel = 0 ");
		Record record = Db.findFirst(sql.toString() , userId);
		String  indate = record.getStr("addTime");
		int i = Utils.countTimeHour(indate, Utils.getStrFormDateTwo(new Date()));
		double date  = i/24;
		MtOrder mtOrder = new MtOrder();
		Record record2 = mtOrder.userOrder(userId , date);
		double userPoint = record2.getDouble("user_point");
		
		StringBuffer sqlT = new StringBuffer();
		sqlT.append(" select count(*)+1  Mrow  from ");
		sqlT.append(this.tableName());
		sqlT.append("  where  user_point > (select max(user_point) from  2018a_01_mt_goods_type where user_point = ? and isDel = 0 ) ");
		//个人排名
		
		long Mrow =0 ;
		//使用Long 类型取数据
		Record  record5= Db.findFirst(sqlT.toString(),userPoint);
		//if(record5!=null){
		//	Mrow = record5.getLong("Mrow");
		//}
		//全网人数
		long Allrow  = 0;
		Record  record4=	Db.findFirst("select count(*) Allrow from  " + this.tableName() + " where isDel = 0 ");	
		//if(record4!=null){
		//	Allrow = record4.getLong("Allrow");
		//}
		js.put("Mrow", record5.get("Mrow"));
		js.put("one_price", record2.getDouble("one_price"));
		js.put("all_price", record2.getDouble("all_price"));
		js.put("frequency", record2.getDouble("frequency"));
		js.put("one_num", record2.getDouble("one_num"));		
		js.put("buy_num", record2.getInt("buy_num"));
		
		
		StringBuffer AllDate = new StringBuffer();
		AllDate.append(" select ");
		AllDate.append(" max(one_price), max(all_price) , max(frequency) , max(one_num) , max(buy_num) from  ");
		AllDate.append(this.tableName());
		AllDate.append(" where isDel = 0 ");
		//各项指标最高的数据
		Record record3 = Db.findFirst(AllDate.toString());
		js.put("Allrow", record4.get("Allrow"));
		js.put("max_one_price", record3.getDouble("one_price"));
		js.put("max_all_price", record3.getDouble("all_price"));
		js.put("max_frequency", record3.getDouble("frequency"));
		js.put("max_one_num", record3.getDouble("one_num"));		
		js.put("max_buy_num", record3.getInt("buy_num"));
		return js;
	}
	
	/**
	* @方法名: 获取用户信息
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月28 3:31:00
	* @修改:
	*/ 
	public Record getUserInfo(String userId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where id = ?  and isDel = 0  ");
		Record record =  Db.findFirst(sql.toString() , userId );
		return record;
	}
	
	
	/**
	* @方法名: 获取用户信息 通过电话号码
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月28 3:31:00
	* @修改:
	*/ 
	public Record getUserInfoByPhone(String phone){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where phone = ?  and isDel = 0  ");
		Record record =  Db.findFirst(sql.toString() , phone );
		return record;
	}
	/**
	* @方法名: 获取用户信息
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月28 3:31:00
	* @修改:
	*/ 
	public String register(String phone , String password , String user_name , String img   ){
		String userId = Utils.getUUID();
		Record record = new Record().set("id",userId )
				.set("phone", phone)
				.set("password", password)
				.set("user_name", user_name)
				.set("img", img)
				.set("addTime", Utils.getStrFormDateTwo(new Date()))
				.set("point", 0)
				.set("status", 1)
				.set("isDel", 0);
		Db.save(this.tableName(), record);
		return userId;
	}
	
	/**
	* @方法名:修改用户  手机号码 或者  密码
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月2 3:18:42
	* @修改:
	*/ 
	public boolean updateUserPwdOrPhone(Record record){
		boolean flag = Db.update(this.tableName(), record);
		return flag ;
	}
	
	
	
}
