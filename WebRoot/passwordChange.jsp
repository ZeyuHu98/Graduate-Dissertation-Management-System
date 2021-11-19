<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ include file="topLine.jsp" %>
<html>
<head>
<title>硕士学位论文评审答辩系统</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/mycss.css">
	<script src="bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function checkpwd() {
		var p1 = document.getElementById("upassword1").value;
		var p2 = document.getElementById("upassword2").value;
		var p3 = document.getElementById("upassword3").value;
		if (p1 == "") {
			document.getElementById("msg1").innerHTML = "请输入原密码";
			document.getElementById("upassword1").focus();
			return false;
		}
		if (p2 != p3) {
			document.getElementById("msg2").innerHTML = "两次输入密码不一致，请重新输入";
			return false;
		}
	}
	//循环更改表格tr标签的背景颜色
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
			<jsp:include page="menu.jsp" flush="true"><jsp:param value="" name=""/></jsp:include>
		</div>
		<div class="col-md-10 column">
			<form action="<%=path%>/submit_Password.html" method="post"
				onsubmit="return checkpwd();">
				<table class="table" id="table">
					<caption>修改密码</caption>
					<tbody class="password-tbody">
						<tr><td colspan="4"></td></tr>
						<tr>
							<td></td>
							<td>原密码</td>
							<td><input type="password" id="upassword1" name="upassword1"
								required="required" autofocus="autofocus"/>
								<div id="msg1" style="color: red;"></div></td>
							<td></td>
						</tr>
						<tr><td colspan="4"></td></tr>	
						<tr>
							<td></td>
							<td>新的密码</td>
							<td><input type="password" id="upassword2" name="upassword2"
								required="required" autofocus="autofocus" /></td>
							<td></td>
						</tr>
						<tr><td colspan="4"></td></tr>
						<tr>
							<td></td>
							<td>确认密码</td>
							<td><input type="password" id="upassword3" name="upassword3"
								required="required" autofocus="autofocus" /></td>
							<td></td>
						</tr>
						<tr><td colspan="4"><div id="msg2" style="color: red;"></div></td></tr>
						<tr>
							<td colspan="4" align="center"><button
									class="btn btn-info"
									type="submit">确认提交</button></td>
						</tr>
						<tr>
							<td colspan="4">${msg}</td>
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