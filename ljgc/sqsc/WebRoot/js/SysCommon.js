var tableUrl = "";
var modelName = "";

var tableInitPanel = function(columns,url, queryParams) {
	var $table = $('#table'),
	$searchForm = $("#searchForm");
	if (queryParams === undefined||queryParams===null) {
		queryParams = function(params) {
			if($searchForm){
				params.where = JSON.stringify($searchForm.serializeObject());
			}
			return params;
		}
	}
	
	modelName = $table.attr('model-name');
	if(url==undefined){
		url = "viewList/"+modelName;
	}
	if($searchForm){
		var $searchButton = $searchForm.find('.table-search-button');
		$searchForm.keyup(function(event){
			if(event.keyCode==13){
				//tableSearch();
				$table.bootstrapTable('refresh');
			}
		});
		$searchButton.on('click',function(){
			$table.bootstrapTable('refresh');
		});
		// $('#table').bootstrapTable('refresh',{query:{where:JSON.stringify(json)}});
	}
	$table.bootstrapTable({
		method : 'POST',
		dataType : 'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		url:sysUrl+url,
		paginationLoop : true,
		pageList : [ 5, 10, 15, 20, 40 ],
		pageNumber : 1,
		showHeader : true,
		fixed : true,
		showFooter : false,
		showPaginationSwitch : false,
		showColumns:true,
		showRefresh:true,
		toolbar:'#toolbar',
		pageSize : 15,
		sidePagination : "server",
		pagination : true,
		queryParams : queryParams,
		columns : columns,
		responseHandler : function(res) {
			var result = res.data;
			return {
				rows : result.list,
				total : result.totalRow
			};
		}
	});
	
//	$('#table thead>tr:first-child').css('position','fixed').css('height','40px');

}


var swalDialog = function(text,action){
	swalAlert({
		title:'提示',
		text: text,
		confirm:{
			text:'确定'
		},
		action:function(res){
			if(action){
				action(res);
			}
			swal.close();
		}
	});
}

var swalTip = function(title,text,success,action){
	var json = {
			title:title,
			text: text,
			confirm:{
				text:'确定'
			},
			action:function(res){
				if(action){
					action(res);
				}
				swal.close();
			}	
	};
	if(success){
		json.type="success";
	}else{
		json.type="error";
	}
	swalAlert(json);
}

var swalWarning = function(text,action){
	swalAlert({
		title:'警告',
		text: text,
		confirm:{
			text:'确定'
		},
		cancel:{
			text:'取消'
		},
		action:function(res){
			if(action){
				action(res);
			}
		}
	});
}

var swalAlert = function(json){
	var alert = {
		title: json.title||'标题',
		text: json.text||'内容',
		closeOnConfirm: false,
		showCancelButton:false,
		showConfirmButton:false,
	}
	if(json.type){
		alert.type = json.type;
	}
	if(json.cancel){
		alert.showCancelButton = true;
		alert.cancelButtonColor="#D0D0D0";
		alert.cancelButtonText=json.cancel.text||'取消';
	}
	if(json.confirm){
		alert.showConfirmButton = true;
		alert.confirmButtonColor= "#DD6B55";
		alert.confirmButtonText=json.confirm.text||'确定';
	}
	swal(alert,function(res){
		if(json.action){
			json.action(res)
		}
	});
}
var commonImg = function(url) {
	return ['<img alt="图片去哪了" src="',url,'"style="width:100px;100px"/>'].join("");
}

var blueimpImg = function(url) {
	return ['<a href="',url,'" title="图片" data-gallery=""><img width="100px" height="100px" alt="图片去哪了" src="',url,'"></a>'].join("");
}

var operator = function(see, modify, del,id,type) {
	var v = '';
	if (see) {	
		v += '<a onclick="tableSee(\''+id+'\','+type+')" title="查看"><i class="glyphicon glyphicon-eye-open"></i></a> ';
		}
	if (modify) {
		v += '<a onclick="tableModify(\''
				+ id
				+ '\','+type+')" title="修改"><i class="glyphicon glyphicon-pencil"></i></a> ';
	}
	if (del) {
		v += '<a onclick="tableDel(\''
				+ id
				+ '\')" title="删除"><i class="glyphicon glyphicon-trash"></i></a> ';
	}
	return v;
}




