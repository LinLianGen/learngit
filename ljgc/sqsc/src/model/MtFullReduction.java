package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 满减金额表 model
 *
 * @version 1.0
 * @since 2018-3-5 10:31:49
 */
public class MtFullReduction extends BaseModel<MtFullReduction> {

	private static final long serialVersionUID = 1L;
	
	public static final MtFullReduction dao = new MtFullReduction();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_full_reduction";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	
	/**
	* @方法名: 获取满？金额免邮费
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 3:39:01
	* @修改:
	*/ 
	public Record getFullReduction(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  ");
		Record list=Db.findFirst(sql.toString());	
		return list;
	}

}
