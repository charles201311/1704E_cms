<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章标题列表</title>
</head>
<body>
	<div class="container">
		<div class="form-group row">

			<div class="col">
				审核状态: <select name="status" id="status" class="custom-select">
					<option value="99">所有</option>
					<option value="0">待审核</option>
					<option value="1">已审核</option>
					<option value="-1">未通过</option>
				</select>

			</div>

			<div class="col">
				文章栏目: <select class="custom-select" id="channel" name="channelId">
					<option value="0">请选择</option>

				</select>
			</div>
			<div class="col">
				文章分类: <select class="custom-select" id="category" name="categoryId">
					<option value="0">请选择</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<input type="button" name="button" class="btn btn-warning" value="查询"
				onclick="query()" />

		</div>


		<ul class="list-unstyled">

			<c:forEach items="${articles }" var="a">
				<li class="media"><a href="/article/selectByAdmin?id=${a.id }"
					target="_blank"><img class="mr-3" src="/pic/${a.picture }"
						alt="no pic" width="100px" height="100px"> </a>
					<div class="media-body">
						<dl>
							<dt>
								<a href="/article/selectByAdmin?id=${a.id }" target="_blank">${a.title}</a>
							</dt>

							<dd style="float: right; color: blue;">${a.status==0?"待审核":a.status==1?"审核通过":"未通过" }</dd>

							<dd>${a.user.nickname }${a.updated }</dd>

						</dl>
					</div></li>
				<hr>
			</c:forEach>

		</ul>
		${pages }
	</div>
	<script type="text/javascript">

//查询

function query(){
var url="/article/selectsByAdmin?status="+$("#status").val()+"&channelId="+$("#channel").val()+"&categoryId="+$("#category").val()
	$("#content-wrapper").load(url)
}

$(function(){
	 //让下拉框回显
	$("#status").val('${article.status==null?"99":article.status}')
	var channelId='${article.channelId==null?"0":article.channelId}';
	//为栏目下拉框填充内容
   $.get("/channel/channels",function(list){
	   for(var i in list){
			$("#channel").append("<option value='"+list[i].id+"'> "+list[i].name+"</option>")
	   }
	   //让下拉框回显
	   $("#channel").val(channelId)
	   
   })
	//为栏目添加绑定事件
	$("#channel").change(function(){
			//先清空分类下所有内容
		$("#category").empty();
		//获取当前的栏目ID
	  var cid =$(this).val();	
	 if(cid!=0)
	  getCategory(cid);	
	 if(cid==0)
	$("#category").append("<option value='0'>请选择</option>") 
	})
	
	
	if(channelId!=""){
		 getCategory(channelId);	
	}
})



function getCategory(channelId){

	 $.get("/channel/categorys",{cid:channelId},function(list){
		   for(var i in list){
				$("#category").append("<option value='"+list[i].id+"'> "+list[i].name+"</option>")
				
		   }
		   
		   
		   //让下拉框回显
		   var catId='${article.categoryId}';
		   if(catId!='')
		   $("#category").val(catId) 
		   
		   
	   })	
	
}



</script>


<script type="text/javascript" src="/resource/js/cms.js"></script>
</body>
</html>