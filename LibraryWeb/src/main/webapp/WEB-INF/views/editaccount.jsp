<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modification de votre compte utilisateur</title>
</head>
<body>
    <h1>Modifier votre compte utilisateur</h1>
    <p>
        Cette interface vous permet de modifier toutes les informations de votre compte. 
		Vous pouvez modifier votre mot de passe ainsi que votre nom et votre prénom <br /><br />
		La modification de votre mot de passe nécessite une reconnexion.
        <br /><br />Les champs avec le caractère * sont obligatoires.
    </p>
    
    <form:form modelAttribute="userBean">
    <div id="container_account">
    	<div class="title">Informations personnelles</div>
    	<div class="content">
			<ul>
				<li>
					<span>
						<label><fmt:message key="register.user.password"/></label>
					</span>
					<form:password path="password" size="15" maxlength="60"/>&#160;
					<span class="error"><form:errors path="password"/></span>
				</li>
				<li>
					<span>
						<label><fmt:message key="register.user.verifypassword"/></label>
					</span>
					<form:password path="verifyPassword" size="15" maxlength="60"/>&#160;
					<span class="error"><form:errors path="verifyPassword"/></span>
				</li>
				<li>
					<span>
						<label><fmt:message key="register.user.firstname"/></label>
					</span>
					<form:input path="firstName" size="15" maxlength="60"/>&#160;
					<span class="error"><form:errors path="firstName"/></span>
				</li>
				<li>
					<span>
						<label><fmt:message key="register.user.lastname"/></label>
					</span>
					<form:input path="lastName" size="15" maxlength="60"/>&#160;
					<span class="error"><form:errors path="lastName"/></span>
				</li>
				<li>
					<span><label></label></span>
					<input type="submit" value="Modifier votre compte" />
					<input type="hidden" name="user_id" value="${user_id}" />
					<form:hidden path="login" />
				</li>
			</ul>
		</div>
    </div>
    </form:form>
</body>
</html>