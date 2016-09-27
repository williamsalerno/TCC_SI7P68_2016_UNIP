<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<div class="row">
			<div class="thumbnail">
				<img src="<c:url value="/img/uploaded/${game.id}.png"/>" width=410px height=410px alt="Disruption screen image" data-holder-rendered="true">
				<div class="caption">
					<h3>Informações pessoais</h3>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>