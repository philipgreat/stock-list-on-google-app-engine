<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����Ӧ�ó�����</title>
<script src="../scripts/jquery-1.9.1.js" type="text/javascript"></script>
<script src="../scripts/common.js" type="text/javascript"></script>
<style>
.toolbar {
	width: 100%; height: 40px; 	float:left; 	font-size: 20px; text-align: left; 	padding-left: 10px;
	padding-top: 10px; background: #111111; 	overflow:auto; 	border: 1px solid black;
	color: white; letter-spacing:2px
}
.menu {
	width: 20%; 	height: 630px; 	float:left; 	font-size: 20px; text-align: left; 	padding-left: 30px;
	padding-top: 30px; 	background: #eeeeee; 	overflow:auto;
	margin: 0; 
}
.content {
	width: 70%; 	height: 630px; 	text-align: center; 	
	#border: 1px solid grey; 
	#padding-top: 30px;
	float:right; 	background: #fffff;
	padding-top: 10px; 
}

body{
	margin: 0; padding: 0;
}

</style>

<script>


	$(function() {
		var cache = {};

		$(".action").click(function() {
			
					//alert( );
					//$("#content").text($(this).attr("href"));
					//$("#content").text(event.target+"/"+$(this).attr("href"));
					var reqURI = "/naf/"
							+ $(this).attr("href").substring(1) + "/";
					//$("#content").text(reqURI);
					fillResult(reqURI,"#content");
		});

	});

	$(document).ready(function() {
		//alert($(location).attr('href'));
		var currentURL=$(location).attr('href');
		var index=currentURL.indexOf("#");
		if(index<0){
			return;
		}		
		reqURI = "/naf/"	+ currentURL.substring(index+1) + "/";		
		fillResult(reqURI,"#content");
		
	});
</script>
</head>

<body>
	<div class="toolbar" >Spring|Neclues|JNDI</div>
	<div class="menu" >
		  <a href="./" >ˢ��</a><br/> 
		  <a href="#allParameters/" class="action">Get All Parameters</a><br/>
          <c:forEach var="method" items="${result.items}">  <a href="#${method.name}" class="action">${method.name}</a><br/></c:forEach>
	</div>
	<div class="content" id="content">Home Info</div>
</body>
</html>
