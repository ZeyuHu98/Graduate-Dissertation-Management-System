<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="topLine.jsp"%>
<!DOCTYPE html>
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
		var item2= document.getElementById("table2");
		var tbody2=item2.getElementsByTagName("tbody")[0];
		var trs2= tbody2.getElementsByTagName("tr");
		for(var i=0;i<trs2.length;i++)
		{  
			if(i%4==1)
			{  
				trs2[i].style.background="#d0e9c6";
			}
			else if(i%4==3)
			{
				trs2[i].style.background="#fcf8e3"; 
			}
			else
			{
				trs2[i].style.background="#FFFFFF";
       		}
		} 
		var dataList = "${dataList}";
		var msg = "${msg}";
		if(dataList==""&&msg=="")
		{
			var vform = document.getElementById("myform");
			vform.action="<%=path%>/sec_Thesis.html";
			vform.submit();
		}
	}
	</script>
</head>


<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column">
			<div class="top"></div>
			<jsp:include page="menu.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
			<div class="tp" id="tp">
				<div id="tp1"><a href="#top"><img alt="" src="./images/up.png" class="footer-img" width="50px" height="50px"></a></div>
				<div id="tp2"><a href="#under"><img alt="" src="./images/down.png" class="footer-img" width="50px" height="50px"></a></div>
			</div>
		</div>
		<div class="col-md-10 column">
			<form action="<%=path%>/sec_Search.html" method="post">
				<table class="table" id="table">
					<caption>学生论文</caption>
					<tr>
						<th>标题</th>
						<td><e:text name="b102" defval="${U.b102}" /></td>
					</tr>
					<tr>
						<th>检查结果</th>
						<td><e:radio name="b107" value="全部:'',未检查:0,通过:1,未通过:2"
								defval="${U.aab107}" /></td>
					</tr>
					<tr>
						<th>评审结果</th>
						<td><e:radio name="b108" value="全部:'',未评审:0,通过:1,不通过:2"
								defval="${U.aab108}" /></td>
					</tr>
					<tr>
						<th>答辩结果</th>
						<td><e:radio name="b109" value="全部:'',未答辩:0,通过:1,不通过:2"
								defval="${U.aab109}" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button class="btn btn-info" type="submit">筛选</button>
							<button class="btn btn-info" type="submit"
							formaction="<%=path%>/staB01.html">论文审批统计</button>
							<button class="btn btn-info" type="submit"
							formaction="<%=path%>/staB02.html">评审等级统计</button>
						</td>
					</tr>
				</table>
			</form>
			<form id="myform" action="" method="post">
				<table class="table" id="table2">
					<thead>
						<tr>
						    <th>学生姓名</th>
							<th>标题</th>
							<th>检查结果</th>
							<th>评审结果</th>
							<th>答辩结果</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
					<c:when test="${empty dataList}">
					<c:forEach begin="1" step="1" end="6">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<c:forEach var="U" items="${dataList}">
						<tr>
						    <td>${U.name}</td>
							<td>${U.b102}</td>
							<td>${U.b107}</td>
							<td>
							<c:choose>
							<c:when test="${U.b108=='未评审'}">
								<p>${U.b108}</p>
							</c:when>
							<c:otherwise>
								<a href="<%=path%>/view_Review.html?b101=${U.b101}">${U.b108}</a>
							</c:otherwise>
							</c:choose>
							</td>
							<td>
							<c:choose>
							<c:when test="${U.b109=='未答辩'}">
								<p>${U.b109}</p>
							</c:when> 
							<c:otherwise>
								<a href="<%=path%>/view_Reply.html?b101=${U.b101}">${U.b109}</a>
							</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>
					<c:forEach begin="${fn:length(dataList)+1 }" step="1" end="6">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
					</c:otherwise>
					</c:choose>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="row clearfix">
		<div id="under"></div>
		<div class="col-md-12 column">
		<jsp:include page="footer.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
		</div>
	</div>
</div>
</body>
</html>