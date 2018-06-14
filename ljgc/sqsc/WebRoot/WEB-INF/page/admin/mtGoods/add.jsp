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
<script type="text/javascript" src="plugins/layui/layui.all.js"></script>
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
										<%-- <input type="file" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>  id="goods_pic" accept="image/jpg,image/png,image/jpeg" name="img_url" class="layui-upload-file"> --%>
											 <!-- <button type="button" style="width: 120px;" class="layui-btn" id="goods_pic" >上传</button>  -->
										<button type="button" class="layui-btn" id="goods_pic">
												  <i class="layui-icon">&#xe67c;</i>上传图片
												</button>
									</div>
									
								</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品图片</label>
								<div class="layui-input-block" id="demo2">
										<%-- <img alt="暂无图片" src="${v.img_url}" name="img_url" id="img_url" style="width: 200px;height: 200px"> --%>
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品价格</label>
								<div class="layui-input-block">
									<input type="text" name="price" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
									 onkeyup= "if( ! /^-?\d+\.?\d{0,2}$/.test(this.value)){ var s = this.value;this.value=s.substring(0,s.length-1);}" 
										value="${v.price}" placeholder="请输入商品价格" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品数量</label>
								<div class="layui-input-block">
									<input type="text" name="goods_num" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										onkeyup="value=value.replace(/[^\d]/g,'')"
										value="${v.goods_num}" placeholder="请输入商品数量" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品名称</label>
								<div class="layui-input-block">
									<input type="text" name="goods_name" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.goods_name}" placeholder="请输入商品名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
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
								<label class="layui-form-label">商品描述</label>
								<div class="layui-input-block">
									<input type="text" name="content" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.content}" placeholder="请输入商品描述" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
									<label class="layui-form-label">商品详情图片</label>
									<div class="layui-input-block">
										<button type="button" class="layui-btn" id="click_upload">
												  <i class="layui-icon">&#xe67c;</i>上传图片
										</button>
									</div>
								</div>	
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品详情图</label>
								<div class="layui-input-block">
										<img alt="暂无图片" src="${v.goods_detail}" name="goods_detail" id="goods_detail" style="width: 200px;height: 200px">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">是否上架</label>
								<div class="layui-input-block">
						
								<select  style="display:block;" class="layui-input" name="status" id="status" <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >
										<option value="1" ${v.status==1?'selected="selected"':'' }  >上    架</option>		
										<option value="2" ${v.status==2?'selected="selected"':'' }  >不上架</option>		
									</select> 
										   
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">是否推荐</label>
								<div class="layui-input-block">
								<select style="display:block;" class="layui-input" name="recomment" id="recomment" <c:if test="${op eq 'see'}">disabled="disabled"</c:if> >		
										<option value="1" >推    荐</option>		
										<option value="2" >不推荐</option> 
									</select> 
									
								</div>
							</div> 
							
							<div class="layui-form-item">
								<label class="layui-form-label">商品类目</label>
								<div class="layui-input-block">
												<select class="layui-input" id="category_id" name="category_id" style="display:block;">
												    <c:forEach items="${categoryList }" var="type" >
													<option value="${type.id }" >${type.category_name }</option>		
													</c:forEach>		
												</select > 
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
							var s=""
								
	                        var upload = layui.upload;
							//var laydate = layui.laydate;

							var form = layui.form;
  
                            /*  laydate.render({ 
                            	  elem: '#start,#end'
                            	  ,type: 'datetime'
                            	}); */
                            	
                             upload.render({
                            	    elem: '#goods_pic' 
                            	    ,url: sysUrl+'file/uploadImg'
                            	    ,multiple: true
                            	    ,before: function(obj){
                            	      obj.preview(function(index, file, result){
                            	        $('#demo2').append('<img width="200px" height="200px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
                            	        
                            	      });
                            	    }
                            	    ,done: function(res){
                            	      	//上传完毕
                            	     	 s+=res.data[0]+",";
                            	    }
                            	  });	
                             
                             var uploadInst1=upload.render({
								 elem: '#click_upload',
								 url: sysUrl+'file/uploadImg',
								 done: function(res){
										$('#goods_detail').attr("src",res.data[0]);
									    },
									  });
									  
                             
								//监听提交
								form.on('submit(form)', function(data) {
								data.field.goods_detail = $('#goods_detail').attr("src");
							 	data.field.img_url = s;
									ajaxPOST("mtGoods/addMtGoods", {
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
						
							//Demo
							
						</script>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
