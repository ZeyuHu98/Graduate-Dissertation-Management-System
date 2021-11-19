<%@ page  language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl"  prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="topLine.jsp" %>
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
	}
	</script>
</head>

<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column">
			<div id="top"></div>
			<jsp:include page="menu.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
			<div class="tp" id="tp">
				<div id="tp1"><a href="#top"><img alt="" src="./images/up.png" class="footer-img" width="50px" height="50px"></a></div>
				<div id="tp2"><a href="#under"><img alt="" src="./images/down.png" class="footer-img" width="50px" height="50px"></a></div>
			</div>
		</div>
		<div class="col-md-10 column">
			<form id="myform" action="<%=path%>/QueryJudge.html" method="post">
			<!-- 查询条件区 -->
				<table class="table" id="table">
					<caption>专家查询</caption>
					<tbody class="judgeThesis-tbody">
						<tr>
							<td colspan="6">查询条件</td>
						</tr>
						<tr>
						  	<td></td>
						  	<td></td>
						    <td>专家姓名</td>
						    <td></td>
						    <td>
						      <e:text name="qename"/>
						    </td>
						    <td></td>
					    </tr>
				    </tbody>
				</table>
				
				<!-- 数据迭代区 -->
				<table class="table" id="table2">
					<thead>
						<tr>
							<td></td><td></td>
						    <th>专家姓名</th>
						    <th>论文标题</th>
						    <td></td><td></td>
					    </tr>
					</thead>
					<tbody class="judgeThesis-tbody">
					<c:choose>
					<c:when test="${dataList!=null }">
					<!-- 显示实际查询到的数据 -->
					<c:forEach items="${dataList }" var="ins" varStatus="vs">
						<tr>
							<td></td><td></td>
							<td>${ins.name }</td>
							<td>${ins.b102 }</td><td></td><td></td>
						</tr>
					</c:forEach>
					<!-- 补充空行 -->
					<c:forEach begin="${fn:length(dataList)+1 }" step="1" end="10">
						<tr>
							<td></td><td></td><td></td><td></td><td></td><td></td>
						</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<c:forEach begin="1" step="1" end="10">
						<tr>
							<td></td><td></td><td></td><td></td><td></td><td></td>
						</tr>
					</c:forEach>
					</c:otherwise>
					</c:choose>
						<tr>
							<td colspan="6">
								<input type="submit" class="btn btn-info" name="next" value="查询">
								<input type="submit" class="btn btn-info" name="next" value="导出评审安排待定表" formaction="<%=path%>/PreviewExcel.xls">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<form method="post" action="<%=path%>/ExcelJudge.htmn" encType="multipart/form-data">
				<table class="table">
					<tbody>
						<tr>
							<td>
								<input type="file" class="btn btn-info" name="uploadFile" required accept=".xls"/>
								<input class="filesubmit btn btn-info" type="submit"  value="Excel导入" />
							</td>
						</tr>
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