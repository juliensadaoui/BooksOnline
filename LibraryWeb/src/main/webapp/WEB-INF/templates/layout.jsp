<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <title><tiles:insertAttribute name="title" ignore="true" /></title> --%>
    <tiles:useAttribute name="links"/>  
    <c:forEach var="href" items="${links}"> 
    	<c:url value="${href}" var="url" />
        <link type="text/css" rel="stylesheet" href="${url}" />
    </c:forEach>   
</head>
<body>
	<div id="wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		<div id="main" class="front">
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />

	</div>
</body>
</html>
