<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="topLine.jsp"%>
<html>
<head>
<title>硕士学位论文评审答辩系统</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/mycss.css">
	<script src="bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script>
	//循环更改表格tr标签的背景颜色
	window.onload=function()
	{
		var item= document.getElementById("table");
		var tbody=item.getElementsByTagName("tbody")[0];
		var trs= tbody.getElementsByTagName("tr");
		for(var i=0;i<trs.length;i++)
		{  
			if(i%4==1)
			{  
				trs[i].style.background="#d0e9c6";
			}
			else if(i%4==3)
			{
				trs[i].style.background="#fcf8e3"; 
			}
			else
			{
				trs[i].style.background="#FFFFFF";
       		}
		}
		var uid = "${dataMap.uid}";
		var msg = "${msg}";
		if(uid==""&&msg=="")
		{
			var vform = document.getElementById("myform");
			vform.action="<%=path%>/expert_Info.html";
			vform.submit();
		}
	}
</script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column">
			<jsp:include page="menu.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
		</div>
		<div class="col-md-10 column">
			<form id="myform" action="<%=path%>/password_Change.html" method="post">
				<table class="table" id="table">
					<caption>个人信息</caption>
					<tbody>
						<tr>
							<td>姓名</td>
							<td>${dataMap.name}</td>
						</tr>
						<tr>
							<td>所属大学</td>
							<td>${dataMap.a601}</td>
						</tr>
						<tr>
							<td>专家类别</td>
							<td>${dataMap.a602}</td>
						</tr>
						<tr>
							<td>研究领域</td>
							<td>${dataMap.a603}</td>
						</tr>
						<tr>
							<td>职称</td>
							<td>${dataMap.a604}</td>
						</tr>
						<tr>
							<td>身份证号码</td>
							<td>${dataMap.a605}</td>
						</tr>
						<tr>
							<td>银行账号</td>
							<td>${dataMap.a606}</td>
						</tr>
						<tr>
							<td>具体开具银行</td>
							<td>${dataMap.a607}</td>
						</tr>
						<tr>
							<td>手机号码</td>
							<td>${dataMap.a608}</td>
						</tr>
						<tr>
							<td>邮箱</td>
							<td>${dataMap.a609}</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<button class="btn btn-info" type="submit" name="uid"
								value="${dataMap.uid}">修改密码</button>
							</td>
						</tr>
						<tr>
							<td colspan="2">${msg }</td>
						</tr>
					</tbody>
				</table>
			</form>
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