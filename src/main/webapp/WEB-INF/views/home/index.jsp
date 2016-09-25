<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<section id="games-section">
		<div class="content">
			<div class="row" id="thumbHome">
				<c:forEach items="${games}" var="game" varStatus="g">
					<c:set var="gameId" value="id${game.id }" />
					<div class="thumbnail">
						<img src="<c:url value="/img/uploaded/${game.id}.png"/>" width=410px height=410px alt="Disruption screen image" data-holder-rendered="true">
						<div class="caption">
							<h3>Disruption</h3>
							<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
							<p>
								<a href="/games/list#${game.id}" class="btn btn-primary" role="button">Mais detalhes</a>
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
