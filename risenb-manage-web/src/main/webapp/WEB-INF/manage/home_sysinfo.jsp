<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<style type="text/css">
			body{margin:0;padding:0;font-size:12px;color:#364f56;background-color:#FFFFFF;height:100%;}
			.we-txt{background:url(../img/computer.png) no-repeat 0px -80px; margin-left: 10px;}
			.we-txt a{color:#ff0000;}
			.we-txt p{line-height:24px; padding-left:158px; margin-top:17px; height: 200px;}
		</style>
	
	</head>
	<body>
	<div class="we-txt">
		<p>
			欢迎进入后台管理系统！<br>
			服务器操作系统：<span style="color:#0078ff;">${systemInfo.systemVersion}</span>&nbsp;&nbsp;&nbsp;&nbsp;<br>
                                剩余内存：<span style="color:#ff8400;">${systemInfo.systemFreeMemory}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                                最大内存：<span style="color:#00ac41;">${systemInfo.systemTotalMemory}</span><br>
            Java虚拟机：<span style="color:#0078ff;">${systemInfo.jvmVersion}</span><br>
                               虚拟机内存：<span style="color:#0078ff;">${systemInfo.jvmTotalMemory}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                               虚拟机剩余内存：<span style="color:#ff8400;">${systemInfo.jvmFreeMemory}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                               虚拟机最大内存 ：<span style="color:#00ac41;">${systemInfo.jvmMaxMemory}</span>               
        </p>
	</div>
	</body>

</html>
