<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<div class="games">
			<c:forEach items="${games}" var="game" varStatus="g">
				<c:set var="gameId" value="id${game.id }" />
				<section class="moreInfoGame" id=${gameId }>
					<div class="imageGame">
						<img src="<c:url value="/img/uploaded/${game.id}.png"/>" width=300px height=288px alt="Disruption screen image" data-holder-rendered="true">
					</div>
					<div class="detailsGame">
						<div class="titleGame">
							<h1>${game.title }</h1>
						</div>
						<div class="priceGame">
							<h1 style="margin-right: 20px;">
								<c:choose>
									<c:when test="${game.price < 1}">
											Free
										</c:when>
									<c:otherwise>
										<fmt:formatNumber type="currency" value="${game.price }" />
									</c:otherwise>
								</c:choose>
							</h1>
						</div>
						<div class="btnsGame">
							<c:if test="${loggedUser.logged}">
								<c:forEach items="${shoppingCart.items }" var="itemAdded">
									<c:if test="${itemAdded.game.id eq game.id}">
										<c:set var="contains" value="true" />
									</c:if>
								</c:forEach>
								<c:forEach items="${loggedUser.licenses }" var="gameAcquired">
									<c:if test="${gameAcquired.gameId eq game.id}">
										<c:set var="acquired" value="true" />
									</c:if>
								</c:forEach>
								<c:choose>
									<c:when test="${loggedUser.hasGames }">
										<c:choose>
											<c:when test="${!acquired}">
												<form:form servletRelativeAction="/shopping/cart/addGame">
													<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
													<input type="hidden" name="gameId" value="${game.id }" />
													<button id="exibe" type="submit" class="btn btn-default">Adicionar ao carrinho</button>
												</form:form>
											</c:when>
											<c:otherwise>
												<c:forEach items="${loggedUser.licenses }" var="gameAcquired2">
													<c:set var="acquired" value="false" />
													<c:if test="${gameAcquired2.gameId eq game.id }">
														<button class="btn btn-default disabled">Jogo já adquirido</button>
													</c:if>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${!contains}">
												<form:form servletRelativeAction="/shopping/cart/addGame">
													<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
													<input type="hidden" name="gameId" value="${game.id }" />
													<button id="exibe" type="submit" class="btn btn-default">Adicionar ao carrinho</button>
												</form:form>
											</c:when>
											<c:otherwise>
												<c:forEach items="${shoppingCart.items }" var="item">
													<c:set var="contains" value="false" />
													<c:if test="${item.game.id eq game.id}">
														<button class="btn btn-default disabled">Jogo já adicionado</button>
													</c:if>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${loggedUser.logged and loggedUser.admin}">
								<c:url var="editGame_url" value="/games/edit/${game.id}" />
								<form:form action="${editGame_url }" modelAttribute="game" method="get">
									<input type="hidden" name="title" value="${game.title }" />
									<input type="hidden" name="price" value="${game.price }" />
									<input type="hidden" name="description" value="${game.description }" />
									<button type="submit" class="btn btn-default" style="position: absolute; top: 0px; right: 110%;">Editar jogo</button>
								</form:form>
							</c:if>
							<c:if test="${!loggedUser.logged}">
								<a href="<c:url value="/login"/>" class="btn btn-default">Faça login</a>
								<a href="<c:url value= "/users/selectCountry"/>" class="btn btn-default" style="position: absolute; top: 0px; right: 110%;">Cadastre-se</a>
							</c:if>
						</div>
						<div class="textGame"></div>
					</div>
				</section>
			</c:forEach>
			<section class="sectionListGames">
				<div class="listGames">
					<div class="divThumbs">
						<c:forEach items="${games}" var="game" varStatus="g">
							<c:set var="gameIdThumb" value="id${game.id }" />
							<div class="thumbnailGame" id=${gameIdThumb }>
								<img src="<c:url value="/games/${game.id}/image"/>" width="100" height="100" style="height: 100%; width: 100%;" />
							</div>
						</c:forEach>
						<div class="clearfix"></div>
					</div>
				</div>
				<c:if test="${loggedUser.admin}">
					<hr>
					<p style="text-align: center;">
						<a href="<c:url value="/games/new"/>" class="btn btn-default" type="submit"><fmt:message key="btn.newGame" /></a>
					</p>
				</c:if>
			</section>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
