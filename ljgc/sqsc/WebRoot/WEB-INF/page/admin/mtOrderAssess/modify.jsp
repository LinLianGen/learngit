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
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="id" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.id}" placeholder="请输入名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="orderId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.orderId}" placeholder="请输入名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">评价等级</label>
								<div class="layui-input-block">
									<input type="text" name="grade" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.grade}" placeholder="请输入评价等级名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">评价内容</label>
								<div class="layui-input-block">
									<input type="text" name="content" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.content}" placeholder="请输入评价内容名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">外送员iD</label>
								<div class="layui-input-block">
									<input type="text" name="courier_id" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.courier_id}" placeholder="请输入外送员iD名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">外送员姓名</label>
								<div class="layui-input-block">
									<input type="text" name="courier_name" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.courier_name}" placeholder="请输入外送员姓名名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">外送员联系方式</label>
								<div class="layui-input-block">
									<input type="text" name="courier_phone" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.courier_phone}" placeholder="请输入外送员联系方式名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="isDel" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.isDel}" placeholder="请输入名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">添加时间</label>
								<div class="layui-input-block">
									<input type="text" name="addTime" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.addTime}" placeholder="请输入添加时间名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">修改时间</label>
								<div class="layui-input-block">
									<input type="text" name="updTime" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.updTime}" placeholder="请输入修改时间名称" required  lay-verify="required" autocomplete="off"
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
									ajaxPOST("modify/mtOrderAssess", {
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
