<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="main-header">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

			<!-- Logo -->
			<a href="/admin/main" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels --> <span class="logo-mini">
					<b>A</b>LT
				</span> <!-- logo for regular state and mobile devices --> <span class="logo-lg">
					<b>Admin</b>LTE
				</span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
					<c:if test="${sessionScope.admin == null}">
						<li class="dropdown user user-menu">
						<p class="hideen-xs" style="color:white; margin-top:14px; margin-left:15px; margin-right:15px;">
							로그인해주세요.
						</p>
						</li>
						<li class="dropdown user user-menu">
							<a href="/admin/main">
								<span class="hideen-xs">로그인</span>
							</a>
						</li>	
					</c:if>
					<c:if test="${sessionScope.admin != null}">
						<li class="dropdown user user-menu">
						<p class="hidden-xs" style="color:whtie ; margin-top:14px;">
							최근접속시간:
						<fmt:formatDate value="${sessionScope.admin.admin_date_late }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</p>
						</li>
					<li class="dropdown user user-menu">
						<a href="/admin/logout" onclick="return confirm('로그아웃하시겠습니까?');">
							<span class="hidden-xs">로그아웃</span>
						</a>
						</li>
					</c:if>
					
					
						<!-- /.messages-menu -->

						<!-- Control Sidebar Toggle Button -->
						<li>
							<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
						</li>
					</ul>
				</div>
			</nav>
		</header>