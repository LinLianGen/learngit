package controller.api;


import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

import model.MtPointMall;
import model.MtPointMallGoods;
import model.MtUser;
import model.MtUserCoupon;
@RouteViewPath("api")
public class MallPointController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/point";
	}
		
	/**
	* @方法名: 积分商城，商店展示
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 3:52:29
	* @修改:
	*/ 
	public void userPointMall(){
		String userId = getPara("userId");
		MtPointMall mtPointMall = new MtPointMall();
		List<Map<String, Object>> list  =mtPointMall.getPointMallList();
		MtUser mtUser = new MtUser();
		Record record =mtUser.getUserInfo(userId);	
		JSONObject js = new JSONObject();
		js.put("point", record.getInt("point"));
		js.put("mtPointMall", list);
		renderJSON(true,"请求成功", js);
	}
	
	/**
	* @方法名: 积分门店兑换商品展示
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 3:54:55
	* @修改:
	*/ 
	public void getPointGoodsFromBussinessId(){
		String bussinessId =getPara("bussinessId");
		MtPointMallGoods mtPointMallGoods = new MtPointMallGoods();
		List<Map<String, Object>> list= mtPointMallGoods.getPointGoodsList(bussinessId);
		JSONObject js = new JSONObject();
		js.put("pointGoodsList", list);
		renderJSON(true, "请求成功", js);
		
	}
	
	/**
	* @方法名: 兑换商品
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 4:14:38
	* @修改:
	*/ 
	public void exchangePointGoods(){
		String pointGoodsId = getPara("point_goodsId");
		String userId  =getPara("userId");
		MtUser mtUser = new MtUser();
		Record record=  mtUser.getUserInfo(userId);
		int point = record.getInt("point");
		MtPointMallGoods mtPointMallGoods =new MtPointMallGoods();
		Record pointGoodsInfo = mtPointMallGoods.getPointGoodsInfo(pointGoodsId);
		int goodsPoint = record.getInt("need_point");
		if(point>=goodsPoint){
			boolean flag = mtPointMallGoods.reduceGoodsNum(pointGoodsId);
			if(flag){
				MtUserCoupon mtUserCoupon = new MtUserCoupon();
				String couponId = mtUserCoupon.addUserPoupon(userId, "3", "", "", 
							0, 0, pointGoodsInfo.getStr("goods_name"), pointGoodsInfo.getStr("goods_detail"), 
							pointGoodsInfo.getStr("goods_img"), pointGoodsInfo.getStr("address"));
			//TODO  需要在pointGoodsInfo 库中加入bussiness_address 这个字段
				JSONObject js = new JSONObject();
				js.put("couponId", couponId);
				renderJSON(flag, "请求成功" ,js);
			}else{
				//TODO  商品没有库存
				renderJSON(flag, "商品已经下架");
			}
		}else{
			renderJSON(false, "积分不足");
		}
		
	}
	
}
