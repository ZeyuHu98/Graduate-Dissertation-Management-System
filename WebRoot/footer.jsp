<%@ page language="java" pageEncoding="GBK"%>
<script type="text/javascript">
$(document).ready(function() {
	autoFooterHeight();
})
// 解决底部自动导航的问题
function autoFooterHeight() {
	// 获取内容的高度
	var bodyHeight = $("body").height();
	// 获取底部导航的高度
	var navHeight = $(".navbar").height();
	// 获取显示屏的高度
	var iHeight = document.documentElement.clientHeight || document.body.clientHeight;
	// 如果内容的高度大于（窗口的高度 - 导航的高度）,移除属性样式
	if (bodyHeight > (iHeight - navHeight)) {
		$("#footer").removeClass("navbar-fixed-bottom");
	}
}
</script>
<div style="height: 50px"><table>&#12288;</table></div>
<nav class="navbar navbar-default navbar-fixed-bottom footer">
	<p class="footer-P">联系我们：&#12288;</p>
	<img alt="" src="./images/qq.png" class="footer-img" width="17px" height="17px">
	<p class="footer-P">QQ:4543446&#12288;</p>
	<img alt="" src="./images/mail.png" class="footer-img" width="17px" height="17px">
	<p class="footer-P">邮箱：82648912@ifa.com</p>
</nav>