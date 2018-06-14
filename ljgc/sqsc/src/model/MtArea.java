package model;

import java.util.List;

import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 16.区域表 model
 *
 * @version 1.0
 * @since 2018-2-25 14:18:38
 */
public class MtArea extends BaseModel<MtArea> {

	private static final long serialVersionUID = 1L;
	
	public static final MtArea dao = new MtArea();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_area";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	
	/**
	* @方法名: 获取地区详情
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月5 12:56:33
	* @修改:
	*/ 
	public List<Record> getAreaInfo(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select  * from  ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0 ");
		List<Record> list = Db.find(sql.toString());
		return list;
	}
	
	
}
