<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="keywords" content="${article.keywords }"/>


<title>${article.title }</title>
   
</head>
<body>


	<div class="card" align="center">
		<div class="card-header"><h2>${article.title }</h2>
		     <h3> 文章来源: ${article.origin }</h3>
		<hr>
		</div>
		
		<div class="card-body">${article.content }</div>


	</div>
	
	<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
	
	
	
<script type="text/javascript">
$(function(){//更新访问量
	
	$.post("/article/updateHits",{id:'${article.id}'},function(){})
	
	
})



</script>
</body>
</html>