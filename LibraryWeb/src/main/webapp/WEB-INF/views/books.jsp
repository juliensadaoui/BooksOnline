<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Liste des livres</h1>
    <p>
		Liste des livres correspondant aux critères de votre recherche
    </p>
    
    <div class="search_resume">
		<span><strong>Vous recherchez : </strong>${criteria}</span>
	</div>

	<div id="books">
  		<table class="books">
        	<thead>
      			<tr class="header">
                	<th></th>
                	<th>ISBN</th>
                	<th>Titre</th>
                	<th>Genre</th>
                	<th>Auteur</th>
                	<th></th>
            	</tr>
    		</thead>
    		<tbody>
				<tr>
                    <td class="headerline"></td>
                    <td class="headerline"></td>
                    <td class="headerline"></td>
                    <td class="headerline"></td>
                    <td class="headerline"></td>
                    <td class="headerline"></td>
              	</tr>
		
			<c:set var="row" value="1"/>
			<c:forEach var="book" items="${books}" > 
				<c:if test="${row%2 eq 0}">
					<tr class="even">
				</c:if>
				<c:if test="${row%2 eq 1}">
					<tr class="odd">
				</c:if>
				<c:set var="row" value="${row + 1}" />	
				<td class="image">
	              	<c:url value="/resources/images/livre.png" var="url_book" />
	              	<img width="80" height="80" border="0" title="" alt="" src="${url_book}">
	          	</td>	
				<td class="isbn">${book.isbn}</td>
				<td class="title">${book.name}</td>
				<td class="genre">${book.genre}</td>
				<td class="author">${book.authorName}</td>
				<td class="rent">
		             <form action="home.html" method="post">
		                <fieldset>
			                <c:url value="/resources/images/rent.png" var="rent_book" />
		                    <input type="image" src="${rent_book}" value="">
		                    </fieldset>
		            </form>
		        </td>
		        </tr>
			</c:forEach>
         </tbody>
  </table>
</div>
</body>
</html>