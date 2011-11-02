<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recherche un livre</title>
</head>
<body>
	<h1>Rechercher un livre</h1>
    <p>
        Librairie.com permet de rechercher rapidement un livre à partir de son titre, de son genre
        ou de son auteur. Ce module permet d'accèder à l'ensemble des livres de la librairie à partir
        de critères de recherches.
        <br /><br />
    </p>

	<!-- Formulaire de recherche d'un livre par son titre -->
	<div class="container_search">
		<div class="container_search_text">
			<span>Recherchez un livre à partir de son titre</span>
		</div>
		<div class="container_search_form">
			<form method="post" action="searchbookbyname.html" >
				<div class="container_search_form_input_text_1">
					<input class="form-text" type="text" title="Titre" name="book_title" value="" size="60" maxlength="128">
				</div>
				<input class="form-submit" type="submit" value="rechercher">
			</form>
		</div>
	</div>
	
	<!-- Formulaire de recherche d'un livre par son genre -->
	<div class="container_search">
		<div class="container_search_text">
			<span>Recherchez un livre à partir de son genre</span>
		</div>
		<div class="container_search_form">
			<form method="post" action="searchbookbygenre.html" >
				<div class="container_search_form_input_text_1">
					<input class="form-text" type="text" title="Genre" name="book_genre" value="" size="60" maxlength="128">
				</div>
				<input class="form-submit" type="submit" value="rechercher">
			</form>
		</div>
	</div>
	
	<!-- Formulaire de recherche d'un livre par son auteur -->
	<div class="container_search">
		<div class="container_search_text">
			<span>Recherchez un livre à partir de son auteur</span>
		</div>
		<div class="container_search_form">
			<form method="post" action="searchbookbyauthor.html" >
				<div class="container_search_form_input_text_1">
					<input class="form-text" type="text" title="Prenom" name="author_firstname" value="" size="60" maxlength="128">
				</div>
				<div class="container_search_form_input_text_1">
					<input class="form-text" type="text" title="Nom" name="author_lastname" value="" size="60" maxlength="128">
				</div>
				<input class="form-submit" type="submit" value="rechercher">
			</form>
		</div>
	</div>
</body>
</html>