<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ include file="topLine.jsp" %>
<html>
<head>
<title>硕士学位论文评审答辩系统</title>
   <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
   <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/mycss.css">
   <script src="bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
   <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
   <script>
   window.onload=function()
   {
       var item= document.getElementById("table");
       var tbody=item.getElementsByTagName("tbody")[0];
       var trs= tbody.getElementsByTagName("tr");
       for(var i=0;i<trs.length;i++){  
           if(i%4==1){  
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
			<div id="top"></div>
			<jsp:include page="menu.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
			<div class="tp" id="tp">
				<div id="tp1"><a href="#top"><img alt="" src="./images/up.png" class="footer-img" width="50px" height="50px"></a></div>
				<div id="tp2"><a href="#under"><img alt="" src="./images/down.png" class="footer-img" width="50px" height="50px"></a></div>
			</div>
		</div>
		<div class="col-md-10 column">
			<form id="outputExcelForm" class="outputExcelForm" method="post" action="<%=path%>/FinancialAdd.html">
		      <table id="table" class="table">
		      	<caption class="outputExcel-caption">财务报表
		      		<div class="outputExcel-list">
		      			<button type="button" class="btn btn-info" id="dropdownMenu1" data-toggle="dropdown">导出
					        <span class="caret"></span>
					    </button>
					    <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
					    	<li>
					    		<input type="submit" class="btn btn-info" name="next" value="财务报表总表"
					  			formaction="<%=path%>/financialExcel.xls"> 
					    	</li>
					        <li role="presentation">
					            <input type="submit" class="btn btn-info" name="next" value="校内专家报表"
					    		formaction="<%=path%>/financialExcelForInner.xls">
					        </li>
					        <li role="presentation">
					            <input type="submit" class="btn btn-info" name="next" value="校外专家报表"
				       			formaction="<%=path%>/financialExcelForOuter.xls">
					        </li>
					    </ul>
		      		</div>
		      	</caption>
		      	<thead>
		      		<tr>
		      			<th>序号</th>
		      			<th>学生学号</th>
		      			<th>学生姓名</th>
		      			<th>导师</th>
		      			<th>专家</th>
		      			<th>职称</th>
		      			<th>单位</th>
		      			<th>酬金</th>
		      		</tr>
		      	</thead>
		      	<tbody>
		      		<c:choose>
				        <c:when test="${dataList!=null }">
				        <!-- 显示查询到的数据 -->
				           <c:forEach items="${dataList }" var="dataMap" varStatus="vs">
				              <tr>
				                 <td>${vs.count }</td>
			                     <td>${dataMap.b603 }</td>
							     <td>${dataMap.b602 }</td>
							     <td>${dataMap.b604 }</td>
							     <td>${dataMap.name}</td>
							     <td>${dataMap.fvalue }</td>
							     <td>${dataMap.a601 }</td>
							     <td>${dataMap.money }</td>
				              </tr>
				           </c:forEach>
				              
				           <!-- 补充空行 -->
				           <c:forEach begin="${fn:length(dataList)+1 }" step="1" end="15">
				              <tr>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
					          </tr>
				           </c:forEach>
				        </c:when>
				        <c:otherwise>
				           <c:forEach begin="1" step="1" end="15">
				              <tr>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				                 <td></td>
				              </tr>
				           </c:forEach>
				        </c:otherwise>
				    </c:choose>
				    <tr>
						<td colspan="8">
						    <input type="submit" class="btn btn-info" name="next" value="生成财务数据">
						    <input type="submit" class="btn btn-info" name="next" value="查询" formaction="<%=path%>/FinancialQuery.html">
						</td>
					</tr>
					<tr>
						<td colspan="8">${msg }</td>
					</tr>
		      	</tbody>
		      </table>
		   </form>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
		<div id="under"></div>
		<jsp:include page="footer.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
		</div>
	</div>
</div>
</body>
</html>