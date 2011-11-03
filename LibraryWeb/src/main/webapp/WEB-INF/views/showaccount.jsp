<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon compte client</title>
</head>
<body>
	<h1>Gestion du compte client</h1>
    <p>
		Vous souhaitez consulter ou modifier vos informations personnelles. 
		Vous vous voulez modifier votre nom et votre prénom ? Vous souhaitez 
		modifier votre mot de passe ? L'interface de gestion de client vous 
		permet de modifier toutes les informations de votre compte client.    
	</p>

	<div id="center_content_account" >
		<div id="container_account">
	        <div class="title">Mes informations personnelles</div>
	        <div class="content">
	            <table>
	                <tbody>
	                    <tr>
	                        <td class="field">Nom d'utilisateur : </td><td>${user.login}</td>
	                    </tr>
	                    <tr>
	                        <td class="field">Mot de passe : </td><td>${user.password}</td>
	                    </tr>
	                    <tr>
	                        <td class="field">Nom : </td><td>${user.lastName}</td>
	                    </tr>
	                    <tr>
	                        <td class="field">Prenom :</td><td>${user.firstName}</td>
	                    </tr>
	                </tbody>
	            </table>
	            
	            <p>
					<a href="editaccount.html">Modifier votre compte</a>
				</p>
	        </div>
		</div>
	</div>
</body>
</html>