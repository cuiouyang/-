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
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body class="page-body">
	<div class="page-container">
		<%@ include file="frame/left.jsp" %>
		<div class="main-content">
			<%@ include file="frame/nav.jsp" %>
			<script type="text/javascript">
	jQuery(document).ready(function($)
	{	
		// Notifications
		setTimeout(function()
		{			
			var opts = {
				"closeButton": true,
				"debug": false,
				"positionClass": "toast-top-right toast-default",
				"toastClass": "black",
				"onclick": null,
				"showDuration": "100",
				"hideDuration": "1000",
				"timeOut": "5000",
				"extendedTimeOut": "1000",
				"showEasing": "swing",
				"hideEasing": "linear",
				"showMethod": "fadeIn",
				"hideMethod": "fadeOut"
			};	
			toastr.info("欢迎登录项目管理系统!", "", opts);
		}, 3000);		
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
	});	
</script>
			<!-- Main Footer -->
			<%@ include file="frame/footer.jsp" %>
		</div>
	</div>
	<%@ include file="frame/bottomScript.jsp" %>
</body>
</html>