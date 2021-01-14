<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script id="subCGListTemplate" type="text/x-handlebars-template">
	{{#each .}}
		<li><a href="/product/list?cg_code={{cg_code}}">{{cg_name}}</a></li>
	{{/each}}
</script>

<%-- 2차 카테고리 템플릿 적용함수 --%>
<script>
	$(document).ready(function(){
		/* 1차 카테고리에 따른 2차 카테고리 작업.   on()메서드: 매번진행 one()메서드: 단1회만 진행 */
		$(".mainCategory").one("click", function(){
			var mainCGCode= $(this).val();
	
			var url = "/product/subCGList/" + mainCGCode;
			
						
			// REST 방식으로 전송
			$.getJSON(url, function(data){
				// 받은 데이터로 subCategory에 템플릿 적용
			
				subCGList(data, $("#mainCategory_"+mainCGCode) ,$("#subCGListTemplate"));
				
			});

		});	
	});

	var subCGList = function(subCGStr, target, templateObject) {

		var template = Handlebars.compile(templateObject.html());
		var options = template(subCGStr);

		// 기존 option 제거(누적방지)
		//$("#subCategory option").remove();
		target.append(options);
	}
</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<aside class="main-sidebar" style="padding-bottom:67.3%;" style="background-color:lightgray;" >
	<section class="sidebar">
      <div class="list-group" style="padding-top:30px;">
			<ul class="sidebar-menu" data-widget="tree">
				<c:forEach items="${userCategoryList}" var="list">
					<li class="treeview mainCategory"   value="${list.cg_code}">
						<a href="/product/list?cg_code=${list.cg_code}" class="list-group-item">
							
							<span>${list.cg_name}</span>
							<span class="pull-right-container">
								<i class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<!--  2차카테고리 자식수준으로 추가작업 -->
						<ul class="treeview-menu" id="mainCategory_${list.cg_code}"></ul>
					</li>
				</c:forEach>
		</ul>
		</div>
		</section>
		</aside>
</body>
</html>