//显示一组图片
var showPic=function(picArr){	
	var v='';
	picArr.forEach(function(value, index, array){
		v+='<img  src="'+value.pic+'" style="width:100px;height:100px;padding-right:10px" >';
	});
	return v;
}
var setPerm=function(id){
	tableWin('设置权限','role/showPermMenu?id='+id);

}

var seeJoin=function(id){
	tableWin('参与会员','lottoRecord/joinMember?id='+id);
}

  /**
	* @方法名:
	* @参数:
	* @输出:
	* @备注:
	* @作者: 林
	* @时间: 2017年12月18 11:20:10
	* @修改:  
	*/ 
var problem = function(id) {
	var v ='<a onclick="seeProblem(\''+id+'\')" title="查看"> <i class="glyphicon glyphicon-eye-open"></i></a>'
	return v;
}
var seeProblem=function(id){
	tableWin('问题详情','problem/detail?id='+id);	
}
/**
* @方法名:单个用户查询余额
* @参数:
* @输出:
* @备注:
* @作者: 林
* @时间: 2017年12月26 4:56:20
* @修改:
*/ 
var userSum = function(id) {
	var v ='<a onclick="seeSum(\''+id+'\')" title="查看"> <i class="glyphicon glyphicon-eye-open"></i></a>'
	return v;
}
var seeSum=function(id){
	tableWin('此用户充值记录','month/userSum?id='+id);	
}
/**  
* @方法名:  问题分页
* @参数:
* @输出:
* @备注:
* @作者: 林
* @时间: 2018年1月3 5:42:27
* @修改:
*/ 
var tableInitPanelProblem = function(columns,url, queryParams) {
	var $table = $('#table'),$searchForm = $("#searchForm");
	if (queryParams === undefined||queryParams===null) {
		queryParams = function(params) {
			if($searchForm){
				params.where = JSON.stringify($searchForm.serializeObject());
			}
			return params;
		}
	}
	
	modelName = $table.attr('model-name');
	if(url==undefined){
		url = "viewList/"+modelName;
	}
	if($searchForm){
		var $searchButton = $searchForm.find('.table-search-button');
		$searchForm.keyup(function(event){
			if(event.keyCode==13){
				//tableSearch();
				$table.bootstrapTable('refresh');
			}
		});
		$searchButton.on('click',function(){
			$table.bootstrapTable('refresh');
		});
		// $('#table').bootstrapTable('refresh',{query:{where:JSON.stringify(json)}});
	}
	$table.bootstrapTable({
		method : 'POST',
		dataType : 'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		url:sysUrl+url,
		paginationLoop : true,
		pageList : [ 5, 10, 15, 20, 40 ],
		pageNumber : 1,
		
		idfixed : 0 ,
		fixedHeaderTable :false ,
		
		showHeader : true,
		showFooter : false,
		showPaginationSwitch : false,
		showColumns:true,
		showRefresh:true,
		toolbar:'#toolbar',
		pageSize : 5,
		sidePagination : "server",
		pagination : true,
		queryParams : queryParams,
		columns : columns,
		responseHandler : function(res) {
			var result = res.data;
			return {
				rows : result.list,
				total : result.totalRow
			};
		}
	});

}


/**
* @方法名: 确认发款
* @参数:
* @输出:
* @备注:
* @作者: 林
* @时间: 2017年12月28 10:04:23
* @修改:
*/ 

var NoMoney = function(id) {
	
	var v ='<a onclick="tableSee(\''+id+'\')" title="查看"> <i class="glyphicon glyphicon-eye-open"></i></a>'
	return v;
}
var giveMoney = function(id) {
	
	var v ='<a onclick="tableSee(\''+id+'\')" title="查看"> <i style="margin-right:10px;" class="glyphicon glyphicon-eye-open"></i></a>'
	v +=  '<a onclick="getUserMoney(\''+id+'\')" title="已提现"><i class="glyphicon glyphicon-pencil"></a>'
	return v;
}

