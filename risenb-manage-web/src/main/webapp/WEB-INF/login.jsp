<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Risenb</title>
<%@ include file="common/head.jsp" %>
<script>
	if(window.top != window.self){ 
		window.top.location = window.location;
	 }
	function changeImage(img){
	    img.src = img.src + "?" + new Date().getTime();
	}
	var id = ('${sessionManager.manageId }');
	if(id){
		location.href = "<%=basePath%>home/index.im";
	}
</script>
<style type="text/css">
	#j_captcha {
		width: calc(100% - 120px);
	}
</style>
</head>

<body class="gray-bg" id="gray-bg" style="width: 100%;height: 100%; overflow: auto;">
<input type="hidden" value="${error}" id="error">
<script type="text/javascript">
	var msg =$("#error").val();
		if(msg != ""){
		parent.layer.msg(msg,{time: 2000});
	}
</script>
	<div class="middle-box text-center   animated fadeInDown" style="width: 100%;height: 100%;">
		<div>
			<div>
				<h1 class="logo-name">risenb</h1>
			</div>
			<form class="m-t" role="form" action="<%=basePath %>login/login.im" method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="用户名"  name="userName" >
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="密码"  name="password"  required="required">
				</div>
				<div class="form-group" style="position: relative;">
    				<input id="j_captcha" name="checkcode" type="text" class="form-control" required="required"   placeholder="验证码">
    				<img  src="<%=basePath %>codeimg.code" onclick="changeImage(this)" alt="换一张"  style="position: absolute;right: 2px;top: 1px;height: 32px">
    			</div>
				<button type="submit" class="btn btn-primary block full-width m-b">登录</button>
			</form>
		</div>
	</div>
</body>
</html>
