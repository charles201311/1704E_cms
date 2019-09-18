<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>列表</title>
</head>
<body>
	<div class="container">

		<div>
			<form  id="form1">
				<table class="table table-light table-bordered table-hover table-sm">
					<tr>
						<th>链接名称:<input type="text" name="text"></th>
					</tr>
					<tr>
						<th>URL:<input type="url" name="url"></th>
					</tr>
					<tr>
						<th><button type="button" onclick="add()">保存</button></th>
					</tr>

				</table>


			</form>
		</div>

	</div>

	<script type="text/javascript">
		function add(){
			$.post("/links/save",$("#form1").serialize(),function(msg){
				alert(msg)
				if(msg=="ok")
					
			})
		}
	</script>
	<script type="text/javascript" src="/resource/js/cms.js"></script>
</body>
</html>