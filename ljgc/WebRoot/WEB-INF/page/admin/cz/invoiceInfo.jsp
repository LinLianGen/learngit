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
							<input type="hidden" name="U_id" value="${v.U_id}"
								class="layui-input">
							<div class="layui-form-item">
								<label class="layui-form-label">收票人姓名</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" value="${v.name}"
										placeholder="请输入用户名名称" required lay-verify="required"
										autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">收票人号码</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" value="${v.tel}"
										required lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">发票类型</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled"
										value="${v.type==1?'普通发票':'专用发票'}" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>
							<c:if test="${v.type==2}">
								<div class="layui-form-item">
									<label class="layui-form-label">公司名称</label>
									<div class="layui-input-block">
										<input type="text" disabled="disabled" value="${v.company}"
											required lay-verify="required" autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">税号</label>
									<div class="layui-input-block">
										<input type="text" disabled="disabled" value="${v.code}"
											required lay-verify="required" autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">开户行</label>
									<div class="layui-input-block">
										<input type="text" disabled="disabled" value="${v.bank}"
											required lay-verify="required" autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">银行账号</label>
									<div class="layui-input-block">
										<input type="text" disabled="disabled" value="${v.bankcard}"
											required lay-verify="required" autocomplete="off"
											class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">公司地址</label>
									<div class="layui-input-block">
										<input type="text" disabled="disabled"
											value="${v.company_address}" required lay-verify="required"
											autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">公司电话</label>
									<div class="layui-input-block">
										<input type="text" disabled="disabled"
											value="${v.company_tel}" required lay-verify="required"
											autocomplete="off" class="layui-input">
									</div>
								</div>
							</c:if>
							<div class="layui-form-item">
								<label class="layui-form-label">收货地址</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled"
										value="${v.proName} ${v.cityName} ${v.disName} ${v.addressDetail}"
										required lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">收货人电话</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" value="${v.ads_tel}"
										required lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
