<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="OVIT Project Manage System" />
<meta name="author" content="OVIT R&D" />
<title>在线考试系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-forms.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body class="page-body login-page login-light" onkeyup="enterLogin();">	
	<div class="login-container">	
		<div class="row">		
			<div class="col-sm-6" style="width:465px;position:absolute;left:0;right:0;top:0;bottom:0;margin:auto;">			
				<!-- Errors container -->
				<div class="errors-container" style="color:red" id="error">									
				</div>				
				<!-- Add class "fade-in-effect" for login form effect -->
				<form method="post" role="form" id="login" class="login-form fade-in-effect" action="${pageContext.request.contextPath}/manageSystem/login">					
					<div class="login-header">
						<a href="#" class="logo">
							<img src="${pageContext.request.contextPath}/assets/images/log2.png" alt="" width="160" />
						</a>						
						<p><h2>在线考试系统</h2></p>
					</div>					
					<div class="form-group">
						<label class="control-label" for="UserId"></label>
						<input type="text" class="form-control" name="userId" id="userId"  placeholder="userId"/>
					</div>					
					<div class="form-group">
						<label class="control-label" for="password"></label>
						<input type="password" class="form-control" name="password" id="password"  placeholder="Password"/>
					</div>					
					<div class="form-group">
						<button type="button" class="btn btn-primary  btn-block text-left" onclick="login();">
							<i class="fa-lock"></i>
							登录
						</button>
					</div>
					<!-- <div class="login-footer">
						<a href="https://self.51bsi.com/BSIPM/PersonMgr/PWD">忘记密码?</a>
					</div> -->
				</form>	
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
jQuery(document).ready(function($){
	// Reveal Login form
	setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);
	// Set Form focus
	$("form#login .form-group:has(.form-control):first .form-control").focus();
});

function enterLogin(){
	if(event.keyCode==13){  
    	login();  
	}
}

function login(){	
	var userId = $("#userId").val();
	var password = $("#password").val();
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/manageSystem/login",
		data : ({
			userId:userId,
			password:password,
		}),
		success : function(data) {
			if(data == "success"){
				window.location.href="${pageContext.request.contextPath}/manageSystem/index";
			}else if(data == "nullError"){
				$("#error").html("用户名或密码不能为空！");
			}else{
				$("#error").html("用户名或密码错误！");
			}
		},
		error:function(){
		
		}
	});
}
</script>
</html>