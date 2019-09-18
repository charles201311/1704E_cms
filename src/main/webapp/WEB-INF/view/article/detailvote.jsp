<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投票</title>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
		<div align="left">
			<form action="" id="form1">
				<!-- 投票的文章 -->
				<input type="hidden" name="articleId" value="${article.id }">
				<div class="card">

					<div class="card-header">${article.title }</div>
					<div class="card-body">
						<c:forEach items="${mapList }" var="maps">
							<c:forEach items="${maps}" var="m">
								<div class="form-check">
									<input class="form-check-input" type="radio" value="${m.value}"
										id="defaultCheck1" name="optionId"> <label
										class="form-check-label" for="defaultCheck1">${m.value }
									</label>
								</div>
							</c:forEach>
						</c:forEach>
					</div>

				</div>
				<button class="btn btn-info" type="button" onclick="vote()">投票</button>
			</form>
			<!-- 投票结果 -->
		</div>
		<div>
			<h1>投票结果</h1>
			<c:forEach items="${voteMapList }" var="maps">
			
					<div class="progress-bar" role="progressbar"
						style="width:  ${maps.scale }%;" aria-valuenow="${maps.scale }"
						aria-valuemin="0" aria-valuemax="100">${maps.scale }%
						${maps.OPTION_id}
						</div>
				
				<br>
			</c:forEach>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function vote() {

		//	alert($("#form1").serialize())
			$.post("/article/vote", $("#form1").serialize(), function(obj) {
				if (obj) {
					alert("投票成功")
					location.reload();
				} else {
					alert("投票失败,你先登录或已经投票!")
				}
			})
		}
	</script>

	<jsp:include page="/WEB-INF/view/common/footer.jsp" />

</body>
</html>