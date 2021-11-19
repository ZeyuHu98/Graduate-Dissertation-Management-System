<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="topLine.jsp" %>
<html>
<head>
<title>硕士学位论文评审答辩系统</title>
   <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
   <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/mycss.css">
   <script src="bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
   <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type='text/javascript'>
	var i=3;
	$(function()
	{
		if(${empty cuid})
		{
			setTimeout(function(){window.location.href="login.jsp";},3000);//3秒后返回登录页面
			after();
		}
		else
		{
			//setTimeout(function(){window.location.href="javascript:history.go(-1)";},3000);
			setTimeout(function(){window.location.href="main.jsp";},3000);
			after();
		}
	});
	自动刷新页面上的时间
	function after()
	{
		$("#num").empty().append(i);
		i=i-1;
		setTimeout(function(){
		after();
		},1000);
	}
	</script>
<title>硕士学位论文评审答辩系统</title>
</head>
<body class="redirect-body">
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<p align="center">${msg }</p>
			<p align="center" class="redirect-p">页面将在<span id="num" style="color:red">3</span>秒后跳转至${empty cuid?'登录页面':'主页面' }</p><br>
			<img class="redirect-img" alt="" src="./images/404.jpg" width="1013px" height="604px">
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