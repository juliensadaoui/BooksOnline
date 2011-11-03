<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des locations</title>
</head>
<body>
    <h1>Liste des locations</h1>
    <p>
		Liste de l'historique de vos locations
    </p>
    
    <div id="books">
  		<table class="books">
        	<thead>
      			<tr class="header">
                	<th>ISBN</th>
                	<th>Titre du livre</th>
                	<th>Date de location</th>
                	<th>Date de retour</th>
            	</tr>
    		</thead>
    		<tbody>
				<tr>
                    <td class="headerline"></td>
                    <td class="headerline"></td>
                    <td class="headerline"></td>
                    <td class="headerline"></td>
              	</tr>
              	
              				<c:set var="row" value="1"/>
				<c:forEach var="rent" items="${rents}" > 
				<c:if test="${row%2 eq 0}">
					<tr class="even">
				</c:if>
				<c:if test="${row%2 eq 1}">
					<tr class="odd">
				</c:if>
				<c:set var="row" value="${row + 1}" />	
					<td class="isbn">${rent.isbn}</td>
					<td class="title">${rent.bookName}</td>
					<td class="genre">${rent.startDate}</td>
					<td class="author">${rent.endDate}</td>
				</tr>
				</c:forEach>
			</tbody>
   		 </table>
    </div>
   
</body>
</html>