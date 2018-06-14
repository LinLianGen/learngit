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
										<c:if test="${!(para.type eq 1)}">
											<button type="button" class="btn btn-white"
												style="margin-bottom: 0px;" onclick="tableAdd(${para.type})">
												<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
											</button>
										</c:if>
										<div class="form-group">
											<div class="input-group">
												<input class="form-control" name="title" type="text"
													placeholder="请输入标题">
											</div>
										</div>

										<div class="form-group">
											<div class="input-group">
												<input class="form-control" name="content" type="text"
													placeholder="请输入内容">
											</div>
										</div>
										<input type="hidden" name="type" value="${para.type}">
										<button type="button" class="btn btn-white"
											style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="about"></table>
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
			field : 'title',
			title : '标题',
			align : 'center'
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return operator(true, true, ${!(para.type eq 1)}, value,${para.type});
			}
		} ]; 	
		tableInitPanel(columns);
	</script>

</body>
</html>
