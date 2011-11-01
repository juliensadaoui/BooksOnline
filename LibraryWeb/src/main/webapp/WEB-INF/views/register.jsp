<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Créer un compte client</h1>
    <p>
        L'inscription sur Librairie.com permet d'accéder à votre compte personnel et de bénéficier à nombreux services:
            consultation de vos informations personnelles, location de livres et magazines d'actualités,
            consultation de l'historique de vos locations.

        <br /><br />Les champs avec le caractère * sont obligatoires.
    </p>
    
    <form:form modelAttribute="userBean" focus="firstName">
    <div id="container_account">
    	<div class="title">Inscrivez vous c’est gratuit !</div>
    	<div class="content">
			<ul>
				<li>
					<span>
						<label><fmt:message key="register.user.firstname"/></label>
					</span>
					<form:input path="firstName" size="15" maxlength="60"/>&#160;
					<font color="red"><form:errors path="firstName"/></font>
				</li>
				<li>
					<span>
						<label><fmt:message key="register.user.password"/></label>
					</span>
					<form:input path="password" size="15" maxlength="60"/>&#160;
					<font color="red"><form:errors path="password"/></font>
				</li>
				<li>
					<span>
						<label><fmt:message key="register.user.verifypassword"/></label>
					</span>
					<form:input path="verifyPassword" size="15" maxlength="60"/>&#160;
					<font color="red"><form:errors path="verifyPassword"/></font>
				</li>
				<li>
					<span>
						<label><fmt:message key="register.user.firstname"/></label>
					</span>
					<form:input path="firstName" size="15" maxlength="60"/>&#160;
					<font color="red"><form:errors path="firstName"/></font>
				</li>
				<li>
					<span>
						<label><fmt:message key="register.user.lastname"/></label>
					</span>
					<form:input path="lastName" size="15" maxlength="60"/>&#160;
					<font color="red"><form:errors path="lastName"/></font>
				</li>
				<li>
				</li>
			</ul>
		</div>
    </div>
    </form:form>


</body>
</html>