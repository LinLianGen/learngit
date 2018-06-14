package controller.admin;

import model.Admin;
import utils.linUtils.Utils;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.EncodeUtils;

@RouteViewPath("admin/")
public class AdminController extends CRUD {

	@Override
	protected String route() {
		return "/admin/admin";
	}

	/**
	 * @方法名:用户列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月13 5:34:07
	 * @修改:
	 */
	public void showAdminList() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			Page<Record> result = new Admin().getList(page, pageSize);
			renderJSON(true, "数据已更新", result);

		} else {
			renderJsp("admin/index.jsp");
		}
	}

	/**
	 * @方法名:新增账号
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年8月1 4:32:00
	 * @修改:
	 */

	public void add() {
		String where = getPara("where");
		JSONObject json = JSONObject.parseObject(where);
		String password = json.getString("password");
		password = EncodeUtils.MD5Encode(password);
		json.put("password", password);
		json.put("taday_num", 0);
		json.put("before_time", Utils.getStrFormDate(new Date()));
		Admin admin = new Admin();
		CodeBean<?> bean = admin.modifyModel(json);
		renderJSON(bean);
	}

}
