<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/header.jsp" />
<div class="col-md-3">
	<c:import url="../padrao/menuHome.jsp" />
</div>
<div class="col-md-9 userColumLeft">
	<div class="col-md-12 userColumRight">


		<!-- EDITAR SOMENTE ESSE BLOCO -->
		<div class="col-md-12" class="form-control" id="buscar">
		
			<h3>Estabelecimento</h3> ${estabelecimento.page }
		
			<c:if test="${not empty busca}">
				<table class="table table-striped" cellspacing="0" cellpadding="0">
					<thead>
						<th>ID</th>
						<th>Nome</th>
						<th>E-Mail</th>
						<th>Ações</th>
					</thead>
					<tbody>

						<c:forEach var="estabelecimentos" items="${busca}">
							<tr>
								<td>${estabelecimentos.id }</td>
								<td>${estabelecimentos.nomeFantasia }</td>
								<td>${estabelecimentos.email }</td>
								<td><b><a
										href="estabelecimento?id=${estabelecimentos.id }">VER
											ESTABELECIMENTO</a></b></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:if>
			<c:if test="${empty busca}">
				<div class="buscaVazia">Nenhum dado encontrado para a busca
					realizada</div>
			</c:if>
			<c:if test="${paginas > 1 }">
			
				<c:forEach var="i" begin="1" end="${paginas }">
					<ul class="pagination">
						<li><a href="buscaSimples?page=${i }&texto=${texto}">${i }</a></li>
					</ul>
				</c:forEach>
			</c:if>
		</div>
	</div>

	<!-- FIM DO BLOCO -->


</div>
</div>
<c:import url="../padrao/rodape.jsp" />