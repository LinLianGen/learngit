package model;

import com.xiaheng.core.jfinal.BaseModel;

/**
 * 订单商品表 model
 *
 * @version 1.0
 * @since 2018-2-24 17:32:59
 */
public class MtOrderGoods extends BaseModel<MtOrderGoods> {

	private static final long serialVersionUID = 1L;
	
	public static final MtOrderGoods dao = new MtOrderGoods();
	
	
	@Override
	public String tableName() {
		return "2018a_01_mt_order_goods";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	

}
