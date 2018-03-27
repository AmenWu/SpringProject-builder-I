<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ include file="../common/head.jsp" %>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated">
            <div class="col-sm-6" style="width: 800px;" >
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                     <form id="role" class="pageForm" class="form-horizontal m-t">
                          <input type="hidden" name="parentId" value="${parentId}">
      					  <input type="hidden" name="permissionId" value="${permission.permissionId}">
        				<div class="form-group">
                            <label for="rolename" class="control-label x120 l_control-label">权限名称：</label>
                            <input style="display: inline;width: 600px;" class="form-control" type="text" name="title" id="title" value="${permission.title }" >
                         </div>
                         <div class="form-group">   
                            <label for="isMenu" class="control-label x120 l_control-label">权限类型：</label>
                            <c:choose>
                            	<c:when test="${permission.isMenu== '0' }">
                            		<input type="radio" value="0" name="isMenu" checked="checked"> 左侧菜单
                            		<input type="radio" value="1" name="isMenu"> 功能按钮
                            	</c:when>
                            	<c:when test="${permission.isMenu== '1' }">
                            		<input type="radio" value="0" name="isMenu" > 左侧菜单
                            		<input type="radio" value="1" name="isMenu" checked="checked"> 功能按钮
                            	</c:when>
                            	<c:otherwise>
                            		<input type="radio" value="0" name="isMenu" checked="checked"> 左侧菜单
                            		<c:if test="${parentId != 0}">
                            		<input type="radio" value="1" name="isMenu"> 功能按钮
                            		</c:if>
                            	</c:otherwise>
                            </c:choose>
                            </div>
                            <div class="form-group">
                           		 <label for="url" class="control-label x120 l_control-label">访问地址：</label>
                           		 <input style="display: inline;width: 600px;" class="form-control" type="text" name="url" id="url"  value="${permission.url }">
                            </div>
                            <div class="form-group">
                          		  <label for="sort" class="control-label x120 l_control-label">排序：</label>
                           		  <input style="display: inline;width: 600px;" class="form-control" type="text" name="sort" id="sort" value="${permission.sort }">
                            </div>
					        <div class="form-group l-permission-save-btn">
					               <button type="button" class="btn btn-primary" onclick="save()" style="margin-left: 200px;" >保存</button>
					               <button type="button" class="btn" data-icon="save"  onclick="back()" style="margin-left: 110px;" >返回</button>
				     	    </div>
			         </form>
                    </div>
                </div>
            </div>
        </div>

<script type="text/javascript">
	function save(){
		
		if($("[name='title']").val() == ''){
			parent.layer.msg('权限名称', {icon: 0});	
    		return;
		}
		if($("[name='url']").val() == ''){
			parent.layer.msg('访问地址', {icon: 0});	
    		return;
		}
		if($("[name='sort']").val() == ''){
			parent.layer.msg('排序', {icon: 0});	
    		return;
		}
		$.ajax({
			url:"<%=basePath%>permission/save.im",
			type:"post",
			data:$("#role").serialize(),
			dataType:"json",
			success:function(state){
			    var status =  state.status;
				if(status  == 1){
					if($("[name='permissionId']").val() != '' ){
						showSuccess("修改成功");
					}else{
						showSuccess("添加成功");
					}
					window.top.freshPremission();
					closeWindow();
				}else{
					showFailMsg(state.errorMsg);
					closeWindow();
				}
			}
		});
	}
</script>

</body>

</html>