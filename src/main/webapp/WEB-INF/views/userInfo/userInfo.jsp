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
<title>在线考试系统</title>
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
			<!-- <script type="text/javascript">
				jQuery(document).ready(function($){
					$("#s2example-2").select2({
						placeholder: '请选择当值销售',
						allowClear: true
					}).on('select2-open', function()
					{
						// Adding Custom Scrollbar
						$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
					});
					
					$.ajax({
						url : '${pageContext.request.contextPath}/items/dept',
						type:'post',
					    success: function(data) {
							   var itemsDept="<option value='0'>请选择责任部门</option>";
							   $(data).each(function(i,item){
									itemsDept+="<option value='"+item.name+"'>"+item.name+"</option>";
									
								})
					          
					           $('#respDeptName').html(itemsDept);
							   //根据责任部门获取项目经理
							   $("#respDeptName").val("${record.respDeptName}");
							   dept=$('#respDeptName').val();
							   var trObj ="";
							   $.ajax({
									url : '${pageContext.request.contextPath}/items/getMember',
									type:'post',  
									data: ({
										dept:dept
									}),
									success : function(data) { 
										trObj="<option value='0'>请选择项目经理</option>";
										$(data).each(function(i,item){
											 trObj +="<option value='"+item.account+"'>"+item.name+"</option>"; 
											 
										});
										$("#pmAccount").html(trObj);
										$("#pmAccount").val("${record.pmAccount}");
										
									}, 
									error:function(error){ 
										 alert(error.responseText);
									}
							});
							   
					        }
					});
					$.ajax({
						url : '${pageContext.request.contextPath}/items/projectType',
						type:'post',
					    success: function(data) {
							   var itemsType='';
							   $(data).each(function(i,item){
									itemsType+="<option value='"+item.dictValue+"'>"+item.dictName+"</option>";
									
								})
					           $('#projectType').html(itemsType);
							   $("#projectType").val("${record.projectType}");
					        }
					});
					// Resize charts
					$(window).on('xenon.resize', function()
					{
						/* $("#pageviews-visitors-chart").data("dxChart").render();
						$("#server-uptime-chart").data("dxChart").render();
						$("#realtime-network-stats").data("dxChart").render();
						
						$('.first-month').data("dxSparkline").render();
						$('.second-month').data("dxSparkline").render();
						$('.third-month').data("dxSparkline").render(); */
					});
					//改变加载项目经理
					$('#respDeptName').change(function(){ 
				        var dept = $(this).children('option:selected').val(); 
				        var trObj ="";
				        $.ajax({
							url : '${pageContext.request.contextPath}/items/getMember',
							type:'post',  
							data: ({
								dept:dept
							}),
							success : function(data) { 
								trObj="<option value='0'>请选择项目经理</option>";
								$(data).each(function(i,item){
									 trObj +="<option value='"+item.account+"'>"+item.name+"</option>"; 
									 
								});
								$("#pmAccount").html(trObj);
								
							}, 
							error:function(error){ 
								 alert(error.responseText);
							}
					});
				    });
					
				});
				
			
				
			</script> -->
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">用户信息</h1>
					<p class="description">个人信息显示</p>
				</div>

				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
						<li><a href="${pageContext.request.contextPath}/partakeitems/list"><i class="fa-home"></i>首页</a>
						</li>
						<li class="active"><strong>用户信息</strong></li>
						<li class="active"><strong>个人信息管理</strong></li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
			<form id="saveItemsForm" action="${pageContext.request.contextPath}/edit/save" method="post">
							<div class="panel-heading">
							<h3 class="panel-title">查看个人信息</h3>
						    </div>
							
							<div class="row col-margin " style="margin-top:20px">
							<div class="col-xs-12">
								<table>
									<tr>
									<td><div style="width: 148px" >用户id：</div></td>
									<td>
									<input type="hidden" name="userId" id="userId" value="${user.userId}"></input>
									<span id="userId">${user.userId}</span>
									</tr>
								</table>	
								</div>
								<div class="col-xs-12">
								<table>
									<tr>
									<td ><div style="width: 130px" >用户姓名：</div></td>
									<td class="col-xs-10"><span>${user.name}</span></td>
									</tr>
								</table>	
								</div>
									
								<div class="col-xs-12">
								<table>
									<tr>
									<td ><div style="width: 130px">密码：</div></td>
									<td class="col-xs-10"><span >${user.password}</span></td>
									</tr>
								</table>	
								</div>
								
								<div class="col-xs-12">
								<table>
									<tr>
									<td ><div style="width: 130px">性别：</div></td>
									<td class="col-xs-10">
									<label><input name="sex" type="radio" value="男"  disabled <c:if test="${'男' eq user.sex}">checked</c:if> />男</label> 
                                    <label><input name="sex" type="radio" value="女"  disabled <c:if test="${'女' eq user.sex}">checked</c:if> />女 </label> 
                                    </td>
									</tr>
								</table>	
								</div>
								<div class="col-xs-12">
								<table>
									<tr>
									<td class="col-xs-10"><a href="javascript:void(0)" class="btn btn-secondary btn-sm btn-icon icon-left" onclick="edit(${user.userId})">编辑</a></td>
									</tr>
								</table>	
								</div>
							</div>
							<div>
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
function edit(id){
	 window.location.href="${pageContext.request.contextPath}/user/edit?userId=" + id;
}
</script>
</html>