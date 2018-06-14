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
								<label class="layui-form-label">所属类型</label>
								<div class="layui-input-block">
									<select name="type_id" id="type_id" <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >
											<c:forEach items="${typeList }" var="type" >
													<option value="${type.id }" select >${type.type_name }</option>		
											</c:forEach>							
									</select>
					<%-- 				<input type="text" name="type_id" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.type_id}" placeholder="请输入类型ID名称" required  lay-verify="required" autocomplete="off"
										class="layui-input"> --%>
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">是否上架</label>
								<div class="layui-input-block">
									<%-- <input type="text" name="status" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.status}" placeholder="请输入是否上架名称" required  lay-verify="required" autocomplete="off"
										class="layui-input"> --%>
											<select name="status" id="status" <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >
													<option value="1" ${v.status==1?'selected="selected"':'' } >上架</option>		
													<option value="2" ${v.status==2?'selected="selected"':'' }  >不上架</option>		
									</select>
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">推荐</label>
								<div class="layui-input-block">
									<%-- <input type="text" name="recomment" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.recomment}" placeholder="请输入推荐名称" required  lay-verify="required" autocomplete="off"
										class="layui-input"> --%>
										<select name="recomment" id="recomment" <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >
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
								ajaxPOST("mtGoodsCategory/modifyMtGoodsCategory", {
									where:JSON.stringify(data.field)
								}, 
									function(data) {
										swalTip("提示", data.message, data.success,function(res){
											alert(data.success);
											alert(data.message);
											//if(res){
												closeLayer();
											//}
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
