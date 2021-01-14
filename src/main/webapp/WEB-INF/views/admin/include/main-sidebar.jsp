<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside class="main-sidebar">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
			<c:if test="${sessionScope.admin != null}">
				<div class="user-panel">
					<div class="pull-left image">
						<img src="			" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>${sessionScope.admin.admin_name}</p>
						<!-- Status -->
						<a href="#"><i class="fa fa-circle text-success"></i> 접속됨</a>
					</div>
				</div>
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button type="submit" name="search" id="search-btn" class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
					<ul class="sidebar-menu" data-widget="tree">
					<li class="header">HEADER</li>
					<!-- Optionally, you can add icons to the links -->
					<li class="treeview">
						<a href="#"><i class="fa fa-link"></i> <span>상품관리</span>
						<span class="pull-right-container">
								<i class="fa fa-angle-left pull-right"></i>
							</span> </a>
					<ul class="treeview-menu">
						<li>
							<a href="/admin/product/insert">상품 등록</a>
						</li>
						<li>
							<a href="/admin/product/list">상품 목록</a>
					</ul>
					</li>
					<li class="treeview">
				<a href="#"><i class="fa fa-link"></i> <span>주문관리</span> <span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
					</span> </a>
				<ul class="treeview-menu">
					<li>
						<a href="/admin/product/orderList"><i class="fa fa-bookmark"></i>주문목록</a>
					</li>
					<li>
						<a href="/admin/order/orderStat"><i class="fa fa-bookmark"></i>주문통계</a>
					</li>
				</ul>
			</li>
					<li class="treeview">
						<a href="#"><i class="fa fa-link"></i> <span>회원관리</span> <span class="pull-right-container">
								<i class="fa fa-angle-left pull-right"></i>
							</span> </a>
						<ul class="treeview-menu">
							<li>
								<a href="#">Link in level 2</a>
							</li>
							<li>
								<a href="#">Link in level 2</a>
							</li>
						</ul>
					</li>
				</ul>
			</c:if>
			<c:if test="${sessionScope.admin == null}">
				<li class="dropdown user user-menu">
						<p class="hideen-xs" style="color:white; margin-top:14px; margin-left:15px; margin-right:15px;">
							관리자 페이지 입니다
							
						</p>
						<a href="/admin/main" style="margin-left:30px; color:white;">바로가기</a>
						</li>
			
			
			</c:if>

				<!-- search form (Optional) -->
				
				<!-- /.search form -->

				<!-- Sidebar Menu -->
			
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>