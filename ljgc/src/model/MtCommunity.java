package model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 小区表 model
 *
 * @version 1.0
 * @since 2018-2-27 10:53:22
 */
public class MtCommunity extends BaseModel<MtCommunity> {

	private static final long serialVersionUID = 1L;
	
	public static final MtCommunity dao = new MtCommunity();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_community";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	

	/**
	* @方法名: 通过小区ID   查询所在的区域ID
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:14:36
	* @修改:
	*/ 
	public String getAreaId(String communityId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select areaId from ");
		sql.append(this.tableName());
		sql.append(" where id = ?  and isDel = 0 ");
		Record record =  Db.findFirst(sql.toString() , communityId);
		return record.getStr("areaId");
	}	
}
