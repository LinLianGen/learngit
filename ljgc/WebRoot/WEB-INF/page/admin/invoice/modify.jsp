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
							<input type="hidden" name="id" value="${v.id}"
								class="layui-input">

							<div class="layui-form-item">
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="id"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.id}" placeholder="请输入名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">发票类型 1.普通发票 2.专用发票</label>
								<div class="layui-input-block">
									<input type="text" name="type"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.type}" placeholder="请输入发票类型 1.普通发票 2.专用发票名称"
										required lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">订单号</label>
								<div class="layui-input-block">
									<input type="text" name="obm"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.obm}" placeholder="请输入订单号名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">收票人姓名</label>
								<div class="layui-input-block">
									<input type="text" name="name"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.name}" placeholder="请输入收票人姓名名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">收票人电话</label>
								<div class="layui-input-block">
									<input type="text" name="tel"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.tel}" placeholder="请输入收票人电话名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">公司名称</label>
								<div class="layui-input-block">
									<input type="text" name="company"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.company}" placeholder="请输入公司名称名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">税号</label>
								<div class="layui-input-block">
									<input type="text" name="code"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.code}" placeholder="请输入税号名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">开户行</label>
								<div class="layui-input-block">
									<input type="text" name="bank"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.bank}" placeholder="请输入开户行名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">银行账号</label>
								<div class="layui-input-block">
									<input type="text" name="bankcard"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.bankcard}" placeholder="请输入银行账号名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">公司地址</label>
								<div class="layui-input-block">
									<input type="text" name="address"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.address}" placeholder="请输入公司地址名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">公司电话</label>
								<div class="layui-input-block">
									<input type="text" name="company_tel"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.company_tel}" placeholder="请输入公司电话名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">收货地址id</label>
								<div class="layui-input-block">
									<input type="text" name="addressId"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.addressId}" placeholder="请输入收货地址id名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="isDel"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.isDel}" placeholder="请输入名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="addTime"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.addTime}" placeholder="请输入名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
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
									ajaxPOST("modify/invoice", {
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
