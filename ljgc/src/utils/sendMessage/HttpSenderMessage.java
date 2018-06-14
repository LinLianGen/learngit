package utils.sendMessage;


import com.bcloud.msg.http.HttpSender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import utils.linUtils.Utils;
/**
 * 想用户发送信息
 * @author admin
 *
 */
public class HttpSenderMessage{
	/*
	 * 测试短信接口
	 */
	public static void main(String[] args) {
	String i = Utils.getValidateCode(true, 4);
	setMsg("18586813488", i);	
	}
	
	/**
	 * 
	 * @param Phone  用户电话 
	 * @param type  1、验证码       2、用户购课提示        3、教师授课通知        4、教师提现到账提示      5、用户退款到账提示
	 * @param userName  用户名称
	 * @param teacherName  教师名称
	 * @param code     验证码
	 * @param price    金额
	 */
	public static void setMsg(String phone , String code) {
		
		String uri = "http://114.55.25.138/msg/HttpBatchSendSM";// 应用地址
		String account = "yjjcs2018";// 账号
		String pswd = "Yjjcs2018@";// 密码
		String mobiles = phone;// 手机号码，多个号码使用","分割
		String content = "您的验证码是:"+code+"。守住验证码，打死都不要告诉别人。";
		
		System.out.println("短信内容："+content);
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String product = "";// 产品ID，默认为空
		String extno = "";// 扩展码，默认为空
		/*try {
			String returnString = HttpSender.send(uri, account, pswd, mobiles, content, needstatus, product, extno);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}*/
		try {
			String returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product,
					extno);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}
}
