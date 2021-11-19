<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="topLine.jsp"%>
<html>
<title>硕士学位论文评审答辩系统</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/mycss.css">
	<script src="bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/echarts.min.js"></script>
</head>

<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column">
			<jsp:include page="menu.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
		</div>
		<div class="col-md-10 column">
			<form id="dicInfoQueryForm" action="<%=path%>/dicInfoQuery.html" method="post">
			<!-- 数据迭代区域 -->
				<table class="table">
					<caption>评审信息统计表</caption>
					<tbody>
						<tr>
							<td>
								<div id="b202" style="width: 1220px; height: 768px;"></div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<script type="text/javascript">
		      // 基于准备好的dom，初始化echarts实例
		      var myChart = echarts.init(document.getElementById('b202'));
		
		      // 指定图表的配置项和数据
		      var option = {
		    		  title:{
							text:"评审等级统计",
							x:'center'
						},  
		      
		    		  tooltip: {
		  		        trigger: 'item',
		  		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		  		    },
		  		    legend: {
		  		        orient: 'vertical',
		  		        x: 'left',
		  		        data:[
			                      <c:forEach items="${b202List}" var="U">
			                      "${U.b202}",
			                      </c:forEach>
		  		        ]
		  		    },
		  		  series : [
		  		        {
		  		            name: '检查结果',
		  		            type: 'pie',
		  		            radius: '55%',
		  		            data:[
		  	                      <c:forEach items="${b202List}" var="U">
		  	                      {value:<fmt:parseNumber type="number" value="${U.count}" />,name:"${U.b202}"},
		  	                      </c:forEach>
		  		            ]
		  		        }
		  		    ]
		      };
		      // 使用刚指定的配置项和数据显示图表。
		      myChart.setOption(option);
		    </script>
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