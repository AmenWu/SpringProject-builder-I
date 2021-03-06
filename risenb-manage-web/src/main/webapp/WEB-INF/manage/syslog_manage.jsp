<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<%@ include file="../common/head.jsp" %>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                    	<div style="margin-bottom: 10px;display: inline-block;float: right;">
	                    	<form action="<%=basePath %>syslog/manage.im" id="pageForm" name="pageForm" method="post">
	                    	    <input type="hidden" name="pageSize" value="${page.pageSize}">
							    <input type="hidden" name="pageCurrent" value="${page.pageNo}">
							    <input type="hidden" name="orderField" value="${page.orderField}">
							    <input type="hidden" name="orderDirection" value="${page.orderDirection}">
								<label>操作时间：</label>
								<input onclick="WdatePicker()" style="width: 200px;display: inline;" 
									class="form-control" type="text" name="startTime" id="startTime" 
										value="${page.params.startTime }" data-toggle="datepicker" size="15"> ~ 
	        					<input onclick="WdatePicker()" style="width: 200px;display: inline;" 
	        						class="form-control" type="text" name="endTime" id="endTime" 
	        							value="${page.params.endTime }" data-toggle="datepicker" size="15">&nbsp;&nbsp;
	        					<button class="btn btn-primary search_data" id="search_data" type="button">查询</button>&nbsp;&nbsp; 
								<button class="btn btn-primary search_clear" id="search_clear" type="button">刷新</button>
							</form>
						</div>
                        <table class="table table-striped table-bordered table-hover">
                        	<thead>
                        		<tr>
                                    <th>管理员</th>
                                    <th>操作模块</th>
                                    <th>目标方法</th>
                                    <th>请求开始时间</th>
                                    <th>请求结束时间</th>
                                    <th>耗时(毫秒)</th>
                                    <th>操作状态</th>
                                    <th>创建时间</th>
                                    <th width="50px">操作</th>
                                </tr>
                        	</thead>
                            <tbody>
                               <c:forEach var="sysLog" items="${page.results}">
                                	<tr class="gradeX">
                                		<td>${sysLog.operator}</td>
						                <td>${sysLog.operaModule}</td>
						                <td>${sysLog.methodName}</td>
						                <td><fmt:formatDate value="${sysLog.startTime}" type="both"/></td>
						                <td><fmt:formatDate value="${sysLog.endTime}" type="both"/></td>
						                <td>${sysLog.consum}</td>
						                 <td>${sysLog.status == 0 ? "<font color='green'>成功</font>" : "<font color='red'>失败</font>"}</td>
						                <td><fmt:formatDate value="${sysLog.createTime}" type="both"/></td>
						                <td nowrap style="white-space:nowrap;word-break:nowrap">
											<a href="javascript:;" open-type="open" data-width="845px" data-height="600px" data-url="<%=basePath%>syslog/detail.im?logId=${sysLog.logId}"  class="btn btn-default btn-xs boder"><i class="fa fa-eye"></i>&nbsp;详情</a>
                               		     </td>
                               		 </tr>
                                </c:forEach>
                            </tbody>
                        </table>	
                        <%@ include file="../common/page.jsp" %>
                     	<div style="clear:both;"></div>
                    </div>
                     
                </div>
            </div>
        </div>
    </div>
	
</body>

</html>
    