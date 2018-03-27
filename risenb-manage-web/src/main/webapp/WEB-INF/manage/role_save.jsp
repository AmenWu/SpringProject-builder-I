<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ include file="../common/head.jsp" %>

<body class="gray-bg"  style="width: 100%">
       <div class="wrapper wrapper-content animated">
            <div class="col-sm-6" style="width: 100%">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm" action="<%=basePath%>role/save.im" open-type="doajax">
                            <input type="hidden" name="roleId" value="${role.roleId}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">角色名称：</label>
                                <div class="col-sm-8">
                                    <input name="roleName" maxlength="10" required="true" class="form-control" type="text" value="${role.roleName}" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">所有权限：</label>
                                <div class="col-sm-8">
		                            <input type="hidden" name="pids" id="pids" VALUE="${psid}"/>
		                            <input class="form-control"  type="hidden" name="ps" id="ps"  value="${psname}" onclick="toshow()" readonly>
	                                <ul id="treeDemo" class="ztree"></ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3 l-role-save-btn">
                                 	<c:if test="${!empty sessionScope.permissionMap['role/save.im']}">
                                    	<button class="btn btn-primary"  type="submit" id="subbutton">保存</button>
                                    </c:if>
                                    <button class="btn btn-default" onclick="back()" type="button">返回</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
	
<script type="text/javascript">
	var setting = {
			callback: {
				onClick: zTreeOnClick
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "permissionId",
					pIdKey: "parentId"
				},key: {
					name: "title",
					url:"xUrl"
				}
			}
		};

		var zNodes = ${ztreeJson};
		//点击节点
		function zTreeOnClick(event, treeId, treeNode) {
			$("#modules").val(treeNode.name);
		    $("#moduleid").val(treeNode.id);
		    return false
		};
		
		$(document).ready(function(){
			var treeObj =  $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			treeObj.expandAll(true);
		});
		
		
		//保存按钮点击事件   
		$("#subbutton").bind("click",function(){
	    	   chec();
	    })
	    
	    function chec(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");  
	        var nodes=zTree.getChangeCheckedNodes(true);
	        var ids = "";   var titles = "";
	        for (var i = 0; i < nodes.length; i++) {  
				ids += "," + nodes[i].permissionId
				titles += "," + nodes[i].title 
	        }
	        ids = ids.substring(1);
	        titles = titles.substring(1);
	        $("#pids").val(ids);  
	        $("#ps").val(titles);
		}
		//回显选中的状态
		$(function(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = zTree.transformToArray(zTree.getNodes());
			var pids = $("#pids").val().split(",");
			for(var i=0;i<nodes.length;i++){
				$.each(pids,function(a,b){
					if(nodes[i].permissionId==b){
						zTree.checkNode(nodes[i], !nodes[i].checked, false, true)
					}
				});
			}
		});
		
	    
	</script>

</body>

</html>