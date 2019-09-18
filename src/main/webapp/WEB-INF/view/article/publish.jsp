<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布文章</title>
<script>
	KindEditor.ready(function(K) {
		window.editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
	function query() {
		alert(editor1.html())
		//alert( $("[name='content1']").attr("src"))
	}
</script>
</head>
<body>
	<%=htmlData%>
	<form id="form1">


		<div class="form-group">
			<label for="title">标题</label> <input type="text" class="form-control"
				name="title" id="title">
		</div>
		<div class="form-group">
			<textarea name="content1" cols="100" rows="8"
				style="width: 840px; height: 250px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
			<br />
		</div>

		 <div class="form-group">
			<label for="title">标题图片</label> <input type="file"
				class="form-control" name="file" id="file">
		</div>
		<%--  <div class="form-group">
			<label for="centent_type">文章类型</label>
			<select name="types" >
			 <c:forEach items="${types }" var="t">
			   <option value="${t.code }">${t.name }</option>
			</c:forEach>
			</select>
			
		</div>  --%>
		<div class="form-group row">

			<div class="col">
				文章栏目: <select class="custom-select" id="channel" name="channelId">
				 <option value="">请选择</option>
				
				</select>
			</div>
			<div class="col">
				文章分类: <select class="custom-select" id="category" name="categoryId" >
				</select>
			</div>
		</div>

		<div class="form-group">
			<input type="button" name="button" class="btn btn-info" value="提交内容"
				onclick="publish()" />

		</div>

	</form>
	
	
	<script type="text/javascript">
	
	//发布文章
	function publish(){
		var  formData = new FormData($("#form1")[0]);
		//为formData添加文章内容
		formData.set("content",editor1.html())
		$.ajax({
			type:"post",
			data:formData,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			url:"/article/publish",
			success:function(flag){
				if(flag){
					alert("发布成功");
				}else{
					alert("发布失败")
				}
			}
			
			
			
		}) 
		
		
		
		
	}
	
	
	
	
	$(function(){
		
		//为栏目下拉框填充内容
	   $.get("/channel/channels",function(list){
		   for(var i in list){
				$("#channel").append("<option value='"+list[i].id+"'> "+list[i].name+"</option>")
				
		   }
		   
	   })
		//为栏目添加绑定事件
		$("#channel").change(function(){
			
			//先清空分类下所有内容
			
			$("#category").empty();
			
			//获取当前的栏目ID
		  var cid =$(this).val();	
		  $.get("/channel/categorys",{cid:cid},function(list){
			   for(var i in list){
					$("#category").append("<option value='"+list[i].id+"'> "+list[i].name+"</option>")
					
			   }
			   
		   })
			
		})
		
	})
	
	
	</script>
</body>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>




</body>
</html>