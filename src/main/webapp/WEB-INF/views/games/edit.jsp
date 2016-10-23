<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<c:url var="editGame_url" value="/games/${game.id }" />
		<c:url var="deleteGame_url" value="/games/delete/${game.id }" />
		<form:form commandName="game" action="${editGame_url }" method="post">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
			<img src="<c:url value="/games/${game.id }/image"/>" width="100" height="100"></img>
			<div class="input-group">
				<label for="title">T�tulo:</label>
				<form:input type="text" path="title" />
				<strong><form:errors path="title" cssClass="message-error" /> </strong>
			</div>
			<div class="input-group">
				<label for="price">Pre�o:</label>
				<form:input type="text" path="price" />
				<strong><form:errors path="price" cssClass="message-error" /> </strong>
			</div>
			<div class="input-group">
				<label for="description">Descri��o:</label>
				<form:textarea type="text" path="description" />
				<strong><form:errors path="description" cssClass="message-error" /> </strong>
			</div>
			<button type="submit">Alterar</button>
		</form:form>
		<form:form action="${deleteGame_url }" commandName="game" method="post">
			<button type="submit">Apagar</button>
		</form:form>
		<form action="<c:url value="/games/list"/>" method="get">
			<button type="submit">Voltar</button>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
