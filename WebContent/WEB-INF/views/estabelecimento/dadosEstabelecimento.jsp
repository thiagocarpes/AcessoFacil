<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="../padrao/header.jsp" />

<c:if test="${not empty estabelecimento}">
<div class="col-md-9 userColumLeft">
	<div class="col-md-12 userColumRight">
		<!-- EDITAR SOMENTE ESSE BLOCO -->
		<div class="row">
			<div class="col-md-12">
				<h3>Meus Dados</h3>
			</div>
			
			<form action="alterarDados?id=${estabelecimento.id }" method="post">

				<div class="col-md-6">
					<label for="nomeFantasia">Nome Fantasia:</label> <input type="text"
						class="form-control" id="nomeFantasia" 
						value="${estabelecimento.nomeFantasia }" name="nomeFantasia">
				</div>
				<div class="col-md-6">
					<label for="email">E-mail:</label> <input type="text"
						class="form-control" id="email" 
						value="${estabelecimento.email }" name="email">
				</div>
				<div class="col-md-6">
					<label for="senha">Senha:</label> <input type="password"
						class="form-control" id="senha"  
						value="${estabelecimento.senha}" name="senha">
				</div>

				<div class="col-md-6">
					<label for="telefone">Telefone:</label> <input type="text"
						class="form-control" id="telefone"  
						value="${estabelecimento.telefone}" name="telefone">
				</div>
				<div class="col-md-6">
					<label for="cidade">Cidade:</label> <input type="text"
						class="form-control" id="cidade"  
						value="${estabelecimento.cidade}" name="cidade">
				</div>
				<div class="col-md-6">
					<label for="estado">Estado:</label> <input type="text"
						class="form-control" id="estado"  
						value="${estabelecimento.estado}" name="estado">
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
</c:if>
<c:import url="../padrao/rodape.jsp" />