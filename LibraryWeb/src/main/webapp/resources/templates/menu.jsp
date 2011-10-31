<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Menu navigation -->
<div id="nav">

	<ul>
		<li class="first">
<%-- 			<c:url value="/resources/views/home.jsp" var="url_home" /> --%>
<%-- 			<a href="${url_home}" title="Home">Home </a>       --%>
			<a href="home.html" title="Home">Home </a>                
		</li>
		
		<li>
			<a href="/livres">Livres</a>
		</li>
		
		<li>
			<a href="/auteurs" class="active active">Auteurs</a>
		</li>
	</ul>
</div>
<!-- /Menu navigation -->
