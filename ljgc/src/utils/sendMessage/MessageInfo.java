/*package utils.sendMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.catalina.mbeans.UserMBean;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import model.MtPointRecord;
import model.MtUserBalanceRecord;
import model.Proportion;
import model.User;
import model.UserOppintment;
import model.UserParkingLot;
import utils.linUtils.Utils;

*//**
* @方法名:短信类型
* @参数:
* @输出:
* @备注:
* @作者: 林
* @时间: 2017年12月18 4:06:24
* @修改:
*//*

public class MessageInfo {
	//争议一-虚假车位-举报为假-发给举报者
	//亲，你举报的车位信息，确认后发现和事实不符，按照使用协议，你的使用费用不返还，同时扣除你的30个信用分。
	public final static String 	SMS_117515955 = "SMS_117515955";  
	
	//争议一-虚假车位-举报为假-发给出租方
	//亲，经确认，此前被举报的情况不符，深表歉意，将返还你的租金，并奖励你10个信用分，并请尽快上线车位哦。
	public final static String 	SMS_117525926 = "SMS_117525926"; 
	
	
	
		//争议一-虚假车位-发给后续出租方
		//亲，经确认你预定的车位为虚假车位，只能残忍取消你的预定，给你造成的不便深表歉意，请亲再预定其他的车位满足自己停车需求。
		public final static String 	SMS_117515905 = "SMS_117515905"; 
			
		//争议一-虚假车位-发给出租方
		//亲，你发布的车位被举报为虚假车位，据使用协议，将暂时下架车位，如有异议，请在在两个工作日内联系客服，如未联系，将删除车位，并扣除信用分。
		public final static String 	SMS_117525876 = "SMS_117525876"; 
		*//**
		* @方法名:userId	出租人
		* @参数:
		* @输出:
		* @备注:
		* @作者: 林
		* @时间: 2017年12月20 4:43:56
		* @修改:
		*//* 
		public static boolean getCodeXuJiaTrue(String parking_id ,String userId){
			boolean msg = false;
			SmsDemo smsDemo = new SmsDemo();
			try {
				User u = new User();
				String phone =  u.getUserPhonefromId(userId).getStr("phone");
				//亲，你发布的车位被举报为虚假车位，据使用协议，将暂时下架车位，如有异议，请在在两个工作日内联系客服，如未联系，将删除车位，并扣除信用分。
				msg = smsDemo.handleMsg(phone, "", SMS_117525876);
				UserParkingLot upk = new UserParkingLot();
				upk.upOrDownPark(parking_id, "2","1","2");
				UserOppintment uop = new UserOppintment();
				List<Record> list =uop.findOpp(parking_id);
				for (Record record : list) {
					uop.del(record.getStr("id"));
					msg = smsDemo.handleMsg(record.getStr("rent_phone"), "", SMS_117515905);
					uop.del(record.getStr("id"));
				}
			} catch (Exception e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			return msg;
		}
	
	//争议二-车位占用-发送给出租方
	//亲，你的车位正在被其他未知车辆占用，请尽快去处理，系统暂时下线此车，待亲解决完后，尽快上线哦，以防影响后续他人使用。
	public final static String 	SMS_117525894 = "SMS_117525894"; 
	public static boolean parking_isNo(String parking_id ,String userId){
		boolean msg = false;
		SmsDemo smsDemo = new SmsDemo();
		try {
			User u = new User();
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			//亲，你的车位正在被其他未知车辆占用，请尽快去处理，系统暂时下线此车，待亲解决完后，尽快上线哦，以防影响后续他人使用。
			msg = smsDemo.handleMsg(phone, "", SMS_117525894);
			//msg = uoOrDown(parking_id);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;		
	}
	
	
	//争议二-车位占用-发送给车主-未结束租用
	//亲，已过了预计结束时间，而影响了后续使用者，请尽快结束使用并尽快挪车，据使用协议，从预计结束时间的费用将加收10%的费用，扣除20信用分。
	public final static String 	SMS_117515919 = "SMS_117515919"; 
	//争议二-车位占用- 给车主-已结束租用
	//亲，继续占用车位，据使用协议，将补扣你的费用，并加收10%的费用，扣除30个信用分；请尽快挪车。望亲以后结束使用后能尽快挪车
	public final static String 	SMS_117510968 = "SMS_117510968"; 
	public static boolean send968And919(String type,String user_type ,String userId ,String time ,String car_no ,String parking_id){
		boolean msg = false;
		
		UserParkingLot upk = new UserParkingLot();
		Proportion p = new Proportion();
		UserOppintment uop = new UserOppintment();
		//1、租用者    2、出租者
		Record record =	uop.LikeOppintment(user_type, userId, car_no, parking_id);
		
		int i = countTime(record.getStr("end_time"), time);
		
		int j = countTime(record.getStr("start_time"), record.getStr("end_time"));
		
		double price = record.getDouble("pay_price")/j*i*(1+p.getProportion());
		User u = new User();
		u.reduceMoney(Math.abs(price),  record.getStr("rent_id"));
		MtUserBalanceRecord balanceRecord = new MtUserBalanceRecord();
		balanceRecord.save(record.getStr("id"), record.getStr("rent_id"), "7", price);
		SmsDemo smsDemo = new SmsDemo();
		try {
			
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			MtPointRecord  mpr = new MtPointRecord();
			mpr.addUserPoint( phone ,userId, u.getUserPhonefromId(userId).getStr("user_name"), "30","4", Utils.getStrFormDate(new Date()), Utils.getStrFormDateThree(new Date()));
			//亲，你的车位正在被其他未知车辆占用，请尽快去处理，系统暂时下线此车，待亲解决完后，尽快上线哦，以防影响后续他人使用。
			if(type.equals("2")){
				u.countUserAnything(userId, "2", 20);
				msg = smsDemo.handleMsg(phone, "", SMS_117515919);
			}else{
				u.countUserAnything(userId, "2", 30);
				msg = smsDemo.handleMsg(phone, "", SMS_117510968);
			}
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;	
	}
	
	
	
	//争议二-车位占用-发送给车主-未结束租用-提前通知
	//亲，在你之后紧接着有车主使用此车位，请准时结束租用，并尽快挪车，如影响到他人使用，则费用将提高10%，并扣除20分信用分。
	public final static String 	SMS_117520837 = "SMS_117520837";
	public static boolean onTime(String userId){
		boolean msg = false;
		SmsDemo smsDemo = new SmsDemo();
		try {
			User u = new User();
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			//亲，你的车位正在被其他未知车辆占用，请尽快去处理，系统暂时下线此车，待亲解决完后，尽快上线哦，以防影响后续他人使用。
			msg = smsDemo.handleMsg(phone, "", SMS_117520837);
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;	
	}
	
	//争议三-不在租用期内-出租方举报-发给占用车主
	//亲，你目前仍占用车位，据使用协议，将补扣此间费用，并加收10%费用，补扣信用分30分，并请亲及时挪车，如有异议，请联系客服。
	public final static String 	SMS_117515946 = "SMS_117515946";
	public static boolean send946(String user_type ,String userId ,String time ,String car_no ,String parking_id){
		boolean msg = false;
		UserOppintment uop = new UserOppintment();
		Record record =	uop.LikeOppintment(user_type, userId, car_no, parking_id);
		int i = countTime(record.getStr("end_time"), time);
		
		int j = countTime(record.getStr("start_time"), record.getStr("end_time"));
		Proportion p = new Proportion();
		double price = record.getDouble("pay_price")/j*i*(1+p.getProportion());
		User u = new User();
		u.reduceMoney(Math.abs(price),  record.getStr("rent_id"));
		MtUserBalanceRecord balanceRecord = new MtUserBalanceRecord();
		balanceRecord.save(record.getStr("id"), record.getStr("rent_id"), "7", price);
		SmsDemo smsDemo = new SmsDemo();
		try {
			u.countUserAnything(userId, "2", 30);
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			MtPointRecord  mpr = new MtPointRecord();
			mpr.addUserPoint(phone , userId, u.getUserPhonefromId(userId).getStr("user_name"), "30","4", Utils.getStrFormDate(new Date()), Utils.getStrFormDateThree(new Date()));
			//亲，你的车位正在被其他未知车辆占用，请尽快去处理，系统暂时下线此车，待亲解决完后，尽快上线哦，以防影响后续他人使用。
			msg = smsDemo.handleMsg(phone, "", SMS_117515946);
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;	
	}
	
	//争议三-不在租用期内-出租方举报-发给后续预定者
	//亲，你预定的当前车位仍未上架，请联系车主核对情况后，再做进一步决定。
	public final static String 	SMS_117525872 = "SMS_117525872"; 	
	public static boolean send872(String userId){
		boolean msg = true;
		SmsDemo smsDemo = new SmsDemo();
		try {
			User u = new User();
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			msg = smsDemo.handleMsg(phone, "", SMS_117525872);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	//争议四-强制结束使用-发给车主
	//亲，你之前举报的被强制结束使用租用，确认属实，并补偿亲10%的费用，同时补偿10个信用分。多谢亲的支持。
	public final static String 	SMS_117510945 = "SMS_117510945"; 
	public static boolean send945(String user_type ,String userId ,String time ,String car_no ,String parking_id){
		boolean msg = false;
		UserOppintment uop = new UserOppintment();
		Proportion p = new Proportion();
		User u = new User();
		try{
		Record record =	uop.LikeOppintment(user_type, userId, car_no, parking_id);
		int i = countTime(record.getStr("end_time"), time);
		
		int j = countTime(record.getStr("start_time"), record.getStr("end_time"));
		
		double price = record.getDouble("pay_price")/j*0.1;
		u.inCreaseMoney(Math.abs(price),  record.getStr("rent_id"));
		MtUserBalanceRecord balanceRecord = new MtUserBalanceRecord();
		balanceRecord.save(record.getStr("id"), record.getStr("rent_id"), "7", price);
		}catch (Exception e) {
			// TODO: handle exception
		}
		SmsDemo smsDemo = new SmsDemo();
		try {
			u.countUserAnything(userId, "1", 10);
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			MtPointRecord  mpr = new MtPointRecord();
			mpr.addUserPoint(phone,userId, u.getUserPhonefromId(userId).getStr("user_name"), "30","4", Utils.getStrFormDate(new Date()), Utils.getStrFormDateThree(new Date()));
			//亲，你的车位正在被其他未知车辆占用，请尽快去处理，系统暂时下线此车，待亲解决完后，尽快上线哦，以防影响后续他人使用。
			msg = smsDemo.handleMsg(phone, "", SMS_117510945);
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;	
	}
	
	
	//争议四-强制结束使用-发给出租方
	//亲，你提前结束了租用，据使用协议，被扣除交易额的20%作为罚金，同时扣除30信用分，如有异议请及时联系客服，如两日未联系则默认认同此结果。
	public final static String 	SMS_117510943 = "SMS_117510943"; 
	public static boolean send943(String user_type ,String userId ,String time ,String car_no ,String parking_id){
		boolean msg = false;
		UserParkingLot upk = new UserParkingLot();
		Record userInfo = upk.getParkingMasterInfo(parking_id);
		UserOppintment uop = new UserOppintment();
		Record record =	uop.LikeOppintment(user_type, userId, car_no, parking_id);
		int i = countTime(record.getStr("end_time"), time);
		
		int j = countTime(record.getStr("start_time"), record.getStr("end_time"));
		
		double price = record.getDouble("pay_price")/j*0.2;
		User u = new User();
		u.reduceMoney(Math.abs(price),  userInfo.getStr("id"));
		MtUserBalanceRecord mubr = new MtUserBalanceRecord();
		mubr.save( record.getStr("id") ,userInfo.getStr("id"), "7", price);
		SmsDemo smsDemo = new SmsDemo();
		try {
			u.countUserAnything(userInfo.getStr("id"), "2", 30);
			String phone =  userInfo.getStr("phone");
			MtPointRecord  mpr = new MtPointRecord();
			mpr.addUserPoint(phone,userInfo.getStr("id"), userInfo.getStr("user_name"), "30","4", Utils.getStrFormDate(new Date()), Utils.getStrFormDateThree(new Date()));
			//亲，你的车位正在被其他未知车辆占用，请尽快去处理，系统暂时下线此车，待亲解决完后，尽快上线哦，以防影响后续他人使用。
			msg = smsDemo.handleMsg(phone, "", SMS_117510943);
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;	
	}	
	
	
	//预约车位-仍未上线-发给车主
	//亲，检测到当前车位还未上架，可能会影响你的使用，请联系车主核对情况后，再做进一步决定，或亲可取消订单，再寻找其他附近可用车位。
	public final static String 	SMS_117520834 = "SMS_117520834"; 
	public static boolean send834(String userId){
		boolean msg = true;
		SmsDemo smsDemo = new SmsDemo();
		try {
			User u = new User();
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			msg = smsDemo.handleMsg(phone, "", SMS_117520834);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	
	//车位删除-发给后续预定者
	//亲，你预定的车位，由于车为主的其他原因，已经删除了，将取消亲的预约订单，小栖建议亲再预约其他附近的车位，并给亲10个信用分作为补偿。
	public final static String 	SMS_117510941 = "SMS_117510941"; 	
	public static boolean send941(String userId){
		boolean msg = true;
		SmsDemo smsDemo = new SmsDemo();
		try {
			User u = new User();
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			msg = smsDemo.handleMsg(phone, "", SMS_117510941);
			u.countUserAnything(userId,"1" , 10);
			MtPointRecord  mpr = new MtPointRecord();
				mpr.addUserPoint(phone,userId, u.getUserPhonefromId(userId).getStr("user_name"), "10","3", Utils.getStrFormDate(new Date()), Utils.getStrFormDateThree(new Date()));
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	//预约车位-仍未上线-发给出租方
	//亲，你的车位之前已被预约，请尽快上线，以防影响他人使用，如未及时上线，据使用协议，将扣除亲20个信用分。
	public final static String 	SMS_117510970 = "SMS_117510970";  
	public boolean send970(String userId){
		boolean msg = true;
		SmsDemo smsDemo = new SmsDemo();
		try {
			User u = new User();
			String phone =  u.getUserPhonefromId(userId).getStr("phone");
			msg = smsDemo.handleMsg(phone, "", SMS_117510970);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	//登录确认验证码
	public final static String 	SMS_109680386 = "SMS_109680386"; 
	
	
	*//**
	* @方法名: 虚假举报发送短信
	* @参数:1、添加信用分    2、减去信用分    3、信用分不动    
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月18 7:56:33
	* @修改:
	*//* 
	public static boolean getCodeXuJiaFalse(String parking_id ,String userId ){
	boolean msg = true;
	SmsDemo smsDemo = new SmsDemo();
	try {
		User u = new User();
		String phone =  u.getUserPhonefromId(userId).getStr("phone");
		//亲，你举报的车位信息，确认后发现和事实不符，按照使用协议，你的使用费用不返还，同时扣除你的30个信用分。
		msg = smsDemo.handleMsg(phone, "", SMS_117515955);
		//  1、添加信用分    2、减去信用分    3、信用分不动    
		u.countUserAnything(userId,"2" , 30);
		UserParkingLot upk = new UserParkingLot();
		String least_id = upk.getParkingMasterInfo(parking_id).getStr("userId");
		String lease_phone =  u.getUserPhonefromId(least_id).getStr("phone");
		//亲，经确认，此前被举报的情况不符，深表歉意，将返还你的租金，并奖励你10个信用分，并请尽快上线车位哦。
		msg = smsDemo.handleMsg(lease_phone, "", SMS_117525926);
		//  1、添加信用分    2、减去信用分    3、信用分不动    
		u.countUserAnything(least_id,"1" , 10);
	} catch (Exception e) { // TODO Auto-generated catch block
		e.printStackTrace();
	}
	return msg;
	}	
	*//**
	* @方法名:  直接将车位下架
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月20 6:54:07
	* @修改:
	*//* 
	private static boolean uoOrDown(String parking_id){
		UserParkingLot upk = new UserParkingLot();
		boolean flag = upk.upOrDownPark(parking_id, "2" ,Utils.getStrFormDateTwo(new Date()), Utils.getStrFormDateTwo(new Date()));
		return flag;
	}
	
	*//**
	* @方法名: 计算时间差！几个小时
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月20 7:59:56
	* @修改:
	*//* 
	private static Integer countTime(String endTime  ,String nowTime){
		long i = Utils.getDateFromStrTwo(nowTime).getTime();
		long j  = Utils.getDateFromStrTwo(endTime).getTime();
		Integer d  = 0;
		if(i>j){
			long a 	= i-j;
			double c= Double.valueOf(a);
			double b  = c/3600/1000;
			d =(int)Math.ceil(b);
		}
		return d;
	}
	
	public static void main(String[] args) {
		System.out.println(countTime("2017-12-13 11:30:00", "2018-12-13 13:31:00"));
		UserOppintment uop = new UserOppintment();
			//List<Record> record = Db.find(" select id from mt_user_oppintment where parking_lot_id = '1251sd65f4ds65cs16f5wdsaw4s' and start_time between '2018-01-01 11:30:00' and '2018-01-13 13:31:00'  or end_time  between '2018-01-01 11:30:00' and '2018-01-13 13:31:00'  or start_time < '2018-01-01 11:30:00'  and end_time > '2018-11-13 13:31:00' ");
			//System.out.println(uop.getparkingFlag("1251sd65f4ds65cs16f5wdsaw4s", "2018-01-12 11:30:00", "2018-08-13 11:30:00"));
		//System.out.println(uop.getMyRent("1d56w41d3s2a156fw1weq1", "-1", "1", "-1").toString());
		StringBuffer sql = new StringBuffer();
    	sql.append("select  *  from ");
    	sql.append(	" mt_user_oppintment m ");
    	sql.append(" where  m.parking_lot_id = ?  and m.isDel = '0' ");
    	//sql.append(" and start_time between ? and ? ");
    	//sql.append(" or end_time between ? and  ?    ");
    	//sql.append(" or start_time < ? and end_time > ? ");     start_time , end_time    , start_time , end_time , start_time  , end_time
    	 Db.find("select * from mt_user_oppintment  where  parking_lot_id = '1251sd65f4ds65cs16f5wdsaw4s'  and isDel = '0'");
	}
	
	
}*/