<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title }</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<!-- 轮播图 -->
	<div id="carousel" class="carousel slide" data-ride="carousel">
		<div class="carousel-inner">

			<c:forEach items="${images }" var="m" varStatus="i">

				<div class="carousel-item  ${i.index==0?"active":""}">
					<img class="d-block w-100" src="/pic/${m.url }" alt="First slide">
						<div class="carousel-caption">${m.descr }</div>
				</div>

			
			</c:forEach>
		</div>
		<a class="carousel-control-prev" href="#carousel" role="button"
			data-slide="prev"> <span class="carousel-control-prev-icon"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carousel" role="button"
			data-slide="next"> <span class="carousel-control-next-icon"
			aria-hidden="true"></span> <span class="sr-only">Next</span>
		</a>
	</div>


<jsp:include page="/WEB-INF/view/common/footer.jsp" />
</body>
</html>