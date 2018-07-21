<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="indexInclude.jsp" />
<style>
	.datagrid{float: left; margin-left: 5px;}
	.treeDept{width:300px; float:left; border: 1px solid #8DB2E3; overflow-y: auto;}
</style>
<div class="treeDept">
	<ul id="userDeptTree"></ul>
</div>
<div id="userList"></div>
<script type="text/javascript">

	$(document).ready(function() {
		initDeptTree();
		initUserList();
	});
	
	function initDeptTree(){
		var bodyHeight = $("#userList").parent().height();
		$(".treeDept").css('height', bodyHeight - 2);
		$('#userDeptTree').tree({
			width: 300,
			height: 10,
			url:'${path}/dept/deptTree',
			method : 'post', //检索数据的 http 方法（method）。 默认 post
			animate: false, //定义当节点展开折叠时是否显示动画效果。
			lines : true, //定义是否显示树线条。
			checkbox : false, //定义是否在每个节点前边显示复选框。
			dnd : false, //定义是否启用拖放
			onClick: function(node) {
				//nodeClick(node);
				console.log("111");
			},
			onLoadSuccess: function(data) {
				if(data.d){
					return data.d;
				} else {
					return data;
				}
			}
		});
	}
	
	function initUserList(){
		var bodyHeight = $("#userList").parent().height();
		var bodyWidth = ($("#userList").parent().width() - 25 - 300);
		$('#userList').datagrid({
			url : '${path}/user/userPage',
			pagination : true,
			width: bodyWidth,
			height : bodyHeight,
			rownumbers:true,
			singleSelect:true,
			columns : [[
				{field: 'id', width:30, checkbox:'true'},
				{field : 'userName',width : bodyWidth / 2,align : 'center',title : '用戶名'}, 
				{field : 'password',width : bodyWidth / 2,align : 'center',title : '密碼'} 
			] ]
		});
	}
</script>