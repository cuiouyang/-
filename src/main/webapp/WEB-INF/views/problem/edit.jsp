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
}
</style> -->
</head>
<body class="page-body">
	<%@ include file="../frame/setting.jsp" %>
	<div class="page-container">
		<%@ include file="../frame/left.jsp" %>
		<div class="main-content">
			<%@ include file="../frame/nav.jsp" %>
			
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">编辑试题</h1>
					<p class="description">编辑试题信息</p>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
						<%-- <li><a href="${pageContext.request.contextPath}/partakeitems/list"><i class="fa-home"></i>首页</a>
						</li>
 --%>						<li class="active"><strong>考试信息管理</strong></li>
						<li class="active"><strong>编辑试题</strong></li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<form id="saveItemsForm" action="${pageContext.request.contextPath}/edit/updata" method="post">
					<div class="panel-heading">
					<h3 class="panel-title">编辑试题信息</h3>
				    </div>
					<div class="row col-margin " id=" Information" style="margin-top:20px">
					<div class="col-xs-12">
						<table>
							<tr>
							<td><div style="width: 130px" >试题ID：</div></td>
							<td class="col-xs-10">
						    <input type="text"  readonly="readonly" id="problemId" name="problemId" class="form-control" placeholder="problemId"  value="${record.problemId}"/></td>
							</tr>
						</table>
						</div>
						<div class="col-xs-12">
						<table>
							<tr>
							<td ><div style="width: 130px" >试题科目：</div></td>
							<td class="col-xs-10"><select class="form-control" id="courseName" name="courseName">
					<option value="">请选择科目</option>
					<c:forEach items="${course}" var="item" varStatus="status">
					<option value='${item.courseName}' <c:if test='${item.courseName eq record.courseName}'>selected</c:if>><c:out value='${item.courseName}'/></option>
					</c:forEach>
					</select></td>
							</tr>
						</table>
						</div>
						<div class="col-xs-12">
						<table>
							<tr>
							<td ><div style="width: 130px">试题类型：</div></td>
					<td class="col-xs-10"><select class="form-control" id="problemType"  name="problemType">
					<option value="" <c:if test="${record.problemType eq ''}">selected</c:if>>请选择试题类型</option>
					<option value="1"<c:if test="${record.problemType eq '1'}">selected</c:if>>单选题</option>
					<option value="2"<c:if test="${record.problemType eq '2'}">selected</c:if>>多选题</option>
					<option value="3"<c:if test="${record.problemType eq '3'}">selected</c:if>>判断题</option>
					<option value="4"<c:if test="${record.problemType eq '4'}">selected</c:if>>简答题</option>
					</select></td>
							</tr>
						</table>
						</div>
						
						<div class="col-xs-12">
						<table>
							<tr>
							<td><div style="width: 130px" >试题内容：</div></td>
							<td class="col-xs-10">
						    <textarea id="problemTitle" name="problemTitle" style="width:300px;height:100px;" class="form-control" placeholder="试题内容" >${record.problemTitle}</textarea></td>
							</tr>
						</table>
						</div>
						<div class="col-xs-12">
						<table>
							<tr>
							<td><div style="width: 130px" >选项A：</div></td>
							<td class="col-xs-10">
						    <input type="text" id="keyA" name="keyA" class="form-control" placeholder="选项A"  value="${record.keyA}"/></td>
							</tr>
						</table>
						</div>
						<div class="col-xs-12">
						<table>
							<tr>
							<td><div style="width: 130px" >选项B：</div></td>
							<td class="col-xs-10">
						    <input type="text" id="keyB" name="keyB" class="form-control" placeholder="选项B"  value="${record.keyB}"/></td>
							</tr>
						</table>
						</div>
						<div class="col-xs-12">
						<table>
							<tr>
							<td><div style="width: 130px" >选项C：</div></td>
							<td class="col-xs-10">
						    <input type="text" id="keyC" name="keyC" class="form-control" placeholder="选项C"  value="${record.keyC}"/></td>
							</tr>
						</table>
						</div>
						<div class="col-xs-12">
						<table>
							<tr>
							<td><div style="width: 130px" >选项D：</div></td>
							<td class="col-xs-10">
						    <input type="text" id="keyD" name="keyD" class="form-control" placeholder="选项D"  value="${record.keyD}" /></td>
							</tr>
						</table>
						</div>
						<div class="col-xs-12">
						<table>
							<tr>
							<td><div style="width: 130px" >难度分：</div></td>
							<td class="col-xs-10">
						    <select class="form-control" id="difficulty" name="difficulty">
					<option value="1" <c:if test="${record.difficulty eq '1'}">selected</c:if>>1</option>
					<option value="2" <c:if test="${record.difficulty eq '2'}">selected</c:if>>2</option>
					<option value="3" <c:if test="${record.difficulty eq '3'}">selected</c:if>>3</option>
					</select>
						    </td>
							</tr>
						</table>
						</div>
						<div class="col-xs-12">
						<table>
							<tr>
							<td><div style="width: 130px" >参考答案：</div></td>
							<td class="col-xs-10">
							<textarea  id="solution" name="solution" style="width:300px;height:50px;" class="form-control" placeholder="参考答案">${record.solution}</textarea>
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
						<span class="btn"><a href="javascript:void(0)"onclick="window.location.href=document.referrer; return false" class="btn btn-red">取&nbsp;消</a></span>
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
	 if($('#courseName').val()==""){
		 layer.tips('所属科目不能为空！', '#courseName', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
	 return false;
	 }
	 
	 if($('#problemtype').val()==""){
		 layer.tips('考题类型不能为空！', '#problemtype', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 return false;
	 }
	 if($('#problemTitle').val()==''){
		 layer.tips('试题内容不能为空！', '#problemTitle', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#problemType').val()==1 || $('#problemType').val()==1 ){
		 if($('#keyA').val()=='' || $('#keyB').val()==''|| $('#keyC').val()==''|| $('#keyD').val()=='' ){
			 layer.tips('s试题类型为选择题时，四个选项不能为空！', '#problemType', {
				  tips: [1, '#3595CC'],
				  time: 4000
			});
			 
			 return false;
		 }
		}
		if($('#solution').val()==''){
			 layer.tips('试题内容不能为空！', '#solution', {
				  tips: [1, '#3595CC'],
				  time: 4000
			});
			 
			 return false;
		 }
		return true;
	 }

function save(){
	
	if(!validate()){
		return false ;	
	}
	
   $('#saveItemsForm').submit();
			
}

</script>
</html>