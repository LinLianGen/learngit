package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 商品表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:17:47
 */
public class MtGoods extends BaseModel<MtGoods> {

	private static final long serialVersionUID = 1L;
	
	public static final MtGoods dao = new MtGoods();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_goods";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	
	/**
	* @方法名: 获取商品销量排名
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 3:39:01
	* @修改:
	*/ 
	public List<Record> getGoods(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  ");
		sql.append(" order by sale desc , addTime desc");
		sql.append(" limit 1 , 20 ");
		List<Record> list=Db.find(sql.toString());	
		return list;
	}
	
	/**
	* @方法名: 获取热门零食商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 3:39:01
	* @修改:
	*/ 
	public List<Record> getHotSnackGoods(){
		MtGoodsType goodsType = new MtGoodsType();
		String goodsId = goodsType.getHotSnacksTypeId();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  and recomment = 1 and type_id = ? ");
		sql.append(" order by sale desc , addTime desc");
		sql.append(" limit 1 , 10 ");
		List<Record> list=Db.find(sql.toString() , goodsId);	
		return list;
	}
	
	/**
	* @方法名: 获取热门商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 4:06:22
	* @修改:
	*/ 
	public List<Record> getHotGoods(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.* from ");
		sql.append(this.tableName());
		sql.append(" t1 where t1.isDel =0  and t1.recomment = 1  ");
		sql.append(" order by t1.sale desc , t1.addTime desc ");
		List<Record> list=Db.find(sql.toString());	
		return list;
	}
	
	/**
	* @方法名: 获取热门商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 4:06:22
	* @修改:
	*/ 
	public List<Record> getGoodsFromCategory(String categoryId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  and category_id = ? ");
		sql.append(" order by recomment asc ,  addTime desc");
		List<Record> list=Db.find(sql.toString(),categoryId);	
		return list;
	}
	
	/**
	* @方法名: 根据ID 获取商品详细信息
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 10:06:22
	* @修改:
	*/ 
	public Record getGoodsInfo(String goodsId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0  and id = ? ");
		Record list=Db.findFirst(sql.toString(),goodsId);	
		return list;
	}
	
	/**
	* @方法名: 后台添加商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月6 2:38:32
	* @修改:
	*/ 
	public boolean saveGoods(Record record){
		return Db.save(this.tableName(), record);
	}

}
