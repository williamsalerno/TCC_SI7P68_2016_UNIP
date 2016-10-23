<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div id="container">
	<div class="content">
		<section class="cartView">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<h2>
						<fmt:message key="h2.myGames" />
					</h2>
				</div>
				<c:forEach items="${games}" var="gamesList">
					<div class="myGames">
						<img src="<c:url value="/img/uploaded/${gamesList.id}_min.png"/>" width="100" height="100" />
						<h4>${gamesList.title }</h4>
						<a class="link btn btn-default" href="${gamesList.downloadLink }">Download </a>
					</div>
				</c:forEach>
				<div class="btnCart">
					<form action="<c:url value="/user/myProfile"/>" method="get">
						<button class="link btn btn-default">
							<fmt:message key="btn.back" />
						</button>
					</form>
				</div>
			</div>
		</section>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/end.jspf"%>
