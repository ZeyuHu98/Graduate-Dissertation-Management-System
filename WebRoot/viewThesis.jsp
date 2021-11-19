<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="topLine.jsp"%>
<html>
<head>
<title>硕士学位论文评审答辩系统</title>
</head>

<body>
	${msg}
	<script src="js/pdfobject.min.js"></script>
	<script>
		PDFObject.embed("<%=path%>/upload/${dataMap.b106}");
	</script>
</body>
</html>