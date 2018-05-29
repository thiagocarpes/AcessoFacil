<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../padrao/header.jsp" />
<div class="limit">
	<div class="l">
		<h2>
			<span class="glyphicon glyphicon-user"></span>Login
		</h2>
		<form id="fazerLogin" action="fazerLogin" method="post">
			<div class="form-group">
				<label for="usuario">Usuario:</label> <input type="text"
					class="form-control" id="usuario" name="usuario" validate=1>
			</div>
			<div class="form-group">
				<label for="senha">Senha:</label> <input type="password"
					class="form-control" id="senha" name="senha" validate=1>
			</div>
			<br><br><div class="alerta"><b>${mensagem }</b></div>
			<button onclick="fm('#fazerLogin'); return false;" type="submit" class="btn btn-primary">ENTRAR</button>
			
		</form>
	</div>
</div>

<c:import url="../padrao/rodape.jsp" />

