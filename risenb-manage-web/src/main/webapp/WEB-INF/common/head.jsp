<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%> 
<link href="<%=basePath %>img/ico.ico" rel="shortcut icon" > 
<link href="<%=basePath%>js/plugins/ztree/css/demo.css" rel="stylesheet"  type="text/css">
<link href="<%=basePath%>js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"  type="text/css">
<link href="<%=basePath %>css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="<%=basePath %>css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="<%=basePath %>css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="<%=basePath %>css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>css/animate.css" rel="stylesheet">
<link href="<%=basePath %>css/style.css?v=4.1.0" rel="stylesheet">
<link href="<%=basePath %>css/custom.css" rel="stylesheet">

<!-- jQuery  -->
<script src="<%=basePath %>js/jquery.min.js?v=2.1.4"></script>

<script src="<%=basePath%>js/plugins/ztree/js/jquery.ztree.core-3.5.js" type="text/javascript" ></script>
<script src="<%=basePath%>js/plugins/ztree/js/jquery.ztree.excheck-3.5.js" type="text/javascript" ></script>

<!-- bootstrap  -->
<script src="<%=basePath %>js/bootstrap.min.js?v=3.3.6"></script>

<!-- table plugins -->
<script src="<%=basePath %>js/plugins/jeditable/jquery.jeditable.js"></script>
<script src="<%=basePath %>js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="<%=basePath %>js/plugins/dataTables/dataTables.bootstrap.js"></script>

<script src="<%=basePath %>js/common.js?v=1.0.0"></script>

<!-- jQuery Validation plugins-->
<script src="<%=basePath %>js/plugins/validate/jquery.metadata.js"></script>
<script src="<%=basePath %>js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=basePath %>js/plugins/validate/messages_zh.min.js"></script>
<script src="<%=basePath %>js/demo/form-validate-demo.js"></script>
<script src="<%=basePath %>js/plugins/webuploader/webuploader.min.js"></script>
<script src="<%=basePath %>js/web-upload-object.js"></script>
<link href="<%=basePath %>css/plugins/webuploader/webuploader.css" rel="stylesheet">
<!-- 自动以js 处理表单提交和异步操作数据 -->
<script src="<%=basePath %>js/custom.js"></script>
<!-- iCheck -->
<script src="<%=basePath %>js/plugins/iCheck/icheck.min.js"></script>

<!-- layer plugins -->
<script src="<%=basePath%>js/plugins/layer/layer.min.js"></script>

<!-- MyDatePicker 97 -->
<script src="<%=basePath %>js/plugins/My97DatePicker/WdatePicker.js"></script>

<!-- jqPaginator plugins -->
<script type="text/javascript" src="<%=basePath %>js/plugins/jqPaginator-1.2.0/jqPaginator.js"></script>

<!-- global variable -->
<script type="text/javascript">
	var shade = 0.3;
	var icon = "<i class='fa fa-times-circle'></i> ";
	var g_path= '<%=basePath%>';
</script>
