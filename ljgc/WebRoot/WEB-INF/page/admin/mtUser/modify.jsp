<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="plugins/layui/css/layui.css">
<script type="text/javascript" src="plugins/layui/layui.js"></script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content layui-form">
						<form class="layui-form" action="">
							<input type="hidden" name="id" value="${v.id}" class="layui-input">
							
						
						<div class="layui-form-item">
								<label class="layui-form-label">用户头像</label>
								<div class="layui-input-block">
									<img alt="" src="${v.img }" height="80px" width="80px">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">联系方式</label>
								<div class="layui-input-block">
									<input type="text" name="phone" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.phone}" placeholder="请输入联系方式名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">登入密码</label>
								<div class="layui-input-block">
									<input type="text" name="password" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.password}" placeholder="请输入登入密码名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">用户昵称</label>
								<div class="layui-input-block">
									<input type="text" name="user_name" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.user_name}" placeholder="请输入用户昵称名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
										
								</div>
							</div>
							
							
							
							 
							
							<div class="layui-form-item">
								<label class="layui-form-label">全网分数</label>
								<div class="layui-input-block">
									<input type="text" name="user_point" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.user_point}" placeholder="请输入全网分数名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">平均消费</label>
								<div class="layui-input-block">
									<input type="text" name="one_price" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.one_price}" placeholder="请输入平均消费名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">购买次数</label>
								<div class="layui-input-block">
									<input type="text" name="buy_num" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.buy_num}" placeholder="请输入购买次数名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">累计消费</label>
								<div class="layui-input-block">
									<input type="text" name="all_price" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.all_price}" placeholder="请输入累计消费名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">购买频率</label>
								<div class="layui-input-block">
									<input type="text" name="frequency" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.frequency}" placeholder="请输入购买频率名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">平均单量</label>
								<div class="layui-input-block">
									<input type="text" name="one_num" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.one_num}" placeholder="请输入平均单量名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<c:if test="${op ne 'see'}">
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="form">立即提交</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div>
							</c:if>
						</form>
						<script type="text/javascript">
							//Demo
							layui.use('form', function() {
								var form = layui.form();
								//监听提交
								form.on('submit(form)', function(data) {
									ajaxPOST("modify/mtUser", {
										where:JSON.stringify(data.field)
									}, 
										function(data) {
											swalTip("提示", data.message, data.success,function(res){
												if(res){
													closeLayer();
												}
											});
									});
									return false;
								});
							});
						</script>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