var getUserMoney=function(id){
	layer.confirm('确认已打款？', {
		  btn: ['已打款', '未打款', '已驳回'] //可以无限个按钮
		  ,btn3: function(index, layero){
		    //按钮【按钮三】的回调
			tableWin('','userTiXian/TiXian?id='+id+'&status=3');
		  }
		}, function(index, layero){
			tableWin('','userTiXian/TiXian?id='+id+'&status=2');
		  //按钮【按钮一】的回调
		}, function(index){
			return ;
		  //按钮【按钮二】的回调
		});
	
	/*layer.msg('', {
		  time: 0 //不自动关闭
		  ,btn: ['', '未打款','已驳回']
		  ,yes: function(index){
			tableWin('','userTiXian/TiXian?id='+id);	
		    layer.close(index);
		 }
	});*/
}


/**
* @方法名: 数据来了
* @参数:
* @输出:
* @备注:
* @作者: 林
* @时间: 2018年1月8 5:36:44
* @修改:
*
*/
var tableOut = function(url){
	layer.msg('导出成功');
	ajaxPOST(url,function(data){
		swalTip("提示", data.message, data.success,function(res){
			if(res){
				
				refresh();
			}
		});
	})
}

/**
* @方法名: 用户删除功能增强
* @参数:
* @输出:
* @备注:
* @作者: 林
* @时间: 2018年1月13 9:50:29
* @修改:
*/ 
var operatorUser = function(see, modify, del,id,type) {
	var v = '';
	if (see) {	
		v += '<a onclick="tableSee(\''+id+'\','+type+')" title="查看"><i class="glyphicon glyphicon-eye-open"></i></a> ';
		}
	if (modify) {
		v += '<a onclick="tableModify(\''
				+ id
				+ '\','+type+')" title="修改"><i class="glyphicon glyphicon-pencil"></i></a> ';
	}
	if (del) {
		v += '<a onclick="userTableDel(\''
				+ id
				+ '\')" title="删除"><i class="glyphicon glyphicon-trash"></i></a> ';
	}
	return v;
}

var openLotto=function(id){
	ajaxPOST("lotto/openLotto",{id:id},function(data){
		swalTip("提示", data.message, data.success,function(res){
			if(res){
				refresh();
			}
		});
	})
}
var commentList=function(id){
	tableWin('评论列表','comment/showList?id='+id);
}


var tableSee = function(id,type) {
	var typeStr="";
	if(type!=null&&type!=undefined){
		typeStr="&type="+type;
	}
	tableWin('查看','view/'+modelName+'-see?id='+id+typeStr);
}

var tableAdd = function(type) {
	if(type!=null&&type!=undefined){
		type="?type="+type;
	}else{
		type="";
	}
	tableWin('添加','view/'+modelName+'-add'+type);
}

var tableModify = function(id,type) {
	tableWin('修改','view/'+modelName+'-modify?id='+id+"&type="+type);
}



var layerIndex;

var tableWin = function(title,url){
	layerIndex =layer.open({
		  type: 2,
		  title: title,
		  shadeClose: true,
		  shade: 0.8,
		  anim :2,
		  move: false,
		  area: ['100%', '100%'],
		  content: sysUrl+ url//iframe的url
	}); 
}


var showCityIndex;
//选择城市列表
var showCityList = function(ADID){
	showCityIndex =layer.open({
		  type: 2,
		  title: "选择城市",
		  shadeClose: true,
		  shade: 0.8,
		  anim :2,
		  move: true,
		  area: ['70%', '60%'],
		  content: sysUrl+ "china/showCityList?ADID="+ADID//iframe的url
	}); 
}


var tableSearch = function(){
	var json = $('#searchForm').serializeObject();
	$('#table').bootstrapTable('refresh',{query:{where:JSON.stringify(json)}});
}



/**
 * var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
 * parent.layer.close(index)
 */
var closeLayer = function(url){
	var index = parent.layer.getFrameIndex(window.name);
	try{
		parent.refresh(url);
	}catch(error){
		console.log(error);
	}
	parent.layer.close(index);
}


