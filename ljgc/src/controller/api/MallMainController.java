package controller.api;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

import model.MtArea;
import model.MtGoods;
import model.MtGoodsCategory;
import model.MtGoodsType;
import model.MtLimitedBuying;
import model.MtOnMall;
import model.MtPic;
import model.MtShoppingCart;
import model.MtUser;
import utils.linUtils.Utils;

@RouteViewPath("api")
public class MallMainController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/mall";
	}
		
	
	
	/**
	* @方法名: 用户登入主业 
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 10:02:57
	* @修改:
	*/ 
	public void mall(){
		String areaId = "";
		try {
			String longitude = getPara("longitude");
			String latitude = getPara("latitude");
			double l  = Double.valueOf(longitude);
			double d  = Double.valueOf(latitude);
			MtArea mtArea = new MtArea();
			Map<Float, String> map = new HashMap<Float, String>();
			List<Float> list2 = new ArrayList<Float>();
			List<Record> list =mtArea.getAreaInfo();
				for (Record record : list) {
					double l2 = record.getDouble("longitude");
					double d2 = record.getDouble("latitude");
					float leave = Utils.calculateLineDistance(d , l, d2, l2);
					map.put(leave, record.getStr("id"));
					list2.add(leave);
				}
				Collections.sort(list2);
				float leave2 = list2.get(0);
				areaId = map.get(leave2);
			} catch (Exception e) {
				// TODO: handle exception
			}	
		//首页图片
		MtPic mtPic = new MtPic();
		List<Record> picList = mtPic.getPic();
		MtGoodsType goodsType = new MtGoodsType();
		List<Record> goodsTypeList =goodsType.getType();
		MtLimitedBuying buying = new MtLimitedBuying();
		Calendar calendar =  Calendar.getInstance();
		int i = calendar.DAY_OF_WEEK;
		List<Record> limitedList = buying.getLimited(i);
		MtGoods goods = new MtGoods();
		List<Record> goodsList = goods.getGoods();
		List<Record> hotSnacksGoodsList = goods.getHotSnackGoods();
		List<Record> hotGoodsList = goods.getHotGoods();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("areaId", areaId);
		//首页图
		jsonObject.put("picList", picList);
		//商品类型
		jsonObject.put("goodsTypeList", goodsTypeList);
		//抢购商品
		jsonObject.put("limitedList", limitedList);
		//销量排名
		jsonObject.put("goodsList", goodsList);
		//热门零食
		jsonObject.put("hotSnacksGoodsList", hotSnacksGoodsList);
		//热门商品
		jsonObject.put("hotGoodsList", hotGoodsList);
		
		renderJSON(true, "请求成功",jsonObject);
	}	
	
	/**
	* @方法名: 获取类目 默认第一行：热门推荐类目
	* @参数:
	* @输出:goodsTypeList：商品类型集合   categoryList：商品类目集合
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 5:08:14
	* @修改:
	*/ 
	public void classification(){
		MtGoodsType goodsType = new MtGoodsType();
		List<Record> goodsTypeList =goodsType.getType();	
		MtGoodsCategory category = new MtGoodsCategory();
		List<Record> categoryList = category.getHotGoodsCategory();
		JSONObject js = new JSONObject();
		js.put("goodsTypeList", goodsTypeList);
		js.put("categoryList", categoryList);
		renderJSON(true , "请求成功",js);
	}
	
	
	/**
	* @方法名: 根据类型获取商品类目
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 5:09:14
	* @修改:
	*/ 
	public void getCategoryFromType(){
		String type_id = getPara("type_id");
		MtGoodsCategory category = new MtGoodsCategory();
		List<Record> categoryList = category.getHotGoodsCategory(type_id);
		JSONObject js = new JSONObject();
		js.put("categoryList", categoryList);
		renderJSON(true , "请求成功",js);
	}
	
	/**
	* @方法名: 根据商品类目获得商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 5:09:14
	* @修改:
	*/ 
	public void getGoodsFromCategory(){
		//类目ID
		String category_id = getPara("category_id");
		MtGoods goods = new MtGoods();
		List<Record> list = goods.getGoodsFromCategory(category_id);
		JSONObject js = new JSONObject();
		js.put("goodsList", list);
		renderJSON(true , "请求成功",js);
	}
	
	/**
	* @方法名: 用户购物车
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月25 5:34:48
	* @修改:
	*/ 
	public void shoppingCart(){
		String userId = getPara("userId");
		MtShoppingCart shoppingCart = new MtShoppingCart();
		List<HashMap<String, Object>> list = shoppingCart.getUserShoppingCart(userId);
		JSONObject js = new JSONObject();
		js.put("shoppingCartItem", list);
		renderJSON(true, "请求成功" ,js);
	} 
	
	/**
	* @方法名: 个人中心
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 10:10:27
	* @修改:
	*/ 
	public void userCenter(){
		String userId = getPara("userId");
		MtUser mtUser = new MtUser();
		try {
			// TODO: 返回数据
			JSONObject js = mtUser.userCenter(userId);
			Record record = mtUser.getUserInfo(userId);
			js.put("user_name", record.getStr("user_name"));
			js.put("phone", record.getStr("phone"));
			js.put("img_url", record.getStr("img"));
			renderJSON(true, "请求成功" , js);
			return ;
		} catch (Exception e) {
			
		}
		renderJSON(false, "请求失败" ); 
	}
	
	/**
	* @方法名: 关于驿家家超市
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 11:50:51
	* @修改:
	*/ 
	public  void  onSupermarket(){
		MtOnMall mall = new MtOnMall();
		Record record = mall.onMall();
		renderJSON(true, "请求成功", record);
	}
}
