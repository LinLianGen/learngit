package controller.api;


import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

import model.MtCourier;
import model.MtUser;

@RouteViewPath("api")
public class LoginController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/login";
	}
		
	/**
	* @方法名: 登入
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 10:02:57
	* @修改:
	*/ 
	public void login(){
		String phone = getPara("phone");
		String password = getPara("password");
		String in_type = getPara("in_type");
		MtUser mtUser = new MtUser();
		String type = "1";
		Record record = mtUser.login(phone, password,in_type);
		if(record==null){
			MtCourier courier = new MtCourier();
			 record = courier.login(phone, password);
			 type  = "2";
		}
		if(record==null){
			type = "3";
		}
		record.set("type", type);
		renderJSON(true, "请求成功" , record);
		
		
	}	
	
	/**
	* @方法名: 用户注册
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 10:02:57
	* @修改:
	*/ 
	public void register(){
		String phone = getPara("phone");
		String user_name = getPara("user_name");
		String password = getPara("password");
		String img = getPara("img");
		MtUser mtUser = new MtUser();
		if(mtUser.getUserInfoByPhone(phone)!=null){
			renderJSON(true, "用户已存在!");
			return;
		}
		String idString = mtUser.register(phone, password, user_name, img); 
		Record  record = new Record().set("userId", idString);
		renderJSON(true, "请求成功" , record);
	}
	
	/**
	* @方法名: 忘记密码
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 10:03:08
	* @修改:
	*/ 
	public void forgetPwd(){
		String phone = getPara("phone");
		
	}	
	
	/**
	* @方法名:修改密码
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 10:22:39
	* @修改:
	*/ 
	public void updatePwd(){
		String userId = getPara("userId");
		String newPwd = getPara("newPwd");
		String type = getPara("type");
		MtUser mtUser = new MtUser();
		boolean flag =false;
		if(type.equals("1")){
			flag= mtUser.updatePwd(userId, newPwd);
		}else{
			MtCourier courier = new MtCourier();
			flag= courier.updatePwd(userId, newPwd);
		}
		renderJSON(flag, "请求成功");
	}
}
