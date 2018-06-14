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
								<label class="layui-form-label">商品图片</label>
								<div class="layui-input-block" id="dome">
									 <input type="text"  style=" display: none;" name="img_url" id="img_url"
										value="${v.img_url} " placeholder="请输入商品图片路径名称" required  lay-verify="required" autocomplete="off"
										class="layui-input"> 
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">类型名称</label>
								<div class="layui-input-block">
									<input type="text" name="type_name" disabled="disabled"
										value="${v.type_name}" placeholder="请输入类型名称名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
								<div class="layui-form-item">
								<label class="layui-form-label">类目名称</label>
								<div class="layui-input-block">
									<input type="text" name="category_name" disabled="disabled"
										value="${v.category_name}" placeholder="请输入类目名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品价格</label>
								<div class="layui-input-block">
									<input type="text" name="price" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										onkeyup= "if( ! /^-?\d+\.?\d{0,2}$/.test(this.value)){ var s = this.value;this.value=s.substring(0,s.length-1);}" 
										value="${v.price}" placeholder="请输入商品价格名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品数量</label>
								<div class="layui-input-block">
									<input type="text" name="goods_num" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										onkeyup="value=value.replace(/[^\d]/g,'')"
										value="${v.goods_num}" placeholder="请输入商品数量名称" required  lay-verify="required" autocomplete="off"
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
								<label class="layui-form-label">商品详情图</label>
								<div class="layui-input-block">
									<%-- <input type="text" name="goods_detail" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.goods_detail}" placeholder="请输入商品详情图名称" required  lay-verify="required" autocomplete="off"
										class="layui-input"> --%>
										<img alt="" class="layui-img" src="${v.goods_detail}" name="goods_detail" width="200px" height="200px">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">是否推荐</label>
								<div class="layui-input-block">
									<select  class="layui-input" name="recomment" id="recomment" <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >		
										<option value="1"   ${v.recomment==1?'selected="selected"':'' } >推    荐</option>		
										<option value="2"  ${v.recomment==1?'selected="selected"':'' } >不推荐</option> 
									</select> 
								</div>
							</div>
							
						<div class="layui-form-item">
								<label class="layui-form-label">是否上架</label>
								<div class="layui-input-block">
								<select  class="layui-input" name="status"  <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >
										<option value="1"  ${v.status==1?'selected="selected"':'' }  >上    架</option>		
										<option value="2"  ${v.status==2?'selected="selected"':'' }  >不上架</option>		
									</select> 
								</div>
							</div>
							
							
							<div class="layui-form-item">
								<label class="layui-form-label">销量</label>
								<div class="layui-input-block">
									<input type="text" name="sale" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										onkeyup="value=value.replace(/[^\d]/g,'')"
										value="${v.sale}" placeholder="请输入销量名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
						
							
							<div class="layui-form-item">
								<label class="layui-form-label">文字描述</label>
								<div class="layui-input-block">
									<input type="text" name="content" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.content}" placeholder="请输入文字描述名称" required  lay-verify="required" autocomplete="off"
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
						
						var value = $("#img_url").val();
						console.log("value:"+value);
						var splitArray = new Array();
						var regex = /,/;
						splitArray = value.split(regex)
							for(i=0; i <splitArray.length-1; i++){
								 $('#dome').append('<img width="200px" height="200px" alt="" src="'+ splitArray[i] +'" class="layui-upload-img">')
							}
							//Demo
							layui.use('form', function() {
								var form = layui.form();
								//监听提交
								form.on('submit(form)', function(data) {
									ajaxPOST("modify/mtGoods", {
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
