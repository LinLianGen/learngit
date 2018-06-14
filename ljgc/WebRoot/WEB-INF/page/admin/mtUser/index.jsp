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
										      <input class="form-control" name="phone" 
										      		 type="text" placeholder="请输入联系方式">
										    </div>
										</div>
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="mtUser"></table>
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
			field : 'phone',
			title : '联系方式',
			align : 'center'
		},{
			field : 'user_name',
			title : '用户昵称',
			align : 'center'
		},{
			field : 'img',
			title : '用户头像',
			align : 'center',
			formatter : function(value, row, index) {
				return '<img alt="加载失败" src="'+value+'" height="80px"  width="80px" >'
			}
		},{
			field : 'point',
			title : '消费获得积分',
			align : 'center'
		},{
			field : 'status',
			title : '用户状态',
			align : 'center',
			formatter : function(value, row, index) {
				return value==1?'正常用户':'异常用户';
			}
		},{
			field : 'user_point',
			title : '全网分数',
			align : 'center'
		},{
			field : 'one_price',
			title : '平均消费',
			align : 'center'
		},{
			field : 'buy_num',
			title : '购买次数',
			align : 'center'
		},{
			field : 'all_price',
			title : '累计消费',
			align : 'center'
		},{
			field : 'frequency',
			title : '购买频率',
			align : 'center'
		},{
			field : 'one_num',
			title : '平均单量',
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
