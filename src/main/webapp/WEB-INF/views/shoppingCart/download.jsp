<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="container">
		<section class="cartView">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<h2>Iniciar download</h2>

					<p>Clique no bot�o abaixo para inicar o download do jogo.
						Quando ele terminar, siga as instru��es para validar a chave de
						ativa��o.
				</div>
				<div class="btnCart">
					<a class="link btn btn-default"
						href=https://s3.amazonaws.com/timetrialgames/Disruption/Disruption.rar>Download
					</a>
				</div>
			</div>
		</section>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</div>