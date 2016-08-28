<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="containerLogin">
		<div class="content">
			<div class="login">
				<c:url var="newUser_url" value="/newUser/form" />
				<form:form class="escondido form-horizontal" role="form"
					id="usersForm" action="${newUser_url }" method="post"
					commandName="user">
					<input type="hidden" name="${_csrf.parameterName }"
						value="${_csrf.token }" />
					<br>
					<h2 id="h2Form" style="text-align: center; color: #FFC277">
						<fmt:message key="h2.completeRegister" />
						<br> <small style="font-size: 15px"></small>
					</h2>
					<div class="input-group">
						<fmt:message var="phFirstName" key="placeholder.firstName" />
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
						<form:input type="text" class="form-control" path="firstName"
							placeholder="${phFirstName }" aria-describedby="basic-addon1"
							required="required" />
						<strong><form:errors delimiter="br" path="firstName"
								cssClass="message-error" /></strong>
					</div>
					<div class="input-group">
						<fmt:message var="phLastName" key="placeholder.lastName" />
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
						<form:input type="text" class="form-control" path="lastName"
							placeholder="${phLastName }" aria-describedby="basic-addon1"
							required="required" />
						<strong><form:errors delimiter="br" path="lastName"
								cssClass="message-error" /></strong>
					</div>
					<div class="input-group">
						<fmt:message var="phEmail" key="placeholder.email" />
						<span class="input-group-addon" id="basic-addon1">@</span>
						<form:input type="email" class="form-control" path="email"
							placeholder="${phEmail }" required="required" />
						<strong> <form:errors delimiter="br" path="email"
								cssClass="message-error" />
						</strong>
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-globe" aria-hidden="true"></span></span>
						<form:input type="text" class="form-control"
							path="address.country" value="${param.country}" readonly="true" />
						<strong><form:errors delimiter="br"
								path="address.country" cssClass="message-error" /> </strong>
					</div>
					<c:set var="brazil" value="BRAZIL" />
					<c:if test="${brazil eq param.country or brazil eq settedCountry}">
						<div class="input-group">
							<fmt:message var="phCep" key="placeholder.cep" />
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span></span>
							<form:input type="text" class="form-control" path="address.cep"
								placeholder="${phCep }" required="required" />
							<strong> <form:errors delimiter="br" path="address.cep"
									cssClass="message-error" />
							</strong>
						</div>
						<div class="input-group">
							<fmt:message var="phCity" key="placeholder.city" />
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span></span>
							<form:input type="text" class="form-control" path="address.city"
								placeholder="${phCity }" required="required" />
							<strong> <form:errors delimiter="br" path="address.city"
									cssClass="message-error" />
							</strong>
						</div>
						<div class="input-group">
							<fmt:message var="phState" key="placeholder.state" />
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span></span>
							<form:input type="text" class="form-control" path="address.state"
								placeholder="${phState }" required="required" />
							<strong><form:errors delimiter="br" path="address.state"
									cssClass="message-error" /> </strong>
						</div>
						<br>
					</c:if>
					<div class="input-group">
						<fmt:message var="phUsername" key="placeholder.username" />
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-log-in" aria-hidden="true"></span></span>
						<form:input type="text" class="form-control" path="login"
							placeholder="${phUsername }" required="required" />
						<strong><form:errors delimiter="br" path="login"
								cssClass="message-error" /> </strong>
					</div>
					<div class="input-group">
						<fmt:message var="phPassword" key="placeholder.password" />
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-asterisk" aria-hidden="true"></span></span>
						<form:input type="password" class="form-control" id="password"
							path="password" placeholder="${phPassword }" required="required" />
						<strong><form:errors delimiter="br" path="password"
								cssClass="message-error" /> </strong>
					</div>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-asterisk" aria-hidden="true"></span></span><input
							type="password" class="form-control" id="confirm"
							equalTo="#password"
							placeholder='<fmt:message key="placeholder.confirmPassword" />'
							required="required">
					</div>
					<hr style="margin: 40px;">
					<c:if test="${error}">
						<div class="alert alert-danger" style="text-align: center;">
							<fmt:message key="message.invalidValue" />
							<strong>${userLogin }</strong>
						</div>
					</c:if>
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