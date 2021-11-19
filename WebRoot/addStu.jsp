<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="topLine.jsp"%>
<%@ taglib uri="http://org.wangxg/jsp/extl"  prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<form action="<%=path%>/addStu.html" method="post">
				<table class="table" id="table">
					<caption>
				       学生${empty dataMap.uid?'添加':'修改' }
				    </caption>
				    <tbody>
					    <tr>
					    	<td colspan="2">学生数据</td>
					    </tr>
						<tr>
							<td>姓名</td>
							<td>
								<e:text name="name" required="true" autofocus="true" defval="${dataMap.name }"/>
							</td>
						</tr>
						<c:if test="${!empty dataMap.uid }">
						<tr>
							<td>编号</td>
							<td>
								<e:text name="uid"  readonly="true" defval="${dataMap.uid }"/> 
							</td>
						</tr>
						</c:if>
						<tr>
							<td>学号</td>
							<td>
								<e:text name="a101"  required="true" defval="${dataMap.a101 }"/>
							</td>
						</tr>
						<tr>
							<td>身份证号</td>
							<td>
								<e:text name="a102"  required="true" defval="${dataMap.a102 }"/>
							</td>
						</tr>
						<tr>
							<td>学生类别</td>
							<td>
								<e:text  name="a103" defval="${dataMap.a103 }"/>
							</td>
						</tr>
						<tr>
							<td>学院</td>
							<td>
								<e:text  name="a104" defval="${dataMap.a104 }"/>
							</td>
						</tr>
						<tr>
							<td>导师姓名</td>
							<td>
								<e:text  name="name2" defval="${dataMap.name2 }"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" name="next" class="btn btn-info" value="${empty dataMap.uid?'添加':'修改' }"
								formaction="<%=path%>/${empty dataMap.uid?'add':'modify' }Stu.html">
								<input type="submit" name="next" class="btn btn-info" value="返回"
								formaction="<%=path%>/queryStu.html"
								formnovalidate="formnovalidate">
							</td>
						</tr>
						<tr>
							<td>${msg }</td>
						</tr>
				    </tbody>
				</table>
				<input type="hidden" name="hid" value="${dataMap.uid }">
				<e:hidden name="qname"/>
				<e:hidden name="qa101"/>
				<e:hidden name="qa104"/>
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