<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<section id="games-section">
		<div class="content">
			<div class="row" id="thumbHome">
				<c:forEach items="${games}" var="game" varStatus="g">
					<c:set var="gameId" value="id${game.id }" />
					<div class="thumbnail">
						<img src="<c:url value="/img/uploaded/${game.id}_thumb.png"/>" width=410px height=410px alt="Disruption screen image" data-holder-rendered="true">
						<div class="caption">
							<h3>${game.title }</h3>
							<p>Baseado em clássicos de terror como Resident Evil, Silent Hill e Alone in the Dark, Disruption foi feito por um e para todos os amantes de jogos de terror.</p>
							<p>
								<a id="moreDetails" href="/games/list?id=${game.id}" class="btn btn-primary" role="button"><fmt:message key="btn.details" /></a>
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
