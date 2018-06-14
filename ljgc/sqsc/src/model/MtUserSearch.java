package model;

import com.xiaheng.core.jfinal.BaseModel;

/**
 * 19.用户搜索表 model
 *
 * @version 1.0
 * @since 2018-2-25 14:33:37
 */
public class MtUserSearch extends BaseModel<MtUserSearch> {

	private static final long serialVersionUID = 1L;
	
	public static final MtUserSearch dao = new MtUserSearch();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_user_search";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	

}
