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
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableAddTwo('admin/mtGoodsCategory/mtGoodsCategoryAdd')">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
										</button>
										
					   				
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="category_name" 
										      		 type="text" placeholder="请输入类目名称">
										    </div>
										</div>
										
										
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="mtGoodsCategory"></table>
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
			field : 'img_url',
			title : '商品类目图片',
			align : 'center',
			formatter : function(value, row, index) {
				return '<img alt="" src="'+value+'" height="80px" width="80px">';
			}
		},{
			field : 'category_name',
			title : '类目名称',
			align : 'center'
		},{
			field : 'type_name',
			title : '类型名称',
			align : 'center'
		},{
			field : 'status',
			title : '是否上架',
			align : 'center',
			formatter : function(value, row, index) {
				return value==1?'上架':'下架';
			}
		},{
			field : 'recomment',
			title : '推荐',
			align : 'center',
			formatter : function(value, row, index) {
				return value==1?'推荐':'不推荐';
			}
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return operator(false, true, true, value);
			}
		} ];
		tableInitPanel(columns);
	</script>

</body>
</html>
