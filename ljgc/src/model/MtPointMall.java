package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 积分商城表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:52:58
 */
public class MtPointMall extends BaseModel<MtPointMall> {

	private static final long serialVersionUID = 1L;
	
	public static final MtPointMall dao = new MtPointMall();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_point_mall";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	/**
	* @方法名: 获取积分商城
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 3:32:42
	* @修改:
	*/ 
	public List<Map<String, Object>> getPointMallList(){
		List<Map<String, Object>> pointMallList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0 ");
		sql.append("  order by addTime desc ");
		List<Record> list = Db.find(sql.toString());
		for (Record record : list) {
			map = new HashMap<String, Object>();
			map.put("bussiness_name", record.get("bussiness_name"));
			map.put("bussiness_address", record.get("bussiness_address"));
			map.put("bussiness_phone", record.get("bussiness_phone"));
			map.put("bussiness_img", record.get("bussiness_img"));
			map.put("bussinessId", record.get("id"));
			pointMallList.add(map);
		}
		return pointMallList;
	}

}
