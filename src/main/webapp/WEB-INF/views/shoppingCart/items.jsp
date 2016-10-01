<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<section class="cartView">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<h2>
						<fmt:message key="h2.itemsCart" />
					</h2>
				</div>
				<c:choose>
					<c:when test="${empty shoppingCart.items }">
						<h3 style="text-align: center;">
							<fmt:message key="h3.emptyCart" />
						</h3>
					</c:when>
					<c:otherwise>
						<c:forEach items="${shoppingCart.items}" var="item" varStatus="s">
							<div class="gameInCart">
								<h4 id="cartTitle">${item.game.title }</h4>
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
					</c:otherwise>
				</c:choose>
				<div class="btnCart">
					<form class="btnsRow" action="<c:url value="/games/list"/>" method="get">
						<button class="link btn btn-default">
							<fmt:message key="btn.back" />
						</button>
					</form>
					<c:if test="${!empty shoppingCart.items }">
						<c:url var="checkout_url" value="/shopping/cart/checkout" />
						<form:form class="btnsRow" action="${checkout_url }" modelAttribute="shoppingCart" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" name="gamesInCart" value="${shoppingCart}" />
							<button class="link btn btn-default">
								<fmt:message key="btn.buy" />
							</button>
						</form:form>
					</c:if>
				</div>
			</div>
		</section>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
