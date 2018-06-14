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
										<div class="form-group">
											<div class="input-group">
												<select class="form-control" name="status">
													<option value="">全部</option>
													<option value="0">未付款</option>
													<option value="1">已付款</option>
												</select>
											</div>
											<div class="input-group">
												<input type="text" name="tel" class="form-control"
													placeholder="请输入用户名名称">
											</div>
										</div>
										<button type="button" class="btn btn-white"
											style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="cz"></table>
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
			field : 'nickname',
			title : '用户昵称',
			align : 'center'
		},{
			field : 'money',
			title : '充值金额',
			align : 'center'
		},{
			field : 'time',
			title : '下单时间',
			align : 'center',
			
		},{
			field : 'pay_type',
			title : '付款方式',
			align : 'center',
			formatter : function(value) {			
				if(value=="1"){
					return "支付宝支付";
				}else if(value=="2"){
				return "微信支付";
				}else{
				return "线下支付";
				}	
			}
		},{
			field : 'status',
			title : '支付状态',
			align : 'center',
			formatter : function(value) {			
			return value=="0"?"未支付":"已支付";	
			}
		},{
			field : 'invoiceId',
			title : '发票信息',
			align : 'center',
			formatter : function(value) {
				console.log(value);
				if(value==null){return "无"};
				return '<a onclick="invoiceInfo(\''
				+ value
				+ '\')">查看</a> ';
				}
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var v="";
				if(row.pay_type=="3"&&row.status=="0"){
				v += '<a onclick="enterPayResult(\''+value+'\')" title="确认收款"><i class="glyphicon glyphicon-usd"></i></a> ';
				}
				return v+operator(false, false, true, value);
			}
		} ];
		tableInitPanel(columns,"cz/CzRecord");
	</script>

</body>
</html>
