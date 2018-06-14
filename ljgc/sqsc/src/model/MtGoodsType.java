package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.expr.ast.Map;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 商品类型表 model
 *
 * @version 1.0
 * @since 2018-2-24 16:59:11
 */
public class MtGoodsType extends BaseModel<MtGoodsType> {

	private static final long serialVersionUID = 1L;
	
	public static final MtGoodsType dao = new MtGoodsType();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_goods_type";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	/**
	* @方法名: 获取商品类型
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 3:39:01
	* @修改:
	*/ 
	public List<Record> getType(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  ");
		sql.append(" order by goods_sort asc ,addTime desc");
		List<Record> list=Db.find(sql.toString());	
		return list;
	}
	
	
	/**
	* @方法名: 通过类型ID获取商品类型
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月06 3:39:01
	* @修改:
	*/ 
	public Record getType(String type_id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  and  id = ? ");
		sql.append(" order by goods_sort asc ,addTime desc");
		Record list=Db.findFirst(sql.toString() , type_id);	
		return list;
	}
	/**
	* @方法名: 获取热门零食类型ID
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 3:39:01
	* @修改:
	*/ 
	public String getHotSnacksTypeId(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select id from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0 and type_name = '休闲零食'  ");
		Record record=Db.findFirst(sql.toString());	
		return record.getStr("id");
	}
	
	
	
	

}
