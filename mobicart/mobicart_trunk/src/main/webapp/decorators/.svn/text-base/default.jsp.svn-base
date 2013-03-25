<%@ include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html>
<html lang="en" dir="ltr" class="no-js">
<head>

<title> <decorator:title /> </title>
<meta charset=utf-8>
<meta name="description" content="Mobile commerce app builder for the iPhone, iPad, Android and BlackBerry. Free and easy to use mcommerce shopping cart CMS." >
<meta name="keywords" content="App builder, m-commerce, mobile commerce, iPhone, iPad, Android, BlackBerry, Apple, IOS, app store, shopping cart, store builder, CMS, ecommerce, e-commerce, native apps, free, API, UK, USA" > 
<meta name="robots" content="all" > 
<meta name="Revisit-After" content="7 days" > 
<meta name="author" content="MobiCart" > 
<meta name="viewport" content="width=940">
<link rel="stylesheet" href="<spring:url value="/static/assets/css/main.css" />" media="screen, projection">
<!--[if gte IE 7]><link rel="stylesheet" href="<spring:url value="/static/assets/css/ie7.css" />" media="screen, projection"><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="<spring:url value="/static/assets/css/ie6.css" />" media="screen, projection"><![endif]-->
<link rel="stylesheet" href="<spring:url value="/static/assets/css/print.css" />" media="print">

<!-- JS -->
<script src="<spring:url value="/static/assets/js/core.js" />" > </script>

<!--[if IE 6]><script src="<spring:url value="/static/assets/js/belated.js" />" ></script><![endif]-->

<!--[if lt IE 9]>
<script src="<spring:url value="/static/assets/js/DOMAssistant-2.0.min.js" />" ></script>
<script src="<spring:url value="/static/assets/js/ie-css3.js"  />" ></script>
<![endif]-->
 
<!-- FAVICON -->
<link rel="shortcut icon" href="<spring:url value="/static/assets/img/favicon.ico" />" type="image/vnd.microsoft.icon">
<decorator:head></decorator:head>

