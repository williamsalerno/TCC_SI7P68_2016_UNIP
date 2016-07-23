<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div id="errors">
		<ul>
			<c:forEach items="${errors}" var="error">
				<li>${error.category }-${error.message }</li>
			</c:forEach>
		</ul>
	</div>
	<div class="containerLogin">
		<div class="content">
			<div class="login">
				<c:url var="login_url" value="/success" />
				<form:form class="escondido form-horizontal" role="form"
					id="usersForm" action="${login_url }" method="post"
					commandName="user">
					<input type="hidden" name="${_csrf.parameterName }"
						value="${_csrf.token }" />
					<br>
					<h2 id="h2Form" style="text-align: center; color: #FFC277">
						<fmt:message key="h2.completeRegister" />
						<br> <small style="font-size: 15px"></small>
					</h2>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
						<form:input type="text" class="form-control" path="firstName"
							placeholder="Primeiro nome" aria-describedby="basic-addon1"
							required="required" />
						<form:errors path="firstName" />
					</div>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
						<form:input type="text" class="form-control" path="lastName"
							placeholder="Último nome" aria-describedby="basic-addon1"
							required="required" />
						<form:errors path="lastName" />
					</div>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1">@</span>
						<form:input type="email" class="form-control" path="email"
							placeholder="email válido" required="required" />
						<form:errors path="email" />
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-globe" aria-hidden="true"></span></span>
						<form:input type="text" class="form-control" path="address.country"
							value="${param.country}" readonly="true" />
						<form:errors path="address.country" />
					</div>
					<c:set var="selectedCountry" value="BRAZIL" />
					<c:if test="${param.country eq selectedCountry}">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span></span>
							<form:input type="text" class="form-control" path="address.cep"
								placeholder="CEP" required="required" />
							<form:errors path="address.cep" />
						</div>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span></span>
							<form:input type="text" class="form-control" path="address.city"
								placeholder="Cidade" required="required" />
							<form:errors path="address.city" />
						</div>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span></span>
							<form:input type="text" class="form-control" path="address.state"
								placeholder="Estado" required="required" />
							<form:errors path="address.state" />
						</div>
						<br>
					</c:if>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-log-in" aria-hidden="true"></span></span>
						<form:input type="text" class="form-control" path="login"
							placeholder="Nome de usuário" required="required" />
						<form:errors path="login" />
					</div>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-asterisk" aria-hidden="true"></span></span>
						<form:input type="password" class="form-control" path="password"
							placeholder="Senha (entre 6 e 10 dígitos alfanuméricos)"
							required="required" />
						<form:errors path="password" />
					</div>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-asterisk" aria-hidden="true"></span></span><input
							type="password" class="form-control" id="confirm"
							equalTo="#password" placeholder="Confirme a senha"
							required="required">
						<form:errors path="password" />
					</div>
					<hr style="margin: 40px;">
					<div class="btnForm">
						<button type="submit" class="btn btn-default">Confirmar</button>
					</div>
					<br>
					<div class="clearfix"></div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
<%@ include file="/WEB-INF/jspf/end.jspf"%>