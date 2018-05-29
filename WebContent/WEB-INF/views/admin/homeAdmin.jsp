
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="row">
		<div class="homeAdminConteudo">
			<div class="col-sm-4 boxList">
				<div class="reinvidicados">
					<div class="tReinvidicados">
						<b><span class="glyphicon glyphicon-ok"></span>REIVINDICAÇÃO
							DE AUTENTICAÇÃO</b>
					</div>
					<c:if test="${not empty reivindicados}">
						<c:forEach var="estabelecimento" items="${reivindicados}">
							<div class="reinvidicado">
								<b> ${estabelecimento.nomeFantasia } -
									${estabelecimento.cidade } ${estabelecimento.estado }</b><BR>
								${estabelecimento.telefone } - ${estabelecimento.email } <br>
								Data da Solicitação: ${estabelecimento.dtReinvidicacao } -<b>
									<a href="solicitadoAutenticacao?id=${estabelecimento.id }">Ver
										Solicitação</a>
								</b> <br>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${empty reivindicados }">
						<div style="font-size: 17px">Não existem estabelecimentos
							reivindicados no momento.</div>
					</c:if>
					<a href="reivindicacoesSolicitadas"><button
							class="btn btn-primary">REIVINDICAÇÕES</button></a>
				</div>
			</div>
			
			<div class="col-sm-4 boxList">
				<div class="reinvidicados">
					<div class="tReinvidicados">
						<b><span class="glyphicon glyphicon-alert"></span>ESTABELECIMENTOS DENUNCIADOS</b>
					</div>
					<c:if test="${not empty denunciados}">
						<c:forEach var="estabelecimento" items="${denunciados}">
							<div class="reinvidicado">
								<b> ${estabelecimento.nomeFantasia } -
									${estabelecimento.cidade } ${estabelecimento.estado }</b><BR>
								${estabelecimento.telefone } - ${estabelecimento.email } <br>
								Data da Solicitação: ${estabelecimento.dtReinvidicacao } -<b>
									<a href="estabelecimentoDenunciado?id=${estabelecimento.id }">Ver
										Denuncia</a>
								</b> <br>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${empty denunciados }">
						<div style="font-size: 17px">Não existem estabelecimentos
							denunciados no momento.</div>
					</c:if>
					<a href="reivindicacoesSolicitadas"><button
							class="btn btn-danger">DENUNCIADOS</button></a>
				</div>
			</div>
			
			
			

		</div>
	</div>
</div>
</div>
<c:import url="../padrao/rodape.jsp" />