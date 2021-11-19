<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath", basePath);
%>
<html>
<head>
<title>登录系统</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/mycss.css">
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script src="bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>

<body class="login-body" background="../images/background.jpg">
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<nav class="navbar navbar-default" role="navigation">
					    <div class="navbar-header">
						    <a class="navbar-brand" href="#">硕士学位论文评审答辩系统</a>
						</div>
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav navbar-right">
								<li class="dropdown">
									<a href="#"></a>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-3 column">
					
				</div>
				<div class="col-md-6 column" id="login-div">
					<form role="form" action="<%=path %>/login" class="login-form" method="post">
						<table align="center" class="table">
							<caption class="login-caption">
								<h2 class="login-p-white">
									欢迎您
								</h2>
							</caption>
						    <tbody class="login-tbody">
							    <tr>
									<td class="login-p-white">用户名</td>
									<td>
										<input type="text" name="username" class="form-control login-input" required="true"/>
									</td>
								</tr>
								<tr>
								 	<td class="login-p-white">密码</td>
									<td>
										<input type="password"  class="form-control login-input" name="password" required="true">
									</td>
								</tr>
								<tr>
									<td colspan="2" class="login-p-white">
										<input type="submit" class="btn loginbtn-primary" name="next" value="登录">
									</td>
								</tr>
								<tr>
									<td colspan="2"><e:message/></td>
								</tr>
						    </tbody>
						</table>
					</form>
				</div>
				<div class="col-md-3 column"></div>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
		<jsp:include page="footer.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
		</div>
	</div>
</div>
</body>
</html>