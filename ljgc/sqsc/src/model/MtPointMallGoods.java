package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 积分商城商品表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:59:23
 */
public class MtPointMallGoods extends BaseModel<MtPointMallGoods> {

	private static final long serialVersionUID = 1L;
	
	public static final MtPointMallGoods dao = new MtPointMallGoods();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_point_mall_goods";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	/**
	* @方法名: 通过商家  展示积分兑换商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 3:56:40
	* @修改:
	*/ 
	public List<Map<String, Object>> getPointGoodsList(String bussinessId){
		List<Map<String, Object>> pointGoodsList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0 and bussinessId = ?  ");
		sql.append("  order by addTime desc");
		List<Record> list = Db.find(sql.toString());
		for (Record record : list) {
			map = new HashMap<String, Object>();
			map.put("need_point", record.get("need_point"));
			map.put("goods_name", record.get("goods_name"));
			map.put("goods_detail", record.get("goods_detail"));
			map.put("goods_img", record.get("goods_img"));
			map.put("point_goodsId", record.get("id"));
			pointGoodsList.add(map);
		}
		return pointGoodsList;
	}
	
	
	/**
	* @方法名: 通过积分商品ID 兑换商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 3:56:40
	* @修改:
	*/ 
	public boolean reduceGoodsNum(String pointGoodsId){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0 and id = ?  ");
		Record record = Db.findFirst(sql.toString());
		int goods_num = record.getInt("goods_num");
		if(goods_num == 0 ){
			return false;
		}else{
			goods_num = goods_num - 1 ;
			record.set("goods_num", goods_num);
			boolean i =Db.update(this.tableName(), record);
			return i;
		}
	}
	
	
	/**
	* @方法名: 查询积分商品详情
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 3:56:40
	* @修改:
	*/ 
	public Record getPointGoodsInfo(String pointGoodsId){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append(this.tableName());
		sql.append(" where isDel = 0 and id = ?  ");
		Record record = Db.findFirst(sql.toString());
		return record;
	}
	
}
