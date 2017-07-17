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
			
			
			<div class="page-title">
				<div class="title-env">
					<h5 class="title">简答题：共${testSet.shortAnswer}个，总分${testSet.shortAnswerScore}</h5>
				</div>
			</div>
			<form id="course" name="course" 
			 action="${pageContext.request.contextPath}/papers/save" method="post">
			 <input name='courseName' id='courseName' type='hidden' value='${list[0].courseName }' />
			 <input name='userId' id='userId' type='hidden' value='${list[0].userId }' />
			<!-- 简答 -->
			<div class="col-sm-12">
			<table>
			<c:forEach items="${list}" var="item" varStatus="status">
               <tr><td colspan="2" rowspan="2">${status.index+1}. ${item.problemTitle} 难度分：${item.difficulty}</td>
               </tr>
               <tr></tr>
               <tr>
               <td>学生答案：<textarea readonly="readonly"  id="problemTitle"  style="width:500px;height:150px;" class="form-control">
               ${item.userSolution}
               </textarea></td>
               <td>
                             该题得分：<input type="text" name="${item.problemId}" id="score"  onkeyup="value=value.replace(/[^\d.]/g,'')" class="col-sm-3 form-control "/>
               </td>
               </tr>
               <tr><td>参考答案：<textarea  readonly="readonly" id="problemTitle"  style="width:500px;height:150px;" class="form-control">
               ${item.solution}
               </textarea></td>
               </tr>
             </c:forEach>	
			</table>
			
			<div>
					<table>
						<tr>
						<td><div style="width: 130px" ></div></td>
						<td>
						<span class="btn"><a href="javascript:void(0)" class="btn btn-purple" onclick="save()"id="saveBtn">保&nbsp;存</a></span>
						</td>
						</tr>
					</table>
				   </div>
			</div>
			</form> 
	<!-- Main Footer -->
			<%@ include file="../frame/footer.jsp" %>
		</div>
	</div>
	<%@ include file="../frame/bottomScript.jsp" %>		
</body>	

<script type="text/javascript">

function save(){
		layer.confirm('确认要提交吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
			$('#course').submit();
			}, function(){
			});
		
		
		
}
</script>
</html>