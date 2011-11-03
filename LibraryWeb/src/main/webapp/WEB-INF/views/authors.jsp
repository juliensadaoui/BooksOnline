<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des auteurs</title>
</head>
<body>
    <h1>Liste des auteurs</h1>
    <p>
		Liste des auteurs correspondant aux critères de votre recherche
    </p>
    
    <div class="search_resume">
		<span><strong>Vous recherchez : </strong>${criteria}</span>
	</div>

    <div id="container_author">
    	<div class="title">Résultat pour les auteurs
    		<span class="result">${ fn:length(authors) }</span>
    	</div>
    	<div class="content">
    		<ul>
    		<c:forEach var="author" items="${authors}" > 
				<li>
					<span>
						${author.firstName}, ${author.lastName}
					</span>
				</li>
    		</c:forEach>
    		</ul>
    	</div>
    </div>
</body>
</html>