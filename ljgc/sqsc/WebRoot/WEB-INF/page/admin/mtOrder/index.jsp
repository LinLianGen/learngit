<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<!-- Basic Columns -->
					<div class="col-sm-12">
						<!-- Example Basic Columns -->
						<div class="example-wrap" style="height: 76%;">
							<div class="example">
								<div id="toolbar" class="btn-group">
									<form class="form-inline" id="searchForm">
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableAdd()">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
										</button>
										
					   					<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="id" 
										      		 type="text" placeholder="请输入">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="userId" 
										      		 type="text" placeholder="请输入用户Id">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="order_no" 
										      		 type="text" placeholder="请输入订单编号">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="status" 
										      		 type="text" placeholder="请输入订单状态">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="order_img" 
										      		 type="text" placeholder="请输入订单图片">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="order_price" 
										      		 type="text" placeholder="请输入订单价格">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="postage" 
										      		 type="text" placeholder="请输入邮费">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="point" 
										      		 type="text" placeholder="请输入订单积分">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="pay_time" 
										      		 type="text" placeholder="请输入支付时间">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="pay_type" 
										      		 type="text" placeholder="请输入支付方式">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="pay_price" 
										      		 type="text" placeholder="请输入支付金额">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="pick_up_code" 
										      		 type="text" placeholder="请输入提货码">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="courier_id" 
										      		 type="text" placeholder="请输入配送小哥ID">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="courier_name" 
										      		 type="text" placeholder="请输入配送小哥名称">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="courier_phone" 
										      		 type="text" placeholder="请输入配送小哥联系方式">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="address_id" 
										      		 type="text" placeholder="请输入收件地址ID">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="consignee" 
										      		 type="text" placeholder="请输入收件人姓名">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="phone" 
										      		 type="text" placeholder="请输入收件人联系方式">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="address" 
										      		 type="text" placeholder="请输入收件人地址">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="isDel" 
										      		 type="text" placeholder="请输入">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="addTime" 
										      		 type="text" placeholder="请输入添加时间">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="updTime" 
										      		 type="text" placeholder="请输入修改时间">
										    </div>
										</div>
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="mtOrder"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var columns = [ 
		{
			field : 'id',
			title : '',
			align : 'center'
		},{
			field : 'userId',
			title : '用户Id',
			align : 'center'
		},{
			field : 'order_no',
			title : '订单编号',
			align : 'center'
		},{
			field : 'status',
			title : '订单状态',
			align : 'center'
		},{
			field : 'order_img',
			title : '订单图片',
			align : 'center'
		},{
			field : 'order_price',
			title : '订单价格',
			align : 'center'
		},{
			field : 'postage',
			title : '邮费',
			align : 'center'
		},{
			field : 'point',
			title : '订单积分',
			align : 'center'
		},{
			field : 'pay_time',
			title : '支付时间',
			align : 'center'
		},{
			field : 'pay_type',
			title : '支付方式',
			align : 'center'
		},{
			field : 'pay_price',
			title : '支付金额',
			align : 'center'
		},{
			field : 'pick_up_code',
			title : '提货码',
			align : 'center'
		},{
			field : 'courier_id',
			title : '配送小哥ID',
			align : 'center'
		},{
			field : 'courier_name',
			title : '配送小哥名称',
			align : 'center'
		},{
			field : 'courier_phone',
			title : '配送小哥联系方式',
			align : 'center'
		},{
			field : 'address_id',
			title : '收件地址ID',
			align : 'center'
		},{
			field : 'consignee',
			title : '收件人姓名',
			align : 'center'
		},{
			field : 'phone',
			title : '收件人联系方式',
			align : 'center'
		},{
			field : 'address',
			title : '收件人地址',
			align : 'center'
		},{
			field : 'isDel',
			title : '',
			align : 'center'
		},{
			field : 'addTime',
			title : '添加时间',
			align : 'center'
		},{
			field : 'updTime',
			title : '修改时间',
			align : 'center'
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return operator(true, true, true, value);
			}
		} ];
		tableInitPanel(columns);
	</script>

</body>
</html>