var tableDel = function(id) {
	var array = new Array();
	array.push(id);
	swalWarning("确认删除?删除之后将无法恢复!",function(res){
		if(res){
			ajaxPOST("delete/"+modelName,{ids:array.join(",")},
				function(data) {			
					swalTip("提示", data.message, data.success,function(res){
						if(res){
							refresh();
						}
						swal.close();
					});
				});
		}
	});
}

var ajaxPOST = function(url,data,callback){
	var json = {
		type : "POST",
		url : sysUrl+url,
		dataType : "json",
		cache: false,
		timeout:30000,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : data,
		success : function(data) {
			callback(data);
		},
		error  :function(data){
		}
	};
	$.ajax(json);
}


var delSelect=function() {
	var obj = $('#table').bootstrapTable('getSelections');
	var array = new Array();
	obj.forEach(function(x) {
		array.push(x.menuId);
	});
	if(array.length<=0){
		swalDialog("请选择要删除的数据");
		return
	}
	swalWarning("确认删除?删除之后将无法恢复!",function(res){
		if(res){
			ajaxPOST("delete/"+modelName,{ids:array.join(",")},
					function(data) {			
						swalTip("提示", data.message, data.success,function(res){
							if(res){
								refresh();
							}
						});
					});
		}
	});
}

var refresh = function(url){
	if(url==undefined){
		$('#table').bootstrapTable('refresh',{});
	}else{
		$('#table').bootstrapTable('refresh',{url:sysUrl+url});
	}
	
}



var img = function(url) {
	var v = '';
	
		v += '<img src=\''
				+ url
				+ '\' style="width:100px;100px"/> ';
	
	return v;
}

var responseHandler = function(res) {
	return res.data.list;
}



$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		var newName = this.name;
		if (o[newName]) {
			if (!o[newName].push) {
				o[newName] = [ o[newName] ];
			}
			o[newName].push(this.value || '');
		} else {
			o[newName] = this.value || '';
		}
	});
	return o;
}
//用户管理
var modifyMember=function(obj){	
	ajaxPOST("modify/member",{where:JSON.stringify(obj)},
			function(data) {			
				swalTip("提示", data.message, data.success,function(res){
					if(res){
						refresh();
					}
					swal.close();
				});
			});
}

//审核
var memberCheck=function(id){
	swalWarning("确认审核通过？",function(res){
		var json={U_id:id,isDel:"0"};
		if(res){
			modifyMember(json);
		}
	});
}

//启用&禁用用户账号
var operationMember=function(memberId,type){
	var tipMsg=type==0?"确定启用?":"确认禁用?";
	swalWarning(tipMsg,function(res){
		if(res){
			ajaxPOST("member/operationMember",{id:memberId,type:type},
					function(data) {			
						swalTip("提示", data.message, data.success,function(res){
							if(res){
								refresh();
							}
						});
					});
		}
	});
	
}

//确认线下支付
var enterPayResult=function(id){
	swalWarning("确认到账?!",function(res){
		if(res){
			ajaxPOST("cz/enterPayResult",{id:id},
					function(data) {			
						swalTip("提示", data.message, data.success,function(res){
							if(res){
								refresh();
							}
						});
					});
		}
	});
	
}

//验证表单
var checkVals=function(val){
if(val==""||val==null||val==undefined){
	return true
}
return false;
}

//修改平台收益比例
var modifyRatio=function(id){
	var ratio=$("#"+id).val();
	var json={U_id:id,ratio:ratio};	
	modifyMember(json);
}

/**
* @方法名:  用户删除功能增强
* @参数:
* @输出:
* @备注:
* @作者: 林
* @时间: 2018年1月13 9:51:53
* @修改:
*/ 
var userTableDel = function(id) {
	var array = new Array();
	array.push(id);
	swalWarning("删除用户会将此用户的所有信息全部删除，确认删除?",function(res){
		if(res){
			ajaxPOST("MtUser/delete",{ids:array.join(",")},
				function(data) {			
					swalTip("提示", data.message, data.success,function(res){
						if(res){
							refresh();
						}
						swal.close();
					});
				});
		}
	});
}

var tableAddTwo = function(url) {
	console.log("访问地址是:"+url);
	location.href=url;
}

