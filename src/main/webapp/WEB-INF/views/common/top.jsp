<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>

	
</style>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" style="background-color: wheat ">
    <div class="container">
      <a class="navbar-brand" href="http://localhost:8080/#" ><img src="/resources/skin/drx.png" ></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive" >
        <ul class="navbar-nav ml-auto" style="color:black;">
          
          <c:if test="${sessionScope.user == null}">
					<li class="nav-item">
						 <a class="nav-link" href="/member/join" style="color:black">회원가입</a>
					</li>
					
					<li class="nav-item">
						<a class = "nav-link" href="/member/login" style="color:black">로그인</a>
					</li>
				</c:if>
          
          <c:if test="${sessionScope.user != null}">
          <li class="dropdown user user-menu"> 
						<p class="hidden-xs" style="color:black; margin-top:14px;">
							최근 접속 시간: 
							<fmt:formatDate value="${sessionScope.user.mem_date_last}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</p>
					</li>
					<li class="dropdown user user-menu"> 
						<p class="hidden-xs" style=" margin-top:14px; margin-left:15px; margin-right:15px;">
							${sessionScope.user.mem_name}님
						</p>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/logout" style="color:black">로그아웃
							
						</a>
					</li>
						<li class="dropdown user user-menu" id="modify">
							<a class="nav-link" href="#"style="color:black">회원정보 관리

							</a>
						<ul class="dropdown-menu" id= "modify_dropdown">
							<li><a href="/member/checkPw?url=modify"style="color:black">회원정보 수정</a></li>
							<li><a href="/member/checkPw?url=changePw"style="color:black">비밀번호 변경</a></li>
							<li><a href="/member/checkPw?url=delete"style="color:black">회원 탈퇴</a></li>
						</ul>
					</li>	
					<li class="nav-item">
						<a class="nav-link"href="/cart/list"style="color:black"> 
							장바구니
						</a>
					</li>		
					<li class="nav-item">
						<a class="nav-link"href="/order/list"style="color:black">
							주문조회
						</a>
					</li>		
          
          </c:if>
        </ul>
      </div>
    </div>
  </nav>