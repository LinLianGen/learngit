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
									<label class="layui-form-label">图片</label>
									<div class="layui-input-block">
										<input type="file" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>  accept="image/jpg,image/png,image/jpeg" name="img_url" class="layui-upload-file">
									</div>
								</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">图片</label>
								<div class="layui-input-block">
										<img alt="暂无图片" src="${v.img_url}" id="img_url" style="width: 200px;height: 200px">
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
								<label class="layui-form-label">类型排序</label>
								<div class="layui-input-block">
									<input type="text" name="goods_sort" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.goods_sort}" placeholder="请输入类型排序名称" required  lay-verify="required" autocomplete="off"
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
							layui.use(['form','upload'], function() {
							layui.upload({
							  url: sysUrl+'file/uploadImg',
							  ext: 'jpg|png|jpeg',
							  before: function(input){
							    //返回的参数item，即为当前的input DOM对象
							    console.log('文件上传中');
							  },success: function(res){
							  	$('#img_url').attr("src",res.data[0]);
							  }
							}); 
					
							var form = layui.form();
							//监听提交
							form.on('submit(form)', function(data) {
							data.field.img_url = $('#img_url').attr("src");
								ajaxPOST("modify/mtGoodsType", {
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
