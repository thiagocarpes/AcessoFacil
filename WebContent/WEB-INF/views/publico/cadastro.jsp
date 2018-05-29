<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../padrao/header.jsp" />

<div class="limit">
	<div class="l">
		<h2>
			<spam class="glyphicon glyphicon-plus"></spam>
			Cadastro de Usuário
		</h2>
		<form id="novoUsuario" action="novoUsuario" method="post">
			<div class="form-group">
				<label for="nome">Nome</label> 
				<input type="nome" class="form-control" id="nome" name="nome" validate=1>
			</div>
			<div class="form-group">
				<label for="usuario">Usuario:</label> 
				<input type="usuario" class="form-control" id="usuario" name="usuario" validate=1>
			</div>
			<div class="form-group">
				<label for="email">E-mail:</label> 
				<input type="email"	class="form-control" id="email" name="email" validate=1>
			</div>
			<div class="form-group">
				<label for="senha">Senha</label> 
				<input type="password"	class="form-control" id="senha" name="senha" validate=1>
			</div>
			<div class="form-group">
				<label for="csenha">Confirma a Senha:</label> 
				<input type="password" class="form-control" id="cosenha" name="cosenha" validate=1>
			</div>
			<button type="submit" class="btn btn-primary" onclick="fm('#novoUsuario'); return false;" name="acao" value="Salvar"> CADASTRAR </button>
		</form>
	</div>
</div>
<c:import url="../padrao/rodape.jsp" />

</body>
</html>