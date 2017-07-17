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
					<h1 class="title"></h1>
					<p class="description">试卷</p>
				</div>
			</div>
			<form id="course" name="course" 
			 action="${pageContext.request.contextPath}/papers/mark" method="post">
			 <input name='courseName' id='courseName' type='hidden' value='${testSet.courseName }' />
			 
			<!--  单选题 -->
			<c:if test="${testSet.radio > 0}">
			<div class="col-sm-12">
			
			<h5><font><font>一：单选题，总分${testSet.radioScore }分。</font></font></h5>
			<br>
			<table>
			<c:forEach items="${radioList}" var="item" varStatus="status">
               <tr><td colspan="2" rowspan="2">${status.index+1}. ${item.problemTitle}</td></tr>
               <tr></tr>
               <tr>
               <td> <input type="radio" name="${item.problemId}"value='A'/><label for="male">A.${item.keyA}</label></td>
               <td><input type="radio" name="${item.problemId}"value='B'/><label for="male">B.${item.keyB}</label></td>
               </tr>
               <tr>
               <td><input type="radio" name="${item.problemId}"value='C'/><label for="male">C.${item.keyC}</label></td>
               <td><input type="radio" name="${item.problemId}" value='D'/><label for="male">D.${item.keyD}</label></td>
               </tr>
             </c:forEach>	
			</table>
			</div>
			</c:if>
			
			<!-- 多选题 -->
			<c:if test="${testSet.choice > 0}">
			<div class="col-sm-12">
			<h5><font><font>二：多选题，总分${testSet.choiceScore }分。</font></font></h5>
			<br>
			<table>
			<c:forEach items="${choiceList}" var="item" varStatus="status">
               <tr><td colspan="2" rowspan="2">${status.index+1}. ${item.problemTitle}</td></tr>
               <tr></tr>
               <tr>
               <td> <input type="checkbox" name="${item.problemId}" value='A'/><label for="male">A.${item.keyA}</label></td>
               <td><input type="checkbox" name="${item.problemId}" value='B'/><label for="male">B.${item.keyB}</label></td>
               </tr>
               <tr>
               <td><input type="checkbox" name="${item.problemId}" value='C'/><label for="male">C.${item.keyC}</label></td>
               <td><input type="checkbox" name="${item.problemId}" value='D'/><label for="male">D.${item.keyD}</label></td>
               </tr>
             </c:forEach>	
			</table>
			</div>
			</c:if>
			
			<!-- 判断题 -->
			<c:if test="${testSet.judge > 0}">
			<div class="col-sm-12">
			<h5><font><font>三：判断题，共${testSet.judge}个，总分${testSet.judgeScore}分。</font></font></h5>
			<br>
			<table>
			<c:forEach items="${judgeList}" var="item" varStatus="status">
               <tr><td colspan="2" rowspan="2">${status.index+1}. ${item.problemTitle}</td>
               </tr>
               <tr></tr>
               <tr>
               <td>正确:<input type="radio" name="${item.problemId}" value='正确'/><label for="male"></label></td>
               <td>错误<input type="radio" name="${item.problemId}" value='错误'/><label for="male"></label></td>
               </tr>
             </c:forEach>	
			</table>
			</div>
			</c:if>
			
			
			<!-- 简答 -->
			<c:if test="${testSet.shortAnswer> 0}">
			<div class="col-sm-12">
			<h5><font><font>四：简答题，共${testSet.shortAnswer}个，总分${testSet.shortAnswerScore}分。</font></font></h5>
			<br>
			<table>
			<c:forEach items="${shortSubjectList}" var="item" varStatus="status">
               <tr><td colspan="2" rowspan="2">${status.index+1}. ${item.problemTitle}</td>
               </tr>
               <tr></tr>
               <tr>
               <td><textarea  id="problemTitle" name="${item.problemId}" style="width:500px;height:150px;" class="form-control" placeholder="答案"></textarea></td>
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
			</c:if>
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
			  layer.msg('提交成功，系统正在批改客观题，请稍后', {icon: 1});
			  
			  $("#course").ajaxSubmit({
					url : "${pageContext.request.contextPath}/papers/mark",
					success : function(data) {
						
						layer.alert('客观题得分为'+data);
						document.getElementById("saveBtn").style.display = "none";
						/* layer.alert(, {
							  skin: 'layui-layer-molv' //样式类名
							  ,closeBtn: 0
							  ,anim: 4 //动画类型
							}, function(){
								$('#saveBtn').attr('type',"hidden");
								return ;
							}); */
						
					},
					failure : function(data) {
						alertDialog("加载页面出错！");
					},
					error:function(){
						alertDialog("登录超时，请刷新并重新登录后进行查找");
					}
			});
			 // $('#course').submit();
			}, function(){
			});
		
		
		
}

</script>

</html>