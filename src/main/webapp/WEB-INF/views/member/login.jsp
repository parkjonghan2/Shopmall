<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
<%@include file="/WEB-INF/views/common/plugin_js.jsp"%>
<script type="text/javascript" src="/js/member/join.js"></script>
  
  <style>
.login-form {
    width: 340px;
    margin: 50px auto;
  	font-size: 15px;
}
.login-form form {
    margin-bottom: 15px;
    background: #f7f7f7;
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    padding: 30px;
}
.login-form h2 {
    margin: 0 0 15px;
}
.form-control, .btn {
    min-height: 38px;
    border-radius: 2px;
}
.btn {        
    font-size: 15px;
    font-weight: bold;
}
</style>

</head>

<body>

  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">

        <h1 class="my-4">Shop Name</h1>
        <div class="list-group">
          <a href="#" class="list-group-item">Category 1</a>
          <a href="#" class="list-group-item">Category 2</a>
          <a href="#" class="list-group-item">Category 3</a>
        </div>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
		<!-- main-carousel begin  -->
       
		<!-- main-carousel end -->
		
		
        <div class="row">
		
		<!-- 로그인 -->
          
           <div class="col">
    
    			<section class="content container-fluid">
				<div class="container" style="width: 450px; height:620px; background-color: white; margin-top:30px;">
					<form id="loginForm" class="form-signin" action="/member/loginPost" method="post" style="padding:50px 30px;">
						<h2 class="form-signin-heading">Please sign in</h2>
						<br><br>
						<label for="inputId" class="sr-only">Id</label> 
						<input type="text" id="mem_id" name="mem_id" class="form-control" style="margin-bottom: 15px"
							placeholder="Id" required autofocus> 
						<label for="inputPassword" class="sr-only">Password</label> 
						<input type="password" id="mem_pw" name="mem_pw" class="form-control"
							placeholder="Password" required>
						<br><br>
						<div class="checkbox">
							<label> <input type="checkbox" name="useCookie" />
								Remember me
							</label>
						</div>
						<button id="btn_login" class="btn btn-lg btn-primary btn-block" 
								type="submit"  >
							Sign in
						</button>
						<a class="nav-link" href="/member/join">회원가입</a>
					</form>
				</div>
			</section>
   		   </div>
          
		<!-- 로그인 -->
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

  <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>

</body>

</html>

