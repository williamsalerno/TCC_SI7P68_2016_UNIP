<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="profile" id="container">
	<div class="content">
		<div class="row">
			<div class="thumbnail">
				<div class="center">
					<img src="<c:url value="/img/new-file.png"/>" alt="Informações pessoais" data-holder-rendered="true">
					<div class="caption">
						<h3>Informações pessoais</h3>
					</div>
				</div>
			</div>
			<div class="thumbnail">
				<div class="center">
					<a href="<c:url value="/user/myGames"/>"><img src="<c:url value="/img/game-console.png"/>" alt="Meus jogos" data-holder-rendered="true" /></a>
					<div class="caption">
						<h3>Meus jogos</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>