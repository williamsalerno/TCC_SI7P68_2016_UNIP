<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="content">
		<div class="container">
			<fieldset>
				<legend>Editar Produto</legend>
				<c:url var="editGame_url" value="/games/${game.id }" />
				<c:url var="deleteGame_url" value="/games/delete/${game.id }" />
				<form:form modelAttribute="game" action="${editGame_url }"
					method="post">
					<input type="hidden" name="${_csrf.parameterName }"
						value="${_csrf.token }" />
					<img src="<c:url value="/games/${game.id }/image"/>" width="100"
						height="100"></img>
					<div class="input-group">
						<label for="title">Título:</label>
						<form:input id="title" type="text" name="title" path="title"
							value="${game.title }" />
						<strong><form:errors delimiter="br" path="title"
								cssClass="message-error" /> </strong>
					</div>
					<div class="input-group">
						<label for="price">Preço:</label>
						<form:input id="price" type="text" name="price" path="price"
							value="${game.price }" />
						<strong><form:errors delimiter="br" path="price"
								cssClass="message-error" /> </strong>
					</div>
					<div class="input-group">
						<label for="description">Descrição:</label>
						<form:input id="description" type="text" name="description"
							path="description" value="${game.description }" />
						<strong><form:errors delimiter="br" path="description"
								cssClass="message-error" /> </strong>
					</div>
					<button type="submit" name="_method" value="put">Alterar</button>
				</form:form>
				<form:form modelAttribute="game" action="${deletetGame_url }"
					method="post">
					<button type="submit">Apagar</button>
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
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
