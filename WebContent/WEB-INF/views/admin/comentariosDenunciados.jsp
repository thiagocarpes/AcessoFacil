
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="tReinvidicados">
		<b><span class="glyphicon glyphicon-alert"></span>COMENTÁRIOS DENUNCIADOS</b><br>
	</div>
	<div class="row">
		<table class="table table-striped" cellspacing="0" cellpadding="0">
			<thead>
				<th>ID</th>
				<th>Nome</th>
				<th>E-Mail</th>
				<th>Telefone</th>
				<th>Data da Denuncia</th>
				<th>Ações</th>
			</thead>
			<tbody>
				<c:if test="${not empty comentarios}">
					<c:forEach var="comentarios" items="${comentarios}">
						<tr>
							<td>${comentarios.id }</td>
							<td>${comentarios.nomeFantasia }</td>
							<td>${comentarios.email }</td>
							<td>${comentarios.telefone }</td>
							<td>${comentarios.dtReportado }</td>
							<td><b><a href="comentarioDenunciado?id=${comentarios.id }">VER SOLICITAÇÃO</a></b></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>

<c:import url="../padrao/rodape.jsp" />