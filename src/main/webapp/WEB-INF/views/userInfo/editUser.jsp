<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="OVIT Project Manage System" />
<meta name="author" content="OVIT R&D" />
<title>光谷信息项目管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/arimo/css/arimo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/linecons/css/linecons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-forms.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/select2/select2.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/select2/select2-bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/multiselect/css/multi-select.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery-form.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- <style type="text/css">
	.select2-choices{
	width:618px
} -->
</style>
</head>
<body class="page-body">
	<%@ include file="../frame/setting.jsp" %>
	<div class="page-container">
		<%@ include file="../frame/left.jsp" %>
		<div class="main-content">
			<%@ include file="../frame/nav.jsp" %>
			<script type="text/javascript">
					
		</script>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">编辑用户</h1>
					<p class="description">重新编辑用户信息</p>
				</div>

				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
						<%-- <li><a href="${pageContext.request.contextPath}/partakeitems/list"><i class="fa-home"></i>首页</a> --%>
						</li>
						<li class="active"><strong>用户管理</strong></li>
						<li class="active"><strong>编辑用户</strong></li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
			<form id="saveItemsForm" action="${pageContext.request.contextPath}/edit/saveUser" method="post">
						   <input type="hidden" name="id" id="id" value="${record.userId}"></input>
							<div class="panel-heading">
							<h3 class="panel-title">编辑用户信息</h3>
						    </div>
							
							<div class="row col-margin " style="margin-top:20px">
							<div class="col-xs-12">
								<table>
									<tr>
									<td><div style="width: 148px" >用户ID</div></td>
									<td>
									<input type="hidden" name="userId" id="userId" value="${record.userId}"></input>
									<span id="userId">${record.userId}</span>
									</tr>
								</table>	
								</div>
								<div class="col-xs-12">
								<table>
									<tr>
									<td ><div style="width: 130px" >用户姓名：</div></td>
									<td class="col-xs-10"><input type="text" id="name" name="name" class="form-control" placeholder="用户姓名" value=${record.name} /></td>
									</tr>
								</table>	
								</div>
									
								<div class="col-xs-12">
								<table>
									<tr>
									<td ><div style="width: 130px">用户密码：</div></td>
									<td class="col-xs-10"><input type="text" id="password" name="password" class="form-control" placeholder="用户密码" value='${record.password}' /></td>
									</tr>
								</table>	
								</div>
								
								
						
								<div class="col-xs-12">
								<table>
									<tr>
									<td><div style="width: 130px" >用户类型：</div></td>
									<td class="col-xs-10">
									
									<select class="form-control" id="type" name="type"  >
                                    <option value="0">请选择用户类型</option>
					    			<option value="1" <c:if test="${'1' eq record.type}">selected</c:if>>学生</option>
									<option value="2" <c:if test="${'2' eq record.type}">selected</c:if>>教师</option>
									<option value="3" <c:if test="${'3' eq record.type}">selected</c:if>>管理员</option>
									</select></td>
									</tr>
								</table>	
								</div>
								<div class="col-xs-12">
								<table>
									<tr>
									<td ><div style="width: 130px">性别：</div></td>
									<td class="col-xs-10">
									<label><input name="sex" type="radio" value="男"<c:if test="${'男' eq record.sex}">checked</c:if> />男</label> 
                                    <label><input name="sex" type="radio" value="女" <c:if test="${'女' eq record.sex}">checked</c:if> />女 </label> 
                                    </td>
									</tr>
								</table>	
								</div>
							</div>
							<div>
							
							<table>
									<tr>
									<td><div style="width: 130px" ></div></td>
									<td>
									<span class="btn"><a href="javascript:void(0)" class="btn btn-purple" onclick="save()"id="saveBtn">保&nbsp;存</a></span>
									<span class="btn"><a href="${pageContext.request.contextPath}/user/list" class="btn btn-red">取&nbsp;消</a></span>
									</td>
									</tr>
							</table>
						   </div>
						
						</form>
						</div>
						
			<!-- Main Footer -->
			<%@ include file="../frame/footer.jsp" %>
		</div>
	</div>
	<%@ include file="../frame/bottomScript.jsp" %>
</body>
<script type="text/javascript">


function validate(){
	var reg=/^([1-9][0-9]*)$/;
	 if($('#name').val()==""){
		 layer.tips('用户姓名不能为空！', '#name', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
	 return false;
	 }
	 
	 if($('#userId').val()==""){
		 layer.tips('用户ID不能为空！', '#userId', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 return false;
	 }
	 if($('#password').val()==''){
		 layer.tips('密码不能为空！', '#password', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#type').val()==0){
		 layer.tips('请选择用户类型！', '#type', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }else{
		 return true;
	 }
}
function save(){
	
	if(!validate()){
		return ;	
	}
	 $('#saveItemsForm').submit();
}

</script>
</html>