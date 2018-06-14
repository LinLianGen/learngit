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
								<label class="layui-form-label">用户Id</label>
								<div class="layui-input-block">
									<input type="text" name="userId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.userId}" placeholder="请输入用户Id名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">订单编号</label>
								<div class="layui-input-block">
									<input type="text" name="order_no" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.order_no}" placeholder="请输入订单编号名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">订单状态</label>
								<div class="layui-input-block">
									<input type="text" name="status" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.status}" placeholder="请输入订单状态名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">订单图片</label>
								<div class="layui-input-block">
									<input type="text" name="order_img" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.order_img}" placeholder="请输入订单图片名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">订单价格</label>
								<div class="layui-input-block">
									<input type="text" name="order_price" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.order_price}" placeholder="请输入订单价格名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">邮费</label>
								<div class="layui-input-block">
									<input type="text" name="postage" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.postage}" placeholder="请输入邮费名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">订单积分</label>
								<div class="layui-input-block">
									<input type="text" name="point" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.point}" placeholder="请输入订单积分名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">支付时间</label>
								<div class="layui-input-block">
									<input type="text" name="pay_time" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.pay_time}" placeholder="请输入支付时间名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">支付方式</label>
								<div class="layui-input-block">
									<input type="text" name="pay_type" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.pay_type}" placeholder="请输入支付方式名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">支付金额</label>
								<div class="layui-input-block">
									<input type="text" name="pay_price" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.pay_price}" placeholder="请输入支付金额名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">提货码</label>
								<div class="layui-input-block">
									<input type="text" name="pick_up_code" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.pick_up_code}" placeholder="请输入提货码名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">配送小哥ID</label>
								<div class="layui-input-block">
									<input type="text" name="courier_id" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.courier_id}" placeholder="请输入配送小哥ID名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">配送小哥名称</label>
								<div class="layui-input-block">
									<input type="text" name="courier_name" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.courier_name}" placeholder="请输入配送小哥名称名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">配送小哥联系方式</label>
								<div class="layui-input-block">
									<input type="text" name="courier_phone" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.courier_phone}" placeholder="请输入配送小哥联系方式名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">收件地址ID</label>
								<div class="layui-input-block">
									<input type="text" name="address_id" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.address_id}" placeholder="请输入收件地址ID名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">收件人姓名</label>
								<div class="layui-input-block">
									<input type="text" name="consignee" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.consignee}" placeholder="请输入收件人姓名名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">收件人联系方式</label>
								<div class="layui-input-block">
									<input type="text" name="phone" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.phone}" placeholder="请输入收件人联系方式名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">收件人地址</label>
								<div class="layui-input-block">
									<input type="text" name="address" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.address}" placeholder="请输入收件人地址名称" required  lay-verify="required" autocomplete="off"
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
									ajaxPOST("modify/mtOrder", {
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
