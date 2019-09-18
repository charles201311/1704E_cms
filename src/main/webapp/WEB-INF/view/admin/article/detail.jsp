<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title }</title>

</head>

<body>

<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<div class="card" align="center">
	    <div><button class="btn btn-success" onclick="check(1)">同意</button>
	    <button class="btn btn-warning" onclick="check(-1)">不同意</button>
	    </div>
		<div class="card-header"><h2>${article.title }</h2>
		<hr>
		</div>
		
		<div class="card-body">${article.content }</div>


	</div>
<script type="text/javascript">
function check(status){
	var id='${article.id}'
	$.post("/article/check",{status:status,id:id},function(flag){
		if(flag){
			alert("操作成功!");
			window.close();
		}
		
	})
	
	
}

</script>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
</body>

</html>