<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/fonts/arimo/css/arimo.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/fonts/linecons/css/linecons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/xenon-forms.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/custom.css">
<!-- Imported styles on this page -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.css">

<script
	src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<style type="text/css">
table{  
    table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
}
table .mtd{  
    width:auto;
    word-break:keep-all;/* 不换行 */
    white-space:nowrap;/* 不换行 */
    overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
    text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
}	
table .btn + .btn{
   margin-left:2px;/* 按钮margin*/
}

</style> 
</head>
<body class="page-body">
	<%@ include file="../frame/setting.jsp"%>
	<div class="page-container">
		<%@ include file="../frame/left.jsp"%>
		<div class="main-content">
			<%@ include file="../frame/nav.jsp"%>
			  <div class="page-title">
				<div class="title-env">
					<p class="description">查看成绩</p>
				</div>
			</div>
			<!-- Basic Setup -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-body">
						<%-- <div class="form-group">
							<form class="col-md-10" id="searchForm"
								action="${pageContext.request.contextPath}/user/query"
								method="post">
								<div class="row">
								<div class="col-md-3">
								<select class="form-control" id="courseName" name="courseName">
								<option value="">请选择科目</option>
								<c:forEach items="${course}" var="item" varStatus="status">
								<option value="${item.courseName}"><c:out value='${item.courseName}'/></option>
								</c:forEach>
								</select>
							     </div>
								<div class="col-md-3">
									<input type="text" class="col-sm-3 form-control "placeholder="请输入用户ID" id="userId" name="userId" /></div>
									<div class="col-md-3">
									 <select class="col-sm-3 form-control" name ="whetherGrade"  id="whetherGrade">
								            <option value="">请选择批改状态</option>
											<option value="0">未批改</option>
											<option value="1">已批改</option>	
									 </select>
								</div>
								<div class="col-md-3">
									<a href="javascript:void(0)" id="btnSelect" onclick="queryWork()" class=" btn btn-blue btn-danger linecons-search">
									查询
									</a>
								</div>
								</div>
							</form>
						</div> --%>
					</div>
				</div>
				<div class="panel-body">
					<table id="table" class="table table-bordered table-striped"
						cellspacing="0" width="100%">
						<thead align="center">
							<tr>
								<td>ID</td>
								<td >考试科目</td>
								<td >单选题得分</td>
								<td >多选题得分</td>
								<td >判断题得分</td>
								<td >简答题得分</td>
								<td >总分</td>
								<td >状态</td>
							</tr>
						</thead>
						<tbody id="tbody">
							<c:forEach items="${list}" var="item" varStatus="status">
								<tr>
									<td align="center"><c:out value='${item.userId}' /></td>
									<td align="center"><c:out value='${item.courseName}' /></td>
									<td align="center"><c:out value='${item.radioGrade}' /></td>
									<td align="center"><c:out value='${item.selectGrade}'/></td>
									<td align="center"><c:out value='${item.judgeGrade}' /></td>
									<td align="center"><c:out value='${item.shortAnswerGrade}'/></td>
									<td align="center"><c:out value='${item.score}'/></td>
									<td align="center">
									<c:if test="${item.whetherGrade==0}"><c:out value='未批改'/></c:if>
									<c:if test="${item.whetherGrade==1}"><c:out value='已批改'/></c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<%-- <form name="hidden_form" id="Form"
								action="${pageContext.request.contextPath}/correct/paper"
								method="post">
			<input type="hidden" id="course" name="course"/>	
			<input type="hidden" id="user" name="user"/>				
			
			</form> --%>
			<!-- Main Footer -->
			<%@ include file="../frame/footer.jsp"%>
		</div>
	</div>
	<%@ include file="../frame/bottomScript.jsp"%>
	<script src="${pageContext.request.contextPath}/assets/js/datatables/js/jquery.dataTables.min.js"></script>
	<%-- <script src="${pageContext.request.contextPath}/assets/js/datatables/js/fnReloadAjax.js"></script> --%>
	<!-- Imported scripts on this page -->
	<script src="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
	<script type="text/javascript">
	var table;
 
	$(function() {
		table = $('#table').DataTable({
                    "bLengthChange" : false, //改变每页显示数据数量
					"bFilter" : false, //过滤功能
					'ordering'  :false,
					"oLanguage" : {
						"sProcessing" : "<img src='/images/datatable_loading.gif'>  努力加载数据中.",
						"sLengthMenu" : "每页显示 _MENU_ 条记录",
						"sZeroRecords" : "抱歉， 没有找到",
						"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
						"sInfoEmpty" : "没有数据",
						"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
						"sZeroRecords" : "没有检索到数据",
						"sSearch" : "模糊查询:  ",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : "前一页",
							"sNext" : "后一页",
							"sLast" : "尾页"
						}
					}, 
		});

		
	});

	/* function del(id){
		 layer.confirm('你确定要删除吗？', {
			  btn: ['删除','取消'] //按钮
			}, function(){ 
			    $.ajax({
					url : '${pageContext.request.contextPath}/user/del',
					type: "POST",
	                data: "userId="+id,
					success : function(result) {
						if(result>0) {
							 layer.msg("删除成功！", {icon: 1}); 
							window.location.reload();
						} else  {
							
							 layer.msg(msg, {icon: 1}); 
						}
					},
					error:function(error){ 
						layer.msg(error.responseText, {icon: 1});
			        }
	            })
			}, function(){
						
					}); 
	      
	} */

	/*  function edit(userId,courseName){
		$("#course").attr("value",courseName);
		$('#user').attr('value',userId);
		$('form').submit();
	 }
	 
	//根据三个条件联合查找工作项
		function queryWork(){
		var name=$('#courseName').val();
		var userId=$('#userId').val();
		var type=$('#whetherGrade').val();
		$.ajax({
					url : '${pageContext.request.contextPath}/results/query',
					type:'post',
					data: ({
						courseName:name,
					    userId:userId,
					    whetherGrade:type
					}),
					success : function(result) {  
						statement(result.data);
						var pageCount = result.pageNum;//取到pageCount的值(把返回数据转成object类型)
			            var currentPage = result.currentPage;// //得到currentPage
			            var options = {
			                 bootstrapMajorVersion: 3, //版本
			                 currentPage: currentPage, //当前页数
			                 totalPages: pageCount, //总页数
			                 itemTexts: function (type1, page, current) {
			                     switch (type1) {
			                       case "first":
			                             return page;
			                         case "prev":
			                             return '上一页';
			                         case "next":
			                             return '下一页';
			                        case "last":
			                             return page;
			                         case "page":
			                             return page;
			                     }
			                 },
			                 pageUrl: function(type1, page, current){
			                	 if (page==current) {
			                         return "javascript:void(0)";
			                     }
			                 },
			                 onPageClicked: function (event, originalEvent, type1, page) { //异步换页
			                     $.post("${pageContext.request.contextPath}/results/query",{
			                    	 courseName:name,
			 					    userId:userId,
			 					    whetherGrade:type,
									currentPage:page},function(d) {
			                    	 statement(d.data);
			                    	 start = d.totalCount==0?0:(d.currentPage -1 )*d.pagerSize+1;
			                         end = d.currentPage*d.pagerSize < d.totalCount ?d.currentPage*d.pagerSize:d.totalCount;
			                         $("#table_info").empty().append("从 "+start+" 到 "+end+" 共"+d.totalCount+" 数据");
			                     });
			                 },
			            };
			            var start = result.totalCount == 0?0:(result.currentPage -1 )*result.pagerSize+1;
			            var end = result.currentPage*result.pagerSize < result.totalCount ?result.currentPage*result.pagerSize:result.totalCount;
			            $("#table_info").empty().append("从 "+start+" 到 "+end+" 共 "+result.totalCount+"数据");
			            $('#table_paginate .pagination').bootstrapPaginator(options);	
					},
					 failure : function(data) {
						 layer.open({
							  title: '操作提醒'
							  ,content: '查询失败，请重新查询！'
							});
						
					},
					error:function(){
						 layer.open({
							  title: '操作提醒'
							  ,content: 'error.responseText'
							});
						
					} 
			});
		
		//异步请求后拼接html显示
		function statement(data){
				$("#tbody").empty();
				var datas="";
				$(data).each(function(i,item){
					datas+="<tr>"; 
					if(item.userId !== null && item.userId !== undefined && item.userId !== ''){
						datas+="<td align='center' title='"+item.userId+"'>"+item.userId+"</td>";
					}else{datas+="<td align='center' title=''></td>";
					}
					if(item.courseName !== null && item.courseName !== undefined && item.courseName !== ''){
						datas+="<td align='center' title='"+item.courseName+"'>"+item.courseName+"</td>";
					}else{datas+="<td align='center' title=''></td>";
					}
					if(item.radioGrade !== null && item.radioGrade !== undefined && item.radioGrade !== ''){
						datas+="<td align='center' title='"+item.radioGrade+"'>"+item.radioGrade+"</td>";
					}else{datas+="<td align='center' title=''></td>";
					}
					if(item.selectGrade !== null && item.selectGrade !== undefined && item.selectGrade !== ''){
						datas+="<td align='center' title='"+item.selectGrade+"'>"+item.selectGrade+"</td>";
					}else{datas+="<td align='center' title=''></td>";
					}
					if(item.judgeGrade !== null && item.judgeGrade !== undefined && item.judgeGrade !== ''){
						datas+="<td align='center' title='"+item.judgeGrade+"'>"+item.judgeGrade+"</td>";
					}else{datas+="<td align='center' title=''></td>";
					}
					if(item.shortAnswerGrade !== null && item.shortAnswerGrade !== undefined && item.shortAnswerGrade !== ''){
						datas+="<td align='center' title='"+item.shortAnswerGrade+"'>"+item.shortAnswerGrade+"</td>";
					}else{datas+="<td align='center' title=''></td>";
					}
					if(item.score!== null && item.score !== undefined && item.score !== ''){
						datas+="<td align='center' title='"+item.score+"'>"+item.score+"</td>";
					}else{datas+="<td align='center' title=''></td>";
					}
					
					var types="";
					if(item.whetherGrade==0){
						types="未批改";
					}
					if(item.whetherGrade==1){
						types="已批改";
					}
					
					
					datas+="<td align='center' title='"+item.whetherGrade+"'> "+types+"</td>";
					datas+="<td align='center'><a href='javascript:void(0)' class='btn btn-secondary btn-sm btn-icon icon-left' onclick='edit("+item.userId+","+item.courseName+")'>编辑</a></tr> ";
				})
				$("#tbody").html(datas);
			 	
			} 
		} */
	</script>
</body>
</html>