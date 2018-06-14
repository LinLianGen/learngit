/*
 * @(#) config.java 2017年5月27日
 *
 * Copyright (c) 2015, XiaHeng NetWork. All Rights Reserved.
 * XiaHeng NetWork. CONFIDENTIAL
 */
package jfinal;

import javax.servlet.ServletContext;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.xiaheng.core.jfinal.BaseConfig;
import com.xiaheng.core.jfinal.ModelPlugin;
import com.xiaheng.utils.PathConst;

public class Config extends BaseConfig {

	@Override
	protected void configConstPath(PathConst me) {
		me.setAdminViewPath("WEB-INF/page");
		me.setControllerPath("controller");
		me.setModelPath("model");
		
	}
	
	private static final PathConst path = new PathConst();
	
	/**
	 * 插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0 = new C3p0Plugin(PropKit.get("url"),
				PropKit.get("userName"), PropKit.get("passWord"));
		c3p0.start();
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0.getDataSource());
		me.add(new ModelPlugin(arp, path.getModelPath(), path.getBaseSqlPath()));
		me.add(arp);
		arp.setShowSql(PropKit.getBoolean("showSql", false));
		//me.add(new EhCachePlugin("src/ehcache.xml"));
		// 定时任务
		me.add(new Cron4jPlugin(PropKit.use("job.properties")));
	}

	@Override
	public void afterJFinalStart() {
		ServletContext context = JFinal.me().getServletContext();
		context.setAttribute("sysUrl", JFinal.me().getContextPath() + "/admin/");// 后台路径
		// TODO Auto-generated method stub
		super.afterJFinalStart();
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.addGlobalActionInterceptor(new Interceptor() {
			@Override
			public void intercept(Invocation inv) {
				// TODO Auto-generated method stub
				inv.getController().getResponse()
						.setHeader("Access-Control-Allow-Origin", "*");
				inv.getController().getResponse()
						.setContentType("text/html; charset=utf-8");
				inv.invoke();
			}
		});
		
		super.configInterceptor(me);
	}

}
