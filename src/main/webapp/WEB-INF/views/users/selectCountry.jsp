<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="containerLogin">
		<div class="content">
			<div class="login">
				<form class="escondido form-horizontal" role="form" id="countryForm"
					action="<c:url value="/users/new"/>" method="get">
					<br>
					<h2 id="h2Form" style="text-align: center; color: #FFC277">
						<fmt:message key="h2.selectCountry" />
					</h2>
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1"><span
							class="glyphicon glyphicon-globe" aria-hidden="true"></span></span> <select
							class="form-control" id="country"
							name="user.address.country.name"
							value="${user.address.country.name }"
							aria-describedby="basic-addon1" required="required">
							<option selected>BRAZIL</option>
							<option>OTHER</option>
						</select>
					</div>
					<hr style="margin: 40px;">
					<div class="btnForm">
						<button type="submit" class="btn btn-default" id="countryButton">
							<fmt:message key="btn.continue" />
						</button>
					</div>
					<br>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>