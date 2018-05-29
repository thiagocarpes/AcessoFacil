
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="tReinvidicados">
		<b><span class="glyphicon glyphicon-ok"></span>SOLICITAÇÕES DE
			AUTENTICAÇÃO DE ESTABELECIMENTO</b><br>
	</div>
	<div class="row">
		<table class="table table-striped" cellspacing="0" cellpadding="0">
			<thead>
				<th>ID</th>
				<th>Nome</th>
				<th>E-Mail</th>
				<th>Telefone</th>
				<th>Data da Solicitação</th>
				<th>Ações</th>
			</thead>
			<tbody>
				<c:if test="${not empty reinvidicados}">
					<c:forEach var="reinvidicados" items="${reinvidicados}">
						<tr>
							<td>${reinvidicados.id }</td>
							<td>${reinvidicados.nomeFantasia }</td>
							<td>${reinvidicados.email }</td>
							<td>${reinvidicados.telefone }</td>
							<td>${reinvidicados.dtReinvidicacao }</td>
							<td><b><a href="solicitadoAutenticacao?id=${reinvidicados.id }">VER SOLICITAÇÃO</a></b></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>

<c:import url="../padrao/rodape.jsp" />