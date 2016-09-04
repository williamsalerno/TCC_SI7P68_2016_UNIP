<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="container">
		<section class="cartView">
			<div class="panel panel-default">
				<form:form action="newGame" method="post" commandName="game">
					<input type="hidden" name="${_csrf.parameterName }"
						value="${_csrf.token }" />
					<div class="input-group">
						<span class=input-group-addon>Título:</span>
						<form:input class="form-control" type="text" minlength="5"
							path="title" />
						<strong> <form:errors path="title"
								cssClass="message-error" /></strong>
					</div>
					<div class="input-group">
						<span class=input-group-addon>Preço:</span>
						<form:input class="form-newgame" path="price" min="1" />
						<form:errors path="price" />
					</div>
					<div class="input-group">
						<span class=input-group-addon>Descrição:</span>
						<form:textarea class="form-newgame" path="description" rows="5"
							cols="20" />
					</div>
					<div class="btnForm">
						<button class="btn btn-default" type="submit">Enviar</button>
					</div>
				</form:form>
			</div>
		</section>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
<script type="text/javascript">
	$('#gamesForm').validate();
</script>