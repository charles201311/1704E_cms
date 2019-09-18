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
		<form class="form-inline">
			<div class="form-group">
				<label for="username">用户名</label> <input class="form-control-sm "
					type="text" name="username" id="username" value="${username }">
				&nbsp;
				<button type="button" class="btn btn-success btn-sm"
					onclick="query()">查询</button>
			</div>
		</form>



		<div>
			<table class="table table-light table-bordered table-hover table-sm">
				<tr align="center">
					<th>序号</th>
					<th>用户名</th>
					<th>昵称</th>
					<th>性别</th>
					<th>注册日期</th>
					<th>修改日期</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${users }" var="u" varStatus="i">

					<tr align="center">
						<td>${i.index+1 }</td>
						<td>${u.username }</td>
						<td>${u.nickname }</td>
						<td>${u.gender==0?"女":"男" }</td>
						<td><fmt:formatDate value="${u.createTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${u.updateTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>
						 <c:if test="${u.locked==0 }">
						 
						  <button type="button" class="btn btn-success" onclick="update(${u.id},this)">正常</button>
						 </c:if>
						<c:if test="${u.locked==1 }">
						 
						  <button type="button" class="btn btn-warning" onclick="update(${u.id},this)">禁用</button>
						 </c:if>
						
						
						
						
						</td>
					</tr>

				</c:forEach>

			</table>
			<div>
				${pages }
			</div>
		</div>
	</div>

	<script type="text/javascript">
	 //改变用户状态

	 function update(uid,obj){
		//alert($(obj).text()) 
		var locked =$(obj).text()=="正常"?"1":"0";

		$.post("/user/update",{id:uid,locked:locked},function(flag){
			
			if(flag){
				//改变按钮的值
			  $(obj).text($(obj).text()=="正常"?"禁用":"正常")	
			  //改变按钮的样式 颜色
			  $(obj).attr("class",locked=="1"?"btn btn-warning":"btn btn-success");
			}
			
		})
	 }
	
	  function query(){
		 var url="/user/selects?username="+$("#username").val()
		$("#content-wrapper").load(url)  ;
		  
	  }
	
	</script>
	<script type="text/javascript" src="/resource/js/cms.js"></script>
</body>
</html>