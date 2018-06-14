
package controller.admin;


import utils.interceptor.LoginAdminInterceptor;
import utils.linUtils.Utils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import model.MtGoodsCategory;
import model.MtGoodsType;



@RouteViewPath("admin")
@Before(LoginAdminInterceptor.class)
public class MtGoodsCategoryController extends CRUD {
	@Override
	protected String route() {
		return "admin/mtGoodsCategory";
	}
	
	
	public void showList(){
		if (isPost()) {
			Record record = getSessionAttr("admin");
			System.out.println(record.toJson());
		    int page = getParaToInt("offset", 1); 
		    int pageSize = getParaToInt("limit", 10);
		    page = (page / pageSize);
		    page++;
		    JSONObject where = new JSONObject();
		    String jsonStr = getPara("where", "{}");
		    JSONObject json = new JSONObject();
		    json = JSONObject.parseObject(jsonStr);
		    MtGoodsCategory model = new MtGoodsCategory();
		    Page<Record> result = model.getPaging(page, pageSize, where,json);
		    renderJSON(true, "请求成功", result);
		} else {
		    renderJsp("mtGoodsCategory/index.jsp");
		}
	}
	
	
	/**
	* @方法名:  进入添加查询
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月6 1:35:17
	* @修改:
	*/ 
	public void mtGoodsCategoryAdd(){
		MtGoodsType mtGoodsType = new MtGoodsType();
		List<Record> typeList = mtGoodsType.getType();
		setAttr("typeList" , typeList);	
		renderJsp("mtGoodsCategory/add.jsp");
	}
	
	/**
	* @方法名:   添加
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月6 1:35:27
	* @修改:
	*/ 
	public void modifyMtGoodsCategory(){
		String jsonStr = getPara("where", "{}");
	    JSONObject json = new JSONObject();
	    json = JSONObject.parseObject(jsonStr);
		System.out.println(jsonStr+"-----------------------");
		MtGoodsType goodsType = new MtGoodsType();
		Record record = goodsType.getType(json.getString("type_id"));
		json.put("type_name", record.getStr("type_name"));
        json.remove("id");
		Set<Entry<String, Object>> js = json.entrySet();
        String idString = Utils.getUUID();
        Record record2 = new Record().set("type_name", record.getStr("type_name"))
        							 .set("id", idString)
        							 .set("isDel", 0)
        							 .set("addTime", Utils.getStrFormDateTwo(new Date()));	
        for (Entry<String, Object> entry : js) {
        	record2.set(entry.getKey(), entry.getValue());
		}
        MtGoodsCategory goodsCategory = new MtGoodsCategory();
        boolean flag =   goodsCategory.saveCategory(record2);
       
        renderJSON(flag, "请求成功");
		
	}
	
}
