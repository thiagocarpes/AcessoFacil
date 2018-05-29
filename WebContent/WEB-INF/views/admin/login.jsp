<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../padrao/headerAdmin.jsp" />
<div class="limit">
	<div class="l">
		<h2>
			<span class="glyphicon glyphicon-user"></span>Login Retaguarda
		</h2>
		<form action="fazerLoginAdmin" method="post">
			<div class="form-group">
				<label for="usuario">Usuario:</label> <input type="text"
					class="form-control" id="usuario" name="usuario">
			</div>
			<div class="form-group">
				<label for="senha">Senha:</label> <input type="password"
					class="form-control" id="senha" name="senha">
			</div>
			<button type="submit" class="btn btn-primary">ENTRAR</button>
		</form>
	</div>
</div>

<c:import url="../padrao/rodape.jsp" />

