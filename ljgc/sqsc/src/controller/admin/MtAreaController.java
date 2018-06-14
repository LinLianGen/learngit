
package controller.admin;


import utils.interceptor.LoginAdminInterceptor;
import utils.linUtils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

import model.MtArea;
import model.MtGoods;
import model.MtGoodsCategory;


@RouteViewPath("admin")
@Before(LoginAdminInterceptor.class)
public class MtAreaController extends CRUD {
	@Override
	protected String route() {
		return "admin/mtArea";
	}
	
	
	public void showList(){
		if (isPost()) {
		    int page = getParaToInt("offset", 1); 
		    int pageSize = getParaToInt("limit", 10);
		    page = (page / pageSize);
		    page++;
		    JSONObject where = new JSONObject();
		    String jsonStr = getPara("where", "{}");
		    JSONObject json = new JSONObject();
		    json = JSONObject.parseObject(jsonStr);
		    MtArea model = new MtArea();
		    Page<Record> result = model.getPaging(page, pageSize, where,json);
		    renderJSON(true, "请求成功", result);
		} else {
		    renderJsp("mtArea/index.jsp");
		}
	}
	
	/**
	* @方法名: 添加商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月6 3:32:00
	* @修改:
	*/ 
	public void mtGoodsAdd(){
		MtGoodsCategory goodsCategory = new MtGoodsCategory();
		List<Record> categoryList = goodsCategory.getCategoryList();
		setAttr("categoryList" , categoryList);	
		renderJsp("mtGoods/add.jsp");
	}
	
	/**
	* @方法名:   添加
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月6 15:35:27
	* @修改:
	*/ 
	public void addMtGoods(){
		String jsonStr = getPara("where", "{}");
	    JSONObject json = new JSONObject();
	    json = JSONObject.parseObject(jsonStr);
		System.out.println(jsonStr+"-----------------------");
		MtGoodsCategory goodsType = new MtGoodsCategory();
		Record record = goodsType.getCategoryInfo(json.getString("category_id"));
        json.remove("id");
        json.remove("file");
		Set<Entry<String, Object>> js = json.entrySet();
        String idString = Utils.getUUID();
        Record record2 = new Record().set("type_name", record.getStr("type_name"))
   				        			 .set("type_id", record.getStr("type_id"))
   				        			 .set("category_name", record.getStr("category_name"))
				        			 .set("category_id", record.getStr("category_id"))
        							 .set("id", idString)
        							 .set("isDel", 0)
        							 .set("addTime", Utils.getStrFormDateTwo(new Date()));	
        for (Entry<String, Object> entry : js) {
        	record2.set(entry.getKey(), entry.getValue());
		}
        MtGoods goods= new MtGoods();
        boolean flag =   goods.saveGoods(record2);
       
        renderJSON(flag, "请求成功");
		
	}
	
}
