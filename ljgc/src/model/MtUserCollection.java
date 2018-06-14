package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

import utils.linUtils.Utils;

/**
 * 用户收藏表 model
 *
 * @version 1.0
 * @since 2018-2-24 18:06:04
 */
public class MtUserCollection extends BaseModel<MtUserCollection> {

	private static final long serialVersionUID = 1L;
	
	public static final MtUserCollection dao = new MtUserCollection();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_user_collection";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	/**
	* @方法名: 添加商品至我的搜藏
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 5:23:11
	* @修改:
	*/ 
	public String saveConllection(String userId , String goodsId){
		String idString = Utils.getUUID();
		Record record =new Record().set("id", idString)
				.set("userId", userId)
				.set("goodsId", goodsId)
				.set("isDel", 0)
				.set("addTime", Utils.getStrFormDateTwo(new Date()));
		Db.save(this.tableName(), record);
		return idString;
	}

	/**
	* @方法名:  查询该用户的收藏
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月28 11:53:03
	* @修改:
	*/ 
public List<Map<String, Object>> getUserCollection(String userId){
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select goodsId from ");
		sql.append(this.tableName());
		sql.append(" where userId = ? ");
		sql.append("  order by addTime desc ");
		List<Record> list = Db.find(sql.toString() , userId);
		List<Map<String, Object>> collectionList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (Record record : list) {
			String goodsId = record.getStr("goodsId");
			MtGoods goods = new MtGoods();
			Record record2 = goods.getGoodsInfo(goodsId);
			map = new HashMap<String, Object>();
			map.put("img_url", record2.getStr("img_url"));
			map.put("goods_name", record2.getStr("goods_name"));
			map.put("price", record2.get("price"));
			map.put("goods_num", record2.get("goods_num"));
			map.put("sale", record2.get("sale"));
			map.put("goodsId", record2.getStr("id"));
			collectionList.add(map);
		}
		return collectionList;
	}
}
