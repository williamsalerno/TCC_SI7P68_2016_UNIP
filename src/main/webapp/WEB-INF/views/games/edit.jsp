<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<section class="login" id="editGame">
			<c:url var="editGame_url" value="/games/${game.id }" />
			<c:url var="deleteGame_url" value="/games/delete/${game.id }" />
			<form:form class="form-horizontal updateInfo" commandName="game" action="${editGame_url }" method="post">
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
				<h2 id="h2Form" style="text-align: center; color: #FFC277">
					Alterar Jogo <br> <small style="font-size: 15px"></small>
				</h2>
				<div class="center">
					<img src="<c:url value="/img/uploaded/${game.id}_thumb.png"/>" width="100" height="100"></img>
				</div>
				<div class="input-group">
					<span><fmt:message key="title" /></span>
					<form:input class="form-control" type="text" minlength="5" path="title" />
					<strong> <form:errors path="title" cssClass="message-error" /></strong>
				</div>
				<div class="input-group">
					<span><fmt:message key="price" /></span>
					<form:input class="form-control" path="price" />
					<strong> <form:errors path="price" cssClass="message-error" /></strong>
				</div>
				<div class="input-group">
					<span><fmt:message key="description" /></span>
					<form:textarea class="form-control" path="description" rows="5" cols="20" />
					<strong> <form:errors path="description" cssClass="message-error" /></strong>
				</div>
				<div class="center" style="margin-top: 145px;">
					<a class="btn btn-default" href="<c:url value="/games/list"/>"> <fmt:message key="btn.back" />
					</a>
					<button class="btn btn-default" type="submit">Alterar</button>
				</div>
			</form:form>
			<form:form action="${deleteGame_url }" commandName="game" method="post">
				<div class="center">
					<button class="btnForm btn btn-default" type="submit">Apagar</button>
				</div>
			</form:form>
		</section>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
