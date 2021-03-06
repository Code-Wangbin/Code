<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>


    <link href="css/bootstrap.min.css" rel="stylesheet">
	  <link href="css/register.css" rel="stylesheet">

    <script src="js/jquery-2.1.0.min.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
	  <script>
		  function refer() {
			  var check = document.getElementById("vcode");
			  check.src = "${pageContext.request.contextPath}/checkCode?"+new Date().getTime();
		  }
	  </script>
  </head>
  <body>
  <div class="container" style="width: 400px;">
	  <h3 style="text-align: center;">管理员注册</h3>
	  <form action="${pageContext.request.contextPath}/registerServlet" method="post">
		  <div class="form-group">
			  <label for="name">昵称：</label>
			  <input type="text" name="name" class="form-control" id="name" placeholder="请输入用户名"/>
		  </div>

		  <div class="form-group">
			  <label for="username">用户名：</label>
			  <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名"/>
		  </div>

		  <div class="form-group">
			  <label for="password">密码：</label>
			  <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
		  </div>

		  <div class="form-inline">
			  <label for="verifycode">验证码：</label>
			  <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码" style="width: 120px;"/>
			  <a href="javascript:void(0)" onclick="refer()">
				  <img src="${pageContext.request.contextPath}/checkCode" title="看不清点击刷新" id="vcode"/>
			  </a>
			  <a href="javascript:void(0)" onclick="refer()">
				  点击刷新
			  </a>
		  </div>
		  <hr/>
		  <div class="form-group" style="text-align: center;">
			  <input class="btn btn btn-primary" type="submit" value="注册">&nbsp;&nbsp;
		  </div>
	  </form>


	  <div class="alert alert-warning alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" >
			  <span>&times;</span>
		  </button>
		  <strong>
			  <c:if test="${error != null}">
				  ${error}
			  </c:if>
		  </strong>
	  </div>
  </div>
  </body>
</html>
