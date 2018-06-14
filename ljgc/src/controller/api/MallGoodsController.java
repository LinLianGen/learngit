package controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

import model.MtArea;
import model.MtCommunity;
import model.MtGoods;
import model.MtGoodsLibrary;
import model.MtShoppingCart;
@RouteViewPath("api")
public class MallGoodsController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/goods";
	}
		
	/**
	* @方法名: 商品详情
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 5:03:02
	* @修改:
	*/ 
	public void goodsDetail(){
		String goodsId = getPara("goodsId");
		String userId = getPara("userId");
		String areaId = getPara("areaId");
		MtGoods goods = new MtGoods();
		Record record = goods.getGoodsInfo(goodsId);
		JSONObject js = new JSONObject();
		MtGoodsLibrary goodsLibrary = new MtGoodsLibrary();
		Record library = goodsLibrary.getGoodsEndTime(goodsId ,areaId);
		if(library!=null){
			js.put("end_time", library.get("end_time"));
			js.put("inventory", library.getLong("num"));
			js.put("goods_date", library.get("goods_date"));			
			js.put("goods_date_long", library.get("goods_date_long"));
		}
		js.put("goods_name", record.get("goods_name"));
		js.put("goods_name", record.get("goods_name"));
		js.put("img_url", record.get("img_url"));
		js.put("price", record.get("price"));
		js.put("sale", record.get("sale"));
		js.put("goods_detail", record.get("goods_detail"));
		MtShoppingCart cart = new MtShoppingCart();
		double shoppingCartMoney = cart.getUserShoppintCartMoney(userId);
		js.put("shoppingCartMoney", shoppingCartMoney);
		renderJSON(true, "请求成功", js);
	}

	/**
	* @方法名: 选择了地址 进入购物车判断商品是否可以被购买
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 10:56:38
	* @修改:
	*/ 
	public void goOrder(){
		String userId = getPara("userId");
		String communityId = getPara("communityId");
		MtCommunity community = new MtCommunity();
		MtShoppingCart cart = new MtShoppingCart();
		List<Map<String , Object>> canBuyGoodsList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map =  null;
		List<Record> list = cart.getShoppingCartGoodsId(userId);
		for (Record record : list) {
			String goodsId = record.getStr("goodsId");
			MtGoodsLibrary goodsLibrary = new MtGoodsLibrary();
			String areaId = community.getAreaId(communityId);
			List<Record> listT = goodsLibrary.getGoodsInfoFromAreaId(areaId, goodsId);
			//商品库中的内存
			int i = listT.size();
			if(i>0){
				MtGoods goods = new MtGoods();
				Record record2 = goods.getGoodsInfo(goodsId);
				map = new HashMap<String, Object>();
				map.put("maxNum", i);
				map.put("goodsId", goodsId);
				map.put("goods_name", record2.getStr("goods_name"));
				map.put("price", record2.getDouble("price"));
				map.put("img_url", record2.getStr("img_url"));
				map.put("goods_num", record2.getStr("goods_num"));
				map.put("content", record2.getStr("content"));
				canBuyGoodsList.add(map);
			}
			
		}
		JSONObject js = new JSONObject();
		js.put("canBuyGoodsList", canBuyGoodsList);
		renderJSON(true, "请求成功", js);
	}
	
	/**
	* @方法名: 删除购物车商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 12:03:39
	* @修改:
	*/ 
	public void delShoppingCartGoods(){
		String userId = getPara("userId");
		String goodsId = getPara("goodsId");
		MtShoppingCart mtShoppingCart = new MtShoppingCart();
		boolean flag =mtShoppingCart.delShoppingCartGoods(userId, goodsId);
		renderJSON(flag, "请求成功");
	}
}
