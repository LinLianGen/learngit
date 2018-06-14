package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 限时抢购表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:25:28
 */
public class MtLimitedBuying extends BaseModel<MtLimitedBuying> {

	private static final long serialVersionUID = 1L;
	
	public static final MtLimitedBuying dao = new MtLimitedBuying();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_limited_buying";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	/**
	* @方法名: 获取抢购商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 3:39:01
	* @修改:
	*/ 
	public List<Record> getLimited(int type){
		StringBuffer sql = new StringBuffer();
		sql.append(" select l.*  , g.goods_name , g.img_url from ");
		sql.append(this.tableName());
		sql.append("  l inner join 2018a_01_mt_goods g on g.id = l.goodsId ");
		sql.append(" where l.isDel = 0 and l.limited_num > 0 and l.type = ? and l.status = 1  ");
		sql.append(" order by l.addTime desc");
		List<Record> list=Db.find(sql.toString() ,type );	
		return list;
	}
	
	
}
