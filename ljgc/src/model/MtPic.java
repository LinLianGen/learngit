package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 首页图片表 model
 *
 * @version 1.0
 * @since 2018-2-24 16:56:59
 */
public class MtPic extends BaseModel<MtPic> {

	private static final long serialVersionUID = 1L;
	
	public static final MtPic dao = new MtPic();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_pic";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	
	
	public List<Record> getPic(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select  * from ");
		sql.append( this.tableName() );
		sql.append("  where isDel = 0 ");
		List<Record> list = Db.find(sql.toString());
		return list;
	}

}
