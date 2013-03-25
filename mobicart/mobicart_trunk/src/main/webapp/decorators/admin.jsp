<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html>
<html lang="en" dir="ltr" class="no-js">
<head>
<title> MobiCart Admin | <decorator:title/> </title>
<meta charset=utf-8>
<meta name="viewport" content="width=940">
<link rel="stylesheet" href="<spring:url value="/static/assets/css/main.css" /> " media="screen, projection">
<!--[if gte IE 7]><link rel="stylesheet" href="<spring:url value="/static/assets/css/ie7.css" />" media="screen, projection"><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="<spring:url value="/static/assets/css/ie6.css" />" media="screen, projection"><![endif]-->
<link rel="stylesheet" href="<spring:url value="/static/assets/css/print.css" />" media="print">
<!-- JS -->

<script src="<spring:url value="/static/assets/js/jquery-1.4.2.min.js"/>" ></script>
<script src="<spring:url value="/static/assets/js/modernizr-1.5.min.js" />" ></script>
<script src="<spring:url value="/static/assets/js/jquery.validate.pack.js" />" ></script>
<script src="<spring:url value="/static/assets/js/onload.js" />" ></script>
<!--[if IE 6]><script src="<spring:url value="/static/assets/js/belated.js" />" ></script><![endif]-->
<!--[if lt IE 9]>
<script src="<spring:url value="/static/assets/js/DOMAssistant-2.0.min.js" />" ></script>
<script src="<spring:url value="/static/assets/js/ie-css3.js"  />" ></script>
<![endif]-->

<!-- FAVICON -->
<link rel="shortcut icon" href="<spring:url value="/static/assets/img/favicon.ico" />" type="image/vnd.microsoft.icon">
<script type="text/javascript">
jQuery(document).ready(function() {
	jQuery('header h1').click(function() {
		document.location.href="<spring:url value='/' />";
	});
	 jQuery("a.close").click(function() {
         jQuery(this).parent().parent('.dismiss').fadeOut(800);
     });
});
</script>
<decorator:head></decorator:head>
</head>
<body id="page">
<header role="banner" class="group">
<h1>MobiCart</h1>
<nav role="navigation">
<sec:authorize ifAllGranted="ROLE_ADMIN">
	<p> Welcome Admin <a href="<spring:url value="/j_spring_security_logout" />">Sign out</a></p>
</sec:authorize>
</nav>
</header>

<decorator:body/>

<footer role="contentinfo">
<nav class="supp group">
<p>
<a href="<spring:url value="/" />" target="_blank">MobiCart home</a> &#8226;
<a href="<spring:url value="/faqs" />" target="_blank">FAQs</a> &#8226;
<a href="http://getsatisfaction.com/mobicart" title="Opens external site"  target="_blank">Support</a> &#8226;
<a href="<spring:url value="/terms" />"  target="_blank">Terms</a> &#8226;
<a href="<spring:url value="/contact" />"  target="_blank">Contact</a> 
</p>
</nav>

<sec:authorize ifNotGranted="ROLE_ADMIN">
<p><a href="<spring:url value="/credits" />" >Credits</a>. 
All text and design is &#169; copyright. All rights reserved.</p> 
</sec:authorize>
</footer>
	
</body>
</html>