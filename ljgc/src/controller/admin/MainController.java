/*
 * @(#) MainController.java 2017年5月31日
 *
 * Copyright (c) 2015, XiaHeng NetWork. All Rights Reserved.
 * XiaHeng NetWork. CONFIDENTIAL
 */
package controller.admin;

import java.util.Date;
import java.util.List;

import model.Admin;
import model.Menu;
import utils.interceptor.LoginAdminInterceptor;
import utils.linUtils.Utils;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.EncodeUtils;

@RouteViewPath("admin/")
@Before(LoginAdminInterceptor.class)
public class MainController extends CRUD {

	/**
	 * 当前登陆用户的信息
	 * 
	 * @author Chuck Don
	 * @since 2016年12月20日
	 * @return
	 */
	protected Record getUserInfo() {
		return (Record) getSession().getAttribute("admin");
	}

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "/admin";
	}

	public void index() {
		if (getPara(0) != null && !"index".equals(getPara(0))) {
			redirect("/admin/index");
			return;
		}
		Record recod = getUserInfo();
		List<Record> menuList = Menu.dao.menuList(recod.getStr("roleId"));
		setAttr("menuList", menuList);
		renderJsp("index.jsp");
	}

	@Clear(LoginAdminInterceptor.class)
	public void login() {
		if (isPost()) {
			if (checkPara("userName", "passWord")) {
				String userName = getPara("userName");
				String passWord = getPara("passWord");
				Record admin = Admin.dao.adminInfo(userName);
				Admin record = new Admin();
				if (admin == null) {
					renderJSON(false, "用户名不存在");
					return;
				}
				System.out.println(admin.toString());
				if(admin.getInt("taday_num")==5){
					if(admin.getStr("before_time").equals(Utils.getStrFormDate(new Date()))){
						renderJSON(false, "用户锁定");
						return;						
					}else{
						record.updateBefore(Utils.getStrFormDate(new Date()), 0, userName);
					}
				}
				if (EncodeUtils.MD5Encode(passWord).equals(
						admin.getStr("password"))) {
					admin.remove("password");
					getSession().setAttribute("admin", admin);
					renderJSON(true, "登陆成功");
				} else {
					String before_time = admin.getStr("before_time");
					String now = Utils.getStrFormDate(new Date());
					int taday_num = admin.getInt("taday_num");
					if(before_time.equals(now)){
						taday_num = taday_num + 1 ;
						record.updateBefore(now, taday_num, userName);
					}else{
						record.updateBefore(now, 0, userName);
					
					}
					renderJSON(false, "密码不正确");
				}
			}
		} else {
			renderJsp("login.jsp");
		}
	}

	/**
	 * @方法名:退出
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月26 9:58:14
	 * @修改:
	 */
	public void logout() {
		getSession().removeAttribute("admin");
		renderJsp("login.jsp");
	}

}
