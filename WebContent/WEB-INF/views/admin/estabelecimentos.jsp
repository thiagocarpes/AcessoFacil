
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="homeAdminConteudo">
		<div class="tReinvidicados">
			<b>ESTABELECIMENTOS</b><br>
		</div>
		<div class="buscaAdmin">
			<div class="row">
				<form action="buscaEstabelecimentos" method="post">
					<div class="col-md-3">
						Status: <select name="status">
							<option <c:if test="${selected == '' }">SELECTED</c:if> value="">Qualquer</option>
							<option <c:if test="${selected == 'publico' }">SELECTED</c:if> value="publico">Publico</option>
							<option <c:if test="${selected == 'reivindicado' }">SELECTED</c:if> value="reivindicado">Reivindicado</option>
							<option <c:if test="${selected == 'denunciado' }">SELECTED</c:if> value="denunciado">Denunciado</option>
							<option <c:if test="${selected == 'inativo' }">SELECTED</c:if> value="inativo">Inativo</option>
						</select>
					</div>
					<div class="col-md-3">
						Buscar por: <select name="tipo">
							<option <c:if test="${selected2 == 'nomeFantasia' }">SELECTED</c:if> value="nomeFantasia">Nome Fantasia</option>
							<option <c:if test="${selected2 == 'email' }">SELECTED</c:if> value="email">E-mail</option>
							<option <c:if test="${selected2 == 'telefone' }">SELECTED</c:if> value="telefone">Telefone</option>
						</select>
					</div>
					<div class="col-md-3">
						<input type="text" name="texto">
					</div>
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary">Buscar</button>
					</div>
				</form>
			</div>
			<div class="row">
				<c:if test="${not empty estabelecimentos}">
					<table class="table table-striped" cellspacing="0" cellpadding="0">
						<thead>
							<th>ID</th>
							<th>Nome</th>
							<th>E-Mail</th>
							<th>Telefone</th>
							<th>Data da Cadastro</th>
							<th>Status</th>
							<th>Ações</th>
						</thead>
						<tbody>

							<c:forEach var="estabelecimentos" items="${estabelecimentos}">
								<tr>
									<td>${estabelecimentos.id }</td>
									<td>${estabelecimentos.nomeFantasia }</td>
									<td>${estabelecimentos.email }</td>
									<td>${estabelecimentos.telefone }</td>
									<td>${estabelecimentos.dtCadastro }</td>
									<td>${estabelecimentos.status }</td>
									<td><b><a
											href="estabelecimentoDenunciado?id=${estabelecimentos.id }">VER
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
	</div>
</div>

<c:import url="../padrao/rodape.jsp" />