<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="menu">
	<nav class="navbar navbar-inverse navbar-fixed-top" <c:if test="${sessionScope.tipousuario == 2}"> style="display:none;border:0px;" </c:if>>
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${usuarioLogado == null }">
						<li><a href="login"><span
								class="glyphicon glyphicon-user"></span>ENTRAR</a></li>
					</c:if>
					<c:if test="${usuarioLogado != null }">
						<c:if test="${sessionScope.tipousuario != 2 && sessionScope.tipousuario != 3}"> 
							<li><a href="home">MINHA CONTA</a></li> 
							<li><a href="logout"><span	class="glyphicon glyphicon-remove-sign"></span>SAIR</a></li>
						</c:if>
						<c:if test="${sessionScope.tipousuario == 3 }">
							<li><a href="minhaConta">MINHA CONTA</a></li> 
							<li><a href="logout"><span	class="glyphicon glyphicon-remove-sign"></span>SAIR</a></li>
						</c:if>
					</c:if>
					<c:if test="${usuarioLogado == null }">
					<li><a href="cadastro"><span
							class="glyphicon glyphicon-plus"></span>CADASTRE-SE</a></li>
					</c:if>
					<c:if test="${usuarioLogado == null }">
					<li><a href="loginEstabelecimento"><span
							class="glyphicon glyphicon-home"></span>AREA DO ESTABELECIMENTO</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
</div>
