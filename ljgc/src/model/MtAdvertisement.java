package model;

import com.xiaheng.core.jfinal.BaseModel;

/**
 *  首页广告表 model
 *
 * @version 1.0
 * @since 2018-3-15 10:49:32
 */
public class MtAdvertisement extends BaseModel<MtAdvertisement> {

	private static final long serialVersionUID = 1L;
	
	public static final MtAdvertisement dao = new MtAdvertisement();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_advertisement";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	

}
