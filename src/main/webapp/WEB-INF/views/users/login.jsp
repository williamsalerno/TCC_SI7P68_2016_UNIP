
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="containerLogin">
		<div class="content">
			<section class="login">
				<div class="escondido">
					<h2>Login</h2>
					<form:form modelAttribute="user" servletRelativeAction="login"
						method="post" class="form-horizontal">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-log-in" aria-hidden="true"></span></span><input
								type="text" class="form-control" id="login" name="username"
								value='' placeholder=<fmt:message key="placeholder.username"/>
								required="required">
						</div>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span
								class="glyphicon glyphicon-asterisk" aria-hidden="true"></span></span>
							<input type="password" class="form-control" id="password"
								name="password"
								placeholder=<fmt:message key="placeholder.password"/>
								required="required">
						</div>
						<div class="btnForm" style="padding-bottom: 30px;">
							<input name="submit" type="submit" class="btn btn-default"
								value="<fmt:message key="btn.enter" />" />
						</div>
						<hr style="margin-bottom: 40px;">
						<c:if test="${success }">
							<div class="alert alert-success" style="text-align: center;">
								<fmt:message var="messageNewUser" key="message.success" />
								<c:set var="messageSuccess" value="${messageNewUser }" />
								${messageSuccess }
							</div>
						</c:if>
						<c:if test="${loginError }">
							<div class="alert alert-danger" style="text-align: center;">
								<strong><fmt:message key="message.error" /></strong>
								<fmt:message key="message.invalidCredential" />
							</div>
						</c:if>
					</form:form>
					<div class=bLogin>
						<a id="newAcc" href="<c:url value= "/newUser/selectCountry"/>"><fmt:message
								key="a.register1" /> <br> <fmt:message key="a.register2" />
						</a>
					</div>
					<div class=aLogin>
						<a href="<c:url value= "/users/new"/>"><fmt:message
								key="a.forgetPassword" /></a>
					</div>
				</div>
			</section>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>
<%@ include file="/WEB-INF/jspf/end.jspf"%>