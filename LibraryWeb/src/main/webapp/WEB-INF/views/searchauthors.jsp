<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rechercher un auteur</title>
</head>
<body>
	<h1>Rechercher un auteur</h1>
    <p>
        Librairie.com permet de rechercher rapidement un auteur à partir de son prénom, de son nom
        ou d'un livre qu'il a écrit. Ce module permet d'accèder à l'ensemble des auteurs de la librairie 
        à partir de critères de recherches.
        <br /><br />
    </p>

	<!-- Formulaire de recherche d'un livre par son titre -->
	<div class="container_search">
		<div class="container_search_text">
			<span>Recherchez un auteur par son prénom</span>
		</div>
		<div class="container_search_form">
			<form method="post" action="searchauthorbyfirstname.html" >
				<div class="container_search_form_input_text_1">
					<input class="form-text" type="text" title="Prénom" name="author_firstname" value="" size="60" maxlength="128">
				</div>
				<input class="form-submit" type="submit" value="rechercher">
			</form>
		</div>
	</div>
	
	<!-- Formulaire de recherche d'un auteur par son nom -->
	<div class="container_search">
		<div class="container_search_text">
			<span>Recherchez un auteur par son nom</span>
		</div>
		<div class="container_search_form">
			<form method="post" action="searchauthorbylastname.html" >
				<div class="container_search_form_input_text_1">
					<input class="form-text" type="text" title="Nom" name="author_lastname" value="" size="60" maxlength="128">
				</div>
				<input class="form-submit" type="submit" value="rechercher">
			</form>
		</div>
	</div>
	
	<!-- Formulaire de recherche d'un auteur par un livre qu'il a écrit -->
	<div class="container_search">
		<div class="container_search_text">
			<span>Recherchez un auteur à partir d'un livre qu'il a écrit</span>
		</div>
		<div class="container_search_form">
			<form method="post" action="searchauthorbybook.html" >
				<div class="container_search_form_input_text_1">
					<input class="form-text" type="text" title="Titre" name="book_title" value="" size="60" maxlength="128">
				</div>
				<input class="form-submit" type="submit" value="rechercher">
			</form>
		</div>
	</div>
</body>
</html>