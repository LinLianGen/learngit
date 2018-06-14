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
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableAddTwo('admin/mtGoods/mtGoodsAdd')">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
										</button>
										
					   				
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="type_name" 
										      		 type="text" placeholder="请输入类型名称">
										    </div>
										</div>
									
										
										
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="mtGoods"></table>
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
				field : 'goods_name',
				title : '商品名称',
				align : 'center'
			},{
			field : 'img_url',
			title : '商品图片',
			align : 'center',
			formatter : function(value, row, index) {
				var i = "";
				var splitArray = new Array();
				var regex = /,/;
				splitArray = value.split(regex)
			
			return '<img alt="" src="'+splitArray[0]+'" width="80px;" height="80px;">';
					
			
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
			field : 'price',
			title : '商品价格',
			align : 'center'
		},{
			field : 'goods_num',
			title : '商品数量',
			align : 'center'
		},{
			field : 'goods_detail',
			title : '商品详情图',
			align : 'center',
			formatter : function(value, row, index) {
				return '<img alt="" src="'+value+'" width="80px;" height="80px;">';
			}
		},{
			field : 'recomment',
			title : '是否推荐',
			align : 'center',
			formatter : function(value, row, index) {
				return value==1?'推荐商品':'非推荐商品';
			}
		},{
			field : 'content',
			title : '文字描述',
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
