<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ include file="../common/head.jsp" %>
<style>
   #permissionList {
	   margin-left:210px;
	   margin-right:20px;
	}
</style>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row animated">
           <div class="col-sm-3" style="width: 200px; float:left;">
                <div class="ibox float-e-margins">
                	<div class="ibox-content" style="padding-bottom: 5px; padding-top: 9px;">
                        <h5 style="padding-bottom: 0px;">权限列表</h5>
                    </div>
                    <div class="ibox-content" style="padding-left: 7px; padding-top: 3px;">
                        <div id="external-events">
                          	<ul id="treeDemo" class="ztree" style="padding-top: 0px;"></ul>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="animated" id="permissionList" style="background-color: white; padding: 10px 15px; border-top: 1px solid #e7eaec;">
            </div>
       </div>
    </div>
	<input type="hidden" value="${parentId}" name="perPid"  id="perPid">
	<input type="button" style="display: none" id="reloadPermison" value="tijia" onclick="refreshNavList();">
<script type="text/javascript">
	var setting = {
			callback: {
				onClick: zTreeOnClick
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "permissionId",
					pIdKey: "parentId"
				},key: {
					name: "title",
					url: "xUrl"
				}
			}
		};

		var zNodes = ${ztreeJson};
		//点击节点
		function zTreeOnClick(event, treeId, treeNode) {
		 	getPermissionList("<%=basePath%>permission/manage.im?parentId="+treeNode.permissionId);
		};
		$(document).ready(function(){
			var treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			treeObj.expandAll(true);
			var perPid = $("#perPid").val();
			if (perPid == undefined){
				perPid =0;
			}
			try {
				var node = treeObj.getNodeByParam("permissionId", perPid, null);
				treeObj.selectNode(node);
			}catch(error){
				
			}
			var url1 = "<%=basePath%>permission/manage.im?parentId="+perPid;
			getPermissionList(url1);
			
		});
		function getPermissionList(url){
			$("#permissionList").load(url);
		}
		function refreshNavList(){
			var permissionPid = $("#permissionPid").val();
			window.location.href="<%=basePath%>permission/navList.im?parentId="+permissionPid;
		}
		 //新增
		function add(){
			var permissionPid = $("#permissionPid").val();
			parent.layer.open({
			    type: 2,
			    title: '新增',
			    shadeClose: true,
			    shade: shade,
			    area: ['840px', '56%'],
			    content: '<%=basePath%>permission/toSavePage.im?parentId='+permissionPid, 
			});
		}
	    
	    //编辑
	    function edit(id){
	    	var permissionPid = $("#permissionPid").val();
			parent.layer.open({
			    type: 2,
			    title: '编辑',
			    shadeClose: true,
			    shade: shade,
			    area: ['840px', '56%'],
			    content: '<%=basePath%>permission/toSavePage.im?permissionId='+id+'&parentId='+permissionPid, 
			});
		}
	    
		  //删除
		  function del(id){	
			  var permissionPid = $("#permissionPid").val();
			  parent.layer.confirm('您是确定要删除吗？', {
			      btn: ['确定','取消'], //按钮
			      shade: false 
			  }, function(){
				  $.ajax({
			      		url:"<%=basePath%>permission/del.im",
			      		type:"post",
			      		data:{id:id},
			      		dataType:"json",
			      		success:function(state){
			      			var status =  state.status;
			 				if(status == 1){
			      				location = '<%=basePath%>permission/navList.im?parentId='+permissionPid;
			      				showSuccess("删除成功");
			      			}else{
			      				showFailMsg("删除失败");
			      			}
			      		}
			      	});
			  });
	      }
		
</script>
</body>

</html>