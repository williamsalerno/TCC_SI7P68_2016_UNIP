<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="content">
		<div class="container">
			<c:url var="editGame_url" value="/games/${game.id }" />
			<c:url var="deleteGame_url" value="/games/delete/${game.id }" />
			<form:form commandName="game" action="${editGame_url }" method="post">
				<input type="hidden" name="${_csrf.parameterName }"
					value="${_csrf.token }" />
				<img src="<c:url value="/games/${game.id }/image"/>" width="100"
					height="100"></img>
				<div class="input-group">
					<label for="title">Título:</label>
					<form:input type="text" path="title" />
					<strong><form:errors path="title" cssClass="message-error" />
					</strong>
				</div>
				<div class="input-group">
					<label for="price">Preço:</label>
					<form:input type="text" path="price" />
					<strong><form:errors path="price" cssClass="message-error" />
					</strong>
				</div>
				<div class="input-group">
					<label for="description">Descrição:</label>
					<form:input type="text" path="description" />
					<strong><form:errors path="description"
							cssClass="message-error" /> </strong>
				</div>
				<button type="submit">Alterar</button>
			</form:form>
			<form:form action="${deleteGame_url }" commandName="game" method="post">
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
			<form action="<c:url value="/games/list"/>" method="get">
				<button type="submit">Voltar</button>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
