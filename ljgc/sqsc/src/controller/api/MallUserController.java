package controller.api;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

import model.MtUser;
import model.MtUserAddress;
import model.MtUserCollection;
import model.MtUserCoupon;
import utils.linUtils.Utils;
import utils.sendMessage.HttpSenderMessage;

@RouteViewPath("api")
public class MallUserController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/user";
	}

	/**
	* @方法名: 用户收藏
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月26 5:18:47
	* @修改:
	*/ 
	public void userCollection() {				
		String userId = getPara("userId");
		String goodsId = getPara("goodsId");
		MtUserCollection collection = new MtUserCollection();
		String idString = collection.saveConllection(userId, goodsId);
		JSONObject js = new JSONObject();
		js.put("idString", idString);
		renderJSON(true, "请求成功", js);
	}

	
	/**
	* @方法名: 用户添加地址
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:45:49
	* @修改:
	*/ 
	public void addUserAddress(){
		String userId = getPara("userId");
		String consignee = getPara("consignee");
		String phone = getPara("phone");
		String address = getPara("address");
		String is_default = getPara("is_default");
		String communityId = getPara("communityId");
		MtUserAddress mtUserAddress = new MtUserAddress();
		String idString = mtUserAddress.addUserAddress(userId, consignee, phone, address, is_default, communityId);
		JSONObject js = new JSONObject();
		js.put("idString", idString);
		renderJSON(true, "请求成功", js);
		}
	
	/**
	* @方法名: 删除我的地址
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:55:20
	* @修改:
	*/ 
	public void delUserAddress(){
		String id = getPara("addressId");
		MtUserAddress mtUserAddress = new MtUserAddress();
		boolean flag = mtUserAddress.delAddress(id);
		String message = flag==true?"请求成功":"请求失败"; 
		renderJSON(flag, message);
	}
	
	/**
	* @方法名: 获取用户地址
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:55:20
	* @修改:
	*/ 
	public void getUserAddressList(){
		String userId =  getPara("userId");
		MtUserAddress mtUserAddress = new MtUserAddress();
		List<Map<String, Object>>  list=mtUserAddress.getUserAddress(userId);
		JSONObject js = new JSONObject();
		js.put("addressList", list);
		renderJSON(true, "请求成功" , js);
	}

	/**
	* @方法名: 获取用户收藏
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年2月27 11:55:20
	* @修改:
	*/ 
	public void getUserCollectionList(){
		String userId =  getPara("userId");
		MtUserCollection collection = new MtUserCollection();
		List<Map<String, Object>>  list=collection.getUserCollection(userId);
		JSONObject js = new JSONObject();
		js.put("collectionList", list);
		renderJSON(true, "请求成功" , js);
	}
	
	/**
	* @方法名:修改用户默认地址
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 2:01:06
	* @修改:
	*/ 
	public void updateDefaultAddress(){
		String userId = getPara("userId");
		String addressId = getPara("addressId");
			MtUserAddress mtUserAddress =new MtUserAddress();
			boolean flag =  mtUserAddress.updateDefaultAddress(userId, addressId);
			renderJSON(flag, "请求成功");
	}
	
	/**
	* @方法名: 用户优惠券展示
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月1 2:12:33
	* @修改:
	*/ 
	public void  getUserCouponList(){
		String userId =  getPara("userId");
		MtUserCoupon mtUserCoupon = new MtUserCoupon();
		List<Map< String, Object>> list = mtUserCoupon.getUserCouponList(userId);
		JSONObject js = new JSONObject();
		js.put("couponList", js);
		renderJSON(true, "请求成功", list);
	}
	
	/**
	* @方法名: 给用户发送 验证码
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月2 3:12:40
	* @修改:
	*/ 
	public void sendUserCode(){
		String phone = getPara("phone");
		//MtUser mtUser = new MtUser();
		//Record record = mtUser.getUserInfo(userId);
		String code = Utils.getValidateCode(true, 4);
		try {
			HttpSenderMessage.setMsg(phone, code);
			JSONObject js = new JSONObject();
			js.put("code", code);
			renderJSON(true, "短信发送成功", js);			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	* @方法名: 更改用户密码   换绑手机
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2018年3月2 3:27:07
	* @修改:
	*/ 
	public void updateUserPwdOrPhone(){
		String userId = getPara("userId");
		String password = getPara("password");
		String phone  =getPara("phone");
		MtUser mtUser = new MtUser();
		Record record =mtUser.getUserInfo(userId);
		if(!password.equals("-1")){
			record.set("password", password);
		}
		if(!phone.equals("-1")){
			record.set("phone", phone);
		}
		boolean flag = mtUser.updateUserPwdOrPhone(record);
		renderJSON(flag, "请求成功");
	}
	
}

