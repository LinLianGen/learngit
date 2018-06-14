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
								<label class="layui-form-label">商品ID</label>
								<div class="layui-input-block">
									<input type="text" name="goodsId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.goodsId}" placeholder="请输入商品ID名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品状态</label>
								<div class="layui-input-block">
									<input type="text" name="status" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.status}" placeholder="请输入商品状态名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">类型名称</label>
								<div class="layui-input-block">
									<input type="text" name="type_name" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.type_name}" placeholder="请输入类型名称名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">类型ID</label>
								<div class="layui-input-block">
									<input type="text" name="typeId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.typeId}" placeholder="请输入类型ID名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品价格</label>
								<div class="layui-input-block">
									<input type="text" name="price" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.price}" placeholder="请输入商品价格名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品名称</label>
								<div class="layui-input-block">
									<input type="text" name="goods_name" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.goods_name}" placeholder="请输入商品名称名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">保质期结束时间</label>
								<div class="layui-input-block">
									<input type="text" name="end_time" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.end_time}" placeholder="请输入保质期结束时间名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品日期</label>
								<div class="layui-input-block">
									<input type="text" name="goods_date" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.goods_date}" placeholder="请输入商品日期名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品保质期</label>
								<div class="layui-input-block">
									<input type="text" name="goods_date_long" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.goods_date_long}" placeholder="请输入商品保质期名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品详情图</label>
								<div class="layui-input-block">
									<input type="text" name="goods_detail" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.goods_detail}" placeholder="请输入商品详情图名称" required  lay-verify="required" autocomplete="off"
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
							
							<div class="layui-form-item">
								<label class="layui-form-label">区域Id</label>
								<div class="layui-input-block">
									<input type="text" name="areaId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.areaId}" placeholder="请输入区域Id名称" required  lay-verify="required" autocomplete="off"
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
									ajaxPOST("modify/mtGoodsLibrary", {
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
