<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>欢迎注册</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<div class="container">
		<div class="row passport_bg">

			<div class="col-md-6">
				<div class="passport_panel">

					<form:form action="/passport/reg" method="post" id="form1"
						modelAttribute="userVO">
						<div class="card">
							<div class="card-header">欢迎注册</div>
							<div class="card-body">
							  <span style="color: red"> ${error }</span> 
								<div class="form-group">
									<label for="username">用户名</label>
									<form:input path="username" id="username" cssClass="form-control" />
                                    <form:errors path="username" cssStyle="color:red"/>
								</div>


								<div class="form-group">
									<label for="password">密码</label>
									<form:password path="password" id="password"  cssClass="form-control"/>
									<form:errors path="password" cssStyle="color:red"/>

								</div>
								<div class="form-group">
									<label for="repassword">重复密码</label>
									<form:password path="repassword" id="repassword" cssClass="form-control" />
										<form:errors path="repassword" cssStyle="color:red"/>

								</div>



								<div class="form-group form-check-inline">

                                   
									<form:radiobutton path="gender" value="1" cssClass="form-check-input"  checked="true" />
									男
									<form:radiobutton path="gender" value="0" cssClass="form-check-input"  />
									女

								</div>

								<div>
									<button type="submit" class="btn btn-success">注册</button>
									<button type="reset" class="btn btn-primary">重置</button>

								</div>
							</form:form>


							</div>
						</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
						<img src="/resource/images/guest.jpg" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/resource/images/guest1.jpg" alt="..."
							class="rounded-circle img-thumbnail" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br /> <br />
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
	<script type="text/javascript">
	/*
		$(function() {
			//对form表单进行jquery.validate校验
			$("#form1").validate({
				//定义规则
				rules : {
					username : {
						required : true,//用户名不能为空
						rangelength : [ 2, 5 ]
					//长度在2-5之间
					},
					password : {
						required : true,//密码不能为空
						rangelength : [ 6, 10 ],//长度在6-10之间
					},
					repassword : {
						equalTo : "#password",//两次密码必须一致

					}

				},
				messages : {
					username : {
						required : "用户名不能为空",
						rangelength : "长度在2-5之间",
					},
					password : {
						required : "密码不能为空",
						rangelength : "长度在6-10之间",
					},
					repassword : {
						equalTo : "两次密码必须一致",

					}

				}

			})

		})*/
	</script>
</body>
</html>