<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="sidebar-menu toggle-others fixed">
	<div class="sidebar-menu-inner">
		<header class="logo-env">
			<!-- logo -->
			<div class="logo">
				<a href="#" class="logo-expanded">
					<img src="${pageContext.request.contextPath}/assets/images/log2.png" width="100%" alt="" />
				</a>
			</div>
			<div style="color:#F00 ; font-family: 微软雅黑  ">
				作者： &nbsp;&nbsp;  崔欧阳
				</div>
		</header>
		<ul id="main-menu" class="main-menu">
		<li>
				
				<ul>
					
						<a href="${pageContext.request.contextPath}/userInfo/list">
							<span class="title">个人信息展示</span>
						</a>
					
					<c:if test="${role eq 'admin'}">
					
						<a href="${pageContext.request.contextPath}/user/addUser">
							<span class="title">新增用户信息</span>
						</a>
					
					
						<a href="${pageContext.request.contextPath}/user/list">
							<span class="title">用户信息管理</span>
						</a>
					
					</c:if>
				
					<a href="${pageContext.request.contextPath}/subject/choice">
						<span class="title">我要考试</span>
					</a>
				
				
				<c:if test="${role eq 'teacher' || role eq 'admin'}">
						<a href="${pageContext.request.contextPath}/papers/setPapers">
							<span class="title">设置试卷</span>
						</a>
					
						<a href="${pageContext.request.contextPath}/papers/correctssss">
							<span class="title">试卷批改</span>
						</a>
					
						<a href="${pageContext.request.contextPath}/questions/manage">
							<span class="title">试题管理</span>
						</a>
					
					
					</c:if>
					
					<c:if test="${role eq 'student'}">
					<a href="${pageContext.request.contextPath}/look/query">
						<span class="title">查看成绩</span>
					</a>
					</c:if>
				
			
					
				
				</ul>
		</li>
	</div>
</div>