<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.tipousuario !=3}">
<div class="row userNav">
	<form class="navbar-form" action="buscaSimples" method="get">
		<div class="input-group">
			<input type="hidden" name="page" value="1">
			<input type="text" class="form-control" placeholder="Busca Rápida"
				name="texto" value="${texto }">
			<div class="input-group-btn">
				<button class="btn btn-default" type="submit">
					<i class="glyphicon glyphicon-search"></i>
				</button>

			</div>
		</div>
	</form>
</div>
<hr>
</c:if>
<div class="row userNav">
	
	
	<c:if test="${usuarioLogado != null }">
		<c:if test="${sessionScope.tipousuario != 2 && sessionScope.tipousuario!=3}"> 
			<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="cadastroEstabelecimento">+ NOVO ESTABELECIMENTO</a></li>
				<li><a href="#">Minhas Avalições</a></li>
				<li><a href="meusDados?id=${id }">Meus Dados</a></li>
				<li><a href="logout">Sair</a></li>
			</ul>
		</c:if>
		<c:if test="${sessionScope.tipousuario == 3 }">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="dadosEstabelecimento?id=${id }">Meus Dados</a></li>
				
			</ul>
		</c:if>
	</c:if>

</div>
