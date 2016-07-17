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
		<form action="<c:url value="/games/${game.id }"/>" method="post">
			<img src="<c:url value="/games/${game.id }/image"/>" width="100"
				height="100"></img> <label for="title">Título:</label> <input
				id="title" type="text" name="game.title" value="${game.title }" />
			<label for="price">Preço:</label> <input id="price" type="text"
				name="game.price" value="${game.price }" />
			<button type="submit" name="_method" value="put">Alterar</button>
			<button type="submit" name="_method" value="delete">Apagar</button>
		</form>
		<form action="<c:url value="/games/${game.id }/image"/>" method="post"
			enctype="multipart/form-data">
			<fieldset>
				<legend>Upload de imagem</legend>
				<input type="file" name="image">
				<button type="submit">Enviar</button>
			</fieldset>
		</form>
		<form action="<c:url value="/games"/>" method="get">
			<button type="submit">Voltar</button>
		</form>
	</fieldset>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>