<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="support" id="container">
	<div class="content">
		<div class="row" id="accordion">
			<div class="thumbnail" id="profile_support">
				<div class="center">
					<a data-toggle="collapse" data-parent="#accordion" href="#faq"><img src="<c:url value="/img/faq.png"/>" alt="FAQ" data-holder-rendered="true"></a>
					<div class="caption">
						<h3>
							<a data-toggle="collapse" data-parent="#accordion" href="#faq">FAQ</a>
						</h3>
					</div>
					<div id="faq" class="center collapse" style="color: white;">
						<h4>
							<fmt:message key="h4.q1" />
						</h4>
						<p>
							<fmt:message key="p.q1" />
						</p>
						<br> <br>
						<h4>
							<fmt:message key="h4.q2" />
						</h4>
						<p>
							<fmt:message key="p.q2" />
						</p>
						<br> <br>
						<h4>
							<fmt:message key="h4.q3" />
						</h4>
						<p>
							<fmt:message key="p.q3" />
						</p>
					</div>
				</div>
			</div>
			<div class="thumbnail" id="profile_support">
				<div class="center">
					<a data-toggle="collapse" data-parent="#accordion" href="#forgotPassword"><img src="<c:url value="/img/forgotPassword.png"/>" alt="I Forgot my password" data-holder-rendered="true" /></a>
					<div class="caption">
						<h3>
							<a data-toggle="collapse" data-parent="#accordion" href="#forgotPassword"><fmt:message key="forgotPassword" /></a>
						</h3>
					</div>
					<div id="forgotPassword" class="left collapse">
						<form:form action="/support/forgotMyPassword/recover" commandName="user" class="form-horizontal" method="post">
							<fmt:message var="phUsername" key="placeholder.username" />
							<form:input type="text" class="form-support" path="login" placeholder="${phUsername }" required="required" />
							<fmt:message var="phEmail" key="placeholder.email" />
							<form:input type="text" class="form-support" path="email" placeholder="${phEmail }" required="required" />
							<input name="submit" type="submit" class="btn btn-default" value="<fmt:message key="btn.recover" />" />
						</form:form>
						<c:if test="${passwordRecover }">
							<div class="alert alert-info infoRecoverPassword">
								<fmt:message var="messageRecoverPassword" key="message.recoverPassword" />
								<c:set var="messagePasswordInfo" value="${messageRecoverPassword }" />
								${messagePasswordInfo }
							</div>
						</c:if>
						<c:if test="${recoverError }">
							<div class="alert alert-danger infoRecoverPassword">
								<fmt:message var="messageRecoverPassword" key="message.recoverPasswordError" />
								<c:set var="messagePasswordInfo" value="${messageRecoverPassword }" />
								${messagePasswordInfo }
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="thumbnail" id="profile_support">
				<div class="center">
					<a data-toggle="collapse" data-parent="#accordion" href="#contact"><img src="<c:url value="/img/contact.png"/>" alt="Contact" data-holder-rendered="true" /></a>
					<div class="caption">
						<h3>
							<a data-toggle="collapse" data-parent="#accordion" href="#contact"><fmt:message key="contact" /></a>
						</h3>
					</div>
					<div id="contact" class="center collapse">
						<form:form action="/support/message" class="form-horizontal" method="post">
							<fmt:message var="phSender" key="placeholder.sender" />
							<input type="text" class="form-support" name="sender" placeholder="${phSender }" required="required" />
							<fmt:message var="phSubject" key="placeholder.subject" />
							<input type="text" class="form-support" name="subject" placeholder="${phSubject }" required="required" />
							<div class="center">
								<fmt:message var="phMessage" key="placeholder.message" />
								<textarea class="form-support sendMessage" name="message" placeholder="${phMessage }" required="required" rows="5" cols="20"></textarea>
							</div>
							<input style="margin-bottom: 15px;" name="submit" type="submit" class="btn btn-default" value="<fmt:message key="btn.send" />" />
						</form:form>
						<c:if test="${messageSuccess }">
							<div class="alert alert-success infoRecoverPassword">
								<fmt:message var="messageSent" key="message.sent" />
								<c:set var="messageSentInfo" value="${messageSent }" />
								${messageSentInfo }
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
