<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="page-loading-overlay">
	<div class="loader-2"></div>
</div>
<!-- Imported styles on this page -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.css">
<!-- Bottom Scripts -->
<script src="${pageContext.request.contextPath}/assets/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/timepicker/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/TweenMax.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/resizeable.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/joinable.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/xenon-api.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/xenon-toggles.js"></script>
<!-- Imported scripts on this page -->
<script	src="${pageContext.request.contextPath}/assets/js/datatables/dataTables.bootstrap.js"></script>
<script	src="${pageContext.request.contextPath}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
<script	src="${pageContext.request.contextPath}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/xenon-widgets.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/devexpress-web-14.1/js/globalize.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/toastr/toastr.min.js"></script>
<!-- 弹出框组件 -->
<script src="${pageContext.request.contextPath}/assets/js/layer/layer.js"></script>

<!-- 项目人员的多选框引入js -->
<script src="${pageContext.request.contextPath}/assets/js/select2/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery-ui/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/selectboxit/jquery.selectBoxIt.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/tagsinput/bootstrap-tagsinput.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/typeahead.bundle.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/handlebars.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/multiselect/js/jquery.multi-select.js"></script>
<!-- JavaScripts initializations and stuff -->
<script src="${pageContext.request.contextPath}/assets/js/xenon-custom.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap-paginator.js"></script>
<script>
	$(document).ready(function(){
		var currentMenu = "${currentMenu}";
		var currentUrl = "${currentURL}";
		$("#main-menu ul").each(function(i,obj){
			if(i == currentMenu){
				$(obj).parent("li").addClass("expanded");
				$(obj).show();
			}else{
				$(obj).parent("li").removeClass("expanded");
				$(obj).hide();
			}
		});
		
		$("#main-menu ul>li").each(function(i,obj){
			var href = $(obj).find("a").prop("href");
			if(href.indexOf(currentUrl) != -1){
				$(obj).addClass("active");
			}else{
				$(obj).removeClass("active");
			}
		});
	});

</script>