package model;

import java.util.List;

import javax.security.auth.login.FailedLoginException;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 商品库表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:11:50
 */
public class MtGoodsLibrary extends BaseModel<MtGoodsLibrary> {

	private static final long serialVersionUID = 1L;
	
	public static final MtGoodsLibrary dao = new MtGoodsLibrary();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_goods_library";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	
	/**
	* @方法名: 商品减库存
	* @参数:lessNum:应减数量
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 17:12:22
	* @修改:
	*/ 
	public boolean lessGoods(int lessNum ,String goodsId){
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0 and goodsId = ?");
		sql.append(" order by end_time asc ");
		List<Record> list=Db.find(sql.toString(),goodsId);	
		for(int i = 0 ; i<lessNum ;i++){
			String goodLibraryId = list.get(i).getStr("id");
			flag = Db.delete(this.tableName(), list.get(i));
			if(!flag){
				return false;
			}
		}
		return flag;
	}

	
	/**
	* @方法名: 根据区域 , 商品ID 查询对应信息
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:21:06
	* @修改:
	*/ 
	public List<Record> getGoodsInfoFromAreaId(String areaId , String goodsId){
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from ");
			sql.append(this.tableName());
			sql.append(" where goodsId = ? and isDel = 0 and areaId = ? and status = 1 ");
			sql.append(" order by  end_time asc ,  addTime desc");
			List<Record> list = Db.find(sql.toString() , goodsId ,areaId);
			return list; 
	}
	
	
	/**
	* @方法名: 通过商品Id获取商品库中的商品保质期最短的商品的保质期  end_time
	* @参数: end_time ,goods_date , goods_date_long
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月2 2:43:13
	* @修改:
	*/ 
	public Record getGoodsEndTime(String goodsId , String areaId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(id) num , end_time ,goods_date , goods_date_long from  ");
		sql.append(this.tableName());
		sql.append(" where goodsId = ? and areaId = ?  and  isDel = 0 and status = 1 ");
		sql.append(" order by end_time asc , addTime desc ");
		Record record = Db.findFirst(sql.toString() , goodsId ,areaId);
		if(record==null){
			return null;
		}else{
			return record;
		}
		
	}
	
}
