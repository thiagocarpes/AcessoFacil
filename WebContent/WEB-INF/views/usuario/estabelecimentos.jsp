<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/header.jsp" />
<div class="col-md-3">
	<c:import url="../padrao/menuHome.jsp" />
</div>
<div class="col-md-9 userColumLeft">
	<div class="col-md-12 userColumRight">


		<!-- EDITAR SOMENTE ESSE BLOCO -->

		<form action="buscaEstabelecimentos" method="post">
			<div class="row">
				<div class=col-md-12>
					<h3>Estabelecimentos</h3>
				</div>
				<div class="col-md-6">

					<div class="form-group">
						<label for="status">Status</label> <select name="status"
							class="form-control" id="status">
							<option <c:if test="${selected == '' }">SELECTED</c:if> value="">Qualquer</option>
							<option <c:if test="${selected == 'publico' }">SELECTED</c:if>
								value="publico">Publico</option>
							<option
								<c:if test="${selected == 'reivindicado' }">SELECTED</c:if>
								value="reivindicado">Reivindicado</option>
							<option <c:if test="${selected == 'denunciado' }">SELECTED</c:if>
								value="denunciado">Denunciado</option>
							<option <c:if test="${selected == 'inativo' }">SELECTED</c:if>
								value="inativo">Inativo</option>
						</select>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="categoria">Categoria:</label> <select
							class="form-control" name="categoria">
							<option value="#">Selecione a Categoria</option>
							<c:forEach var="categoria" items="${lista}">
								<option value="${categoria.id}">${categoria.nome}</option>
							</c:forEach>
						</select>
					</div>

				</div>


				<div class="col-md-6">
					<label for="buscarPor">Buscar por:</label> <select name="tipo"
						class="form-control" id="buscarPor">
						<option
							<c:if test="${selected2 == 'nomeFantasia' }">SELECTED</c:if>
							value="nomeFantasia">Nome Fantasia</option>
						<option <c:if test="${selected2 == 'email' }">SELECTED</c:if>
							value="email">E-mail</option>
						<option <c:if test="${selected2 == 'telefone' }">SELECTED</c:if>
							value="telefone">Telefone</option>
					</select>
				</div>
				<div class="col-md-6" class="form-control" id="texto">
					<label for="textp">Texto</label> <input type="text"
							class="form-control" id="texto" name="texto">
					
					
				</div>
				<div class="col-md-12" class="form-control" id="buscar">
					<button type="submit" class="btn btn-primary">BUSCAR</button>
				</div>
				<div class="col-md-12" class="form-control" id="buscar">
					<c:if test="${not empty estabelecimentos}">
							<table class="table table-striped" cellspacing="0"
								cellpadding="0">
								<thead>
									<th>ID</th>
									<th>Nome</th>
									<th>E-Mail</th>
									<th>Ações</th>
								</thead>
								<tbody>

									<c:forEach var="estabelecimentos" items="${estabelecimentos}">
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
						<c:if test="${empty estabelecimentos}">
							<div class="buscaVazia">Nenhum dado encontrado para a busca
								realizada</div>
						</c:if>
				</div>
			</div>
		</form>

		<!-- FIM DO BLOCO -->


	</div>
</div>
<c:import url="../padrao/rodape.jsp" />