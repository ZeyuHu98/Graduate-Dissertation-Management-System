<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="topLine.jsp"%>
<html>
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
				<table class="table" id="table">
					<caption>答辩信息统计</caption>
					<tbody>
						<tr>
							<td>
								<div id="b107" style="width: 406px; height: 400px;"></div>
							</td>
							<td>
								<div id="b108" style="width: 408px; height: 400px;"></div>
							</td>
							<td>
								<div id="b109" style="width: 406px; height: 400px;"></div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<script type="text/javascript">
			// 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('b107'));
			// 指定图表的配置项和数据
		      var option = {
						title:{
							text:"导师检查结果",
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
			                      <c:forEach items="${b107List}" var="U">
			                      "${U.b107}",
			                      </c:forEach>
		    		        ]
		    		    },
		    		  series : [
		    		        {
		    		            name: '检查结果',
		    		            type: 'pie',
		    		            radius: '55%',
		    		            data:[
		    	                      <c:forEach items="${b107List}" var="U">
		    	                      {value:<fmt:parseNumber type="number" value="${U.count}" />,name:"${U.b107}"},
		    	                      </c:forEach>
		    		            ]
		    		        }
		    		    ]
		      };
		
		      // 使用刚指定的配置项和数据显示图表。
		      myChart.setOption(option);
		    </script>
			<script type="text/javascript">
		      // 基于准备好的dom，初始化echarts实例
		      var myChart = echarts.init(document.getElementById('b108'));
		
		      // 指定图表的配置项和数据
		      var option = {
		    		  title:{
							text:"专家评审结果",
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
			                      <c:forEach items="${b108List}" var="U">
			                      "${U.b108}",
			                      </c:forEach>
		  		        ]
		  		    },
		  		  series : [
		  		        {
		  		            name: '检查结果',
		  		            type: 'pie',
		  		            radius: '55%',
		  		            data:[
		  	                      <c:forEach items="${b108List}" var="U">
		  	                      {value:<fmt:parseNumber type="number" value="${U.count}" />,name:"${U.b108}"},
		  	                      </c:forEach>
		  		            ]
		  		        }
		  		    ]
		      };
		
		      // 使用刚指定的配置项和数据显示图表。
		      myChart.setOption(option);
		    </script>
		
			<script type="text/javascript">
		      // 基于准备好的dom，初始化echarts实例
		      var myChart = echarts.init(document.getElementById('b109'));
		
		      // 指定图表的配置项和数据
		      var option = {
		    		  title:{
							text:"专家答辩结果",
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
			                      <c:forEach items="${b109List}" var="U">
			                      "${U.b109}",
			                      </c:forEach>
		  		        ]
		  		    },
		  		  series : [
		  		        {
		  		            name: '检查结果',
		  		            type: 'pie',
		  		            radius: '55%',
		  		            data:[
		  	                      <c:forEach items="${b109List}" var="U">
		  	                      {value:<fmt:parseNumber type="number" value="${U.count}" />,name:"${U.b109}"},
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