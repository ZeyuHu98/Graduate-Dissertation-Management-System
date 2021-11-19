<%@ page  language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="topLine.jsp" %>
<%@ taglib uri="http://org.wangxg/jsp/extl"  prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="edu.whu.system.tools.Tools" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%String datesub = application.getAttribute("datesub").toString(); %>

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
			<form id="myform" action="<%=path%>/SetTime.html" method="post">
				<!-- 查询条件区 -->
				<table class="table" id="table">
					<caption>设定空闲时间表</caption>
					<tbody class="setTime-tbody">
						<tr>
							<td></td><td></td>
							<td></td>
							<td>上午</td>
							<td>下午</td>
							<td></td><td></td>
						</tr>
						<c:if test="${not empty applicationScope.date1}">
						<tr>
							<td></td><td></td>
							<td>${applicationScope.date1}</td>
							<c:forEach var="i" begin="1" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td>
							</c:forEach>
							<c:forEach var="i" begin="2" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td><td></td><td></td>
							</c:forEach>
						</tr>
						</c:if>
						<c:if test="${not empty applicationScope.date2}">
						<tr><td></td><td></td>
							<td>${applicationScope.date2}</td>
							<c:forEach var="i" begin="3" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td>
							</c:forEach>
							<c:forEach var="i" begin="4" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td><td></td><td></td>
							</c:forEach>
						</tr>
						</c:if>
						<c:if test="${not empty applicationScope.date3}">
						<tr><td></td><td></td>
							<td>${applicationScope.date3}</td>
							<c:forEach var="i" begin="5" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td>
							</c:forEach>
							<c:forEach var="i" begin="6" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td><td></td><td></td>
							</c:forEach>
						</tr>
						</c:if>
						<c:if test="${not empty applicationScope.date4}">
						<tr><td></td><td></td>
							<td>${applicationScope.date4}</td>
							<c:forEach var="i" begin="7" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td>
							</c:forEach>
							<c:forEach var="i" begin="8" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td><td></td><td></td>
							</c:forEach>
						</tr>
						</c:if>
						<c:if test="${not empty applicationScope.date5}">
						<tr><td></td><td></td>
							<td>${applicationScope.date5}</td>
							<c:forEach var="i" begin="9" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td>
							</c:forEach>
							<c:forEach var="i" begin="10" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td><td></td><td></td>
							</c:forEach>
						</tr>
						</c:if>
						<c:if test="${not empty applicationScope.date6}">
						<tr><td></td><td></td>
							<td>${applicationScope.date6}</td>
							<c:forEach var="i" begin="11" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td>
							</c:forEach>
							<c:forEach var="i" begin="12" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td><td></td><td></td>
							</c:forEach>
						</tr>
						</c:if>
						<c:if test="${not empty applicationScope.date7}">
						<tr><td></td><td></td>
							<td>${applicationScope.date7}</td>
							<c:forEach var="i" begin="13" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td>
							</c:forEach>
							<c:forEach var="i" begin="14" end="${applicationScope.datesub * 2 }" step="14">
							<td>
								<input type="checkbox" name="settime" value="${i }">
							</td><td></td><td></td>
							</c:forEach>
						</tr>
						</c:if>
						<tr>
							<td colspan="7">
								<input class="btn btn-info" type="submit" name="next" value="提交">
							</td>
						</tr>
						<tr>
							<td colspan="7">
								${msg }
							</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="hided" value="${dataMap.uid }">
				<e:hidden name="datesub" value="<%=datesub %>"/>
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