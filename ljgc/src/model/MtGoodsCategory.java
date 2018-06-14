package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 商品类目表 model
 *
 * @version 1.0
 * @since 2018-2-25 16:59:45
 */
public class MtGoodsCategory extends BaseModel<MtGoodsCategory> {

	private static final long serialVersionUID = 1L;
	
	public static final MtGoodsCategory dao = new MtGoodsCategory();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_goods_category";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	/**
	* @方法名: 获取所有热门商品类目
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 5:06:22
	* @修改:
	*/ 
	public List<Record> getHotGoodsCategory(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  and recomment = 1  ");
		sql.append(" order by addTime desc");
		List<Record> list=Db.find(sql.toString());	
		return list;
	}
	
	/**
	* @方法名: 根据类型ID 获取所有热门商品类目 
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 5:18:22
	* @修改:
	*/ 
	public List<Record> getHotGoodsCategory(String type_id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  and type_id = ? ");
		sql.append(" order by recomment asc , addTime desc  ");
		List<Record> list=Db.find(sql.toString(),type_id);	
		return list;
	}
	
	/**
	* @方法名: 添加商品类目
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月6 2:38:32
	* @修改:
	*/ 
	public boolean saveCategory(Record record){
		return Db.save(this.tableName(), record);
	}
	
	/**
	* @方法名: 获取类目信息
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月6 3:42:13
	* @修改:
	*/ 
	public Record getCategoryInfo(String catagoryId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  and id = ? ");
		Record list=Db.findFirst(sql.toString() ,catagoryId);	
		return list;
	}
	
	/**
	* @方法名: 获取类目信息 通过类目ID
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月6 3:42:13
	* @修改:
	*/ 
	public List<Record> getCategoryList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0   ");
		sql.append(" order by recomment asc , addTime desc");
		List<Record> list=Db.find(sql.toString());	
		return list;
	}
}
