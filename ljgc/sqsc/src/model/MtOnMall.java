package model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 关于香岛表 model
 *
 * @version 1.0
 * @since 2018-2-24 18:12:17
 */
public class MtOnMall extends BaseModel<MtOnMall> {

	private static final long serialVersionUID = 1L;
	
	public static final MtOnMall dao = new MtOnMall();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_on_mall";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	

		public Record onMall(){
			StringBuffer sql = new StringBuffer();
			sql.append(" select  * from ");
			sql.append( this.tableName());
			sql.append(" where  isDel = 0 ");
			Record record= Db.findFirst(sql.toString());
			record.remove("isDel");
			record.remove("id");
			record.remove("updTime");
			record.remove("addTime");
			return record;
		}
}
