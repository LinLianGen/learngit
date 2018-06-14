package utils.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Record;
/**
 * 后台登陆拦截器
 * 
 * @Description
 * 
 * @author Chuck Don
 * @version 1.0
 * @since 2016年12月20日
 */
public class LoginAdminInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
			// 		System.out.println("拦截器："+inv.getActionKey()+"--------"+inv.getControllerKey()+"------"+
			//			inv.getViewPath()+"------"+inv.getMethodName()+"------"+inv.getController());
		
		Record recod = (Record) inv.getController().getSessionAttr("admin");
		if (recod == null) {
			inv.getController().forwardAction("/admin/login");
			return;
		}
		
		inv.invoke();
	}

}
