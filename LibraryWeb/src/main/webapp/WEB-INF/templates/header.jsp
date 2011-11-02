<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<a id="logo" href="home.html">
		<c:url value="/resources/images/librairie_logo.png" var="url_logo" />
		<img alt="Lecture en ligne, lire et partager ses lectures en ligne sur Lecteurs" 
			src="${url_logo}">
	</a>
	
	<div class="login">
		<ul>
			<li>
				<a class="lnk_login" href="register.html">S'inscrire</a>
			</li>
			<li>|</li>
			<li>
				<a class="lnk_login" href="login.html">Mon compte</a>
			</li>
		</ul>
	</div>

	<div class="search">
		<form method="post" accept-charset="UTF-8" action="searchbookbyname.html">
			<div>
				<div id="search-input-text-container" class="form-item">
					<input id="search-input-text" class="form-text" type="text" 
						title="Trouver un livre à partir de son titre ..."
						placeholder="Trouver un livre à partir de son titre ..." 
						value="" size="15" name="book_title" maxlength="128">
				</div>
				
				<div class="button-submit-container">
					<input class="form-submit" 
							type="submit" value="rechercher" >
				</div>
			</div>
		</form>
	</div>
</div>

