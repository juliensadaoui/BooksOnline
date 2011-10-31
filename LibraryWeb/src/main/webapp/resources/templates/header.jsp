<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
	<a id="logo" href="/">
		<c:url value="/resources/images/librairie_logo.png" var="url_logo" />
		<img alt="Lecture en ligne, lire et partager ses lectures en ligne sur Lecteurs" 
			src="${url_logo}">
	</a>

	<div class="search">
		<form method="post" accept-charset="UTF-8" action="/lecteurs">
			<div>
				<div id="search-input-text-container" class="form-item">
					<input id="search-input-text" class="form-text" type="text" 
						title="Trouver un auteur, un lecteur, un livre ou un article..." 
						value="" size="15" name="keywords" maxlength="128">
				</div>
				
				<div class="button-submit-container">
					<input class="form-submit form-submit btn-search" 
							type="submit" value="rechercher" >
				</div>
			</div>
		</form>
	</div>
</div>

