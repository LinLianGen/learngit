package controller.api;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import controller.admin.wxback.ClientCustomSSL;

@RouteViewPath("api")
public class WeiXinHttps extends BaseController{

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/start";
	}
	 
	/*public void start(){
		try {
			//ClientCustomSSL.start();
		} catch (Exception e) {
		
		}
	}*/
	
	/*public void myVersion(){
		
		String type  = getPara("type","");
		IosAndroid ia = new IosAndroid();
		Record record = ia.getNewVersion(type);
		String id = record.getStr("id");
		String version_num = record.getStr("version_num");
		String version_name = record.getStr("version_name");
		String note = record.getStr("note");
		String link_url = record.getStr("link_url");
		JSONObject js = new JSONObject();
		js.put(id, id);
		js.put(version_num, version_num);
		js.put(version_name, version_name);
		js.put(note, note);
		js.put(link_url, link_url);
		//System.out.println("myVersion:"+js.toString());
		renderJSON(true, "成功", record); 
	
}	
	*/
}
