<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
<form action="<%=basePath%>permission/list.im" id="pageForm1" name="pageForm" >
<input type="hidden" name="parentId" id="permissionPid" value="${parentId}"/>
<input type="hidden" name="pageSize" value="${page.pageSize}" id="pageSize">
<input type="hidden" name="pageCurrent" value="${page.pageNo}">
<input type="hidden" value="${parentId}" name="perPid"  >
</form>
<c:if test="${!empty sessionScope.permissionMap['permission/toSavePage.im'] and !empty sessionScope.permissionMap['permission/save.im']}">
   <a class="btn btn-primary" href="javascript:;" onclick="add()" style="margin-bottom: 10px;" ><i class="fa fa-plus"></i> 新增</a>
</c:if>
	<div class="mail-box" style="border: 0px;">
	   <table id="permissionListTable" class="table table-striped table-bordered table-hover" >  
	         <thead>
	         	<tr>
		          	<th>权限ID</th>
		          	<th>权限名称</th>
		          	<th>URL</th>
		            <th>菜单类型</th>
		            <th>排序</th>
		            <th width="50px">操作</th>
	        	</tr>
	         </thead>
	         <tbody>
				<c:forEach var="ps" items="${page.results}">
		           <tr>
			           	<td>${ps.permissionId}</td>
			           	<td>${ps.title}</td>
			           	<td>${ps.url}</td>
			           	<td>
			           		<c:if test="${ps.isMenu == 0}">
			           			左侧菜单
			           		</c:if>
			           		<c:if test="${ps.isMenu == 1}">
			           			功能菜单
			           		</c:if>
			           	</td>
			           	<td>${ps.sort}</td>
			           	<td nowrap style="white-space:nowrap;word-break:nowrap">
			           	    <c:if test="${!empty sessionScope.permissionMap['permission/toSavePage.im']}">
			           			<a href="javascript:;" onclick="edit(${ps.permissionId})" class="btn btn-default btn-xs " ><i class="fa fa-pencil"></i> 编辑</a>
			           		</c:if>
			           		<c:if test="${!empty sessionScope.permissionMap['permission/del.im']}">
								<a href="javascript:;" onclick="del(${ps.permissionId})" class="btn btn-default btn-xs " ><i class="fa fa-trash-o"></i> 删除</a>
							</c:if>
			           	</td>
		           </tr>
	          </c:forEach>
	      </tbody>
	 	</table>
	</div>
	<%@ include file="../common/asyncPage.jsp" %>
	<div style="clear:both;"></div>
 
