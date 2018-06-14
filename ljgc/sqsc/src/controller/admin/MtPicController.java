
package controller.admin;


import utils.interceptor.LoginAdminInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

import model.MtPic;
import model.MtUser;



@RouteViewPath("admin")
@Before(LoginAdminInterceptor.class)
public class MtPicController extends CRUD {
	@Override
	protected String route() {
		return "admin/pic";
	}
	
	
	public void showList(){
		if (isPost()) {
			Record record = getSessionAttr("admin");
			System.out.println(record.toString());
		    int page = getParaToInt("offset", 1); 
		    int pageSize = getParaToInt("limit", 10);
		    page = (page / pageSize);
		    page++;
		    JSONObject where = new JSONObject();
		    String jsonStr = getPara("where", "{}");
		    JSONObject json = new JSONObject();
		    json = JSONObject.parseObject(jsonStr);
		    MtPic model = new MtPic();
		    Page<Record> result = model.getPaging(page, pageSize, where,json);
		    renderJSON(true, "请求成功", result);
		} else {
		    renderJsp("mtPic/index.jsp");
		}
	}
	
}
