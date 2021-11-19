<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<form id="myform" action="" method="post">
				<table class="table" id="table">
					<caption>我的论文</caption>
					<thead></thead>
					<tbody>
						<tr>
							<td>标题</td>
							<td colspan="2">${dataMap.b102}</td>
						</tr>
						<tr>
							<td>摘要</td>
							<td colspan="2">${dataMap.b103}</td>
						</tr>
						<tr>
							<td>关键字</td>
							<td colspan="2">${dataMap.b104}</td>
						</tr>
						<tr>
							<td>领域</td>
							<td colspan="2">${dataMap.b105}</td>
						</tr>
						<tr>
							<td>检查结果</td>
							<td colspan="2">${dataMap.b107}</td>
						</tr>
						<tr>
							<td>评审结果</td>
							<td>
							<c:choose>
							<c:when test="${dataMap.b108=='未评审'}">
								<p>${dataMap.b108}</p>
							</c:when>
							<c:otherwise>
								<a class="btn btn-info" href="<%=path%>/view_Review.html?b101=${dataMap.b101}">${dataMap.b108}</a>
							</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<td>答辩结果</td>
							<td>
							<c:choose>
							<c:when test="${dataMap.b109=='未答辩'}">
								<p>${dataMap.b109}</p>
							</c:when>
							<c:otherwise>
								<a class="btn btn-info" href="<%=path%>/view_Reply.html?b101=${dataMap.b101}">${dataMap.b109}</a>
							</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<td>论文</td>
							<td colspan="2">
								<a href="<%=path%>/view_Thesis.html?b101=${dataMap.b101}" class="btn btn-info" target="_blank">在线阅读</a>
							</td>
						</tr>
						<tr>
							<td colspan="2">${msg}</td>
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