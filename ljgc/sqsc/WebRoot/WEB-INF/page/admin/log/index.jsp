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
										<!-- <button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableAdd()">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
										</button> -->
										
					   					
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="user" 
										      		 type="text" placeholder="请输入操作人名称：">
										    </div>
										</div>
											<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="phone" 
										      		 type="text" placeholder="用户联系方式：">
										    </div>
										</div>
										
										
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="log"></table>
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
			field : 'user',
			title : '操作人名称',
			align : 'center'
		},{
			field : 'phone',
			title : '用户联系方式',
			align : 'center'
		},{
			field : 'url',
			title : '操作用户信息',
			align : 'center'
		},{
			field : 'type',
			title : '操作类型',
			align : 'center',
			formatter : function(value, row, index) {
				if(value==1){
					return '修改';
				}
				if(value==2){
					return '删除';
				}
				if(value==3){
					return '新增';
				}
			}
		},{
			field : 'addTime',
			title : '操作时间',
			align : 'center'
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return operator(false, false, true, value);
			}
		} ];
		tableInitPanel(columns );
	</script>

</body>
</html>
