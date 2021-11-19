<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="topLine.jsp"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>硕士学位论文评审答辩系统</title>
   <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
   <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/mycss.css">
   <script src="bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
   <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
   <script type="text/javascript">
   	window.onload=function()
   	{
   		var v1=document.getElementById("state").value;
   		if(v1=="0")
   		{
   		   	var vform=document.getElementById("myform");
   		   	vform.submit();
   		}
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
			<div id="message-div">
			<%
				String index = request.getParameter("index");
				request.setAttribute("index",index);
				Map<String, List<String>> messageContent = (Map<String, List<String>>) request.getSession().getAttribute("messageContent");
				List<String> content = messageContent.get(index);
				request.setAttribute("content", content);
			%>
			<h2 class="message-h2">${content[1] }</h2>
			<p class="message-content">&#12288;&#12288;${content[2] }</p>
			</div>
			<form action="<%=path%>/readMessage" id="myform" name="next" method="post">
			<table class="table" id="table">
				<caption></caption>
				<tbody>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
					<tr><td>&#12288;</td></tr>
				</tbody>
			</table>
			<input type="hidden" id="state" name="state" value="${content[3] }">
			<input type="hidden" id="index" name="index" value="${param.index }">
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