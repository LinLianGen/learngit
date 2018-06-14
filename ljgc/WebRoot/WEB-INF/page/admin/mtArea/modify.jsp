<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.4.0&key=daad8047a4bd93bbda0aba52a58fce3f&plugin=AMap.Autocomplete"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
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
								<label class="layui-form-label">区域名称</label>
								<div class="layui-input-block">
									<input type="text" name="area_name"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.area_name}" placeholder="请输入区域名称名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">区域联系方式</label>
								<div class="layui-input-block">
									<input type="text" name="area_phone"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.area_phone}" placeholder="请输入区域联系方式名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">区域地址</label>
								<div class="layui-input-block">
									<input type="text" name="area_address"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.area_address}" placeholder="请输入区域地址名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">区域状态</label>
								<div class="layui-input-block">
									<select class="layui-input" name="status" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>>
										<option value="1" ${v.status==1?'selected=selected':''}>运营</option>
										<option value="2" ${v.status==2?'selected=selected':''}>关店</option>
									</select>
									<%-- <input type="text" name="status"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.status}" placeholder="请输入区域状态" required
										lay-verify="required" autocomplete="off" class="layui-input"> --%>
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">开店时间</label>
								<div class="layui-input-block">
									<input type="text" name="open_time"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.open_time}" placeholder="请输入开店时间名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">关店时间</label>
								<div class="layui-input-block">
									<input type="text" name="close_time"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.close_time}" placeholder="请输入关店时间名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<%-- <div class="layui-form-item">
								<label class="layui-form-label">经度</label>
								<div class="layui-input-block">
									<input type="text" name="longitude"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.longitude}" placeholder="请输入经度名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">维度</label>
								<div class="layui-input-block">
									<input type="text" name="latitude"
										<c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.latitude}" placeholder="请输入维度名称" required
										lay-verify="required" autocomplete="off" class="layui-input">
								</div>
							</div> --%>

							<div style="height: 500px; width: 1200px;">
									<label class="layui-form-label">搜索</label>
								<div class="layui-input-block">
									<input type="text" placeholder="请输入关键字进行搜索" id="tipinput">
									<input type="text" readonly="true" id="lnglat" name="lnglat"></input>
								</div>
								<div class="layui-input-block" id="container"  style="height: 400px; width: 1200px;">
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
							var map = new AMap.Map("container", {
								resizeEnable : true
							});
							//为地图注册click事件获取鼠标点击出的经纬度坐标
							var clickEventListener = map
									.on(
											'click',
											function(e) {document.getElementById("lnglat").value = e.lnglat
														.getLng()+ ','
														+ e.lnglat.getLat()
											});
							var auto = new AMap.Autocomplete({
								input : "tipinput"
							});
							AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
							function select(e) {
								if (e.poi && e.poi.location) {
									map.setZoom(15);
									map.setCenter(e.poi.location);
								}
							}
						</script>
						<script type="text/javascript">
							//Demo
							layui.use('form', function() {
								var form = layui.form();
								//监听提交
								form.on('submit(form)', function(data) {
								var jj = $('#lnglat').val();
									if(jj){
										var longitude = jj.split(",")[0];
										var latitude = jj.split(",")[1];
										data.field.longitude = longitude;
										data.field.latitude = latitude;
										console.log(longitude,latitude);
									}
									ajaxPOST("modify/mtArea", {
										where : JSON.stringify(data.field)
									}, function(data) {
										swalTip("提示", data.message,
												data.success, function(res) {
													if (res) {
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
