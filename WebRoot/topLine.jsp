<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://org.wangxg/jsp/extl" prefix="e"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<div class="container">
	<nav class="navbar navbar-default" role="navigation">
	    <div class="navbar-header">
		    <a class="navbar-brand" href="#">硕士学位论文评审答辩系统</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
			    <li class="dropdown">
					<a disable="true" class="text-center">${cname }</a>
				</li>
				<li class="dropdown">
					<a href="out">${empty cuid?'未登录':'退出登录' }</a>
				</li>
			</ul>
		</div>
	</nav>
</div>