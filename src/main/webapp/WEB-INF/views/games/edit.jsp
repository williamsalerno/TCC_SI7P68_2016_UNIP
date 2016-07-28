<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div id="errors">
		<ul>
			<c:forEach items="${errors}" var="error">
				<li>${error.category }-${error.message }</li>
			</c:forEach>
		</ul>
	</div>
	<fieldset>
		<legend>Editar Produto</legend>
		<c:url var="editGame_url" value="/games/${game.id }" />
		<form:form modelAttribute="game" action="${editGame_url }"
			method="post">
			<input type="hidden" name="${_csrf.parameterName }"
				value="${_csrf.token }" />
			<img src="<c:url value="/games/${game.id }/image"/>" width="100"
				height="100"></img>
			<label for="title">Título:</label>
			<input id="title" type="text" name="game.title"
				value="${game.title }" />
			<label for="price">Preço:</label>
			<input id="price" type="text" name="game.price"
				value="${game.price }" />
			<button type="submit" name="_method" value="put">Alterar</button>
			<button type="submit" name="_method" value="delete">Apagar</button>
		</form:form>
		<c:url var="editImageGame_url" value="/games/${game.id }/image" />
		<form:form action="${editImageGame_url }" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="${_csrf.parameterName }"
				value="${_csrf.token }" />
			<fieldset>
				<legend>Upload de imagem</legend>
				<input type="file" name="image">
				<button type="submit">Enviar</button>
			</fieldset>
		</form:form>
		<form action="<c:url value="/games"/>" method="get">
			<button type="submit">Voltar</button>
		</form>
	</fieldset>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>