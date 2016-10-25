<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<section class="login" id="editGame">
			<form:form class="form-horizontal updateInfo" action="newGame" commandName="game" method="post">
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
				<h2 id="h2Form" style="text-align: center; color: #FFC277">
					<fmt:message key="newGame" />
					<br> <small style="font-size: 15px"></small>
				</h2>
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
				<div class="btnForm" style="margin-top: 130px;">
					<a class="btn btn-default" href="<c:url value="/games/list"/>"> <fmt:message key="btn.back" />
					</a>
					<button class="btn btn-default" type="submit">Enviar</button>
				</div>
			</form:form>
		</section>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
<script type="text/javascript">
	$('#gamesForm').validate();
</script>