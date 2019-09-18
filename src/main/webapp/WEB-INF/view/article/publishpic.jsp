<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布文章</title>

</head>

<body>

	<button type="button" class="btn btn-info" onclick="addCard()">增加图片</button>
	<button type="button" class="btn btn-success" onclick="publishPic()">发布图片</button>

	<form id="form1">
		<div class="form-group">
			<label for="title">标题</label> <input type="text" class="form-control"
				name="title" id="title">

		</div>
		<hr>

		<div id="mdiv" style="border: 1px solid red">
			<div id="card1" style="float: left">
				<div class="card" style="width: 15rem;">
					<div class="card-header">
						<label for="title">标题图片</label> <input type="file"
							class="form-control" name="files" id="file">

					</div>
					<div class="card-body">
						图片描述:
						<textarea rows="5" cols="" name="descr" style="width: 13rem;"></textarea>
					</div>
				</div>
			</div>
		</div>





	</form>




	<script type="text/javascript">
		function addCard() {
			//var x  =	 document.getElementById("div1").innerHTML;
			//
			$("#mdiv").append($("#card1").html());
		}

		//发布文章
		function publishPic() {
			var formData = new FormData($("#form1")[0]);
			$.ajax({
				type : "post",
				data : formData,
				// 告诉jQuery不要去处理发送的数据
				processData : false,
				// 告诉jQuery不要去设置Content-Type请求头
				contentType : false,
				url : "/article/publishPic",
				success : function(flag) {
					if (flag) {
						alert("发布成功");
					} else {
						alert("发布失败")
					}
				}

			})

		}
	</script>
</body>
</html>



</body>
</html>