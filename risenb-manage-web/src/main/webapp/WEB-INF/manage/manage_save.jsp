<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ include file="../common/head.jsp" %>
<head>
 	<script type="text/javascript">
      </script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated ">
            <div class="col-sm-6" style="width: 800px;" >
                <div class="ibox float-e-margins">
                    <div class="ibox-content ">
                        <form class="form-horizontal m-t" id="signupForm" open-type="doajax" action="<%=basePath %>manage/saveManager.im" >
                             <input type="hidden" name="manageId" value="${manager.manageId}">
     				  		 <input type="hidden" name="idkey" value="${manager.idKey}">
                            <div class="pageFormContent" data-layout-h="0">
					            <div class="form-group" style="margin: 20px 0 20px; ">
					                <label class="control-label x85" for="userName">用户名称：</label>
					                <c:choose>
					                	<c:when test="${empty manager.manageId}">
                         		    		<input class="form-control" required="true" style="display: inline;width: 500px;" type="text" name="userName" id="userName" value="${manager.userName }"  >
					                	</c:when>
					                	<c:otherwise>
					                		<input class="form-control" required="true" style="display: inline;width: 500px;" type="text" name="userName" id="userName" value="${manager.userName }"  >
					                	</c:otherwise>
					                </c:choose>
					            </div>
					            <div class="form-group" style="margin-left: 0px;" >
					                 <label class="control-label x85">用户密码：</label>
                         		     <input class="form-control" required="true" minlength="6" style="display: inline;width: 500px;" type="password" name="password" value="${manager.password }"  size="15">
					            </div>
					            <div class="form-group" style="margin-left: 0px;" >
					                 <label class="control-label x85">真实姓名：</label>
                         		     <input class="form-control" required="true"   style="display: inline;width: 500px;" type="text" name="name" id="name" value="${manager.name }"  size="15">
					            </div>
					            <div class="form-group" style="margin-left: 0px;" >
					                 <label class="control-label x85" for="phone">手机号码：</label>
                       			     <input class="form-control" required="true" mobile="true"  style="display: inline;width: 500px;" type="text" name="phone" id="phone" value="${manager.phone }"  size="15">
					            </div>
							   <div class="form-group" style="margin-left: 0px;">
								<label class="control-label x85" for="phone" style="min-width:65px; float:left;">头像：</label>
								<div style="display: inline-block; margin-left:3px;">
									<div style="position:relative;">
										<div></div>
									</div>
									<div >
										<div class="head-scu-btn upload-btn" style="margin-top:5px;">
											<i class="fa fa-upload"></i>&nbsp;上传
										</div>
									</div>
									<input type="hidden" name="headImg" webupload="webupload" id="userAvatar" data-url="<%=basePath %>upload/uploadImg.im" />
								</div>
							</div>
							</div>
							<script type="text/javascript">
							</script>
                            <div class="form-group">
				                <div class="col-sm-8 col-sm-offset-3 l-ibox-content-btn">
				                   <button class="btn btn-primary"  type="submit">保存</button>
				                   <button class="btn" type="button"  onclick="back()" style="margin-left: 110px;" >返回</button>
				                </div>
				            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
</body>

</html>