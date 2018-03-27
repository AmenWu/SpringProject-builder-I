<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ include file="../common/head.jsp" %>

<head>
 	<style type="text/css">
 		.detail_td{text-align: right; font-weight: 800;}
 	</style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated ">
            <div class="col-sm-6" style="width: 807px;" >
                <div class="ibox float-e-margins">
                    <div class="ibox-content ">
                   		 <div id="table_data">
                       <table class="table table-striped table-bordered table-hover l-syslog-table">
                        	<tbody>
                        		<tr class="gradeX">
                                    <td class="detail_td">操作员：</td>
                                    <td>${sysLog.operator}</td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">操作模块：</td>
                                    <td>${sysLog.operaModule}</td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">目标方法：</td>
                                    <td>${sysLog.methodName}</td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">请求开始时间：</td>
                                    <td><fmt:formatDate value="${sysLog.startTime}" type="both"/></td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">请求结束时间：</td>
                                    <td><fmt:formatDate value="${sysLog.endTime}" type="both"/></td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">耗时(毫秒)：</td>
                                    <td>${sysLog.consum}</td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">请求参数：</td>
                                    <td><span style="word-break:normal; max-width:700px; display:block; white-space:pre-wrap;word-wrap : break-word ;overflow: hidden "><c:out value="${sysLog.args}"></c:out></span> </td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">返回结果：</td>
						            <td><span style="word-break:normal; max-width:700px; display:block; white-space:pre-wrap;word-wrap : break-word ;overflow: hidden "><c:out value="${sysLog.result}"></c:out></span></td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">操作状态：</td>
                                    <td>${sysLog.status == 0 ? "<font color='green'>成功</font>" : "<font color='red'>失败</font>"}</td>
                                </tr>
                        		<tr class="gradeX">
                                    <td class="detail_td">创建时间：</td>
                                    <td><fmt:formatDate value="${sysLog.createTime}" type="both"/></td>
                                </tr>
                        	</tbody>
                        </table>
                        <div class="form-group l-syslogdetail-btn">
					        <button type="button" class="btn" data-icon="save"  onclick="back()"  >返回</button>
				     	 </div>
                    </div>
                </div>
            </div>
        </div>
	</div>
</body>
</html>