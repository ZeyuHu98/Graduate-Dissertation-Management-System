<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="topLine.jsp" %>
<html>
<head>
<title>硕士学位论文评审答辩系统</title>
   <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
   <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/mycss.css">
   <script src="bootstrap-3.3.7-dist/js/jquery-3.3.1.min.js"></script>
   <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
   <script type="text/javascript">
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
   //激活批量删除按钮
   var count=0;	
   function onSelect(vState)
   {
 	  vState?count++:count--;
 	  var vDel=document.getElementById("del");
 	  vDel.disabled=(count==0);
   }
   //单一实例查询
   function onEdit(vuid)
   {
 	  var vform=document.getElementById("secQueryForm");
 	  vform.action="<%=path%>/secFindById.html?uid="+vuid;
 	  vform.submit();
   }
   //单一实例删除
   function onDel(vuid)
   {
 	 var vform = document.getElementById("secQueryForm");
 	 vform.action="<%=path%>/secDelById.html?uid="+vuid;
 	 vform.submit();
   }
   function toggle(targetId) 
   {
	   if (document.getElementById)
	   {
		   target=document.getElementById(targetId);
		   if ( target.style.display=="none") 
		   {
			   target.style.display=""; 
		   } 
		   else
		   {
			   target.style.display="none";
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
			<form id="secAddForm" class="secAddForm" action="<%=path%>/secAdd.html" method="post">
		      <table id="table" class="table">
		         <caption>秘书${empty param.uid?'添加':'修改'}</caption>
		         <tbody>
		         	<tr>
			            <td colspan="4">填写下列数据，若没有，则不填写</td>
			         </tr>
			         <tr>
			            <td>姓名</td>
			            <td>
			               <e:text name="name" required="true" autofocus="true" defval="${dataMap.name }"/>
			            </td>
			         </tr>
			         <tr>
			            <td>所属大学</td>
			            <td>
			               <e:text name="a601" required="true" defval="${dataMap.a601 }"/>
			            </td>
			         </tr>
			         <tr>
			            <td>研究领域</td>
			            <td>
			               <e:text name="a603" defval="${dataMap.a603 }"/>
			            </td>
			         </tr>
			         <tr>
			            <td>职称</td>
			            <td>
			               <e:select name="a604" value="教授:1,副教授:2,讲师:3" defval="${dataMap.a604 }"/>
			            </td>
			         </tr>
			         <tr>
			            <td>身份证号</td>
			            <td>
			               <e:text name="a605" required="true" defval="${dataMap.a605 }"/>
			            </td>
			         </tr>
			         <tr>
			            <td>手机号码</td>
			            <td>
			               <e:text name="a608" required="true" defval="${dataMap.a608 }"/>
			            </td>
			         </tr>
			         <tr>
			            <td>邮箱</td>
			            <td>
			               <e:email name="a609" required="true" defval="${dataMap.a609 }"/>
			            </td>
			         </tr>
			         <tr>
			            <td>其他角色</td>
			            <td>
			               <e:groupbox name="roles" value="导师:2,专家:7" defval="${dataMap.roles }"/>
			            </td>
			         </tr>
			         <tr>
			            <td colspan="2" align="center">
			               <input type="submit" name="next" class="btn btn-info" value="确认${empty param.uid?'添加':'修改'}"
			                formaction="<%=path%>/sec${empty param.uid?'Add':'Modify'}.html">
			               <input type="submit" name="next" value="查询秘书" class="btn btn-info" 
                            formaction="<%=path%>/secQuery.html" formnovalidate="formnovalidate">
			            </td>
			         </tr>
			         <tr>
				     	<td colspan="2">${msg}</td>
				     </tr>
		         </tbody>
		      </table>
		      <e:hidden name="uid" value="${dataMap.uid }"/>
              <e:hidden name="qname"/>
		      <e:hidden name="qa601"/>
		      <e:hidden name="qa603"/>
		      <e:hidden name="qa604"/>
		      <e:hidden name="qa605"/>
		      <e:hidden name="qa608"/>
		   </form>
		   <form enctype="multipart/form-data" method="post">
		   	<table class="table">
		   		<tbody>
			   		<tr>
			   			<td>
				   			<input type="file" class="btn btn-info" name="b106" accept="application/vnd.ms-excel">
				   			<input type="submit" name="next" value="Excel批量导入"
			                formaction="<%=path%>/SecAddByExcel.com" class="btn btn-info"
			                formnovalidate="formnovalidate">
		                </td>
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