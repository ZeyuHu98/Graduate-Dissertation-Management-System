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
		var role= document.getElementById("roles").value;
		if(role.indexOf("4")==-1)
		{
			$("input:checkbox[value='4']").attr('checked',false);
		}
		if(role.indexOf("5")==-1)
		{
			$("input:checkbox[value='5']").attr('checked',false);
		}
		if(role.indexOf("2")==-1)
		{
			$("input:checkbox[value='2']").attr('checked',false);
		}
		if(role.indexOf("7")==-1)
		{
			$("input:checkbox[value='7']").attr('checked',false);
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
	function onSelect()
	{
		if($("input[class='roles']:checked").length == 0)
		{
		     return false;
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
			<form action="<%=path%>/addExp.html" method="post">
				<table class="table" id="table">
					<caption>专家${empty dataMap.uid?'添加':'修改' }</caption>
					<tbody class="addStaff-tbody">
						<tr>
							<td></td>
							<td></td>
							<td>姓名</td>
							<td>
								<e:text name="name" required="true" autofocus="true" defval="${dataMap.name }"/> 
							</td>
							<td></td>
						</tr>
						<c:if test="${!empty dataMap.uid }">
						<tr>
							<td></td>
							<td></td>
							<td>编号</td>
							<td>
								<e:text name="uid"  readonly="true" defval="${dataMap.uid }"/> 
							</td>
							<td></td>
						</tr>
						</c:if>
						<tr>
							<td></td>
							<td></td>
							<td>所属学校</td>
							<td>
								<e:text name="a601"  required="true" defval="${dataMap.a601 }"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>专家类别</td>
							<td>
								<!-- 按照name属性自动分组,组内单选 -->
								<e:radio name="a602" value="校内:1,校外:2" defval="${dataMap.a602 }"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>研究领域</td>
							<td>
								<e:text  name="a603" defval="${dataMap.a603 }"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>职称</td>
							<td>
								<e:radio name="a604" value="教授:1,副教授:2,讲师:3" defval="${dataMap.a604 }" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>身份证号</td>
							<td>
								<e:text name="a605"  required="true" defval="${dataMap.a605 }"/> 
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>银行账号</td>
							<td>
								<e:text name="a606"  required="true" defval="${dataMap.a606 }"/> 
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>开户银行</td>
							<td>
								<e:text name="a607" required="true" defval="${dataMap.a607 }"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>手机号</td>
							<td>
								<e:text name="a608" required="true" defval="${dataMap.a608 }"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>邮箱号</td>
							<td>
								<e:email name="a609" required="true" defval="${dataMap.a609 }" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>专家角色</td>
							<td>
								<input type="checkbox" checked="checked" class="roles" name="roles" onclick="return onSelect();" id="role1" value="2"/>导师
								<input type="checkbox" checked="checked" class="roles" name="roles" onclick="return onSelect();" id="role2" value="7"/>专家
								<input type="checkbox" checked="checked" class="roles" name="roles" id="role3" onclick="return false;" value="4"/>评审专家
								<input type="checkbox" checked="checked" class="roles" name="roles" id="role4" onclick="return false;" value="5"/>答辩专家
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="5">
								<input type="submit" name="next" value="${empty dataMap.uid?'添加':'修改' }" class="btn btn-info"
								formaction="<%=path%>/${empty dataMap.uid?'add':'modify' }Staff.html">
								<input type="submit" name="next" value="返回" class="btn btn-info"
								formaction="<%=path%>/queryStaff.html" formnovalidate="formnovalidate">
							</td>
						</tr>
						<tr><td colspan="5">${msg }</td></tr>
					</tbody>
				</table>
				<input type="hidden" name="hid" value="${dataMap.uid }">
				<input type="hidden" name="roleList" id="roles" value="${dataMap.roles }">
				<e:hidden name="qname"/>
				<e:hidden name="qa602"/>
				<e:hidden name="qa601"/>
				<e:hidden name="qa605"/>
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