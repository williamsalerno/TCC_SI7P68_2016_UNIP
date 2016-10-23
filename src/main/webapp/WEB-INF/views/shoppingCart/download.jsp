<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<section class="cartView">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<h2>
						<fmt:message key="h2.startDownload" />
					</h2>
					<p>
						<fmt:message key="p.infoDownload" />
					</p>
				</div>
				<div>
					<c:forEach items="${myGames }" var="myGame">
						<div class="myGames">
							<img src="<c:url value="/img/uploaded/${myGame.game.id}_min.png"/>" width="100" height="100" />
							<h4>${myGame.game.title }</h4>
							<a class="link btn btn-default" href="${myGame.game.downloadLink }">Download </a>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>