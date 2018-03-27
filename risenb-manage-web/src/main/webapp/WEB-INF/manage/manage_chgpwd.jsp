<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp" %>

<body class="gray-bg">
      <div class="wrapper wrapper-content animated">
            <div class="col-sm-6" style="width: 800px;" >
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm" open-type="doajax" action="<%=basePath %>manage/modifyPwd.im">
                            <div class="pageFormContent"   data-layout-h="0">
					            <hr>
					            <input type="hidden" name="mid" value="${mid}"/>
					            <div class="form-group" style="margin: 20px 0 20px; ">
					                <label style="width:100px; text-align: right;" for="newpwd">新密码：</label>
					                <input style="display: inline;width: 450px;" required="true" minlength="6"  class="form-control"  id="newpwd" name="newpwd"  type="password"  placeholder="新密码" >
					            </div>
					            <div class="form-group" style="margin-left: 0px;" >
					                <label style="width:100px; text-align: right;" for="re_password">确认密码：</label>
					                <input style="display: inline;width: 450px;" class="form-control" required="true" minlength="6" equalTo="#newpwd" name="re_password" type="password" placeholder="确认密码"  >
					            </div>
							</div>
                            <div class="form-group">
				                <div class="col-sm-8 col-sm-offset-3 l-ibox-chgpwd-btn">
				                   <button class="btn btn-primary" type="submit">保存</button>
				                   <button class="btn" type="button" onclick="back()" style="margin-left: 110px;" >返回</button>
				                </div>
				           </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

<script type="text/javascript">
</script>
 
</body>
</html>