<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="editorial">
	    <div class="homepage_title">
	        <h2>Editorial</h2>
	    </div>
	    <c:url value="/resources/images/livre.png" var="url_logo" />
	    <img src="${url_logo}" width="300" height="150" border="0" class="editorial_img" alt="" title="" />
	
	    <div class="editorial_details">
	        <div class="editorial_title">Librairie - La librairie en ligne des passionnés de lecture </div>
	
	        <div class="editorial_text">
	        	Librairie est une librairie en ligne permettant de louer des livres sur internet et de les revevoir par la poste. 
	        	L&rsquo;inscription est simple, rapide et gratuite. Acc&eacute;dez gratuitement &agrave; de nombreux livres de diff&eacute;rents genres.<br />
				<br />
	            Librairie.com vous présente aussi l&rsquo;essentiel de l&rsquo;actualité littéraire avec des chroniques, des interviews d&rsquo;auteurs, des bibliothèques th&eacute;matiques.  </div>
	    </div>
	</div>
</body>
</html>