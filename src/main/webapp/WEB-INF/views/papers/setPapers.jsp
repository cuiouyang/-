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
					<p class="description">设置试卷信息</p>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
						<%-- <li><a href="${pageContext.request.contextPath}/partakeitems/list"><i class="fa-home"></i>首页</a>
						</li>
 --%>						<li class="active"><strong>试卷信息管理</strong></li>
						<li class="active"><strong>设置试卷信息</strong></li>
					</ol>
				</div>
			</div>
			<form id="saveItemsForm" action="${pageContext.request.contextPath}/papers/setSave" method="post">
            <div class="row">
				  <div class="col-md-6">
				  <table>
				 <tr>
				 <td><a>所属科目：</a></td>
				 <td class="col-xs-10"><select class="form-control" id="courseName" name="courseName">
				<option value="">请选择科目</option>
				<c:forEach items="${course}" var="item" varStatus="status">
				<option value="${item.courseName}"><c:out value='${item.courseName}'/></option>
				</c:forEach>
				</select></td>
				 </tr>
				 </table>
				  
				
				 </div>
				 <div class='col-md-6'>
				 <table>
				 <tr>
				 <td><a>考试时长：</a></td>
				 <td class="col-xs-10"><input onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}"  type="text" class="form-control" id="totalTime" name="totalTime" placeholder="分钟" ></input></td>
				 </tr>
				 </table>
				 </div>
			</div>
			<div class="row">
				  <div class="col-md-4">
				  <a>单选题题数：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="radio" name="radio" placeholder="" ></input>
				 </div>
				 <div class='col-md-4'>
				 <a>单选总分：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="radioScore" name="radioScore" placeholder="" ></input>
				 </div>
				 <div class='col-md-4'>
				 <a>单选难度总分：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="radioDifficultyScore" name="radioDifficultyScore"></input>
				 </div>
			</div>
			<div class="row">
				  <div class="col-md-4">
				  <a>多选题题数：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="choice" name="choice" placeholder="" ></input>
				 </div>
				 <div class='col-md-4'>
				 <a>多选总分：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="choiceScore" name="choiceScore" placeholder="" ></input>
				 </div>
				 <div class='col-md-4'>
				 <a>多选难度总分：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="choiceDifficultyScore" name="choiceDifficultyScore"></input>
				 </div>
			</div>
			<div class="row">
				  <div class="col-md-4">
				  <a>判断题题数：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="judge" name="judge" placeholder="" ></input>
				 </div>
				 <div class='col-md-4'>
				 <a>判断题总分：</a>
				 <input type="text"  onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="judgeScore" name="judgeScore" placeholder="" ></input>
				 </div>
				 <div class='col-md-4'>
				 <a>判断题难度总分：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="judgeDifficultyScore" name="judgeDifficultyScore"></input>
				 </div>
			</div>
			<div class="row">
				  <div class="col-md-4">
				  <a>简答题题数：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="shortAnswer" name="shortAnswer" placeholder="" ></input>
				 </div>
				 <div class='col-md-4'>
				 <a>简答题总分：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="shortAnswerScore" name="shortAnswerScore" placeholder="" ></input>
				 </div>
				 <div class='col-md-4'>
				 <a>简答题难度总分：</a>
				 <input type="text" onkeyup="if(/\D/.test(this.value)){alert('只能输入整数');this.value='';}" class="form-control" id="answerDifficultyScore" name="answerDifficultyScore"></input>
				 </div>
			</div>
			<div class="row">
				  <div class="col-md-4"></div>
				  <div class="col-md-4">
				  <span class="btn"><a href="javascript:void(0)" class="btn btn-purple" onclick="save()"id="saveBtn">保&nbsp;存</a></span>
				  <span class="btn"><a href="javascript:void(0)"onclick="window.location.href=document.referrer; return false" class="btn btn-red">取&nbsp;消</a></span>
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
function validate(){
	 if($('#courseName').val()==""){
		 layer.tips('所属科目不能为空！', '#courseName', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
	 return false;
	 }
	 
	 if($('#totalTime').val()==""){
		 layer.tips('考题时长不能为空！', '#totalTime', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 return false;
	 }
	 if($('#radio').val()==''){
		 layer.tips('单选题题数不能为空，若无单选题，则填0！', '#radio', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#radioScore').val()==''){
		 layer.tips('单选题总分不能为空，若无单选题，则填0！', '#radioScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#radioDifficultyScore').val()==''){
		 layer.tips('单选题难度总分不能为空，若无单选题，则填0！', '#radioDifficultyScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#choice').val()==''){
		 layer.tips('多选题题数不能为空，若无多选题，则填0！', '#choice', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#choiceScore').val()==''){
		 layer.tips('多选题总分不能为空，若无多选题，则填0！', '#choiceScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#choiceDifficultyScore').val()==''){
		 layer.tips('多选题难度总分不能为空，若无多选题，则填0！', '#choiceDifficultyScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#judge').val()==''){
		 layer.tips('判断题题数不能为空，若无判断题题，则填0！', '#judge', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#judgeScore').val()==''){
		 layer.tips('判断题总分不能为空，若无判断题，则填0！', '#judgeScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#judgeDifficultyScore').val()==''){
		 layer.tips('判断题难度总分不能为空，若无判断题，则填0！', '#judgeDifficultyScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#shortAnswer').val()==''){
		 layer.tips('简答题题数不能为空，若无简答题，则填0！', '#shortAnswer', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#shortAnswerScore').val()==''){
		 layer.tips('简答题总分不能为空，若无简答题，则填0！', '#shortAnswerScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }
	 if($('#answerDifficultyScore').val()==''){
		 layer.tips('简答题难度总分不能为空，若无简答题，则填0！', '#answerDifficultyScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 
		 return false;
	 }else{
		 return true; 
	 }
}

function validates(){
	var radio=parseInt($('#radio').val());
	var choice=parseInt($('#choice').val());
	var judge=parseInt($('#judge').val());
	var shortAnswer=parseInt($('#shortAnswer').val());
	var radioDifficulty=parseInt($('#radioDifficultyScore').val());
	var choiceDifficulty=parseInt($('#choiceDifficultyScore').val());
	var judgeDifficulty=parseInt($('#judgeDifficultyScore').val());
	var shortAnswerDifficulty=parseInt($('#answerDifficultyScore').val());
    if(radioDifficulty<radio || radioDifficulty>radio*3 ){
		 layer.tips('单选题难度总分设置不合理，每题难度分在【1,3】之间', '#radioDifficultyScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 return false;
	 }
    if(choiceDifficulty<choice || choiceDifficulty>choice*3 ){
		 layer.tips('多选题难度总分设置不合理，每题难度分在【1,3】之间', '#choiceDifficultyScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 return false;
	 }
    if(judgeDifficulty<judge || judgeDifficulty>judge*3 ){
		 layer.tips('判断题难度总分设置不合理，每题难度分在【1,3】之间', '#judgeDifficultyScore', {
			  tips: [1, '#3595CC'],
			  time: 4000
		});
		 return false;
	 }
    if(shortAnswerDifficulty<shortAnswer || shortAnswerDifficulty>shortAnswer*3 ){
		 layer.tips('简答题难度总分设置不合理，每题难度分在【1,3】之间', '#answerDifficultyScore', {
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
	//验证难度分设置
	if(!validates()){
		return;
	}
	var radioScore =parseInt($('#radioScore').val());
	var choiceScore=parseInt($('#choiceScore').val());
	var judgeScore=parseInt($('#judgeScore').val());
	var shortAnswerScore=parseInt($('#shortAnswerScore').val());
	var sum=radioScore + choiceScore + judgeScore + shortAnswerScore; 
	if(sum!=100 && sum!=150){
		layer.confirm('卷面总分不是100或150！确认要这么设置吗？', {
			  btn: ['确定','重新设置'] //按钮
			}, function(){
			  layer.msg('设置成功，正在保存设置', {icon: 1});
			  $('#saveItemsForm').submit();
			}, function(){
			});
	}else{
		  $('#saveItemsForm').submit();
	}
	
	
	
	
	/* $.ajax({
			url: '${pageContext.request.contextPath}/items/saveValidate',
			type:'post',
			data: ({
				courseName:$('#projectName').val(),
				totalTime:$('#contractNo').val(),
				courseName:$('#projectName').val(),
				totalTime:$('#contractNo').val(),
				courseName:$('#projectName').val(),
				totalTime:$('#contractNo').val(),
				courseName:$('#projectName').val(),
				totalTime:$('#contractNo').val(),
				totalTime:$('#contractNo').val(),
				courseName:$('#projectName').val(),
				totalTime:$('#contractNo').val() 
			}),
			success:function(data){
                if(data=='01'){
                	layer.open({
          			  title: '操作提醒'
          			  ,content: '保存失败！ 警告：项目名称不允许重复！'
          			});
                	return false;
				}else if(data=='02'){
					layer.open({
	          			  title: '操作提醒'
	          			  ,content: '保存失败！ 警告：合同编号不允许重复！'
	          			});
					return false;
				}else{
                $('#saveItemsForm').submit();
				}
			},
			error:function(){
				layer.open({
        			  title: '操作提醒'
        			  ,content: '登录超时，请刷新并重新登录后进行此操作！'
        			});
			}
	}); */
}
</script>
</html>