<style>
.upgradeaccount { position:relative; top:30px; float:right;text-shadow: 0 1px 0 #000; font-weight:normal; }
.upgradeaccount a {color: #fff;padding:10px 15px; border-radius:20px; background:rgb(89,89,91);}
.upgradeaccount a:hover { text-decoration:none; background:rgb(0,158,219);
-webkit-transition : background-color .2s linear;
-moz-transition : background-color .2s linear;
-o-transition : background-color .2s linear;
transition : background-color .2s linear; }
</style>

</head>

<body id="page" >
<header role="banner" class="group">
<%-- <sec:authorize ifNotGranted="ROLE_USER">
<h2><a href="<spring:url value="/" />">MobiCart - Native m-commerce app builder for your ecommerce mobile platform</a></h2>
</sec:authorize> --%>
<sec:authorize ifAllGranted="ROLE_USER">
<h2><a href="<spring:url value="/account/dashboard" />">MobiCart - Native m-commerce app builder for your ecommerce mobile platform</a></h2>
</sec:authorize>
<nav role="navigation">
<%-- <sec:authorize ifNotGranted="ROLE_USER">
	<p>Read our <a href="<spring:url value="/blog" />">blog</a> check out the <a href="<spring:url value="/addons.html" />" >add-ons</a> or get <a href="http://support.mobi-cart.com/" >${user.defaultLabelKeyValuesMap['key.dashboard.header.help'] }</a>  </p>
</sec:authorize> --%>
<sec:authorize ifAllGranted="ROLE_USER">
<c:set var="user" value="${sessionScope.user}" />
<spring:url var="homeUrl" value="/account/dashboard" />
	<%-- <p>Signed in as <a href="<spring:url value="/account/detail" />" >${user.sFirstName}</a> <a href="<spring:url value="/j_spring_security_logout" />">${user.defaultLabelKeyValuesMap['key.dashboard.header.signout']}</a> &#124; <a href="http://support.mobi-cart.com/"  target="_blank" >${user.defaultLabelKeyValuesMap['key.dashboard.header.help'] }</a></p> --%>
   <p>${user.defaultLabelKeyValuesMap['key.dashboard.header.signin'] }<a href="<spring:url value="/account/detail" />" >${user.defaultLabelKeyValuesMap['key.dashboard.header.myaccount'] }</a> <a href="<spring:url value="/j_spring_security_logout" />">${user.defaultLabelKeyValuesMap['key.dashboard.header.signout']}</a> &#124; <a href="http://support.mobi-cart.com/"  target="_blank" >${user.defaultLabelKeyValuesMap['key.dashboard.header.help'] }</a></p>


</sec:authorize>
</nav>
<c:if test="${plans.free eq true}">
<p class="upgradeaccount"><a href="../../account/detail#tabs-5" class="csstransitions">Upgrade your account</a></p>
</c:if>
</header>
<decorator:body/>
 
<footer role="contentinfo">
<nav class="supp group">
<p>
<%--<sec:authorize ifNotGranted="ROLE_USER">
 <a href="<spring:url value="/features.html" />">Features</a> &#8226;
<a href="<spring:url value="/faqs.html" />">FAQs</a> &#8226;
<a href="<spring:url value="/terms.html" />">Terms</a> &#8226;
<a href="<spring:url value="/press.html" />">Press</a> &#8226;
<a href="<spring:url value="/contact.html" />">Contact</a> &#8226;
<a href="<spring:url value="/docs/api/index.html" />" target="_blank" >API</a> &#8226;
<a href="<spring:url value="/account/login" />">Login</a> 
</sec:authorize>--%>

<sec:authorize ifAllGranted="ROLE_USER">
<a href="<spring:url value='/' />"  target="_blank" >${user.defaultLabelKeyValuesMap['key.application.pages.home'] }</a> &#8226;
<a href="<spring:url value="http://support.mobi-cart.com/categories/20016957-documentation"/>" target="_blank">${user.defaultLabelKeyValuesMap['key.application.pages.documentation'] }</a> &#8226;
<a href="<spring:url value="/docs/api/index.html" />" target="_blank" >${user.defaultLabelKeyValuesMap['key.application.pages.api'] }</a> &#8226;
<!-- <a href="http://support.mobi-cart.com/categories/20017588-discussions" onclick="feedback_widget.show()" title="Opens external window" target="_blank">Ask a question?</a> -->
<a href="http://support.mobi-cart.com/categories/20017588-discussions"   title="Opens external window" target="_blank">${user.defaultLabelKeyValuesMap['key.application.pages.askaqes'] }</a>
</sec:authorize>
</p>
</nav>

<sec:authorize ifAllGranted="ROLE_USER">

<p> 
${user.defaultLabelKeyValuesMap['key.application.copyrights'] }</p> 
</sec:authorize>

<%-- <sec:authorize ifNotGranted="ROLE_USER">
<p><a href="<spring:url value="/credits.html" />" >Credits</a>. 
All text and design is &#169; copyright. All rights reserved.</p> 
</sec:authorize> --%>
</footer>

<script type="text/javascript">
jQuery(document).ready(function() {
	jQuery('[placeholder]').focus(function() {
		  var input = jQuery(this);
		  if (input.val() == input.attr('placeholder')) {
		    input.val('');
		    input.removeClass('placeholder');
		  }
		}).blur(function() {
		  var input = jQuery(this);
		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));
		  }
		}).blur().parents('form').submit(function() {
			jQuery(this).find('[placeholder]').each(function() {
		    var input = jQuery(this);
		    if (input.val() == input.attr('placeholder')) {
		      input.val('');
		    }
		  })
		});
});


function defunctImageHandler(image){
	image.src="<spring:url value='/static/assets/img/warning.jpg' />";
}

function defunctLogoImageHandler(image){
	image.src="<spring:url value='/static/assets/img/logo-220.png' />";
}

function defunctSmallLogoImageHandler(image){
	image.src="<spring:url value='/static/assets/img/logo-120.png' />";
}


function defunctProductImageHandler(image){
	image.src="<spring:url value='/static/assets/img/prod-warning.jpg' />";
}


//Error tracking function
//err: a string or an Error object
function trackError( err )
{
 var msg = 'Unknown error';
 if ( err.message )
 {
     msg = err.message;
 }
 else if ( typeof err == 'string' || typeof err == 'String' )
 {
     msg = err;
 }
 console.log( 'Tracking error: ' + msg );
};

//Attach trackError to window's onerror event;
//use onerror instead of $(window).error( ... ) in order to get url and lineNo
window.onerror = function( msg, url, lineNo ) 
{ 
 trackError( [ msg, url, lineNo ].join('; ') );
 // return true to stop error from propogating, false to allow propogation
 return true;
};

</script>

<sec:authorize ifAllGranted="ROLE_USER">
<script type="text/javascript" charset="utf-8">
var is_ssl = ("https:" == document.location.protocol);
var asset_host = is_ssl ? "https://s3.amazonaws.com/getsatisfaction.com/" : "http://s3.amazonaws.com/getsatisfaction.com/";
document.write(unescape("%3Cscript src='" + asset_host + "javascripts/feedback-v2.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript" charset="utf-8">
var feedback_widget_options = {};
feedback_widget_options.display = "overlay";  
feedback_widget_options.company = "mobicart";
feedback_widget_options.placement = "hidden";
feedback_widget_options.color = "#222";
feedback_widget_options.style = "question";
var feedback_widget = new GSFN.feedback_widget(feedback_widget_options);
</script>
</sec:authorize>
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-17710648-1']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
</body>
</html>