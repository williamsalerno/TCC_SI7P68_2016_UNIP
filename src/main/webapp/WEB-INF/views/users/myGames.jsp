<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<section class="cartView">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<h2>Meus jogos</h2>
				</div>
				<c:forEach items="${games}" var="gameList" varStatus="g">
					<div class="gameInCart">
						<h4 id="cartTitle">${g.gameId }</h4>
						<h4 id="cartPrice">
							<fmt:formatNumber type="currency" value="${item.game.price }" />
						</h4>
						<form id="removeCart" action="<c:url value="/shopping/cart"/>" method="post">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" /> <input type="hidden" name="gameId" value="${s.index}" />
							<button class="link btn btn-default btnRemoveCart">Remover</button>
						</form>
					</div>
				</c:forEach>
				<div class="totalCart">
					Total:
					<fmt:formatNumber type="currency" value="${shoppingCart.total }" />
				</div>
				<div class="btnCart">
					<form action="<c:url value="/games/list"/>" method="get">
						<button class="link btn btn-default">Voltar</button>
					</form>
					<c:if test="${!empty shoppingCart.items }">
						<c:url var="checkout_url" value="/shopping/cart/checkout" />
						<form:form action="${checkout_url }" modelAttribute="shoppingCart" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" name="gamesInCart" value="${shoppingCart}" />
							<button class="link btn btn-default">Gerar pagamento teste</button>
						</form:form>
						<form action="<c:url value="/confirmPayment"/>" method="post">
							<button class="link btn btn-default">Comprovar pagamento teste</button>
						</form>
					</c:if>
				</div>
			</div>
		</section>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
