<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="support" id="container">
	<div class="content">
		<div class="row">
			<div class="thumbnail" id="profile_support">
				<div class="center">
					<a href="<c:url value="/support/faq"/>"><img src="<c:url value="/img/faq.png"/>" alt="FAQ" data-holder-rendered="true"></a>
					<div class="caption">
						<h3>
							<a href="<c:url value="/support/faq"/>">FAQ</a>
						</h3>
					</div>
				</div>
			</div>
			<div class="thumbnail" id="profile_support">
				<div class="center">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapse1"><img src="<c:url value="/img/forgotPassword.png"/>" alt="I Forgot my password" data-holder-rendered="true" /></a>
					<div class="caption">
						<h3>
							<a data-toggle="collapse" data-parent="#accordion" href="#collapse1"><fmt:message key="forgotPassword" /></a>
						</h3>
					</div>
					<div id="collapse1" class="left collapse in">
						<form:form action="/support/forgotMyPassword/request" commandName="user" class="form-horizontal" method="post">
							<fmt:message var="phUsername" key="placeholder.username" />
							<form:input type="text" class="form-support" path="login" placeholder="${phUsername }" required="required" />
							<fmt:message var="phEmail" key="placeholder.email" />
							<form:input type="password" class="form-support" path="email" placeholder="${phEmail }" required="required" />
							<input name="submit" type="submit" class="btn btn-default" value="<fmt:message key="btn.recover" />" />
						</form:form>
					</div>
				</div>
			</div>
			<div class="thumbnail" id="profile_support">
				<div class="center">
					<a href="<c:url value="/support/contact"/>"><img src="<c:url value="/img/contact.png"/>" alt="Contact" data-holder-rendered="true" /></a>
					<div class="caption">
						<h3>
							<a href="<c:url value="/support/contact"/>"><fmt:message key="contact" /></a>
						</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>