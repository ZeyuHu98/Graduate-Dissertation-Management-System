<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="topLine.jsp"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
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
			<form action="<%=path%>/${empty dataMap?'expert_Add':'expert_Modify'}.html" method="post">
				<table id="table" class="table">
					<caption>评审</caption>
					<thead></thead>
					<tbody class="examExecute-tbody">
						<tr><td></td><td></td><td></td><td></td><td></td></tr>
						<tr>
							<td></td>
							<td></td>
							<td>评审等级</td>
							<td><e:radio name="b202" value="优秀:1,良好:2,合格:3,不合格:4" defval="${dataMap.b202}" /></td>
							<td></td>
						</tr>
						<tr><td></td><td></td><td></td><td></td><td></td></tr>
						<tr>
							<td></td>
							<td></td>
							<td>评语</td>
							<td><e:text name="b203" defval="${dataMap.b203}" /></td>
							<td></td>
						</tr>
						<tr><td></td><td></td><td></td><td></td><td></td></tr>
						<tr>
							<td colspan="5">
								<button class="btn btn-info" name="b101" value="${dataMap.b101}">${empty dataMap?'提交':'修改'}</button>
							</td>
						</tr>
						<tr>
							<td colspan="5">${msg}</td>
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