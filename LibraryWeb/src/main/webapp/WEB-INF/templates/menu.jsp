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
			<a href="searchbooks.html" <c:if test="${search eq 'book'}" ><c:out value="class=active" /></c:if> >Livres</a>
		</li>
		
		<li>
			<a href="searchauthors.html" <c:if test="${search eq 'author'}" ><c:out value="class=active" /></c:if> >Auteurs</a>
		</li>
	</ul>
</div>
<!-- /Menu navigation -->
