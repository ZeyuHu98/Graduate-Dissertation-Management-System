<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
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
			<form id="myform"
			action="<%=path%>/${empty dataMap.b101?'Thesis_Upload':'Thesis_Resubmit'}.com"
			enctype="multipart/form-data" method="post">
				<table class="table" id="table">
					<caption>论文上传</caption>
					<tbody>
						<tr>
							<td>标题</td>
							<td><e:textarea rows="5" cols="45" id="b102" name="b102"
									required="true" autofocus="true" defval="${dataMap.b102}" /></td>
						</tr>
						<tr>
							<td>摘要</td>
							<td><e:textarea rows="5" cols="45" name="b103"
									required="true" defval="${dataMap.b103}" /></td>
						</tr>
	
						<tr>
							<td>关键字</td>
							<td><e:textarea rows="5" cols="45" name="b104"
									required="true" defval="${dataMap.b104}" /></td>
						</tr>
	
						<tr>
							<td>领域</td>
							<td><e:text id="b105" name="b105" required="true"
									defval="${dataMap.b105}" /></td>
						</tr>
	
						<tr>
							<td>论文</td>
							<td><input type="file" class="btn btn-info" id="b106" accept="application/pdf" name="b106"></td>
						</tr>
						<tr>
							<td colspan="2">
								<button class="btn btn-info" type="submit">${empty dataMap.b101?'新的提交':'重新提交'}</button>
								<input class="btn btn-info" type="submit" name="next" value="返回" formaction="<%=path%>/student_Thesis.html">
							</td>
						</tr>
						<tr><td colspan="2">${msg} ${message}</td></tr>
					</tbody>
				</table>
				<input type="hidden" name="b101" value="${dataMap.b101}">
				<input type="hidden" name="uid" value="${dataMap.uid}">
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