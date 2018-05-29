<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/header.jsp" />
<div class="col-md-3">
	<c:import url="../padrao/menuHome.jsp" />
</div>
<div class="col-md-9 userColumLeft">
	<div class="col-md-12 userColumRight">
		<!-- EDITAR SOMENTE ESSE BLOCO -->
		<div class="row">
			<div class="col-md-12">
				<h3>Meus Dados</h3>
			</div>
			<form action="alteraUsuario?id=${id }" method="post">

				<div class="col-md-12">
					<label for="usuario">Usuario:</label> ${usuario.usuario }
				</div>
				<div class="col-md-6">
					<label for="nome">Nome:</label> <input type="text"
						class="form-control" id="nome" name="nome"
						value="${usuario.nome }">
				</div>
				<div class="col-md-6">
					<label for="email">E-mail:</label> <input type="text"
						class="form-control" id="email" name="email"
						value="${usuario.email }">
				</div>
				<div class="col-md-6">
					<label for="senha">Senha:</label> <input type="password"
						class="form-control" id="senha" name="senha">
				</div>
				<div class="col-md-6">
					<label for="cpf">CPF:</label> <input type="text"
						class="form-control" id="cpf" name="cpf" value="${usuario.cpf }">
				</div>
				<div class="col-md-12" class="form-control" id="buscar">
					<button type="submit" class="btn btn-warning">ALTERAR</button>
					<br><br><div class="alerta"><b>${mensagem }</b></div>
				</div>
			</form>
		</div>
		<!-- FIM DO BLOCO -->
	</div>
</div>
<c:import url="../padrao/rodape.jsp" />