
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="homeAdminConteudo">
		<div class="tReinvidicados">
			<b>COMENTÁRIOS</b><br>
		</div>
		<div class="buscaAdmin">
			<div class="row">
				<form action="buscaComentarios" method="post">
					<div class="col-md-3">
						Status: <select name="status">
							<option  value="">Qualquer</option>
							<option  value="publico">Publico</option>
							<option  value="reivindicado">Reivindicado</option>
							<option  value="denunciado">Denunciado</option>
							<option  value="inativo">Inativo</option>
						</select>
					</div>
					<div class="col-md-3">
						Buscar por: <select name="tipo">
							<option  value="nomeFantasia">Estabelecimento</option>
							<option  value="nome">Usuario</option>
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
				<c:if test="${not empty comentario}">
					<table class="table table-striped" cellspacing="0" cellpadding="0">
						<thead>
							<th>ID</th>
							<th>Estabelecimento</th>
							<th>Usuario</th>
							<th>Comentário</th>
							<th>Data do Comentário</th>
							<th>Ações</th>
						</thead>
						<tbody>

							<c:forEach var="comentario" items="${comentario}">
								<tr>
									<td>${comentario.id }</td>
									<td>${comentario.nomeFantasia }</td>
									<td>${comentario.usuario }</td>
									<td>${comentario.comentario }</td>
									<td>${comentario.dtReportado }</td>
									<td><b><a
											href="comentarioDenunciado?id=${comentario.id }">VER
												COMENTÁRIO</a></b></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</c:if>
				<c:if test="${empty comentario}">
					<div class="buscaVazia">Nenhum dado encontrado para a busca
						realizada</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

<c:import url="../padrao/rodape.jsp" />