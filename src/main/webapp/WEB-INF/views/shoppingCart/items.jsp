<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="container">
		<section class="cartView">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<h2>Itens no carrinho</h2>
				</div>
				<c:choose>
					<c:when test="${empty cart.items }">
						<h3 style="text-align: center;">Carrinho vazio</h3>
					</c:when>
					<c:otherwise>
						<c:forEach items="${cart.items}" var="item" varStatus="s">
							<div class="gameInCart">
								<h4 id="cartTitle">${item.game.title }</h4>
								<h4 id="cartPrice">
									<fmt:formatNumber type="currency" value="${item.game.price }" />
								</h4>
								<form id="removeCart" action="<c:url value="/cart/${s.index}"/>"
									method="post">
									<button class="link btn btn-default btnRemoveCart"
										name="_method" value="delete">Remover</button>
								</form>
							</div>
						</c:forEach>
						<div class="totalCart">
							Total:
							<fmt:formatNumber type="currency" value="${cart.total }" />
						</div>
					</c:otherwise>
				</c:choose>
				<div class="btnCart">
					<form action="<c:url value="/games/list"/>" method="get">
						<button class="link btn btn-default">Voltar</button>
					</form>
					<form action="<c:url value="/shopping/checkout"/>" method="post">
						<c:set var="currency" value="BRL" />
						<input type="hidden" name="currency" value="BRL" />
						<button class="link btn btn-default">Gerar pagamento
							teste</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<form action="<c:url value="/confirmPayment"/>" method="post">
						<button class="link btn btn-default">Comprovar pagamento
							teste</button>
					</form>
				</div>
				<div class="btnCart"></div>
			</div>
		</section>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>

