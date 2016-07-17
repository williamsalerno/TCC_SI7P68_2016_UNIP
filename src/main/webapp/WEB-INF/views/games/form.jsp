<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div id="errors">
		<ul>
			<c:forEach items="${errors}" var="error">
				<li>${error.category }-${error.message }</li>
			</c:forEach>
		</ul>
	</div>
	<div>
		<spring:hasBindErrors name="game">
			<ul>
				<c:forEach var="error" items="${errors.allErrors }">
					<li><spring:message code="${error.code }"
							text="${error.defaultMessage}" /></li>
				</c:forEach>
			</ul>
		</spring:hasBindErrors>
		<form:form action="newGame" method="post" commandName="game">
			<div>
				<label for="title">Título:</label>
				<form:input minlength="5" path="title" />
				<form:errors path="title" />
			</div>
			<div>
				<label for="price">Preço:</label>
				<form:input path="price" min="1" />
				<form:errors path="price" />
			</div>
			<div>
				<label for="description">Descrição:</label>
				<form:textarea path="description" rows="5" cols="20" />
			</div>
			<button type="submit">Enviar</button>
		</form:form>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
<script type="text/javascript">
	$('#gamesForm').validate();
</script>