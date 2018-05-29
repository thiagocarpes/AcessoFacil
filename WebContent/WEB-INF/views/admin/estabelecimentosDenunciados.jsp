
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/headerAdmin.jsp" />

<div class="container">
	<div class="tReinvidicados">
		<b><span class="glyphicon glyphicon-alert"></span>ESTABELECIMENTOS DENUNCIADOS</b><br>
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
				<c:if test="${not empty estabelecimentos}">
					<c:forEach var="estabelecimentos" items="${estabelecimentos}">
						<tr>
							<td>${estabelecimentos.id }</td>
							<td>${estabelecimentos.nomeFantasia }</td>
							<td>${estabelecimentos.email }</td>
							<td>${estabelecimentos.telefone }</td>
							<td>${estabelecimentos.dtDenuncia }</td>
							<td><b><a href="estabelecimentoDenunciado?id=${estabelecimentos.id }">VER DENUNCIA</a></b></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>

<c:import url="../padrao/rodape.jsp" />