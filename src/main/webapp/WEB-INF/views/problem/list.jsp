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
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/linecons/css/linecons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-core.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-forms.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-components.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/xenon-skins.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery-form.js"></script>
<script type="text/javascript" src ="${pageContext.request.contextPath}/assets/js/jquery.raty.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<style type="text/css">
td{
overflow:hidden;white-space:nowrap;text-overflow:ellipsis
}	
}
</style>
</head>
<body class="page-body">
	<%@ include file="../frame/setting.jsp" %>
	<div class="page-container">
		<%@ include file="../frame/left.jsp" %>
		<div class="main-content">
			<%@ include file="../frame/nav.jsp" %>
		<div class="page-title">
				<div class="title-env">
					<h1 class="title">试题管理</h1>
					<p class="description">对所有试题进行管理</p>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
						<%-- <li><a href="${pageContext.request.contextPath}/partakeitems/list"><i class="fa-home"></i>首页</a>
						</li> --%>
						<li>
							<a href="#">考试管理</a>
						</li>
						<li class="active"><strong>试题管理</strong></li>
					</ol>
				</div>
			</div>
					<div class="panel panel-default" width="100%">
					
				     <div class="panel-body">	
				  <div class="row" style="  margin-top: 10px">
				  <div class="col-md-3">
				<input type="text" class="col-sm-3 form-control "placeholder="请输入试题内容（模糊）" id="problemTitle" name="problemTitle" />
				  </div>
				  <div class="col-md-3">
					<select class="form-control" id="courseName">
					<option value="">请选择科目</option>
					<c:forEach items="${course}" var="item" varStatus="status">
					<option value="${item.courseName}"><c:out value='${item.courseName}'/></option>
					</c:forEach>
					</select>
				  </div>
				  <div class="col-md-3">
					<select class="form-control" id="problemType">
					<option value="">请选择试题类型</option>
					<option value="1">单选题</option>
					<option value="2">多选题</option>
					<option value="3">判断题</option>
					<option value="4">简答题</option>
					</select>
				  
				  </div>
				  
				  <div class="col-md-3">
				  <a href="javascript:void(0)" class="linecons-search btn btn-info" onclick="queryWork()" id="saveBtn">查询</a>
				  <a href="${pageContext.request.contextPath}/questions/export" class="fa-download btn btn btn-primary " id="download">导出</a> 
				  <a href="${pageContext.request.contextPath}/questions/add" class=" btn btn btn-primary red" id="add">新增</a> 
				  <form class="input-group"
									action="${pageContext.request.contextPath}/questions/import"
									method="post" enctype="multipart/form-data"
									name="uploadMilestoneForm" id="uploadMilestoneForm">
									<div class="input-group-addon">
										<i class="fa-cloud"></i>
									</div>
									<input name="fileType" type="hidden" value="" /> <span
										id="scan" style="position: relative"
										class="input-group-addon   btn-primary "> 导入试题 <input
										name="uploadMilestone" id="uploadMilestone" value=""
										onchange="uploadMilestoneSub('uploadMilestoneForm')"
										style="width: 60px; height: 40px; position: absolute; top: -6px; left: -4px; opacity: 0; filter: alpha(opacity = 0)"
										type="file">
									</span>

								</form>
				  </div>
				  
				  </div>
						<div id="staffTableDiv" >
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr height="2">
									<td class="tbtitlebg"></td>
								</tr>
								<tr>
									<td>
										<div class="panel-body" >
						 					<div style="width:100%">
											<table  class="table table-bordered table-striped  " style="table-layout:fixed"
												id="table">
												<thead>
													<tr>
														<th width="10%">试题ID</th>
														<th width="10%">试题类型</th>
														<th width="10%">所属科目</th>
														<th class="text-center" width="15%">试题内容</th>
														<th width="10%">选项A</th>
														<th width="10%">选项B</th>
														<th width="10%">选项C</th>
														<th width="10%">选项D</th>
														<th  width="10%" class="text-center">参考答案</th>
														<th width="15%" class="text-center">操作</th>
													</tr>
												</thead>

												<tbody id="tbody">
 													<c:forEach items="${list}" var="item"
														varStatus="status">
														<tr>
														<td align="center" title="${item.problemId}"><c:out
																	value='${item.problemId}' />&nbsp;</td>
														<td align="center" title="${item.problemType}">
														<c:if test="${item.problemType eq 1}"><c:out value='单选题'/></c:if>
														<c:if test="${item.problemType eq 2}"><c:out value='多选题' /></c:if>
														<c:if test="${item.problemType eq 3}"><c:out value='判断题' /></c:if>
														<c:if test="${item.problemType eq 4}"><c:out value='简答题' /></c:if>
														<c:if test="${item.problemType eq null}"><c:out value='' /></c:if>
														&nbsp;</td>
														<td align="center" title="${item.courseName}"><c:out
																	value='${item.courseName}' />&nbsp;</td>
														<td align="center" title="${item.problemTitle}"><c:out
																value='${item.problemTitle}' />&nbsp;</td>
														<td align="center" title="${item.keyA}"><c:out
																value='${item.keyA}' />&nbsp;</td>
														<td align="center" title="${item.keyB}"><c:out
																value='${item.keyB}' />&nbsp;</td>
														<td align="center" title="${item.keyC}">
														<c:out value='${item.keyC}' />&nbsp;</td>
														<td align="center" title="${item.keyD}"><c:out
																value='${item.keyD}' />&nbsp;</td>
														<td align="center" title="${item.solution}"><c:out
																	value='${item.solution}' />&nbsp;</td>
														<td align="center">
														 <a href="javascript:void(0)" class="btn btn-secondary btn-sm btn-icon icon-left" onclick="edit(${item.problemId})">编辑</a> 
									                     <a href="javascript:void(0)" class="btn btn-danger btn-sm btn-icon icon-left" onclick="del(${item.problemId})"> 删除 </a>
								                         </td>
														</tr>
													</c:forEach> 
												</tbody>
											</table>
                                           </div>
										</div>
									</td>
								</tr>
							</table>
							
						</div>	
						</div>	
						</div>	
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
				
				
				//根据三个条件联合查找工作项
				function queryWork(){
				var problemType=$('#problemType').val();
				var courseName=$('#courseName').val();
				var problemTitle=$('#problemTitle').val();
				$.ajax({
							url : '${pageContext.request.contextPath}/questions/query',
							type:'post',
							data: ({
								problemType:problemType,
								courseName:courseName,
								problemTitle:problemTitle
							}),
							success : function(result) { 
								debugger;
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
					                     $.post("${pageContext.request.contextPath}/questions/query",{
					                    	 problemType:problemType,
												courseName:courseName,
												problemTitle:problemTitle,
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
									  ,content: '查询失败，请重新选择查询！'
									});
								
							},
							error:function(){
								 layer.open({
									  title: '操作提醒'
									  ,content: 'error.responseText'
									});
								
							} 
					});
				}
				
				//异步请求后拼接html显示
				function statement(data){
					$("#tbody").empty();
					var datas="";
					$(data).each(function(i,item){
						datas+="<tr>"; 
						if(item.problemId !== null && item.problemId !== undefined && item.problemId !== ''){
							datas+="<td align='center' title='"+item.problemId+"'>"+item.problemId+"</td>";
						}else{datas+="<td align='center' title=''></td>";
						}
						var types="";
						if(item.problemType==1){
							types="单选题";
						}
						if(item.problemType==2){
							types="多选题";
						}
						if(item.problemType==3){
							types="判断题";
						}
						if(item.problemType==4){
							types="简答题";
						}
						
						
						datas+="<td align='center' title='"+item.problemType+"'> "+types+"</td>";
						if(item.courseName !== null && item.courseName !== undefined && item.courseName !== ''){
							datas+="<td align='center' title='"+item.courseName+"'>"+item.courseName+"</td>";
						}else{datas+="<td align='center' title=''></td>";
						}
						if(item.problemTitle !== null && item.problemTitle !== undefined && item.problemTitle !== ''){
							datas+="<td align='center' title='"+item.problemTitle+"'>"+item.problemTitle+"</td>";
						}else{datas+="<td align='center' title=''></td>";
						}
						if(item.keyA !== null && item.keyA !== undefined && item.keyA !== ''){
							datas+="<td align='center' title='"+item.keyA+"'>"+item.keyA+"</td>";
						}else{datas+="<td align='center' title=''></td>";
						}
						if(item.keyB !== null && item.keyB !== undefined && item.keyB !== ''){
							datas+="<td align='center' title='"+item.keyB+"'>"+item.keyB+"</td>";
						}else{datas+="<td align='center' title=''></td>";
						}
						if(item.keyC !== null && item.keyC !== undefined && item.keyC !== ''){
							datas+="<td align='center' title='"+item.keyC+"'>"+item.keyC+"</td>";
						}else{datas+="<td align='center' title=''></td>";
						}
						if(item.keyD !== null && item.keyD !== undefined && item.keyD !== ''){
							datas+="<td align='center' title='"+item.keyD+"'>"+item.keyD+"</td>";
						}else{datas+="<td align='center' title=''></td>";
						}
						if(item.solution !== null && item.solution !== undefined && item.solution !== ''){
							datas+="<td align='center' title='"+item.solution+"'>"+item.solution+"</td>";
						}else{datas+="<td align='center' title=''></td>";
						}
						
						
						datas+="<td align='center'><a href='javascript:void(0)' class='btn btn-secondary btn-sm btn-icon icon-left' onclick='edit("+item.problemId+")'>编辑</a> ";
						datas+="<a href='javascript:void(0)' class='btn btn-danger btn-sm btn-icon icon-left' onclick='del("+item.problemId+")'> 删除 </a></td></tr>";
					})
					$("#tbody").html(datas);
			 
				 	
				} 
		function edit(id){
			 window.location.href="${pageContext.request.contextPath}/questions/edit?problemId="+id;
		 }
		function del(id){
			 layer.confirm('数据将永久删除，你确定要删除吗？', {
				  btn: ['删除','取消'] //按钮
				}, function(){ 
				    $.ajax({
						url : '${pageContext.request.contextPath}/questions/del',
						type: "POST",
		                data: "problemId="+id,
						success : function(result) {
							if(result=="1") {
								 layer.msg("删除成功！", {icon: 1}); 
								window.location.reload();
							} else  {
								var msg="操作错误，请重新操作！";
								 layer.msg(msg, {icon: 1}); 
							}
						},
						error:function(error){ 
							layer.msg(error.responseText, {icon: 1});
				        }
		            })
				}); 
		      
		}	
		
		 function uploadMilestoneSub(formId){
			 var file = document.forms[0].uploadMilestone.value;
			 var pos = file.lastIndexOf(".");
			 var ext = file.substring(pos+1).toLowerCase();
			 document.forms[0].fileType.value = ext;
			   $("#"+formId).ajaxSubmit({
			 	  success : function (data){
			 		 if(data=="导入成功"){
			 			 layer.msg('导入成功！', {icon: 1},function(){
			 				 $(".btn-unstyled").click();
			 			 });
			 		 }else{
			 			 layer.msg('导入失败！', {icon: 5});
			 		 }
			 	  }
			   }); 
			 }
				
		</script>            
			
			<!-- Main Footer -->
			<%@ include file="../frame/footer.jsp" %>
		</div>
	</div>
	<%@ include file="../frame/bottomScript.jsp" %>
</body>
</html>