<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<%@include file="/WEB-INF/views/common/plugin_js.jsp"%>
<%@include file="/WEB-INF/views/admin/include/head.jsp" %>
<head>

<style>
	ul{
   		list-style:none;
   		padding-left:0px;
    }
    .product{
    	display: inline-block;
    	margin: 10px;
    	padding:22px 40px;
    }
    .description{
    	margin: 10px;
    }
    * {
    box-sizing:border-box;
    }
  
.container, .container-lg, .container-md, .container-sm, .container-xl {
    max-width:none!important;
}


</style>
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
<script>
	var cart_click = function(pdt_num) {
		$.ajax({
			url:"/cart/add",
			type:"get",
			dataType:"text",
			data:{pdt_num:pdt_num},
			success:function(data) {
				var result = confirm("장바구니에 추가되었습니다 ");
				
			}
			
		})
		
	};

</script>

</head>

<body>
<div class="wrapper" style="overflow-y: hidden;">
  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>
  <!-- Page Content -->
     

			     <%@ include file="/WEB-INF/views/common/left.jsp" %>
     
       <div class="content-wrapper">
   
     

    		<section class="content-header">
				<h1>
					Product List 
				</h1>
				<%-- realPath 주석 
				<%= application.getRealPath("/") %>
				--%>
				<ol class="breadcrumb">
					<li>
						<a href="#"><i class="fa fa-dashboard"></i> 상품목록</a>
					</li>
				</ol>
			</section>
    
		
			<section class="content container-fluid">
  		<div style="width:100%; min-width:1400px; background-color:white; padding: 50px 180px;"class="container text-center" >
        	
        	 	<h3>${cg_name}</h3><br>
					<ul class="pdtList">
					
					<%-- 상품이 존재하지 않는 경우 --%>
						<c:if test="${empty productList}">
							<span>등록된 상품이 존재하지 않습니다.</span>
						</c:if>
						
        	 
        	    <c:forEach items="${productList}" var="productVO" >
						<li class="product"  >
							${productVO.pdt_num}
							<div class="thumnail">
								<a href="/product/read${pm.makeQuery(pm.cri.page)}&pdt_num=${productVO.pdt_num}&cg_code=${cg_code}">
									<img src="/product/displayFile?fileName=${productVO.pdt_img}" >
								</a>
							</div>
							<div class="description">
								<a href="/product/read${pm.makeQuery(pm.cri.page)}&pdt_num=${productVO.pdt_num}&cg_code=${cg_code}" >${productVO.pdt_name}</a>
								<p>가격: <fmt:formatNumber value="${productVO.pdt_price}" pattern="###,###,###" />원<br>
								 	할인가: <fmt:formatNumber value="${productVO.pdt_discount}" pattern="###,###,###" />원</p>
							</div>
							<div class="btnContainer">
								<button class="btn btn-primary" id="btn_buy" type="button" 
									onclick="location.href = '/order/buy?pdt_num=${productVO.pdt_num}&ord_amount=1';">구매</button>
								<button class="btn btn-default" id="btn_cart" type="button" style= "background-color: #f4f4f4; color: #444; border-color: #ddd;"
									onclick="cart_click(${productVO.pdt_num})">장바구니</button> 
							</div>
						</c:forEach>
        	 </ul>
        	 
        	
        </div>
        <div class="box-footer container" style="width:100%; min-width:1400px;">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pm.prev}">
								<li><a href="list${pm.makeQuery(pm.startPage-1)}&cg_code=${cg_code}">&laquo;</a>
								</li>
							</c:if>

							<c:forEach begin="${pm.startPage}" end="${pm.endPage}"
								var="idx">
								<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
									<a href="list${pm.makeQuery(idx)}&cg_code=${cg_code}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pm.next && pm.endPage > 0}">
								<li><a href="list${pm.makeQuery(pm.endPage +1)}&cg_code=${cg_code}">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
      

       
   
        <!-- /.row -->
</section>
      </div>
      <!-- /.col-lg-9 -->
	
    
    <!-- /.row -->

  
  
  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

  <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>

</div>
</body>



</html>

