package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.expr.ast.Id;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 15.订单评价表 model
 *
 * @version 1.0
 * @since 2018-2-25 11:41:44
 */
public class MtOrderAssess extends BaseModel<MtOrderAssess> {

	private static final long serialVersionUID = 1L;
	
	public static final MtOrderAssess dao = new MtOrderAssess();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_order_assess";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	public double getStarFromCourier(String courierId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from  ");
		sql.append(this.tableName());
		sql.append(" where courier_id = ? and isDel = 0 ");
		double i = 0.0;
		List<Record> list = Db.find(sql.toString(),courierId);
		for (Record record : list) {
			i = i + record.getInt("grade");
		}
		i =Math.ceil( i / list.size());
		return i;
	}

}
