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
								<label class="layui-form-label">商品类目图片</label>
								<div class="layui-input-block">
										<img alt="暂无图片" src="${v.img_url}" id="img_url" style="width: 200px;height: 200px">
								</div>
							</div>
							
							
							
							<div class="layui-form-item">
								<label class="layui-form-label">类目名称</label>
								<div class="layui-input-block">
									<input type="text" name="category_name" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.category_name}" placeholder="请输入类目名称名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							
							<div class="layui-form-item">
								<label class="layui-form-label">是否上架</label>
								<div class="layui-input-block">
									<%-- <input type="text" name="status" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.status}" placeholder="请输入是否上架名称" required  lay-verify="required" autocomplete="off"
										class="layui-input"> --%>
											<select class="layui-select" name="status" id="status" <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >
													<option value="1" ${v.status==1?'selected="selected"':'' } >上架</option>		
													<option value="2" ${v.status==2?'selected="selected"':'' }  >不上架</option>		
									</select>
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">推 荐</label>
								<div class="layui-input-block">
									<%-- <input type="text" name="recomment" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.recomment}" placeholder="请输入推荐名称" required  lay-verify="required" autocomplete="off"
										class="layui-input"> --%>
										<select class="layui-select" name="recomment" id="recomment" <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >
													<option value="1" ${v.recomment==1?'selected="selected"':'' } >推荐</option>		
													<option value="2" ${v.recomment==2?'selected="selected"':'' }  >不推荐</option>		
										</select>
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
									ajaxPOST("modify/mtGoodsCategory", {
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
