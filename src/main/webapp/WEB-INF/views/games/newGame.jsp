<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<section class="cartView">
			<div class="panel panel-default">
				<form:form action="newGame" commandName="game" method="post">
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
					<div class="input-group">
						<span class=input-group-addon>Título:</span>
						<form:input class="form-control" type="text" minlength="5" path="title" />
						<strong> <form:errors path="title" cssClass="message-error" /></strong>
					</div>
					<div class="input-group">
						<span class=input-group-addon>Preço:</span>
						<form:input class="form-newgame" path="price" />
						<strong> <form:errors path="price" cssClass="message-error" /></strong>
					</div>
					<div class="input-group">
						<span class=input-group-addon>Descrição:</span>
						<form:textarea class="form-newgame" path="description" rows="5" cols="20" />
						<strong> <form:errors path="description" cssClass="message-error" /></strong>
					</div>
					<div class="btnForm">
						<button class="btn btn-default" type="submit">Enviar</button>
					</div>
				</form:form>
			</div>
		</section>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
<script type="text/javascript">
	$('#gamesForm').validate();
</script>