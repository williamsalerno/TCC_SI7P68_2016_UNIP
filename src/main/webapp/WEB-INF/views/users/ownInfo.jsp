<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="containerLogin" id="container">
	<div class="content">
		<div class="login">
			<c:url var="updateUser_url" value="/user/myInfo/update" />
			<form:form class="form-horizontal updateInfo" role="form" id="usersForm" action="${updateUser_url }" method="post" commandName="user">
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
				<form:input type="hidden" path="id" />
				<br>
				<h2 id="h2Form" style="text-align: center; color: #FFC277">
					<fmt:message key="ownInfo" />
					<br> <small style="font-size: 15px"></small>
				</h2>
				<div class="input-group">
					<span><fmt:message key="placeholder.firstName" /></span>
					<form:input id="firstName" type="text" class="form-control" path="firstName" placeholder="${phFirstName }" aria-describedby="basic-addon1" required="required" />
					<strong id="firstNameError" class="message-error"><form:errors delimiter="br" path="firstName" /></strong> <br>
				</div>
				<div class="input-group">
					<span><fmt:message key="placeholder.lastName" /></span>
					<form:input id="lastName" type="text" class="form-control" path="lastName" placeholder="${phLastName }" aria-describedby="basic-addon1" required="required" />
					<strong><form:errors delimiter="br" path="lastName" cssClass="message-error" /></strong> <br>
				</div>
				<div class="input-group">
					<span><fmt:message key="placeholder.email" /></span>
					<form:input id="email" type="email" class="form-control" path="email" placeholder="${phEmail }" required="required" aria-describedby="basic-addon1" />
					<strong> <form:errors delimiter="br" path="email" cssClass="message-error" />
					</strong> <br>
				</div>
				<br>
				<div class="input-group">
					<span><fmt:message key="placeholder.country" /></span>
					<form:input id="addressCountry" type="text" class="form-control" path="address.country" value="${param.country}" readonly="true" aria-describedby="basic-addon1" />
					<strong><form:errors delimiter="br" path="address.country" cssClass="message-error" /> </strong>
				</div>
				<c:set var="brazil" value="BRAZIL" />
				<c:if test="${brazil eq param.country or brazil eq settedCountry}">
					<div class="input-group">
						<span><fmt:message key="placeholder.cep" /></span>
						<form:input id="addressCep" type="text" class="form-control" path="address.cep" placeholder="${phCep }" required="required" aria-describedby="basic-addon1" />
						<strong> <form:errors delimiter="br" path="address.cep" cssClass="message-error" />
						</strong>
					</div>
					<div class="input-group">
						<span><fmt:message key="placeholder.city" /></span>
						<form:input id="addressCity" type="text" class="form-control" path="address.city" placeholder="${phCity }" required="required" />
						<strong> <form:errors delimiter="br" path="address.city" cssClass="message-error" />
						</strong>
					</div>
					<div class="input-group">
						<span><fmt:message key="placeholder.state" /></span>
						<form:input id="addressState" type="text" class="form-control" path="address.state" placeholder="${phState }" required="required" />
						<strong><form:errors delimiter="br" path="address.state" cssClass="message-error" /> </strong>
					</div>
					<br>
				</c:if>
				<div class="input-group">
					<span><fmt:message key="placeholder.username" /></span>
					<form:input id="username" type="text" class="form-control" path="login" placeholder="${phUsername }" readonly="true" required="required" />
					<strong><form:errors delimiter="br" path="login" cssClass="message-error" /> </strong>
				</div>
				<div class="input-group">
					<span><fmt:message key="placeholder.newPassword" /></span>
					<form:input id="password" type="password" class="form-control" path="password" required="required" />
					<strong><form:errors delimiter="br" path="password" cssClass="message-error" /> </strong>
				</div>
				<div class="input-group">
					<span><fmt:message key="placeholder.confirmPassword" /></span><input id="confirmPassword" equalTo="#password" type="password" class="form-control" required="required"> <strong id="passwordError" class="message-error"> </strong>
				</div>
				<hr style="margin: 40px;">
				<c:if test="${error}">
					<div class="alert alert-danger" style="text-align: center;">
						<fmt:message key="message.invalidValue" />
						<strong>${userLogin }</strong>
					</div>
				</c:if>
				<div class="btnForm">
					<a class="btn btn-default" href="<c:url value="/user/myProfile"/>"> <fmt:message key="btn.back" />
					</a>
					<button type="submit" class="btn btn-default">
						<fmt:message key="btn.update" />
					</button>
				</div>
				<br>
				<div class="clearfix"></div>
			</form:form>
			<script type="text/javascript">
				$('#usersForm').validate();
			</script>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>