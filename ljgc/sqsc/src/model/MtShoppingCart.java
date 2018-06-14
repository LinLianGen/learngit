package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

import utils.linUtils.Utils;

/**
 * 购物车表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:28:15
 */
public class MtShoppingCart extends BaseModel<MtShoppingCart> {

	private static final long serialVersionUID = 1L;
	
	public static final MtShoppingCart dao = new MtShoppingCart();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_shopping_cart";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	
	/**
	* @方法名: 获取用户购物车信息
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 4:49:17
	* @修改:
	*/ 
	public List<HashMap<String, Object>> getUserShoppingCart(String userId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  ");
		sql.append( this.tableName());
		sql.append(" where userId = ? and isDel = 0 ");
		sql.append(" order by addTime desc ");
		List<Record> list = Db.find(sql.toString(),userId);
		List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
		HashMap<String , Object> map = null;
		for (Record record : list) {
			map = new HashMap<String, Object>();
			String goodsId = record.getStr("goodsId");
			MtGoods goods = new MtGoods();
			Record goodsRecord = goods.getGoodsInfo(goodsId);
			map.put("goodsId", goodsId);
			map.put("goods_name", goodsRecord.get("goods_name"));
			map.put("price", goodsRecord.get("price"));
			map.put("img_url", goodsRecord.get("img_url"));
			map.put("goods_num", goodsRecord.get("goods_num"));
			map.put("content", goodsRecord.get("content"));
			list2.add(map);
		}
		return list2;
	}
	
	/**
	* @方法名: 计算用户购物车 价格
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 4:51:11
	* @修改:
	*/ 
	public double getUserShoppintCartMoney(String userId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select price , goods_num  from ");
		sql.append( this.tableName());
		sql.append(" where  userId = ? and isDel = 0  ");
		List<Record> list = Db.find(sql.toString() , userId);
		double  shoppintCartMoney = 0.0;
		for (Record record : list) {
			shoppintCartMoney = shoppintCartMoney+ record.getDouble("price")*record.getInt("goods_num");
		}
		return shoppintCartMoney;
	}

	
	/**
	* @方法名: 获取购物车内商品的ID 去查询
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:49:17
	* @修改:
	*/ 
	public List<Record>  getShoppingCartGoodsId(String userId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select goodsId from  ");
		sql.append( this.tableName());
		sql.append(" where userId = ? and isDel = 0 ");
		sql.append(" order by addTime desc ");
		List<Record> list = Db.find(sql.toString() ,userId);
		return list;
	}
	
	
	/**
	* @方法名: 删除购物车商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 11:59:04
	* @修改:
	*/ 
	public boolean delShoppingCartGoods(String userId , String goodsId){
		StringBuffer sql =new StringBuffer();
		sql.append(" update ");
		sql.append(this.tableName());
		sql.append("  set isDel = 1 ,updTime = ? ");
		sql.append("  where userId= ? and goodsId = ? ");
		int i = Db.update(sql.toString() , Utils.getStrFormDateTwo(new Date()) , userId , goodsId);
		return i>0;
	}
	
}
