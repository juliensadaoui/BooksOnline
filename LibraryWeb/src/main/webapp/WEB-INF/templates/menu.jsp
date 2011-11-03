<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
		
		<security:authorize ifAnyGranted="ROLE_USER">
			<li>
				<a href="showaccount.html" <c:if test="${account eq 'info'}" ><c:out value="class=active" /></c:if> >Mes informations</a>
			</li>
			
			<li>
				<a href="showrentbooks.html" <c:if test="${account eq 'rent'}" ><c:out value="class=active" /></c:if> >Mes locations</a>
			</li>	
		</security:authorize>
	</ul>
</div>
<!-- /Menu navigation -->
